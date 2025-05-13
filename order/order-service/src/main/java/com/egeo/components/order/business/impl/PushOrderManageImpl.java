package com.egeo.components.order.business.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.egeo.components.order.business.PushOrderManage;
import com.egeo.components.order.client.SoChildClient;
import com.egeo.components.order.dto.*;
import com.egeo.components.order.enums.ThirdConst;
import com.egeo.components.order.facade.ReceiverAddressFacade;
import com.egeo.components.order.facade.SoDeliveryItemFacade;
import com.egeo.components.order.facade.SoFacade;
import com.egeo.components.order.facade.SoItemFacade;
import com.egeo.components.order.service.read.SoPackageReadService;
import com.egeo.components.order.strategy.factory.PushOrderFactory;
import com.egeo.components.order.strategy.vo.PushUserOrderReqVO;
import com.egeo.components.order.vo.DefaultReceiverInfoVo;
import com.egeo.components.order.vo.RePushRefundOrderVO;
import com.egeo.components.order.vo.push.*;
import com.egeo.components.pay.enums.OrderConstant;
import com.egeo.components.third.client.ChannelServiceConfigClient;
import com.egeo.components.third.dto.EnterpriseBizConfigDTO;
import com.egeo.components.third.dto.RemoteExecuteDTO;
import com.egeo.components.third.enums.ChannelServiceNameEnum;
import com.egeo.components.third.enums.ChannelServiceTypeEnum;
import com.egeo.components.user.client.UserClient;
import com.egeo.components.user.dto.UserDTO;
import com.egeo.components.user.enums.UserChannelSourceEnum;
import com.egeo.components.utils.FHCollectionUtils;
import com.egeo.components.utils.WorldBuyUtil;
import com.egeo.exception.BusinessException;
import com.egeo.utils.DateUtils;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
@Service
public class PushOrderManageImpl implements PushOrderManage {

    public Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Resource(name = "soFacade")
    private SoFacade soFacade;

    @Resource(name = "soItemFacade")
    private SoItemFacade soItemFacade;

    @Resource
    private SoChildClient soChildClient;

    @Resource
    private ChannelServiceConfigClient channelServiceConfigClient;

    @Resource
    private UserClient userClient;

    @Resource(name = "receiverAddressFacade")
    private ReceiverAddressFacade receiverAddressFacade;

    @Resource
    private SoPackageReadService soPackageReadService;

    @Resource
    private SoDeliveryItemFacade soDeliveryItemFacade;

    @Resource
    private WorldBuyUtil worldBuyUtil;

    public static final String PRODUCT_ID_="2024";
    public static final String PRODUCT_NAME="大米规格";
    public static final String PRODUCT_NAME_REPLACE_STR="X";

    public static final String PRODUCT_ID_REPLACE_STR="0";
    public static final String PRODUCT_TAX_RATE_STR="9";

    @Resource
    private PushOrderFactory pushOrderFactory;


    /**
     * @Description 推送订单信息
     **/
    @Override
    public JSONObject pushOrderInfo(Long soId,Long enterpriseId,String channelCode) {
        try {
            SoDTO soQueryDTO = new SoDTO();
            soQueryDTO.setId(soId);
            SoDTO soDTO = soFacade.findSoById(soQueryDTO);
            if(soDTO ==null){
                return null;
            }
            if(EmptyUtil.isNotEmpty(soDTO.getOrderCardPaid()) && soDTO.getOrderCardPaid().compareTo(BigDecimal.ZERO) ==1){
                logger.info("订单:{}卡劵支付，无需推送",soDTO.getOrderCode());
                return null;
            }

            if(soDTO.getOrderStatus().intValue()== OrderConstant.ORDER_STATUS_UNPAY.getStatus()){
                logger.info("订单:{}状态为:{}未支付，无需推送",soDTO.getOrderCode(),soDTO.getOrderStatus());
                return null;
            }
            UserDTO userDTO = userClient.findUserByID(soDTO.getUserId());
            if(userDTO == null || EmptyUtil.isEmpty(userDTO.getChannelSource())){
                return null;
            }
            if(EmptyUtil.isEmpty(channelCode)){
                channelCode = userDTO.getChannelSource();
            }
            if(!pushOrderFactory.checkHasSupportPushOrder(channelCode)){
                return null;
            }

            if(enterpriseId == null){
                enterpriseId = userDTO.getEnterpriseId();
            }
            EnterpriseBizConfigDTO enterpriseBizConfigDTO = new EnterpriseBizConfigDTO();
            enterpriseBizConfigDTO.setState(0);
            enterpriseBizConfigDTO.setBizCode(channelCode);
            enterpriseBizConfigDTO.setBizType(ChannelServiceNameEnum.ORDER_PUSH.getChannelServiceName());
            enterpriseBizConfigDTO.setEnterpriseId(enterpriseId !=null?enterpriseId+"":null);
            List<EnterpriseBizConfigDTO> bizConfigDTOList = channelServiceConfigClient.findEnterpriseBizConfigAllDTO(enterpriseBizConfigDTO);
            if(CollectionUtils.isEmpty(bizConfigDTOList)){
                //没有需要推送的渠道
                return null;
            }
            PushUserOrderReqVO pushUserOrderReqVO = new PushUserOrderReqVO();
            pushUserOrderReqVO.setUserDTO(userDTO);
            pushUserOrderReqVO.setSoDTO(soDTO);
            pushUserOrderReqVO.setBizConfigDTOList(bizConfigDTOList);
            pushUserOrderReqVO.setChannelCode(channelCode);
            pushUserOrderReqVO.setEnterpriseId(enterpriseId);
            pushUserOrderReqVO.setEnterpriseBizConfigDTO(enterpriseBizConfigDTO);
            pushOrderFactory.getPushUserOrderStrategy(channelCode).pushOrderInfo(pushUserOrderReqVO);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("soId:{}推送订单发生异常：{}",soId,e);
        }
        return null;
    }

    /**
     * @Description 推送订单信息
     **/
    //@Override
    public JSONObject pushOrderInfo_bak(Long soId,Long enterpriseId,String channelCode) {
        try {
            SoDTO soQueryDTO = new SoDTO();
            soQueryDTO.setId(soId);
            SoDTO soDTO = soFacade.findSoById(soQueryDTO);
            if(soDTO ==null){
                return null;
            }
            UserDTO userDTO = userClient.findUserByID(soDTO.getUserId());
            if(userDTO == null || EmptyUtil.isEmpty(userDTO.getChannelSource()) || !Objects.equals(userDTO.getChannelSource(), UserChannelSourceEnum.DLF.getChannelSource())){
                return null;
            }
            if(EmptyUtil.isEmpty(channelCode)){
                channelCode = userDTO.getChannelSource();
            }
            if(enterpriseId == null){
                enterpriseId = userDTO.getEnterpriseId();
            }
            EnterpriseBizConfigDTO enterpriseBizConfigDTO = new EnterpriseBizConfigDTO();
            enterpriseBizConfigDTO.setState(0);
            enterpriseBizConfigDTO.setBizCode(channelCode);
            enterpriseBizConfigDTO.setBizType(ChannelServiceNameEnum.ORDER_PUSH.getChannelServiceName());
            enterpriseBizConfigDTO.setEnterpriseId(enterpriseId !=null?enterpriseId+"":null);
            List<EnterpriseBizConfigDTO> bizConfigDTOList = channelServiceConfigClient.findEnterpriseBizConfigAllDTO(enterpriseBizConfigDTO);
            if(CollectionUtils.isEmpty(bizConfigDTOList)){
                //没有需要推送的渠道
                return null;
            }

            PushOrderRequestVO requestVO = new PushOrderRequestVO(soDTO);

            SoDeliveryItemDTO soDeliveryItemDTO = new SoDeliveryItemDTO();
            soDeliveryItemDTO.setOrderCode(soDTO.getOrderCode());
            List<SoDeliveryItemDTO> soDeliveryItemAll = soDeliveryItemFacade.findSoDeliveryItemAll(soDeliveryItemDTO);
            if(EmptyUtil.isNotEmpty(soDeliveryItemAll)){
                //发货时间
                requestVO.setDeliveryTime(com.egeo.utils.DateUtils.getDefaultDateTime(soDeliveryItemAll.get(0).getDeliveryDate()));
            }

            requestVO.setUserName(userDTO.getName());
            requestVO.setUserMobile(userDTO.getMobile());

            List<PushOrderPaidDetailVO> paidDetails = new ArrayList<>();
            PushOrderPaidDetailVO fanka = new PushOrderPaidDetailVO();
            fanka.setType("1");
            fanka.setPaidAmount(soDTO.getOrderPaidByFubi() !=null?soDTO.getOrderPaidByFubi()+"":"0.00");
            paidDetails.add(fanka);

            PushOrderPaidDetailVO jidian = new PushOrderPaidDetailVO();
            jidian.setType("2");
            jidian.setPaidAmount(soDTO.getOrderPaidByJidian() !=null?soDTO.getOrderPaidByJidian()+"":"0.00");
            paidDetails.add(jidian);

            PushOrderPaidDetailVO wxPaid = new PushOrderPaidDetailVO();
            wxPaid.setType("3");
            wxPaid.setPaidAmount(soDTO.getOrderPaidByCash() !=null?soDTO.getOrderPaidByCash()+"":"0.00");
            paidDetails.add(wxPaid);
            requestVO.setOrderPaidDetail(paidDetails);
            //收货地址
            DefaultReceiverInfoVo receiverInfoVo = defaultReceiverAddress(userDTO.getId(),soDTO.getReceiverAddressId(),userDTO.getPlatformId());
            if(receiverInfoVo !=null){
                requestVO.setReceiverPhone(receiverInfoVo.getPhoneNo());
                requestVO.setReceiverUser(receiverInfoVo.getReceiverName());
                requestVO.setReceiverAddress(receiverInfoVo.getAddress());
            }

            List<SoChildDTO> soChildDTOList =  soChildClient.querySoChildListBySoId(soId);
            List<SoItemDTO>  soItemDTOList =  soItemFacade.querySoItemListBySoId(soId);
            List<SoPackageDTO>  soPackageDTOS = soPackageReadService.packageByOrderCode(soDTO.getOrderCode());
            Map<Long, List<SoPackageDTO>> groupedPackageByChildIdMap = new HashMap<>();
            if(!CollectionUtils.isEmpty(soPackageDTOS)){
                groupedPackageByChildIdMap = soPackageDTOS.stream().collect(Collectors.groupingBy(SoPackageDTO::getSoChildId));

            }
            if(EmptyUtil.isEmpty(requestVO.getDeliveryTime()) && !CollectionUtils.isEmpty(soPackageDTOS)){
                SoPackageDTO soPackageDTO = soPackageDTOS.get(0);
                if(EmptyUtil.isNotEmpty(soPackageDTO.getDeliveryDate())){
                    requestVO.setDeliveryTime(DateUtils.getDefaultDateTime(soPackageDTO.getDeliveryDate()));
                }
            }

            // 根据childCode对列表进行分组
            List<SoItemDTO> finalSoItems = getItemDTOS(soChildDTOList, soItemDTOList);
            Map<Long, List<SoItemDTO>> groupedByChildCode =finalSoItems.stream().collect(Collectors.groupingBy(SoItemDTO::getSoChildId));

            List<PushChildOrderVO> childOrderVOS = getPushChildOrderVOList(soChildDTOList, groupedPackageByChildIdMap, groupedByChildCode);
            requestVO.setChildOrder(childOrderVOS);

            //logger.info("推送订单{}",JSON.toJSONString(requestVO));
            pushOrder(bizConfigDTOList, requestVO);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("soId:{}推送订单发生异常：{}",soId,e);
        }
        return null;
    }

    private  List<PushChildOrderVO> getPushChildOrderVOList(List<SoChildDTO> soChildDTOList, Map<Long, List<SoPackageDTO>> groupedPackageByChildIdMap, Map<Long, List<SoItemDTO>> groupedByChildCode) {
        List<PushChildOrderVO> childOrderVOS = new ArrayList<>();
        for (SoChildDTO childDTO : soChildDTOList) {
            List<SoItemDTO> soItemDTOS = groupedByChildCode.get(childDTO.getId());
            if(CollectionUtils.isEmpty(soItemDTOS)){
                if(EmptyUtil.isNotEmpty(childDTO.getOrdinaryId())){
                    logger.info("推送订单到德律风时对应的子订单id:{},childCode:{}未找到对应的商品，发生了拆单，正常情况",childDTO.getId(),childDTO.getChildCode());
                    //System.out.println("推送订单到德律风时对应的子订单id:"+childDTO.getId()+",childCode:"+childDTO.getChildCode()+"未找到对应的商品，发生了拆单，正常情况");
                }else{
                    logger.error("推送订单到德律风时对应的子订单id:{},childCode:{}未找到对应的商品，请核查",childDTO.getId(),childDTO.getChildCode());
                   // System.out.println("推送订单到德律风时对应的子订单id:"+childDTO.getId()+",childCode:"+childDTO.getChildCode()+"未找到对应的商品，请核查");
                }
               continue;
            }
            List<SoPackageDTO> childSoPackageDTOList = EmptyUtil.isNotEmpty(groupedPackageByChildIdMap)?groupedPackageByChildIdMap.get(childDTO.getId()):null;
            PushChildOrderVO pushChildOrderVO = new PushChildOrderVO();
            pushChildOrderVO.setChildCode(childDTO.getChildCode());
            pushChildOrderVO.setOrderStatus(childDTO.getOrderStatus());
            BigDecimal deliveryFee = childDTO.getDeliveryFee() !=null?childDTO.getDeliveryFee():BigDecimal.ZERO;
            if(EmptyUtil.isNotEmpty(childDTO.getSource()) && Objects.equals(childDTO.getSource(),ThirdConst.Source.JD)){
                deliveryFee = EmptyUtil.isNotEmpty(childDTO.getOrdinaryDeliveryFee())?childDTO.getOrdinaryDeliveryFee():BigDecimal.ZERO;
            }
            pushChildOrderVO.setDeliveryFeeAmount(deliveryFee.toPlainString());
            pushChildOrderVO.setRemark(childDTO.getRemark());
            if(!CollectionUtils.isEmpty(childSoPackageDTOList)){
                SoPackageDTO soPackageDTO = childSoPackageDTOList.get(0);
                 pushChildOrderVO.setLogisticsCompany(soPackageDTO.getDeliveryCompanyName());
                pushChildOrderVO.setCourierNumber(soPackageDTO.getDeliveryCode());
            }
            List<PushOrderProductVO> productVOS = soItemToPushOrderProductVO(soItemDTOS);

            if(deliveryFee !=null && deliveryFee.compareTo(BigDecimal.ZERO) >0){
                String productName = getProductName(getFeeNum(deliveryFee,PRODUCT_NAME_REPLACE_STR));
                String productId = getProductId(getFeeNum(deliveryFee,PRODUCT_ID_REPLACE_STR));
                PushOrderProductVO PushOrderFeeProductVO = new PushOrderProductVO();
                PushOrderFeeProductVO.setProductId(productId);
                PushOrderFeeProductVO.setSkuId(productId);
                PushOrderFeeProductVO.setProductName(productName);
                PushOrderFeeProductVO.setSalePrice(deliveryFee.toPlainString());
                PushOrderFeeProductVO.setNum(1);
                PushOrderFeeProductVO.setDeliveryFeeAver("0.00");
                PushOrderFeeProductVO.setFinalPromotionAver("0.00");
                PushOrderFeeProductVO.setTaxRate(PRODUCT_TAX_RATE_STR);
                productVOS.add(PushOrderFeeProductVO);
            }

            pushChildOrderVO.setProduct(productVOS);
            childOrderVOS.add(pushChildOrderVO);
        }
        return childOrderVOS;
    }

    /*public static void main(String[] args) {
        List<SoChildDTO> soChildDTOList = new ArrayList<>();
        SoChildDTO soChildDTO = new SoChildDTO();
        soChildDTO.setId(1L);
        soChildDTOList.add(soChildDTO);
        SoChildDTO soChildDTO1 = new SoChildDTO();
        soChildDTO1.setId(2L);
        soChildDTO1.setOrdinaryId(soChildDTO.getId());
        soChildDTOList.add(soChildDTO1);
        List<SoItemDTO> soItemDTOList = new ArrayList<>();
        SoItemDTO soItemDTO = new SoItemDTO();
        soItemDTO.setPuCount(1);
        soItemDTO.setPuId(1L);
        soItemDTO.setSoChildId(soChildDTO.getId());
        soItemDTO.setPuName("商品1");
        soItemDTOList.add(soItemDTO);
        SoItemDTO soItemDTO2 = new SoItemDTO();
        soItemDTO2.setPuCount(1);
        soItemDTO2.setPuId(1L);
        soItemDTO2.setExternalProductId("channel1");
        soItemDTO2.setSoChildId(soChildDTO1.getId());
        soItemDTO2.setPuName("商品1");
        soItemDTOList.add(soItemDTO2);
        SoItemDTO soItemDTO3 = new SoItemDTO();
        soItemDTO3.setPuCount(1);
        soItemDTO3.setPuId(2L);
        soItemDTO3.setSoChildId(soChildDTO1.getId());
        soItemDTO3.setPuName("商品2");
        soItemDTOList.add(soItemDTO3);

        List<SoItemDTO> finalSoItems = getItemDTOS(soChildDTOList, soItemDTOList);
        System.out.println(JSON.toJSONString(finalSoItems));
        Map<Long, List<SoItemDTO>> groupedByChildCode =finalSoItems.stream().collect(Collectors.groupingBy(SoItemDTO::getSoChildId));
        List<PushChildOrderVO> childOrderVOS = getPushChildOrderVOList(soChildDTOList, null, groupedByChildCode);
        System.out.println(JSON.toJSONString(childOrderVOS));
    }*/

    private  List<SoItemDTO> getItemDTOS(List<SoChildDTO> soChildDTOList, List<SoItemDTO> soItemDTOList) {
        Map<Long,SoChildDTO> soChildDTOMap = FHCollectionUtils.listToMap(soChildDTOList,SoChildDTO::getId, e->e);
        List<SoItemDTO> sourceSoItems = new ArrayList<>(soItemDTOList);
        Map<String,SoItemDTO> soItemDTOMap = new HashMap<>();
        for (SoItemDTO soItemDTO : sourceSoItems) {
            SoChildDTO soChildDTO = soChildDTOMap.get(soItemDTO.getSoChildId());
            if(EmptyUtil.isNotEmpty(soChildDTO.getOrdinaryId())){
                SoChildDTO  ordinaryChildDTO = soChildDTOMap.get(soChildDTO.getOrdinaryId());
                soItemDTO.setSoChildId(ordinaryChildDTO.getId());
            }
            StringBuffer sb = new StringBuffer();
            if(EmptyUtil.isNotEmpty(soItemDTO.getSource())){
                sb.append(soItemDTO.getSource());
            }
            if(EmptyUtil.isNotEmpty(soItemDTO.getExternalProductId())){
                sb.append(soItemDTO.getExternalProductId());
            }
            sb.append(soItemDTO.getPuId());
            String key = sb.toString();
            //同一个商品发生了拆单情况
            if(soItemDTOMap.containsKey(key)){
                SoItemDTO oldSoItemDTO =  soItemDTOMap.get(key);
                Integer totalNum = oldSoItemDTO.getPuCount()+soItemDTO.getPuCount();
                oldSoItemDTO.setPuCount(totalNum);
            }else{
                soItemDTOMap.put(key,soItemDTO);
            }

        }
        List<SoItemDTO> finalSoItems = new ArrayList<>(soItemDTOMap.values());
        return finalSoItems;
    }

    private static String getFeeNum(BigDecimal deliveryFee,String replaceString){
        if(deliveryFee == null || deliveryFee.compareTo(BigDecimal.ZERO) <=0){
            return null;
        }
        String deliveryFeeStr = deliveryFee.toPlainString();
        if(deliveryFeeStr.indexOf(".") !=-1){
            String arr[] = deliveryFeeStr.split("\\.");
            String num1 = arr[0];
            Integer num2 = Integer.valueOf(arr[1]);
            if(num2 >0){
                num1 =num1+replaceString+num2;
            }
            return num1;
        }
        return deliveryFeeStr;
    }


    private JSONObject pushOrder(List<EnterpriseBizConfigDTO> bizConfigDTOList, PushOrderRequestVO requestVO) {
        for (EnterpriseBizConfigDTO bizConfigDTO : bizConfigDTOList) {
            try {
                RemoteExecuteDTO dto = new RemoteExecuteDTO();
                dto.setEnterpriseId(Integer.valueOf(bizConfigDTO.getEnterpriseId()));
                dto.setChannelCode(bizConfigDTO.getBizCode());
                dto.setChannelServiceName(ChannelServiceNameEnum.ORDER_PUSH.getChannelServiceName());
                dto.setChannelServiceType(ChannelServiceTypeEnum.REQ.getChannelServiceType());
                dto.setBizCode(requestVO.getOrderCode());
                dto.setJsonString(JSON.toJSONString(requestVO));
                JsonResult jsonResult =  channelServiceConfigClient.remoteExecute(dto);
                if(jsonResult !=null && jsonResult.getCode() ==0 && jsonResult.getData() !=null){
                    return  JSONObject.parseObject(JSON.toJSONString(jsonResult.getData()));
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        return null;
    }

    private  List<PushOrderProductVO> soItemToPushOrderProductVO(List<SoItemDTO> soItemDTOS){
        List<PushOrderProductVO> productVOS = new ArrayList<>(soItemDTOS.size());
        for (SoItemDTO soItemDTO : soItemDTOS) {
            productVOS.add(new PushOrderProductVO(soItemDTO));
        }
        return productVOS;
    }

    @Override
    public JSONObject pushRefundOrder(CancelAndRefundOrderExtendsWithTxDTO dto){
        try {
            SoDTO soDTO =dto.getOrder();
            if(soDTO == null){
                return null;
            }
            if(EmptyUtil.isNotEmpty(soDTO.getOrderCardPaid()) && soDTO.getOrderCardPaid().compareTo(BigDecimal.ZERO) ==1){
                logger.info("订单:{}卡劵支付，无需推送退款信息",soDTO.getOrderCode());
                return null;
            }
            Long enterpriseId=null;
            String channelCode =null;
            UserDTO userDTO = userClient.findUserByID(soDTO.getUserId());
            if(userDTO == null || EmptyUtil.isEmpty(userDTO.getChannelSource()) || !Objects.equals(userDTO.getChannelSource(), UserChannelSourceEnum.DLF.getChannelSource())){
                return null;
            }
            if(EmptyUtil.isEmpty(channelCode)){
                channelCode = userDTO.getChannelSource();
            }
            if(enterpriseId == null){
                enterpriseId = userDTO.getEnterpriseId();
            }
            EnterpriseBizConfigDTO enterpriseBizConfigDTO = new EnterpriseBizConfigDTO();
            enterpriseBizConfigDTO.setState(0);
            enterpriseBizConfigDTO.setBizCode(channelCode);
            enterpriseBizConfigDTO.setBizType(ChannelServiceNameEnum.ORDER_REFUND_PUSH.getChannelServiceName());
            enterpriseBizConfigDTO.setEnterpriseId(enterpriseId !=null?enterpriseId+"":null);
            List<EnterpriseBizConfigDTO> bizConfigDTOList = channelServiceConfigClient.findEnterpriseBizConfigAllDTO(enterpriseBizConfigDTO);
            if(CollectionUtils.isEmpty(bizConfigDTOList)){
                //没有需要推送的渠道
                return null;
            }
            List<SoChildDTO> soChildDTOList =  soChildClient.querySoChildListBySoId(soDTO.getId());
            Map<Long,SoChildDTO> jdSoChildDTOMap = new HashMap<>();
            Map<Long,BigDecimal> jdRefundDeliveryFeeAmountMap = new HashMap<>();
            Map<Long,BigDecimal> jdDeliveryFeeAmountMap = new HashMap<>();
            // 使用computeIfAbsent来简化逻辑
            Map<Long, BigDecimal> optimizedMap = new HashMap<>();
            for (SoChildDTO soChildDTO : soChildDTOList) {
                // 提前检查source是否为非空且等于JD
                if (EmptyUtil.isNotEmpty(soChildDTO.getSource()) && ThirdConst.Source.JD.equals(soChildDTO.getSource())) {
                    Long key = EmptyUtil.isNotEmpty(soChildDTO.getOrdinaryId()) ? soChildDTO.getOrdinaryId() : soChildDTO.getId();
                    // 使用computeIfAbsent来简化逻辑
                    BigDecimal currentRefundAmount = soChildDTO.getRefundDeliveryFee() != null ? soChildDTO.getRefundDeliveryFee() : BigDecimal.ZERO;
                    optimizedMap.compute(key, (k, v) -> (v == null ? BigDecimal.ZERO : v).add(currentRefundAmount));
                    if(EmptyUtil.isEmpty(soChildDTO.getOrdinaryId())){
                        jdDeliveryFeeAmountMap.put(soChildDTO.getId(),soChildDTO.getOrdinaryDeliveryFee());
                    }
                }
            }
            jdRefundDeliveryFeeAmountMap.putAll(optimizedMap);

            Map<Long,SoChildDTO> soChildDTOMap = FHCollectionUtils.listToMap(soChildDTOList,SoChildDTO::getId,e->e);
            String remark = EmptyUtil.isNotEmpty(dto.getReason())?dto.getReason():"退款";
            remark = remark.replaceAll("/","|");
            PushRefundOrderRequestVO pushVO = new PushRefundOrderRequestVO();
            pushVO.setOrderCode(soDTO.getOrderCode());
            pushVO.setRemark(remark);
            List<OrderRefundDetailVO> refundDetailList = new ArrayList<>();
            refundDetailList.add(new OrderRefundDetailVO("1",EmptyUtil.isNotEmpty(dto.getSoRefundCodeByFubi())?dto.getSoRefundCodeByFubi():"0.00"));
            refundDetailList.add(new OrderRefundDetailVO("2",EmptyUtil.isNotEmpty(dto.getSoRefundCodeByJiDian())?dto.getSoRefundCodeByJiDian():"0.00"));
            refundDetailList.add(new OrderRefundDetailVO("3",EmptyUtil.isNotEmpty(dto.getSoRefundCodeByCash())?dto.getSoRefundCodeByCash():"0.00"));
            pushVO.setRefundDetail(refundDetailList);
            List<SoItemDTO> soItemDTOList = dto.getItems();
            Map<Long, List<SoItemDTO>> groupedByChildCodeMap = soItemDTOList.stream().collect(Collectors.groupingBy(SoItemDTO::getSoChildId));
            List<RefunChildOrderVO> childOrderVOS = new ArrayList<>();
            List<Long> childIds = new ArrayList<>();
            for (Map.Entry<Long, List<SoItemDTO>> entry : groupedByChildCodeMap.entrySet()) {
                Long key = entry.getKey();
                List<SoItemDTO> soItemDTOS = entry.getValue();

                SoChildDTO soChildDTO = soChildDTOMap.get(key);
                // 处理键和对应的值
                RefunChildOrderVO pushChildOrderVO = new RefunChildOrderVO();
                pushChildOrderVO.setChildCode(soChildDTO.getChildCode());
                pushChildOrderVO.setOrderStatus(soDTO.getOrderStatus());

                List<RefundProductVO> productVOS = new ArrayList<>();
                BigDecimal refundDeliveryFeeAmount = BigDecimal.ZERO;
                BigDecimal refundAmount = BigDecimal.ZERO;
                for (SoItemDTO soItemDTO : soItemDTOS) {
                    RefundProductVO refundProductVO = new RefundProductVO();
                    String productId = soItemDTO.getExternalProductId();
                    if(EmptyUtil.isEmpty(productId)){
                        productId = soItemDTO.getPuId()+"";
                    }
                    String productName = soItemDTO.getPuName().replaceAll("/","|").replaceAll("'","‘");
                    refundProductVO.setProductId(productId);
                    refundProductVO.setSkuId(soItemDTO.getPuId()+"");
                    refundProductVO.setProductName(productName);
                    refundProductVO.setRefundNum(soItemDTO.getRefundCount());
                    refundProductVO.setSalePrice(soItemDTO.getPrice()+"");
                    refundProductVO.setRefundAmount(soItemDTO.getRefundAmount() !=null ?soItemDTO.getRefundAmount().toPlainString():null);
                    refundAmount = refundAmount.add(soItemDTO.getRefundAmount() !=null ?soItemDTO.getRefundAmount():BigDecimal.ZERO);
                    refundDeliveryFeeAmount = refundDeliveryFeeAmount.add(soItemDTO.getRefundDeliveryFee() !=null ?soItemDTO.getRefundDeliveryFee():BigDecimal.ZERO);
                    productVOS.add(refundProductVO);
                }
                boolean hasRefundAll =false;
                BigDecimal refundDeliveryFee = soChildDTO.getDeliveryFee();
                BigDecimal refundDeliveryFeeName = soChildDTO.getDeliveryFee();
                //京东的是后续拆分且分摊了运费
                if(EmptyUtil.isNotEmpty(soChildDTO.getSource()) && Objects.equals(soChildDTO.getSource(),ThirdConst.Source.JD)){
                    Long id = EmptyUtil.isNotEmpty(soChildDTO.getOrdinaryId())?soChildDTO.getOrdinaryId():soChildDTO.getId();
                    //可退-因会发生拆单导致
                    refundDeliveryFeeName = jdDeliveryFeeAmountMap.get(id);
                    if(!childIds.contains(id)){
                        //总的已退
                        BigDecimal refundTotalDeliveryFee = jdRefundDeliveryFeeAmountMap.get(id);
                        hasRefundAll = refundTotalDeliveryFee !=null && refundTotalDeliveryFee.compareTo(BigDecimal.ZERO) >0 && refundTotalDeliveryFee.toPlainString().equals(refundDeliveryFeeName.toPlainString());
                        if(hasRefundAll){
                            childIds.add(id);
                        }
                    }
                }else{
                    hasRefundAll = soChildDTO.getRefundDeliveryFee() !=null && soChildDTO.getRefundDeliveryFee().compareTo(BigDecimal.ZERO) >0 && soChildDTO.getRefundDeliveryFee().toPlainString().equals(soChildDTO.getDeliveryFee().toPlainString());
                }
                if(soChildDTO.getRefundDeliveryFee() !=null && soChildDTO.getRefundDeliveryFee().compareTo(BigDecimal.ZERO) >0 && soChildDTO.getRefundDeliveryFee().toPlainString().equals(soChildDTO.getDeliveryFee().toPlainString())){
                    RefundProductVO refundProductVO = new RefundProductVO();
                    String productName = getProductName(getFeeNum(refundDeliveryFeeName,PRODUCT_NAME_REPLACE_STR));
                    String productId = getProductId(getFeeNum(refundDeliveryFeeName,PRODUCT_ID_REPLACE_STR));
                    refundProductVO.setProductId(productId);
                    refundProductVO.setSkuId(productId);
                    refundProductVO.setProductName(productName);
                    refundProductVO.setSalePrice(refundDeliveryFee.toPlainString());
                    refundProductVO.setRefundAmount(refundDeliveryFee.toPlainString());
                    refundProductVO.setRefundNum(hasRefundAll?1:0);
                    productVOS.add(refundProductVO);

                }
                pushChildOrderVO.setRefundDeliveryFeeAmount(refundDeliveryFeeAmount.toPlainString());
                pushChildOrderVO.setRefundAmount(refundAmount.toPlainString());
                pushChildOrderVO.setProduct(productVOS);

                childOrderVOS.add(pushChildOrderVO);
            }
            pushVO.setChildOrder(childOrderVOS);
            logger.info("推送退款订单{}",JSON.toJSONString(pushVO));
            pushRefundOrder(bizConfigDTOList, pushVO);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("推送退款订单发生异常:{}",e);
        }
        return null;
    }

    public static String getProductName(String feeNum){

        return PRODUCT_NAME+feeNum;
    }

    public static String getProductId(String feeNum){

        return PRODUCT_ID_+feeNum;
    }


    /**
     * @Description 推送退款订单信息
     **/
    @Override
    public JSONObject pushRefundOrder(Long soId,Long enterpriseId,String channelCode){

        return null;
    }

    private List<RefundProductVO> soItemToPushRefundProductVO(List<SoItemDTO> soItemDTOS){
        List<RefundProductVO> productVOS = new ArrayList<>();
        for (SoItemDTO soItemDTO : soItemDTOS) {
            if(soItemDTO.getRefundAmount() ==null || soItemDTO.getRefundAmount().compareTo(BigDecimal.ZERO) <=0){
                continue;
            }
            productVOS.add(new RefundProductVO(soItemDTO));
        }
        return productVOS;
    }

    private JSONObject pushRefundOrder(List<EnterpriseBizConfigDTO> bizConfigDTOList, PushRefundOrderRequestVO pushVO) {
        for (EnterpriseBizConfigDTO bizConfigDTO : bizConfigDTOList) {
            try {
                RemoteExecuteDTO dto = new RemoteExecuteDTO();
                dto.setEnterpriseId(Integer.valueOf(bizConfigDTO.getEnterpriseId()));
                dto.setChannelCode(bizConfigDTO.getBizCode());
                dto.setChannelServiceName(ChannelServiceNameEnum.ORDER_REFUND_PUSH.getChannelServiceName());
                dto.setChannelServiceType(ChannelServiceTypeEnum.REQ.getChannelServiceType());
                dto.setJsonString(JSON.toJSONString(pushVO));
                dto.setBizCode(pushVO.getOrderCode());
                JsonResult jsonResult = channelServiceConfigClient.remoteExecute(dto);
                if(jsonResult !=null && jsonResult.getCode() ==0 && jsonResult.getData() !=null){
                    return  JSONObject.parseObject(JSON.toJSONString(jsonResult.getData()));
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 获取指定收货地址/默认收货地址
     *
     * @param memberId
     * @param addrId
     * @return
     */
    private DefaultReceiverInfoVo defaultReceiverAddress(Long memberId, Long addrId, Long platformId) {
        ReceiverAddressDTO addressDto = null;
        if (addrId == null || addrId.longValue() == 0L) {
            // 返回默认收货地址DefaultReceiverInfoVo
            addressDto = receiverAddressFacade.queryDefaultReceiverAddress(memberId, platformId);
        } else {
            // 返回指定的收货地址
            addressDto = receiverAddressFacade.findReceiverAddressById(addrId);
            if (addressDto == null || addressDto.getUserId().longValue() != memberId.longValue()) {
                addressDto = null;
            }
        }
        DefaultReceiverInfoVo receiverInfo = null;
        if (addressDto != null) {
            receiverInfo = new DefaultReceiverInfoVo();
            receiverInfo.setId(addressDto.getId());
            String address = addressDto.getGoodReceiverProvince() + addressDto.getGoodReceiverCity()
                    + addressDto.getGoodReceiverCounty() + addressDto.getGoodReceiverAddress();
            receiverInfo.setAddress(address);
            receiverInfo.setPhoneNo(addressDto.getGoodReceiverMobile());
            receiverInfo.setUserId(addressDto.getUserId());
            receiverInfo.setReceiverName(addressDto.getGoodReceiverName());
        }
        return receiverInfo;
    }

    @Override
    public JSONObject pushOrderToThird(Long soId,String exceptReason){
        try {
            SoDTO querySoDTO = new SoDTO();
            querySoDTO.setId(soId);
            SoDTO soDTO = soFacade.findSoById(querySoDTO);
            if(soDTO == null){
                return null;
            }
            List<SoChildDTO> soChildDTOList = soChildClient.querySoChildListBySoId(soId);
            if(CollectionUtils.isEmpty(soChildDTOList)){
                return null;
            }
            if(soDTO.getCancelReason() !=null && EmptyUtil.isEmpty(exceptReason)){
                //取消原因  1 未支付自动取消 2 用户主动取消 3 客服取消
                if(soDTO.getCancelReason() == 1){
                    exceptReason="未支付自动取消";
                }else if(soDTO.getCancelReason() == 2){
                    exceptReason="用户主动取消";
                }else if(soDTO.getCancelReason() == 3){
                    exceptReason="客服取消";
                }else{
                    exceptReason="其他";
                }
            }
            for (SoChildDTO soChildDTO : soChildDTOList) {
                if(soChildDTO.getSource() == null || soChildDTO.getSource() !=5){
                    continue;
                }
                if(soChildDTO.getSource() !=null && soChildDTO.getSource() == 5){
                    Map<String,Object> paramMap = new HashMap<>();
                    paramMap.put("orderSn",soChildDTO.getChildCode());
                    paramMap.put("orderSn",exceptReason);
                    logger.info("全球购取消订单请求参数:{}",JSON.toJSONString(paramMap));
                    JSONObject jsonObject = worldBuyUtil.orderCancel(paramMap);
                    if(jsonObject == null){
                        logger.info("全球购取消订单{}响应结果:无响应结果",soChildDTO.getChildCode());
                    }else{
                        logger.info("全球购取消订单:{}响应结果:{}",soChildDTO.getChildCode(),JSON.toJSONString(jsonObject));
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error("取消订单推送订单发生异常:{}",e);
        }
        return null;
    }

    @Override
    public void pushChildOrderToThird(List<Long> needPushChildIds,String exceptReason){
        for (Long needPushChildId : needPushChildIds) {
            SoChildDTO soChildDTO = soChildClient.findSoChildById(needPushChildId);
            if(Objects.isNull(soChildDTO)){
                logger.error("准备推送子订单ID:{}到第三方进行售后申请时未找到子订单信息",needPushChildId);
                continue;
            }
            if(EmptyUtil.isNotEmpty(soChildDTO.getSource()) && Objects.equals(soChildDTO.getSource(), ThirdConst.Source.WORLD)){
                Map<String,Object> paramMap = new HashMap<>();
                paramMap.put("order_sn",soChildDTO.getChildCode());
                paramMap.put("after_sale_status",1);
                paramMap.put("exceptReason",EmptyUtil.isNotEmpty(exceptReason)?exceptReason:"商家主动退款");
                JSONObject jsonObject = worldBuyUtil.orderAfterSale(paramMap);
                if(jsonObject == null){
                    logger.info("全球购进行售后申请订单{}响应结果:无响应结果",soChildDTO.getChildCode());
                }else{
                    logger.info("全球购进行售后申请订单:{}响应结果:{}",soChildDTO.getChildCode(),JSON.toJSONString(jsonObject));
                }
            }
        }

    }

 /*   public static void main(String[] args) {
        RePushRefundOrderVO vo = new RePushRefundOrderVO();
        vo.setOrderCode("DCGJ-W-N-20241122105944024718");
        List<SoItemDTO> items = new ArrayList<>();
        SoItemDTO soItemDTO = new SoItemDTO();
        soItemDTO.setSoChildId(4354L);
        soItemDTO.setPuId(35L);
        soItemDTO.setPrice(new BigDecimal("99.36"));
        soItemDTO.setRefundCount(1);
        soItemDTO.setRefundAmount(new BigDecimal("99.36"));
        soItemDTO.setChildCode("DCGJ-W-N-20241122105944024718-1-W-0");
    }*/



    @Override
    public JSONObject rePushRefundOrderByItemAndRefund(RePushRefundOrderVO vo){
        if(EmptyUtil.isEmpty(vo.getReason())){
            throw new BusinessException("退款原因不能为空");
        }
        if(EmptyUtil.isEmpty(vo.getOrderCode())){
            throw new BusinessException("退款订单号不能为空");
        }
        if(CollectionUtils.isEmpty(vo.getItems())  && EmptyUtil.isEmpty(vo.getOrderCode())){
            throw new BusinessException("缺少必填参数");
        }
        CancelAndRefundOrderExtendsWithTxDTO dto = new CancelAndRefundOrderExtendsWithTxDTO(vo.getItems(),vo.getReason());
        if(EmptyUtil.isNotEmpty(vo.getOrderCode())){
            List<SoDTO> soDTOS = soFacade.findSoByCode(vo.getOrderCode());
            if(!CollectionUtils.isEmpty(soDTOS)){
                dto.setOrder(soDTOS.get(0));
            }
        }
        if(Objects.isNull(dto.getOrder())){
            throw new BusinessException("退款订单未找到");
        }
        List<SoItemDTO> itemDTOS = vo.getItems();
        BigDecimal refundAmount = BigDecimal.ZERO;
        for (SoItemDTO itemDTO : itemDTOS) {
            refundAmount = refundAmount.add(itemDTO.getRefundAmount());
        }
        SoDTO soDTO = dto.getOrder();
        if(EmptyUtil.isNotEmpty(soDTO.getOrderPaidByCash()) && soDTO.getOrderPaidByCash().compareTo(BigDecimal.ZERO) >0){
            //使用的现金支付
            dto.setSoRefundCodeByCash(refundAmount.toPlainString());
        }else if(EmptyUtil.isNotEmpty(soDTO.getOrderPaidByFubi()) && soDTO.getOrderPaidByFubi().compareTo(BigDecimal.ZERO) >0){
            //使用的餐卡支付
            dto.setSoRefundCodeByFubi(refundAmount.toPlainString());
        }else if(EmptyUtil.isNotEmpty(soDTO.getOrderPaidByJidian()) && soDTO.getOrderPaidByJidian().compareTo(BigDecimal.ZERO) >0){
            //使用的积点支付
            dto.setSoRefundCodeByJiDian(refundAmount.toPlainString());
        }else{
            throw new BusinessException("订单未找到支付方式");
        }
        return this.pushRefundOrder(dto);
    }

    /*public static void main(String[] args) {
        String childCode = "DCGJ-W-N-20241113192917024657-1-1";
        String orderCode ="DCGJ-W-N-20241113192917024657";
        Integer orderStatus = 10;
        String remark="";
        String soRefundCodeByFubi ="16.90";
        String soRefundCodeByJiDian = null;
        String soRefundCodeByCash = null;
        List<SoItemDTO> soItemDTOList = new ArrayList<>();
        SoItemDTO soItemDTO = new SoItemDTO();
        soItemDTO.setPuCount(1);
        soItemDTO.setPuId(453L);
        soItemDTO.setPuName("【三珍斋】红烧狮子头200g 200g");
        soItemDTO.setRefundAmount(new BigDecimal("16.90").setScale(2));
        soItemDTO.setRefundCount(1);
        soItemDTO.setPrice(new BigDecimal("16.90").setScale(2));
        soItemDTO.setExternalSkuId(null);
        soItemDTO.setExternalProductId(null);
        soItemDTO.setChildCode(childCode);
        soItemDTO.setSoChildId(4093L);
        soItemDTOList.add(soItemDTO);
        Map<Long,SoChildDTO> soChildDTOMap = new HashMap<>();
        SoChildDTO soChildDTO = new SoChildDTO();
        soChildDTO.setDeliveryFee(BigDecimal.ZERO);
        soChildDTO.setId(4093L);
        soChildDTO.setChildCode(childCode);
        soChildDTOMap.put(4093L,soChildDTO);
        String str = refundJsonString(orderCode,orderStatus,remark,soRefundCodeByFubi,soRefundCodeByJiDian,soRefundCodeByCash,soItemDTOList,soChildDTOMap);
        System.out.println(str);
    }*/

    public static String refundJsonString(String orderCode,Integer orderStatus,String remark,String soRefundCodeByFubi,String soRefundCodeByJiDian,String soRefundCodeByCash,List<SoItemDTO> soItemDTOList,Map<Long,SoChildDTO> soChildDTOMap){
        PushRefundOrderRequestVO pushVO = new PushRefundOrderRequestVO();
        pushVO.setOrderCode(orderCode);
        pushVO.setRemark(remark);
        List<OrderRefundDetailVO> refundDetailList = new ArrayList<>();
        refundDetailList.add(new OrderRefundDetailVO("1",EmptyUtil.isNotEmpty(soRefundCodeByFubi)?soRefundCodeByFubi:"0.00"));
        refundDetailList.add(new OrderRefundDetailVO("2",EmptyUtil.isNotEmpty(soRefundCodeByJiDian)?soRefundCodeByJiDian:"0.00"));
        refundDetailList.add(new OrderRefundDetailVO("3",EmptyUtil.isNotEmpty(soRefundCodeByCash)?soRefundCodeByCash:"0.00"));
        pushVO.setRefundDetail(refundDetailList);
        Map<Long, List<SoItemDTO>> groupedByChildCodeMap = soItemDTOList.stream().collect(Collectors.groupingBy(SoItemDTO::getSoChildId));
        List<RefunChildOrderVO> childOrderVOS = new ArrayList<>();
        for (Map.Entry<Long, List<SoItemDTO>> entry : groupedByChildCodeMap.entrySet()) {
            Long key = entry.getKey();
            List<SoItemDTO> soItemDTOS = entry.getValue();

            SoChildDTO soChildDTO = soChildDTOMap.get(key);
            // 处理键和对应的值
            RefunChildOrderVO pushChildOrderVO = new RefunChildOrderVO();
            pushChildOrderVO.setChildCode(soChildDTO.getChildCode());
            pushChildOrderVO.setOrderStatus(orderStatus);

            List<RefundProductVO> productVOS = new ArrayList<>();
            BigDecimal refundDeliveryFeeAmount = BigDecimal.ZERO;
            BigDecimal refundAmount = BigDecimal.ZERO;
            for (SoItemDTO soItemDTO : soItemDTOS) {
                RefundProductVO refundProductVO = new RefundProductVO();
                String productId = soItemDTO.getExternalProductId();
                if(EmptyUtil.isEmpty(productId)){
                    productId = soItemDTO.getPuId()+"";
                }
                String productName = soItemDTO.getPuName();
                if(EmptyUtil.isNotEmpty(soItemDTO.getPuName()) && soItemDTO.getPuName().indexOf("/") !=-1){
                    productName = soItemDTO.getPuName().replaceAll("/","|");
                }
                refundProductVO.setProductId(productId);
                refundProductVO.setSkuId(soItemDTO.getPuId()+"");
                refundProductVO.setProductName(productName);
                refundProductVO.setRefundNum(soItemDTO.getRefundCount());
                refundProductVO.setSalePrice(soItemDTO.getPrice()+"");
                refundProductVO.setRefundAmount(soItemDTO.getRefundAmount() !=null ?soItemDTO.getRefundAmount().toPlainString():null);
                refundAmount = refundAmount.add(soItemDTO.getRefundAmount() !=null ?soItemDTO.getRefundAmount():BigDecimal.ZERO);
                refundDeliveryFeeAmount = refundDeliveryFeeAmount.add(soItemDTO.getRefundDeliveryFee() !=null ?soItemDTO.getRefundDeliveryFee():BigDecimal.ZERO);
                productVOS.add(refundProductVO);
            }
            if(soChildDTO.getRefundDeliveryFee() !=null && soChildDTO.getRefundDeliveryFee().compareTo(BigDecimal.ZERO) >0 && soChildDTO.getRefundDeliveryFee().toPlainString().equals(soChildDTO.getDeliveryFee().toPlainString())){
                RefundProductVO refundProductVO = new RefundProductVO();
                String productName = getProductName(getFeeNum(soChildDTO.getDeliveryFee(),PRODUCT_NAME_REPLACE_STR));
                String productId = getProductId(getFeeNum(soChildDTO.getDeliveryFee(),PRODUCT_ID_REPLACE_STR));
                refundProductVO.setProductId(productId);
                refundProductVO.setSkuId(productId);
                refundProductVO.setProductName(productName);
                refundProductVO.setSalePrice(soChildDTO.getDeliveryFee().toPlainString());
                refundProductVO.setRefundAmount(soChildDTO.getDeliveryFee().toPlainString());
                refundProductVO.setRefundNum(1);
                productVOS.add(refundProductVO);

            }
            pushChildOrderVO.setRefundDeliveryFeeAmount(refundDeliveryFeeAmount.toPlainString());
            pushChildOrderVO.setRefundAmount(refundAmount.toPlainString());
            pushChildOrderVO.setProduct(productVOS);

            childOrderVOS.add(pushChildOrderVO);
        }
        pushVO.setChildOrder(childOrderVOS);
        return JSON.toJSONString(pushVO);
    }
}
