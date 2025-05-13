package com.egeo.components.order.notice.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.egeo.components.order.bean.NoticeReqHelpBean;
import com.egeo.components.order.business.PushOrderManage;
import com.egeo.components.order.business.SoManage;
import com.egeo.components.order.common.ProductChannelCodeEnum;
import com.egeo.components.order.dto.*;
import com.egeo.components.order.dto.notice.world.OrderAfterSaleVerifyNotifyDTO;
import com.egeo.components.order.dto.notice.world.OrderExpressDTO;
import com.egeo.components.order.dto.notice.world.WorldBuyBaseOrderNoticeDTO;
import com.egeo.components.order.notice.OrderNoticeStrategy;
import com.egeo.components.order.service.read.*;
import com.egeo.components.order.service.write.DeliveryCompanyWriteService;
import com.egeo.components.order.service.write.SoChildWriteService;
import com.egeo.components.order.service.write.SoPackageWriteService;
import com.egeo.components.order.service.write.SoWriteService;
import com.egeo.components.order.vo.cake.CakeOrderStatusNoticeVO;
import com.egeo.components.pay.enums.OrderConstant;
import com.egeo.components.utils.DateUtil;
import com.egeo.components.utils.JsonUtils;
import com.egeo.components.utils.OrderNoticeHelper;
import com.egeo.components.utils.WorldBuyUtil;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.web.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
@Service("worldOrderNoticeStrategyImpl")
public class WorldOrderNoticeStrategyImpl implements OrderNoticeStrategy {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private SoReadService soReadService;

    @Resource
    private SoChildReadService soChildReadService;

    @Resource
    private SoPackageReadService soPackageReadService;

    @Resource
    private SoPackageWriteService soPackageWriteService;

    @Resource
    private SoChildWriteService soChildWriteService;

    @Resource
    private SoWriteService soWriteService;

    @Resource
    private DeliveryCompanyReadService deliveryCompanyReadService;

    @Resource
    private DeliveryCompanyWriteService deliveryCompanyWriteService;

    @Autowired
    private JedisUtil jedisUtil;

    @Resource(name = "so")
    private SoManage soManage;

    private String CANCEL_LOCK_VALUE = "order_cancel";

    private final static String RESULT_KEY="code";

    private final static String RESULT_MSG="msg";

    private final static Integer SUCCESS_CODE=200;

    private final static Integer FAIL_CODE=0;

    @Resource
    private WorldBuyUtil worldBuyUtil;

    @Resource
    private PushOrderManage pushOrderManage;
    @Resource
    private ReceiverAddressReadService receiverAddressReadService;

    @Override
    public String getChannelCode() {
            return ProductChannelCodeEnum.WORLD_BUY.getCode();
    }

    @Override
    public Object orderStatusNotice(NoticeReqHelpBean reqHelpBean, HttpServletRequest req) {

        return returnSuccess("操作成功");
    }

    private void saveOrUpdateSoPackageDTO(OrderExpressDTO orderExpressDTO, SoChildDTO soChildDTO,SoDTO soDTO) {
        //更新或插入so_package
        Map<String, String> deliveryCompanyNameMap = getDeliveryCompanyNameMap();
        List<SoPackageDTO> list = soPackageReadService.queryPackageBySoChildId(soChildDTO.getId());
        if(CollectionUtils.isEmpty(list)){
            SoPackageDTO soPackageDTO = buildSoPackageDTO(orderExpressDTO, soChildDTO, soDTO, deliveryCompanyNameMap);
            soPackageWriteService.insertSoPackageWithTx(soPackageDTO);
        }else{
            for (SoPackageDTO soPackageDTO : list) {
                SoPackageDTO editSoPackageDTO = buildSoPackageDTO(orderExpressDTO, soChildDTO, soDTO, deliveryCompanyNameMap);
                editSoPackageDTO.setId(soPackageDTO.getId());
                soPackageWriteService.updateSoPackageWithTx(soPackageDTO);
            }
        }
    }

    private SoPackageDTO buildSoPackageDTO(OrderExpressDTO orderExpressDTO, SoChildDTO soChildDTO, SoDTO soDTO, Map<String, String> deliveryCompanyNameMap) {
        SoPackageDTO soPackageDTO = new SoPackageDTO();
        soPackageDTO.setOrderCode(soDTO.getOrderCode());
        soPackageDTO.setSoChildId(soChildDTO.getId());
        soPackageDTO.setSoId(soDTO.getId());
        soPackageDTO.setMerchantId(8L);
        soPackageDTO.setPlatformId(7L);
        String code= orderExpressDTO.getExpressCode();
        if(EmptyUtil.isNotEmpty(orderExpressDTO.getExpressCode())){
            DeliveryCompanyDTO deliveryCompanyDTO = getDeliveryCompanyDTO(orderExpressDTO, deliveryCompanyNameMap, code);
            soPackageDTO.setDeliveryCompanyId(deliveryCompanyDTO.getId());
            soPackageDTO.setDeliveryCompanyName(orderExpressDTO.getExpressName());
        }
        soPackageDTO.setDeliveryCode(orderExpressDTO.getExpressNo());
        soPackageDTO.setPackageType(1);
        soPackageDTO.setDeliveryMode(1);
        soPackageDTO.setDeliveryStatus(2);
        soPackageDTO.setUserId(soDTO.getUserId());
        if(EmptyUtil.isNotEmpty(orderExpressDTO.getDeliveryTime())){
            String dateStr =DateUtil.stampToDateSec(Long.valueOf(orderExpressDTO.getDeliveryTime()));
            soPackageDTO.setDeliveryDate(DateUtil.parseDate("yyyy-MM-dd HH:mm:ss",dateStr));
        }
        if(EmptyUtil.isNotEmpty(soDTO.getReceiverAddressId())){
            ReceiverAddressDTO receiverAddressDTO = receiverAddressReadService.findReceiverAddressById(soDTO.getReceiverAddressId());
            if(receiverAddressDTO !=null){
                soPackageDTO.setGoodReceiverName(receiverAddressDTO.getGoodReceiverName());
                soPackageDTO.setGoodReceiverMobile(receiverAddressDTO.getGoodReceiverMobile());
                soPackageDTO.setProCityArea(receiverAddressDTO.getGoodReceiverProvince()+receiverAddressDTO.getGoodReceiverCity());
                soPackageDTO.setGoodReceiverAddress(receiverAddressDTO.getGoodReceiverAddress());
            }
        }
        return soPackageDTO;
    }

    private Map<String, String> getDeliveryCompanyNameMap() {
        Map<String,String> deliveryCompanyNameMap = new HashMap<>();
        // ST 申通
        deliveryCompanyNameMap.put("ST","STO");
        // YUNDA 韵达
        deliveryCompanyNameMap.put("YUNDA","YD");
        // 宅急送	zhaijisong   ZJS
        deliveryCompanyNameMap.put("zhaijisong","ZJS");
        //JT    极兔速递  JTSD
        deliveryCompanyNameMap.put("JT","JTSD");
        //德邦物流	DB
        deliveryCompanyNameMap.put("DB","DBL");
        //优速快递	UCE
        deliveryCompanyNameMap.put("UCE","UC");
        //安能物流	annengwuliu  安能快运
        deliveryCompanyNameMap.put("annengwuliu","ANEKY");
        return deliveryCompanyNameMap;
    }

    private DeliveryCompanyDTO getDeliveryCompanyDTO(OrderExpressDTO orderExpressDTO, Map<String, String> deliveryCompanyNameMap, String code) {
        if(deliveryCompanyNameMap.containsKey(orderExpressDTO.getExpressCode())){
            code = deliveryCompanyNameMap.get(orderExpressDTO.getExpressCode());
        }
        DeliveryCompanyDTO deliveryCompanyDTO = getCompanyDTO(code);
        if(deliveryCompanyDTO == null){
            DeliveryCompanyDTO deliveryCompanyAddDTO = new DeliveryCompanyDTO();
            deliveryCompanyAddDTO.setCoding(orderExpressDTO.getExpressCode());
            deliveryCompanyAddDTO.setPlatformId(7L);
            deliveryCompanyAddDTO.setName(orderExpressDTO.getExpressName());
            deliveryCompanyAddDTO.setElectronicSurface(1);
            deliveryCompanyAddDTO.setPickUp(1);
            deliveryCompanyAddDTO.setTrajectoryQuerying(1);
            deliveryCompanyWriteService.insertDeliveryCompanyWithTx(deliveryCompanyAddDTO);
            deliveryCompanyDTO = getCompanyDTO(orderExpressDTO.getExpressCode());
        }
        return deliveryCompanyDTO;
    }

    private DeliveryCompanyDTO getCompanyDTO(String code) {
        DeliveryCompanyDTO deliveryCompanyQueryDTO = new DeliveryCompanyDTO();
        deliveryCompanyQueryDTO.setCoding(code);
        List<DeliveryCompanyDTO>  deliveryCompanyDTOs = deliveryCompanyReadService.findDeliveryCompanyAll(deliveryCompanyQueryDTO);
        DeliveryCompanyDTO deliveryCompanyDTO = null;
        if(!CollectionUtils.isEmpty(deliveryCompanyDTOs)){
            deliveryCompanyDTO =  deliveryCompanyDTOs.get(0);
        }
        return deliveryCompanyDTO;
    }

    @Override
    public Object orderExpressModify(NoticeReqHelpBean reqHelpBean,HttpServletRequest req) {
        WorldBuyBaseOrderNoticeDTO baseOrderNoticeDTO = getWorldBuyBaseOrderNoticeDTO(reqHelpBean);
        //logger.info("全球购订单物流发货更新接口通知-->{}",JSON.toJSONString(reqHelpBean));
        Map<String, Object> saveOrUpdateExpressInfo = saveOrUpdateExpressInfo(baseOrderNoticeDTO);
        if(saveOrUpdateExpressInfo !=null && saveOrUpdateExpressInfo.containsKey("code")){
            if(saveOrUpdateExpressInfo.get("code").toString().equals(String.valueOf(SUCCESS_CODE))){
                return returnSuccess("处理成功");
            }else{
                return returnFail("处理失败");
            }
        }
        return saveOrUpdateExpressInfo;
    }

    @Override
    public Object orderExpress(NoticeReqHelpBean reqHelpBean, HttpServletRequest req) {
        WorldBuyBaseOrderNoticeDTO baseOrderNoticeDTO = getWorldBuyBaseOrderNoticeDTO(reqHelpBean);
        //logger.info("全球购订单物流发货接口通知-->{}",JSON.toJSONString(reqHelpBean));
        Map<String, Object> saveOrUpdateExpressInfo = saveOrUpdateExpressInfo(baseOrderNoticeDTO);
        if(saveOrUpdateExpressInfo !=null && saveOrUpdateExpressInfo.containsKey("code")){
            if(saveOrUpdateExpressInfo.get("code").toString().equals(String.valueOf(SUCCESS_CODE))){
                return returnSuccess("处理成功");
            }else{
                return returnFail("处理失败");
            }
        }
        return saveOrUpdateExpressInfo;
    }

    private Map<String, Object> saveOrUpdateExpressInfo(WorldBuyBaseOrderNoticeDTO baseOrderNoticeDTO) {
        OrderExpressDTO orderExpressDTO =  JSONObject.parseObject(baseOrderNoticeDTO.getData(),OrderExpressDTO.class);
        SoChildDTO soChildDTO =  soChildReadService.querySoChildByChildCode(orderExpressDTO.getOrder_sn());
        if(soChildDTO == null){
            return returnFail("订单未找到");
        }
        //更新子订单
        //检查母订单是否需要变更
        SoDTO soDTO = soReadService.findSoById(soChildDTO.getSoId());
        if(soDTO == null){
            return returnFail("主订单未找到");
        }
        List<Integer> allowChangeDeliveryStatus =  Arrays.asList(0,1);
        if(allowChangeDeliveryStatus.contains(soChildDTO.getDeliveryStatus())){
            SoChildDTO editSoChildDTO = new SoChildDTO();
            editSoChildDTO.setId(soChildDTO.getId());
            editSoChildDTO.setDeliveryStatus(2);
            soChildWriteService.updateSoChildWithTx(editSoChildDTO);
        }
        List<Integer> allowChangeStatusList = Arrays.asList(0,1);
        if(allowChangeStatusList.contains(soDTO.getOrderStatus())){
            boolean isAllDelivery = checkIsAllDelivery(soDTO);
            SoDTO editSoDTO = new SoDTO();
            editSoDTO.setId(soDTO.getId());
            editSoDTO.setOrderStatus(2);
            editSoDTO.setDeliveryStatus(10);
            if(isAllDelivery){
                editSoDTO.setDeliveryStatus(20);
            }
            soWriteService.updateOrderWithTX(editSoDTO);
        }
        saveOrUpdateSoPackageDTO(orderExpressDTO, soChildDTO,soDTO);
        pushOrderManage.pushOrderInfo(soDTO.getId(),null,null);
        return returnSuccess("发货成功");
    }

    private boolean checkIsAllDelivery(SoDTO soDTO) {
        boolean isAllDelivery = true;
        List<SoChildDTO>  list = soChildReadService.querySoChildListBySoId(soDTO.getId());
        if(!CollectionUtils.isEmpty(list)){
            for (SoChildDTO childDTO : list) {
                //0:待发货，1:分拣中，2:已发货 3：已签收
                if(childDTO.getCancelStatus() !=1 && (childDTO.getDeliveryStatus() ==0 || childDTO.getDeliveryStatus() ==1)){
                    isAllDelivery = false;
                    break;
                }
            }
        }
        return isAllDelivery;
    }

    @Override
    public Object orderAfterSaleVerifyNotify(NoticeReqHelpBean reqHelpBean, HttpServletRequest req) {
        logger.info("全球购订单售后通知参数:{}",JSON.toJSONString(reqHelpBean));
        WorldBuyBaseOrderNoticeDTO baseOrderNoticeDTO = getWorldBuyBaseOrderNoticeDTO(reqHelpBean);
        if(EmptyUtil.isEmpty(baseOrderNoticeDTO.getData())){
            return returnFail("通知失败,缺少参数");
        }
        OrderAfterSaleVerifyNotifyDTO orderAfterSaleVerifyNotifyDTO = JSONObject.parseObject(baseOrderNoticeDTO.getData(), OrderAfterSaleVerifyNotifyDTO.class);
        if(EmptyUtil.isEmpty(orderAfterSaleVerifyNotifyDTO.getOrderSn()) || EmptyUtil.isEmpty(orderAfterSaleVerifyNotifyDTO.getOrderNo())){
            return returnFail("通知失败,缺少订单号");
        }
        if(EmptyUtil.isEmpty(orderAfterSaleVerifyNotifyDTO.getAfterVerifyStatus()) || EmptyUtil.isBlank(orderAfterSaleVerifyNotifyDTO.getAfterVerifyStatus())){
            return returnFail("通知失败,缺少审核状态");
        }
        if(orderAfterSaleVerifyNotifyDTO.getAfterVerifyStatus().equals("2")){
            logger.info("子订单号:{}审核不通过，返回处理成功",orderAfterSaleVerifyNotifyDTO.getOrderSn());
            return returnSuccess("通知成功");
        }
        //是否支持渠道直接退款0不支持 1支持
        if(worldBuyUtil.getHasChannelRefund().equals("0")){
            logger.info("子订单号:{}因配置不支持渠道直接退款，自动忽略，返回处理成功",orderAfterSaleVerifyNotifyDTO.getOrderSn());
            return returnSuccess("通知成功");
        }

        return refundChildOrder(orderAfterSaleVerifyNotifyDTO,reqHelpBean,req);
    }

    private Object refundChildOrder(OrderAfterSaleVerifyNotifyDTO orderAfterSaleVerifyNotifyDTO,NoticeReqHelpBean reqHelpBean,HttpServletRequest req){
        if(!orderAfterSaleVerifyNotifyDTO.getAfterVerifyStatus().equals("1")){
            logger.info("全球购通知订单不是审核通过");
            return returnSuccess("通知成功");
        }
        //审核通过
        String childOrderCode = orderAfterSaleVerifyNotifyDTO.getOrderSn();
        SoChildDTO soChildDTO = soChildReadService.querySoChildByChildCode(childOrderCode);
        if(soChildDTO == null){
            return returnFail("通知失败,订单未找到");
        }
        if(soChildDTO.getCancelStatus() ==1){
            logger.info("全球购通知订单{}已是取消状态，请勿重复推送",childOrderCode);
            return returnSuccess("通知成功");
        }
        SoDTO soDTO = soReadService.findSoById(soChildDTO.getSoId());
        if(soDTO ==null){
            logger.info("全球购通知订单{}母订单未找到",childOrderCode);
            return returnFail("通知失败,母订单未找到");
        }
        if(soDTO.getOrderStatus().equals(OrderConstant.ORDER_STATUS_CANCELED.getStatus()) || soDTO.getOrderStatus().equals(OrderConstant.ORDER_STATUS_RETURN_CASH_FINISHED.getStatus())){
            logger.info("全球购通知子订单{}对应的母订单{}重复推送,订单已处理为{}",childOrderCode,soDTO.getOrderCode(),soDTO.getOrderStatus());
            return returnSuccess("通知成功");
        }
        if(soDTO.getOrderPayStatus() ==2){
            logger.info("全球购通知子订单{}对应的母订单{}重复推送,订单已处理为{},支付状态{}",childOrderCode,soDTO.getOrderCode(),soDTO.getOrderStatus(),soDTO.getOrderPayStatus());
            return returnSuccess("通知成功");
        }
        Object rt = cancelOrRefundChildOrder(orderAfterSaleVerifyNotifyDTO,soChildDTO,soDTO,reqHelpBean,req);
        return rt;
    }


    private Object cancelOrRefundChildOrder(OrderAfterSaleVerifyNotifyDTO orderAfterSaleVerifyNotifyDTO, SoChildDTO soChildDTO, SoDTO soDTO, NoticeReqHelpBean reqHelpBean,HttpServletRequest req){
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
            return returnFail("当前子订单正在取消中,不可重复操作");
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
            return returnFail("取消或退款订单异常");
        }finally {
            jedisUtil.delLock(JedisUtil.ORDER_CANCEL_LOCK_KEY_PRE+orderCode);
        }
        return returnSuccess("通知成功");
    }

    private Map<String,Object> returnSuccess(String message){
        return returnResult(SUCCESS_CODE,message);
    }

    private Map<String,Object> returnFail(String message){
        return returnResult(FAIL_CODE,message);
    }

    private Map<String,Object> returnResult(Integer code,String message){
        return OrderNoticeHelper.returnResult(RESULT_KEY,code,RESULT_MSG,message);
    }



    private static WorldBuyBaseOrderNoticeDTO getWorldBuyBaseOrderNoticeDTO(NoticeReqHelpBean reqHelpBean){
        //WorldBuyBaseOrderNoticeDTO baseOrderNoticeDTO =  JsonUtils.jsonToPojo(JSON.toJSONString(reqHelpBean.getParamObject()), WorldBuyBaseOrderNoticeDTO.class);
        WorldBuyBaseOrderNoticeDTO baseOrderNoticeDTO =  JSONObject.parseObject(JSON.toJSONString(reqHelpBean.getParamObject()), WorldBuyBaseOrderNoticeDTO.class);
        return  baseOrderNoticeDTO;
    }
      /*  public static void main(String[] args) {
        String text = "{\n" +
                "  \"datetime\": 1734588605,\n" +
                "  \"data\": {\n" +
                "    \"orderNo\": \"xst923211013242159107\",\n" +
                "    \"hnOrderNo\": \"923211013242159108\",\n" +
                "    \"dOrderNo\": \"923211013242159106\",\n" +
                "    \"order_sn\": \"DCGJ-W-N-20241219135057024713-1-W-0\",\n" +
                "    \"expressCode\": \"ZTO\",\n" +
                "    \"expressName\": \"中通快递\",\n" +
                "    \"expressNo\": \"78468947429019\",\n" +
                "    \"deliveryTime\": 1734588605\n" +
                "  },\n" +
                "  \"sign\": \"C7B084EA6F94848D564B534D0CB23790\",\n" +
                "  \"MessageType\": \"OrderExpress\"\n" +
                "}";
            JSONObject data = JSONObject.parseObject(text);
            NoticeReqHelpBean reqHelpBean = new NoticeReqHelpBean();
            reqHelpBean.setParamObject(data);
            WorldBuyBaseOrderNoticeDTO baseOrderNoticeDTO = getWorldBuyBaseOrderNoticeDTO(reqHelpBean);
            OrderExpressDTO orderExpressDTO =  JSONObject.parseObject(baseOrderNoticeDTO.getData(),OrderExpressDTO.class);
            String dateStr =DateUtil.stampToDateSec(Long.valueOf(orderExpressDTO.getDeliveryTime()));
            System.out.println(dateStr);
            System.out.println(DateUtil.parseDate("yyyy-MM-dd HH:mm:ss",dateStr));
            System.out.println("sucess");
    }*/
}
