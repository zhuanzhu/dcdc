package com.egeo.components.order.business.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.egeo.components.order.business.GenerateChildOrderManage;
import com.egeo.components.order.business.thread.CommonThreadPoolExecutor;
import com.egeo.components.order.business.thread.GenerateSoChildThread;
import com.egeo.components.order.business.thread.OrderCreateProductThread;
import com.egeo.components.order.dto.*;
import com.egeo.components.order.enums.ThirdConst;
import com.egeo.components.order.facade.SoFacade;
import com.egeo.components.order.manage.write.CakeAddressWriteManage;
import com.egeo.components.order.strategy.vo.CreateOrderRespVO;
import com.egeo.components.order.strategy.vo.SplitSoChildReqVO;
import com.egeo.components.order.strategy.vo.SplitSoChildRespVO;
import com.egeo.components.order.vo.world.WroldSplitOrderRuleVO;
import com.egeo.components.product.dto.CommodityProductUnitDTO;
import com.egeo.components.utils.CakeUtil;
import com.egeo.exception.BusinessException;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/12/6 0:53
 * @Version V1.0
 **/
@Service
public class GenerateChildOrderManageImpl implements GenerateChildOrderManage{
    public Logger logger = LoggerFactory.getLogger(this.getClass().getName());


    @Resource(name = "soFacade")
    private SoFacade soFacade;

    @Override
    public void generateChildByMerchant(List<SoChildDTO> soChildDTOList, List<SoItemDTO> soItems, String orderCode, JSONObject remarkObj, ReceiverAddressDTO addr,JSONObject deliveryPriceObj){
        List<Long> merchantIdList = new ArrayList<>();
        for (SoItemDTO soItemDTO : soItems) {
            if (!merchantIdList.contains(soItemDTO.getMerchantId())) {
                merchantIdList.add(soItemDTO.getMerchantId());
            }
        }

        Collections.sort(merchantIdList);
        int n = 0;
        List<Long> supplierIdList = new ArrayList<>();
        for (SoItemDTO soItemDTO : soItems) {
            if (!supplierIdList.contains(soItemDTO.getSupplierId())) {
                if(soItemDTO.getSupplierId()!=null) {
                    supplierIdList.add(soItemDTO.getSupplierId());
                }
            }
        }
        Collections.sort(supplierIdList);
        int m = 0;
        // 使用固定数量的线程池，避免高并发导致服务器线程无限制的增长
        ThreadPoolExecutor executor = CommonThreadPoolExecutor.getInstance();
        // 主线程优先拿到最先完成的任务的返回值，而不管它们加入线程池的顺序。
        CompletionService<JsonResult<SplitSoChildRespVO>> completionService = new ExecutorCompletionService<>(executor);
        List<Future<JsonResult<SplitSoChildRespVO>>> results = new ArrayList<>();
        Future<JsonResult<SplitSoChildRespVO>> future = null;
        for (Long mId : merchantIdList) {
            n++;
            SplitSoChildReqVO reqVO = new SplitSoChildReqVO();
            reqVO.setSupplierIdList(supplierIdList);
            reqVO.setmId(mId);
            reqVO.setRemarkObj(remarkObj);
            reqVO.setAddr(addr);
            reqVO.setDeliveryPriceObj(deliveryPriceObj);
            reqVO.setOrderCode(orderCode);
            reqVO.setSoItems(soItems);
            reqVO.setM(m);
            reqVO.setN(n);
            future = completionService.submit(new GenerateSoChildThread(reqVO));
            results.add(future);
        }
        String errorMsg = "";
        for (Future<JsonResult<SplitSoChildRespVO>> result : results) {
            try {
                JsonResult<SplitSoChildRespVO> rt =   result.get();
                if(Objects.isNull(rt)){
                    errorMsg = "生成子订单失败";
                    throw new BusinessException("生成子订单失败");
                }
                if(rt.getCode() !=0){
                    errorMsg = rt.getError();
                    throw new BusinessException(rt.getError());
                }
                SplitSoChildRespVO splitSoChildRespVO =  rt.getData();
                if(Objects.isNull(splitSoChildRespVO)){
                    errorMsg = "创建子订单失败";
                    throw new BusinessException("创建子订单失败");
                }
               if(!CollectionUtils.isEmpty(splitSoChildRespVO.getSoChildDTOList())){
                   soChildDTOList.addAll(splitSoChildRespVO.getSoChildDTOList());
               }
            }catch (Exception e){
                logger.error("订单:{}生成子订单失败:{}",orderCode,e);
                throw new BusinessException(errorMsg);
            }
        }

    }


    @Override
    public  List<SoThirdpartyDTO> generateSoThirdpartyDTO(List<SoChildDTO> soChildDTOList, List<SoItemDTO> soItems, Long platformId, Long invoiceId,
                                                          Long receiveAddressId, Integer source, Long commodityTemplateId, String phone,
                                                          CommodityProductUnitDTO puType0,Long puId,boolean isJdType0){
        //设置子订单的公共信息
        List<SoThirdpartyDTO> soThirdpartyDTOList = new ArrayList<>();

        for (SoChildDTO soChildDTO : soChildDTOList) {
            SoThirdpartyDTO soThirdpartyDTO = new SoThirdpartyDTO();
            //计算子订单的金额
            //设置子订单的付款金额(商品-优惠(此时为0))
            BigDecimal soChildAmount = new BigDecimal(0);
            List<SoChildDTO.SkuInfoDTO> infoDTOList = new ArrayList<>();
            for (SoItemDTO soItemDTO : soItems) {
                SoChildDTO.SkuInfoDTO infoDTO = new SoChildDTO.SkuInfoDTO();
                if (soChildDTO.getChildCode().equals(soItemDTO.getChildCode())) {
                    soChildAmount = soChildAmount.add(soItemDTO.getPrice().multiply(BigDecimal.valueOf(soItemDTO.getPuCount())));
                    infoDTO.setNum(soItemDTO.getPuCount());
                    infoDTO.setSkuId(soItemDTO.getExternalSkuId());
                    infoDTOList.add(infoDTO);
                }
            }
            if(EmptyUtil.isNotEmpty(soChildDTO.getSource()) && (soChildDTO.getSource().equals(ThirdConst.Source.CAKE) || soChildDTO.getSource().equals(ThirdConst.Source.WORLD))){
                soChildDTO.setAmount(soChildAmount);
            }
            soChildDTO.setProductAmount(soChildAmount);//商品金额
            soChildDTO.setSkuInfoList(infoDTOList);
            soChildDTO.setDeliveryStatus(0);//设置物流状态为0,代发货
            soChildDTO.setInvoiceId(invoiceId);//设置发票id
            soChildDTO.setPlatformId(platformId);
//            soChildDTO.setRemark(remark);
            soChildDTO.setReceiverAddressId(receiveAddressId);
            // 获取第三方对接参数
            int thirdpartyAtt=0;
            Long merchantId=0L;
            boolean isThird =false;
            if(EmptyUtil.isNotEmpty(soChildDTO.getPerformingParty())){
                if(Objects.equals(soChildDTO.getPerformingParty(),ThirdConst.Merchant.WORLD) || Objects.equals(soChildDTO.getPerformingParty(),ThirdConst.Merchant.WORLD)){
                    isThird = true;
                }else if(EmptyUtil.isNotEmpty(source) && Objects.equals(source,ThirdConst.Source.CAKE) || Objects.equals(source,ThirdConst.Source.WORLD)){
                    isThird = true;
                }
            }else if(EmptyUtil.isNotEmpty(source) && Objects.equals(source,ThirdConst.Source.CAKE) || Objects.equals(source,ThirdConst.Source.WORLD)){
                isThird = true;
            }
            logger.info("isThird={}",isThird);

            if(EmptyUtil.isNotEmpty(puId) && !isThird){
                if(isJdType0) {
                    thirdpartyAtt=13;
                }else {
                    thirdpartyAtt=getThirdpartyAttValue(puType0.getStandardProductUnitId());
                    merchantId = puType0.getMerchantId();
                }
            }

            if (commodityTemplateId != null &&(commodityTemplateId.equals(4L)||commodityTemplateId.equals(9L))) {
                // 话费充值
                // 1)设置子订单的第三方订单类型为话费充值
                soChildDTO.setThirdpartyType(Integer.valueOf(1));
                soThirdpartyDTO.setPhone(phone);
                soThirdpartyDTO.setThirdpartyStatus(Integer.valueOf(-1));
            }else if(thirdpartyAtt==11){
                //第三方对接参数如果是11则是券仓卡券,需要修改订单的第三方订单类型)
                soChildDTO.setThirdpartyType(Integer.valueOf(2));
                soThirdpartyDTO.setThirdpartyStatus(Integer.valueOf(-1));
            }else {
                // 第三方订单类型: 0:无第三方订单 1:话费充值 2:券仓卡券,3:京东
                soChildDTO.setThirdpartyType(Integer.valueOf(0));
            }
            if(soChildDTO.getPerformingParty().equals(6L)){
                soChildDTO.setThirdpartyType(Integer.valueOf(3));
                soThirdpartyDTO.setThirdpartyStatus(Integer.valueOf(-1));
                soThirdpartyDTO.setSoChildCode(soChildDTO.getChildCode());
            }
            else if(soChildDTO.getPerformingParty().equals(ThirdConst.Merchant.CAKE)){
                soChildDTO.setThirdpartyType(ThirdConst.ThirdPartyType.CAKE);
                soThirdpartyDTO.setThirdpartyStatus(Integer.valueOf(-1));
                soThirdpartyDTO.setSoChildCode(soChildDTO.getChildCode());
            }
            else if(soChildDTO.getPerformingParty().equals(ThirdConst.Merchant.WORLD)){
                soChildDTO.setThirdpartyType(ThirdConst.ThirdPartyType.WORLD);
                soThirdpartyDTO.setThirdpartyStatus(Integer.valueOf(-1));
                soThirdpartyDTO.setSoChildCode(soChildDTO.getChildCode());
            }else if(soChildDTO.getPerformingParty().equals(ThirdConst.Merchant.QM)){
                soChildDTO.setThirdpartyType(ThirdConst.ThirdPartyType.QM);
                soThirdpartyDTO.setThirdpartyStatus(Integer.valueOf(-1));
                soThirdpartyDTO.setSoChildCode(soChildDTO.getChildCode());
            }
            soThirdpartyDTOList.add(soThirdpartyDTO);
        }
        return soThirdpartyDTOList;
    }

    //根据spuid查询第三方对接参数值
    private Integer getThirdpartyAttValue(Long spuId){
        int i=soFacade.getThirdpartyAttValue(spuId);
        if(EmptyUtil.isEmpty(i)){
            throw new BusinessException("未查询到第三方对接参数");
        }
        return i;
    }
}
