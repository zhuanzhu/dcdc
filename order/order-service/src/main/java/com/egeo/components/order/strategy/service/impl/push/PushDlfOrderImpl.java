package com.egeo.components.order.strategy.service.impl.push;

import com.alibaba.fastjson.JSONObject;
import com.egeo.components.order.client.SoChildClient;
import com.egeo.components.order.dto.*;
import com.egeo.components.order.enums.ThirdConst;
import com.egeo.components.order.facade.ReceiverAddressFacade;
import com.egeo.components.order.facade.SoDeliveryItemFacade;
import com.egeo.components.order.facade.SoFacade;
import com.egeo.components.order.facade.SoItemFacade;
import com.egeo.components.order.service.read.SoPackageReadService;
import com.egeo.components.order.strategy.service.PushUserOrderStrategy;
import com.egeo.components.order.strategy.vo.PushUserOrderReqVO;
import com.egeo.components.order.vo.DefaultReceiverInfoVo;
import com.egeo.components.order.vo.push.PushChildOrderVO;
import com.egeo.components.order.vo.push.PushOrderPaidDetailVO;
import com.egeo.components.order.vo.push.PushOrderProductVO;
import com.egeo.components.order.vo.push.PushOrderRequestVO;
import com.egeo.components.third.client.ChannelServiceConfigClient;
import com.egeo.components.third.dto.EnterpriseBizConfigDTO;
import com.egeo.components.user.client.UserClient;
import com.egeo.components.user.dto.UserDTO;
import com.egeo.components.user.enums.UserChannelSourceEnum;
import com.egeo.components.utils.FHCollectionUtils;
import com.egeo.utils.DateUtils;
import com.egeo.utils.EmptyUtil;
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
 * @Date 2024/12/30 14:43
 * @Version V1.0
 **/
@Service("pushDlfOrderImpl")
public class PushDlfOrderImpl extends PushUserOrderCommonImpl {

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

    public static final String PRODUCT_ID_="2024";
    public static final String PRODUCT_NAME="大米规格";
    public static final String PRODUCT_NAME_REPLACE_STR="X";

    public static final String PRODUCT_ID_REPLACE_STR="0";
    public static final String PRODUCT_TAX_RATE_STR="9";


    @Override
    public String getChannelCode() {
        return UserChannelSourceEnum.DLF.getChannelSource();
    }

    @Override
    public JSONObject pushOrderInfo(PushUserOrderReqVO pushUserOrderReqVO) {
        SoDTO soDTO = pushUserOrderReqVO.getSoDTO();
        UserDTO userDTO = pushUserOrderReqVO.getUserDTO();
        Long soId = soDTO.getId();
        List<EnterpriseBizConfigDTO> bizConfigDTOList = pushUserOrderReqVO.getBizConfigDTOList();
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
        pushOrder(bizConfigDTOList, requestVO,channelServiceConfigClient);
        return null;
    }


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

    private static String getFeeNum(BigDecimal deliveryFee, String replaceString){
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
            if(EmptyUtil.isNotEmpty(childDTO.getSource()) && Objects.equals(childDTO.getSource(), ThirdConst.Source.JD)){
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

    public static String getProductName(String feeNum){

        return PRODUCT_NAME+feeNum;
    }

    public static String getProductId(String feeNum){

        return PRODUCT_ID_+feeNum;
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

    private  List<PushOrderProductVO> soItemToPushOrderProductVO(List<SoItemDTO> soItemDTOS){
        List<PushOrderProductVO> productVOS = new ArrayList<>(soItemDTOS.size());
        for (SoItemDTO soItemDTO : soItemDTOS) {
            productVOS.add(new PushOrderProductVO(soItemDTO));
        }
        return productVOS;
    }
}
