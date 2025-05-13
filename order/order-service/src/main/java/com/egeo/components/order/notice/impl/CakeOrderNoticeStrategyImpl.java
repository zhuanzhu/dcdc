package com.egeo.components.order.notice.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.egeo.components.order.bean.NoticeReqHelpBean;
import com.egeo.components.order.business.SoManage;
import com.egeo.components.order.common.PhoneCodeValidate;
import com.egeo.components.order.common.ProductChannelCodeEnum;
import com.egeo.components.order.dto.*;
import com.egeo.components.order.enums.ThirdConst;
import com.egeo.components.order.facade.SoFacade;
import com.egeo.components.order.notice.OrderNoticeStrategy;
import com.egeo.components.order.service.read.*;
import com.egeo.components.order.service.write.*;
import com.egeo.components.order.vo.cake.CakeOrderStatusNoticeExpressVO;
import com.egeo.components.order.vo.cake.CakeOrderStatusNoticeVO;
import com.egeo.components.pay.enums.OrderConstant;
import com.egeo.components.utils.CakeUtil;
import com.egeo.components.utils.FHCollectionUtils;
import com.egeo.components.utils.JsonUtils;
import com.egeo.components.utils.OrderNoticeHelper;
import com.egeo.exception.BusinessException;
import com.egeo.utils.DateUtils;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.StringUtils;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.web.JsonResult;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;


/**
 * @Description todo
 * @Author lsl
 * @Date 2024/5/9 17:38
 * @Version V1.0
 **/
@Service("cakeOrderNoticeStrategyImpl")
public class CakeOrderNoticeStrategyImpl implements OrderNoticeStrategy {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private SoChildReadService soChildReadService;
    @Resource
    private SoChildWriteService soChildWriteService;
    @Resource
    private SoWriteService soWriteService;
    @Resource
    private SoReadService soReadService;

    @Resource
    private SoItemReadService soItemReadService;
    @Resource
    private SoItemWriteService soItemWriteService;
    @Resource
    private SoRefundReadService soRefundReadService;

    @Resource
    private SoPackageWriteService soPackageWriteService;

    @Resource
    private SoPackageReadService soPackageReadService;

    @Resource
    public DeliveryCompanyReadService deliveryCompanyReadService;

    @Resource
    private CakeUtil cakeUtil;
    @Resource
    private ReceiverAddressReadService receiverAddressReadService;
    @Resource
    private SoPackageItemReadService soPackageItemReadService;
    @Resource
    private SoPackageItemWriteService soPackageItemWriteService;

    @Autowired
    private JedisUtil jedisUtil;

    @Resource(name = "so")
    private SoManage soManage;

    private String CANCEL_LOCK_VALUE = "order_cancel";

    @Resource
    private SoFacade soFacade;

    @Override
    public String getChannelCode() {
        return ProductChannelCodeEnum.CAKE.getCode();
    }

    @Override
    public Object orderStatusNotice(NoticeReqHelpBean reqHelpBean, HttpServletRequest req) {
        if(null == reqHelpBean.getParamObject()){
            return returnFail(500,"推送消息为空");
        }
        CakeOrderStatusNoticeVO cakeOrderStatusNoticeVO =JSONObject.parseObject(JSON.toJSONString(reqHelpBean.getParamObject()), CakeOrderStatusNoticeVO.class);
        String checkRT = checkOrderResult(cakeOrderStatusNoticeVO);
        if(StringUtils.isNotEmpty(checkRT)){
            return returnFail(500,checkRT);
        }
        String outOrderNo = cakeOrderStatusNoticeVO.getOut_order_no();
        //订单状态 1-已分配到商家 2-已配送完成 4-订单退款且已取消(对应订单列表和详情中的状态 3)
        SoChildDTO soChildDTO = soChildReadService.querySoChildByChildCode(outOrderNo);
        if(soChildDTO == null){
            return returnFail(500,"订单未找到");
        }
        if(soChildDTO.getCancelStatus() !=null && soChildDTO.getCancelStatus() ==1){
            return returnFail(500,"该订单已取消");
        }
        SoDTO soDTO=soReadService.querySoById(soChildDTO.getSoId());
        if(soDTO == null){
            return returnFail(500,"母订单不存在");
        }
        if(soDTO.getOrderStatus().equals(OrderConstant.ORDER_STATUS_CANCELED.getStatus()) || soDTO.getOrderStatus().equals(OrderConstant.ORDER_STATUS_RETURN_CASH_FINISHED.getStatus())){
            return returnFail(500,"母订单已取消或已退款");
        }
        //更新物流状态和物流信息
        if(cakeOrderStatusNoticeVO.getStatus().equals("1") || cakeOrderStatusNoticeVO.getStatus().equals("2")){
            return changeDeliveryStatus(cakeOrderStatusNoticeVO,soChildDTO,soDTO);
        }
        //退款或取消
        if(cakeOrderStatusNoticeVO.getStatus().equals("4") && EmptyUtil.isNotEmpty(cakeUtil.getHasChannelRefund()) && Objects.equals(cakeUtil.getHasChannelRefund(),"1")){
            return cancelOrRefundChildOrder(cakeOrderStatusNoticeVO,soChildDTO,soDTO,reqHelpBean,req);
        }

       //TODO 处理通知
        return returnSuccess();
    }

    private Object cancelOrRefundChildOrder(CakeOrderStatusNoticeVO cakeOrderStatusNoticeVO,SoChildDTO soChildDTO,SoDTO soDTO,NoticeReqHelpBean reqHelpBean,HttpServletRequest req){
        if(soDTO.getOrderPayStatus() ==2){
            return returnFail(500,"已退款无需重复处理");
        }
        String orderCode =soChildDTO.getChildCode();
        boolean lock = true;
        try {
            lock=jedisUtil.lockWithParam(JedisUtil.ORDER_CANCEL_LOCK_KEY_PRE+orderCode,CANCEL_LOCK_VALUE,JedisUtil.ORDER_CANCEL_LOCK_EXPIRE_TIME);
        } catch (InterruptedException e) {
            logger.info("获取子订单取消锁异常,orderCode="+orderCode);
            jedisUtil.delLock(JedisUtil.ORDER_CANCEL_LOCK_KEY_PRE+orderCode);
            e.printStackTrace();
        }
        if(!lock){
            return returnFail(500,"当前子订单正在取消中,不可重复操作");
        }
        JsonResult<Map<String, Object>> result=null;
        try {
            if(soDTO.getOrderPayStatus() ==1){
                //已付款
                result = soManage.cancelChildOrder(orderCode, soDTO.getUserId(), soDTO.getPlatformId(), null, null, null, req);
            }else if(soDTO.getOrderPayStatus() ==0){
                //待付款
                result =soManage.onlyCancelChildOrder(orderCode, soDTO.getUserId(), soDTO.getPlatformId(), null, null, null, req);
            }
        }catch (Exception e){
            logger.error("取消订单异常{}",e);
            return returnFail(500,"取消或退款订单异常");
        }finally {
            jedisUtil.delLock(JedisUtil.ORDER_CANCEL_LOCK_KEY_PRE+orderCode);
        }
        return returnSuccess();
    }

    private Object changeDeliveryStatus(CakeOrderStatusNoticeVO cakeOrderStatusNoticeVO,SoChildDTO soChildDTO,SoDTO soDTO){
        SoChildDTO newSoChildDTO = new SoChildDTO();
        newSoChildDTO.setId(soChildDTO.getId());
        newSoChildDTO.setDeliveryStatus(soChildDTO.getDeliveryStatus());
        //蛋糕叔叔已分配到商家，就是对应我方分拣中
        if(cakeOrderStatusNoticeVO.getStatus().equals("1")){
            newSoChildDTO.setDeliveryStatus(1);
            //若存在物流单号的就更新为已发货
            if(!CollectionUtils.isEmpty(cakeOrderStatusNoticeVO.getExpress_data())){
                List<CakeOrderStatusNoticeExpressVO> expressVOS =  cakeOrderStatusNoticeVO.getExpress_data();
                for (CakeOrderStatusNoticeExpressVO expressVO : expressVOS) {
                    //若快递单号/配送员手机号不为空，证明已发货或已配送了
                    if(EmptyUtil.isNotEmpty(expressVO.getExpress_no())){
                        newSoChildDTO.setDeliveryStatus(2);
                    }
                }
            }
        }
        //蛋糕叔叔已配送完成，就是对应我方已签收
        if(cakeOrderStatusNoticeVO.getStatus().equals("2")){
            newSoChildDTO.setDeliveryStatus(3);
        }
        //物流状态为已签收或已完成就无需变更
        if(newSoChildDTO.getDeliveryStatus() !=3 && !(soChildDTO.getDeliveryStatus() == 3 || soChildDTO.getDeliveryStatus() == 4)){
            soChildWriteService.updateSoChildWithTx(newSoChildDTO);
        }
        if(newSoChildDTO.getDeliveryStatus() ==3 && !(soChildDTO.getDeliveryStatus() == 3 || soChildDTO.getDeliveryStatus() == 4)){
            affirmOrderByChildCode(soChildDTO);
        }

        //处理物流信息
        saveOrUpdatePackageInfo(cakeOrderStatusNoticeVO,soChildDTO,soDTO,newSoChildDTO.getDeliveryStatus());
        //处理母订单的状态,不为处理中
        if(newSoChildDTO.getDeliveryStatus() !=0 && newSoChildDTO.getDeliveryStatus() !=1){
            processSoOrderDeliveryStatus(soDTO);
        }
        return returnSuccess();
    }

    private void affirmOrderByChildCode(SoChildDTO childDTO){
        try {
            soFacade.affirmOrderByChildCode(childDTO,7L);
        }catch (Exception e){
            logger.error("子订单号{}确认收货失败:{}",childDTO.getChildCode(),e);
        }

    }

    private void processSoOrderDeliveryStatus(SoDTO soDTO){
        //若订单状态为已完成，已取消，已退款，无需处理
        List<Integer> unAllowStatusList = Arrays.asList(OrderConstant.ORDER_STATUS_RECEIVED_FINISHED.getStatus(),OrderConstant.ORDER_STATUS_RETURN_CASH_FINISHED.getStatus(),OrderConstant.ORDER_STATUS_CANCELED.getStatus());
        if(unAllowStatusList.contains(soDTO.getOrderStatus())){
            return;
        }
        List<SoChildDTO> list = soChildReadService.querySoChildListBySoId(soDTO.getId());
        SoDTO newSoDTO = new SoDTO();
        newSoDTO.setId(soDTO.getId());
        newSoDTO.setOrderStatus(OrderConstant.ORDER_STATUS_DELIVERED.getStatus());
        newSoDTO.setDeliveryStatus(10);
        //是否全部都发货
        boolean isAllDeliveryStatus = isAllDeliveryStatus(list);
        //是否全部都签收
        boolean isAllSignFor =isAllSignFor(list);
        if(isAllDeliveryStatus){
            newSoDTO.setDeliveryStatus(20);
        }
        if(isAllSignFor){
            newSoDTO.setOrderStatus(OrderConstant.ORDER_STATUS_RECEIVED_GOODS.getStatus());
        }
       soWriteService.updateOrderWithTX(newSoDTO);
    }

    public boolean isAllDeliveryStatus(List<SoChildDTO> list){
        //订单物流信息 0:待发货，1:分拣中，2:已发货 3：已签收
        for (SoChildDTO soChildDTO : list) {
            if(soChildDTO.getDeliveryStatus() == 0 || soChildDTO.getDeliveryStatus()==1){
                return false;
            }
        }
        return true;
    }

    private boolean isAllSignFor(List<SoChildDTO> list){
        //订单物流信息 0:待发货，1:分拣中，2:已发货 3：已签收
        for (SoChildDTO soChildDTO : list) {
            if(!(soChildDTO.getDeliveryStatus() == 3 || soChildDTO.getDeliveryStatus() == 4)){
              return false;
            }
        }
        return true;
    }

    private void saveOrUpdatePackageInfo(CakeOrderStatusNoticeVO cakeOrderStatusNoticeVO,SoChildDTO soChildDTO,SoDTO soDTO,Integer deliveryStatus){
        List<CakeOrderStatusNoticeExpressVO> expressVOS =  cakeOrderStatusNoticeVO.getExpress_data();
        //无物流信息需要处理
        if(CollectionUtils.isEmpty(expressVOS)){
            unFillExpressFinish(cakeOrderStatusNoticeVO, soChildDTO, soDTO, deliveryStatus);
            return;
        }
        List<SoItemDTO> soItemDTOS = soItemReadService.findSoItemsSoChild(soChildDTO.getId());
        Map<Long,SoItemDTO> soItemDTOMap = FHCollectionUtils.listToMap(soItemDTOS,SoItemDTO::getPuId,e->e);
        ReceiverAddressDTO receiverAddressDTO =null;
        if(soDTO.getReceiverAddressId() !=null){
            receiverAddressDTO = receiverAddressReadService.findReceiverAddressById(soDTO.getReceiverAddressId());
        }
        Map<String,SoPackageDTO> soPackageDTOMap = listToPackageMap(soChildDTO.getId());
        Map<Long,SoPackageItemDTO> soPackageItemDTOMap = getSoPackageItemMap(soChildDTO);
        for (CakeOrderStatusNoticeExpressVO expressVO : expressVOS) {
            SoPackageDTO newsSoPackageDTO = buildSoPackageDTO(expressVO,receiverAddressDTO);
            saveOrUpdatePackageDTO(soChildDTO, soDTO, deliveryStatus, soPackageDTOMap, expressVO, newsSoPackageDTO);
            //处理明细-到商品级别
            saveOrUpdatePackageItemDTO(soChildDTO, receiverAddressDTO, soPackageItemDTOMap, expressVO, newsSoPackageDTO,soItemDTOMap);
        }
    }

    //未填写物流信息，但是又配送完成了
    private void unFillExpressFinish(CakeOrderStatusNoticeVO cakeOrderStatusNoticeVO, SoChildDTO soChildDTO, SoDTO soDTO, Integer deliveryStatus) {
        if(deliveryStatus ==3 && cakeOrderStatusNoticeVO.getStatus().equals("2")){
            Map<String,SoPackageDTO> soPackageDTOMap = listToPackageMap(soChildDTO.getId());
            if(CollectionUtils.isEmpty(soPackageDTOMap)){
                SoPackageDTO newsSoPackageDTO = new SoPackageDTO();
                newsSoPackageDTO.setMerchantId(7L);
                newsSoPackageDTO.setDeliveryMode(2);
                newsSoPackageDTO.setDeliveryDate(new Date());
                newsSoPackageDTO.setPackageType(1);
                newsSoPackageDTO.setPlatformId(7L);
                setSoPackageCommon(soChildDTO, soDTO, deliveryStatus, newsSoPackageDTO);
                Long id = soPackageWriteService.insertSoPackageWithTx(newsSoPackageDTO);
                newsSoPackageDTO.setId(id);
            }else{
                for (Map.Entry<String, SoPackageDTO> entry : soPackageDTOMap.entrySet()) {
                    String key = entry.getKey();
                    SoPackageDTO value = entry.getValue();
                   if(value.getDeliveryDate() !=null){
                       continue;
                   }
                    SoPackageDTO newsSoPackageDTO = new SoPackageDTO();
                    newsSoPackageDTO.setId(value.getId());
                    newsSoPackageDTO.setDeliveryDate(new Date());
                    soPackageWriteService.updateSoPackageWithTx(newsSoPackageDTO);
                }
            }
        }
    }

    private void saveOrUpdatePackageItemDTO(SoChildDTO soChildDTO, ReceiverAddressDTO receiverAddressDTO, Map<Long, SoPackageItemDTO> soPackageItemDTOMap, CakeOrderStatusNoticeExpressVO expressVO, SoPackageDTO newsSoPackageDTO,Map<Long,SoItemDTO> soItemDTOMap) {
        if(EmptyUtil.isNotEmpty(expressVO.getSpec_id())){
            SoPackageItemDTO soPackageItemDTO =  buildSoPackageItem(expressVO, receiverAddressDTO, newsSoPackageDTO);
            soPackageItemDTO.setOrderCode(soChildDTO.getChildCode());
            Long puId = Long.valueOf(expressVO.getSpec_id());
            SoItemDTO soItemDTO = soItemDTOMap.get(puId);
            setSoItemCommonInfo(soPackageItemDTO, soItemDTO);
            if(soPackageItemDTOMap.containsKey(puId)){
                SoPackageItemDTO oldSoPackageItemDTO =  soPackageItemDTOMap.get(puId);
                soPackageItemDTO.setId(oldSoPackageItemDTO.getId());
                soPackageItemWriteService.updateSoPackageItemWithTx(soPackageItemDTO);
            }else{
                soPackageItemWriteService.insertSoPackageItemWithTx(soPackageItemDTO);
            }
        }
    }

    private void setSoItemCommonInfo(SoPackageItemDTO soPackageItemDTO, SoItemDTO soItemDTO) {
        if(soItemDTO == null){
            return;
        }
        if(EmptyUtil.isNotEmpty(soItemDTO.getExternalProductId())){
            soPackageItemDTO.setProductId(Long.valueOf(soItemDTO.getExternalProductId()));
        }
        if(EmptyUtil.isNotEmpty(soItemDTO.getExternalSkuId())){
            soPackageItemDTO.setSkuId(Long.valueOf(soItemDTO.getExternalSkuId()));
        }
        soPackageItemDTO.setSoItemId(soItemDTO.getId());
        soPackageItemDTO.setDeliveryTime(DateUtils.getDefaultDateTime(new Date()));
        soPackageItemDTO.setProductCname(soItemDTO.getPuName());
        soPackageItemDTO.setProductPriceSale(soItemDTO.getPrice());
        soPackageItemDTO.setProductItemNum(soItemDTO.getPuCount());
        soPackageItemDTO.setProductItemOutNum(soItemDTO.getPuCount());
        soPackageItemDTO.setProductItemAmount(soItemDTO.getPrice().multiply(new BigDecimal(soItemDTO.getPuCount())).setScale(2));
        if(soItemDTO.getRefundCount() !=null && soItemDTO.getRefundCount() >0){
            soPackageItemDTO.setProductItemOutNum(soItemDTO.getPuCount()-soItemDTO.getRefundCount());
        }
    }

    private void saveOrUpdatePackageDTO(SoChildDTO soChildDTO, SoDTO soDTO, Integer deliveryStatus, Map<String, SoPackageDTO> soPackageDTOMap, CakeOrderStatusNoticeExpressVO expressVO, SoPackageDTO newsSoPackageDTO) {
        setSoPackageCommon(soChildDTO, soDTO, deliveryStatus, newsSoPackageDTO);
        //存在就更新
        if(EmptyUtil.isNotEmpty(expressVO.getExpress_no()) && soPackageDTOMap.containsKey(expressVO.getExpress_no())){
            SoPackageDTO soPackageDTO = soPackageDTOMap.get(expressVO.getExpress_no());
            newsSoPackageDTO.setId(soPackageDTO.getId());
            if(soPackageDTO.getDeliveryDate() !=null){
                newsSoPackageDTO.setDeliveryDate(null);
            }
            soPackageWriteService.updateSoPackageWithTx(newsSoPackageDTO);
        }else{
            //不存在就新增
            //fix-同一个批次传输的情况下会发生重复插入,因此会根据物流单号再获取一次来保证同一批次下也不重复
            boolean isNeedSaveFlag =  isNeedSave(expressVO,newsSoPackageDTO);
            if(!isNeedSaveFlag){
                return;
            }
            Long id = soPackageWriteService.insertSoPackageWithTx(newsSoPackageDTO);
            newsSoPackageDTO.setId(id);
        }
    }


    private Map<Long,SoPackageItemDTO> getSoPackageItemMap(SoChildDTO soChildDTO) {
        Map<Long,SoPackageItemDTO> soPackageItemDTOMap = new HashMap<>();
        SoPackageItemDTO queryDTO = new SoPackageItemDTO();
        queryDTO.setOrderCode(soChildDTO.getChildCode());
        List<SoPackageItemDTO> list = soPackageItemReadService.findSoPackageItemAll(queryDTO);
        if(CollectionUtils.isEmpty(list)){
            return soPackageItemDTOMap;
        }
        for (SoPackageItemDTO soPackageItemDTO : list) {
            soPackageItemDTOMap.put(soPackageItemDTO.getMpId(),soPackageItemDTO);
        }
        return soPackageItemDTOMap;
    }

    private void setSoPackageCommon(SoChildDTO soChildDTO, SoDTO soDTO, Integer deliveryStatus, SoPackageDTO newsSoPackageDTO) {
        newsSoPackageDTO.setDeliveryStatus(deliveryStatus);
        newsSoPackageDTO.setSoChildId(soChildDTO.getId());
        newsSoPackageDTO.setOrderCode(soDTO.getOrderCode());
        newsSoPackageDTO.setSoId(soDTO.getId());
        newsSoPackageDTO.setUserId(soDTO.getUserId());
    }

    private SoPackageItemDTO buildSoPackageItem(CakeOrderStatusNoticeExpressVO vo,ReceiverAddressDTO receiverAddressDTO,SoPackageDTO newsSoPackageDTO){
        SoPackageItemDTO soPackageItemDTO = new SoPackageItemDTO();
        soPackageItemDTO.setSoPackageId(newsSoPackageDTO.getId());
        soPackageItemDTO.setParentOrderCode(newsSoPackageDTO.getOrderCode());
        soPackageItemDTO.setUserId(newsSoPackageDTO.getUserId());
        soPackageItemDTO.setMerchantId(newsSoPackageDTO.getMerchantId());
        soPackageItemDTO.setMpId(Long.valueOf(vo.getSpec_id()));
        soPackageItemDTO.setBuyType(0);
        soPackageItemDTO.setProductType(0);
        soPackageItemDTO.setProductSaleType(1);
        soPackageItemDTO.setPlatformId(newsSoPackageDTO.getPlatformId());
        return soPackageItemDTO;
    }

    private SoPackageDTO buildSoPackageDTO(CakeOrderStatusNoticeExpressVO vo,ReceiverAddressDTO receiverAddressDTO){
        boolean isPhone = PhoneCodeValidate.validatePhoneCode(vo.getExpress_no());
        SoPackageDTO newsSoPackageDTO = new SoPackageDTO();
        newsSoPackageDTO.setDeliveryCode(vo.getExpress_no());
        newsSoPackageDTO.setDeliveryCompanyName(vo.getExpress_cp());
        newsSoPackageDTO.setMerchantId(7L);
        newsSoPackageDTO.setDeliveryMode(1);
        newsSoPackageDTO.setDeliveryDate(new Date());
        newsSoPackageDTO.setPackageType(1);
        newsSoPackageDTO.setPlatformId(7L);
        //若是商家配送
        if(isPhone){
            newsSoPackageDTO.setDeliveryMode(2);
            newsSoPackageDTO.setDeliveryNameMobile(vo.getExpress_no());
            newsSoPackageDTO.setDeliveryName(vo.getExpress_cp());
        }else{
            DeliveryCompanyDTO queryDeliveryCompanyDTO = new DeliveryCompanyDTO();
            queryDeliveryCompanyDTO.setCoding(vo.getExpress_code());
            List<DeliveryCompanyDTO> deliveryCompanyDTOS = deliveryCompanyReadService.findDeliveryCompanyAll(queryDeliveryCompanyDTO);
            if(!CollectionUtils.isEmpty(deliveryCompanyDTOS)){
                DeliveryCompanyDTO deliveryCompanyDTO = deliveryCompanyDTOS.get(0);
                newsSoPackageDTO.setDeliveryCompanyId(deliveryCompanyDTO.getId());
                newsSoPackageDTO.setDeliveryCompanyName(deliveryCompanyDTO.getName());
            }
        }
        //设置收货地址
        if(receiverAddressDTO !=null){
            newsSoPackageDTO.setReceiverAddressId(receiverAddressDTO.getId());
            newsSoPackageDTO.setProCityArea(receiverAddressDTO.getGoodReceiverProvince());
            String proCityArea=receiverAddressDTO.getGoodReceiverCity();
            if(EmptyUtil.isNotEmpty(receiverAddressDTO.getGoodReceiverProvince())){
                proCityArea = receiverAddressDTO.getGoodReceiverProvince();
            }
            if(EmptyUtil.isNotEmpty(receiverAddressDTO.getGoodReceiverCity())){
                proCityArea =proCityArea+receiverAddressDTO.getGoodReceiverCity();
            }
            if(EmptyUtil.isNotEmpty(receiverAddressDTO.getGoodReceiverCountry())){
                proCityArea = proCityArea+receiverAddressDTO.getGoodReceiverCountry();
            }
            newsSoPackageDTO.setProCityArea(proCityArea);
            newsSoPackageDTO.setGoodReceiverAddress(receiverAddressDTO.getGoodReceiverAddress());
            newsSoPackageDTO.setGoodReceiverMobile(receiverAddressDTO.getGoodReceiverMobile());
            newsSoPackageDTO.setGoodReceiverName(receiverAddressDTO.getGoodReceiverName());
        }

        return newsSoPackageDTO;
    }

    private Map<String,SoPackageDTO> listToPackageMap(Long soChildId){
        Map<String,SoPackageDTO> soPackageDTOMap =  new HashMap<>();
        SoPackageDTO soPackageDTO = new SoPackageDTO();
        soPackageDTO.setSoChildId(soChildId);
        List<SoPackageDTO> soPackageAll =  soPackageReadService.findSoPackageAll(soPackageDTO);
        if(CollectionUtils.isEmpty(soPackageAll)){
            return soPackageDTOMap;
        }
        for (SoPackageDTO packageDTO : soPackageAll) {
            if(EmptyUtil.isNotEmpty(packageDTO.getDeliveryCode())){
                soPackageDTOMap.put(packageDTO.getDeliveryCode(),packageDTO);
            }else if(EmptyUtil.isNotEmpty(packageDTO.getDeliveryNameMobile())){
                soPackageDTOMap.put(packageDTO.getDeliveryNameMobile(),packageDTO);
            }

        }
        return soPackageDTOMap;
    }


    private String checkOrderResult(CakeOrderStatusNoticeVO cakeOrderStatusNoticeVO){
        if(cakeOrderStatusNoticeVO == null){
            return "订单信息不能为空";
        }
        if(StringUtils.isEmpty(cakeOrderStatusNoticeVO.getOut_order_no()) || StringUtils.isBlank(cakeOrderStatusNoticeVO.getOut_order_no())){
            return "渠道订单号不能为空";
        }
        if(StringUtils.isEmpty(cakeOrderStatusNoticeVO.getOrder_no()) || StringUtils.isBlank(cakeOrderStatusNoticeVO.getOrder_no())){
            return "蛋糕叔叔订单号不能为空";
        }
        if(StringUtils.isEmpty(cakeOrderStatusNoticeVO.getStatus()) || StringUtils.isBlank(cakeOrderStatusNoticeVO.getStatus())){
            return "订单状态不能为空";
        }
        List<String> list = Arrays.asList("1","2","4");
        if(!list.contains(cakeOrderStatusNoticeVO.getStatus())){
            return "当前订单状态不能处理,当前状态为"+cakeOrderStatusNoticeVO.getStatus();
        }
        return null;
    }

    @Override
    public Object orderExpressModify(NoticeReqHelpBean reqHelpBean, HttpServletRequest req) {
        return returnSuccess();
    }

    @Override
    public Object orderExpress(NoticeReqHelpBean reqHelpBean, HttpServletRequest req) {
        return returnSuccess();
    }

    @Override
    public Object orderAfterSaleVerifyNotify(NoticeReqHelpBean reqHelpBean, HttpServletRequest req) {
        return returnSuccess();
    }

    private Map<String,Object> returnSuccess(){
        return OrderNoticeHelper.returnResult("code",200,"message","success");
    }

    private Map<String,Object> returnFail(Integer code ,String message){
        return OrderNoticeHelper.returnResult("code",code,"message",message);
    }


    private boolean isNeedSave(CakeOrderStatusNoticeExpressVO expressVO, SoPackageDTO newsSoPackageDTO){
        try {
            SoPackageDTO oldSoPackageDTO = checkIsSoPackageDTOExist(expressVO);
            if(oldSoPackageDTO == null || oldSoPackageDTO.getId() == null){
                return true;
            }
            newsSoPackageDTO.setId(oldSoPackageDTO.getId());
            if(oldSoPackageDTO.getDeliveryDate() !=null){
                newsSoPackageDTO.setDeliveryDate(null);
            }
            soPackageWriteService.updateSoPackageWithTx(newsSoPackageDTO);
            return false;
        }catch (Exception e){
            return true;
        }

    }

    private SoPackageDTO checkIsSoPackageDTOExist(CakeOrderStatusNoticeExpressVO expressVO){
        try {
            if(EmptyUtil.isEmpty(expressVO.getExpress_no())){
                return null;
            }
            SoPackageDTO soPackageDTO = new SoPackageDTO();
            soPackageDTO.setDeliveryCode(expressVO.getExpress_no());
            List<SoPackageDTO> soPackageAll =  soPackageReadService.findSoPackageAll(soPackageDTO);
            if(!CollectionUtils.isEmpty(soPackageAll)){
                return soPackageAll.get(0);
            }
        }catch (Exception e){

        }
        return null;
    }

}
