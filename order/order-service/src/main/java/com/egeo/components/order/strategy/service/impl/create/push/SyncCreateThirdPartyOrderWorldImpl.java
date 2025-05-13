package com.egeo.components.order.strategy.service.impl.create.push;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.egeo.components.config.dto.CompanyConfigDTO;
import com.egeo.components.order.business.WorldBuyOrderManage;
import com.egeo.components.order.client.SoItemClient;
import com.egeo.components.order.common.ProductChannelCodeEnum;
import com.egeo.components.order.dto.*;
import com.egeo.components.order.facade.ProductFacade;
import com.egeo.components.order.service.read.SoChildReadService;
import com.egeo.components.order.service.read.SoThirdpartyReadService;
import com.egeo.components.order.service.write.SoChildWriteService;
import com.egeo.components.order.service.write.SoThirdpartyWriteService;
import com.egeo.components.order.service.write.SoWriteService;
import com.egeo.components.order.strategy.vo.SyncCreateThirdPartyOrderReqVO;
import com.egeo.components.order.strategy.vo.SyncCreateThirdPartyOrderRespVO;
import com.egeo.components.product.dto.channel.ChannelProductBatchDTO;
import com.egeo.components.product.dto.channel.ChannelProductSkuDTO;
import com.egeo.components.product.vo.ChannelProductDetailVO;
import com.egeo.components.user.client.UserClient;
import com.egeo.components.user.dto.UserDTO;
import com.egeo.components.utils.WorldBuyUtil;
import com.egeo.exception.BusinessException;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.StringUtils;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.web.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @Description 创建（推送给）全球购订单
 * @Author lsl
 * @Date 2024/12/6 13:50
 * @Version V1.0
 **/
@Service("syncCreateThirdPartyOrderWorldImpl")
public class SyncCreateThirdPartyOrderWorldImpl extends SyncCreateThirdPartyOrderBaseImpl{


    public Logger logger = LoggerFactory.getLogger(this.getClass().getName());


    @Resource
    private SoThirdpartyWriteService soThirdpartyWriteService;

    @Resource
    private SoChildReadService soChildReadService;

    @Resource
    private SoChildWriteService soChildWriteService;

    @Resource
    private SoWriteService soWriteService;

    @Resource
    private SoThirdpartyReadService soThirdpartyReadService;

    @Resource
    private JedisUtil jedisUtil;

    @Resource
    private WorldBuyUtil worldBuyUtil;

    @Autowired
    private WorldBuyOrderManage worldBuyOrderManage;

    @Resource(name = "productFacade")
    private ProductFacade productFacade;

    @Autowired
    private SoItemClient soItemReadService;

    @Autowired
    private UserClient userReadService;

    @Override
    public String getChannelCode() {
        return ProductChannelCodeEnum.WORLD_BUY.getCode();
    }

    @Override
    public JsonResult<SyncCreateThirdPartyOrderRespVO> syncThirdPartyOrder(SyncCreateThirdPartyOrderReqVO reqVO) {

        SoChildDTO soChildDTO = reqVO.getSoChildDTO();
        ReceiverAddressDTO addr = reqVO.getAddr();
        SoDTO sodto = reqVO.getSodto();
        Long orderId = reqVO.getOrderId();
        Long userId = reqVO.getUserId();
        SoChildDTO soChildDTO1 = soChildReadService.querySoChildByChildCode(soChildDTO.getChildCode());
        try {
            JSONObject jsonObject  = buildWorldOrderParam(orderId,soChildDTO1,addr,null,null,userId);
            logger.info("提交全球购订单请求参数:{}", JSON.toJSONString(jsonObject));
            JSONObject submitOrderResult  = worldBuyOrderManage.createOrder(jsonObject);
            logger.info("提交全球购订单请求结果:{}",submitOrderResult !=null?JSON.toJSONString(submitOrderResult):"响应为空");
            JsonResult checkResult = worldBuyUtil.checkResult(submitOrderResult);
            //检查订单是否成功，是否需要渠道订单
            if(Objects.nonNull(checkResult)){
                return JsonResult.fail("当前订单存在全球购专卖子订单,订单下单失败"+checkResult.getError());
            }
            String dataJson = submitOrderResult.getString(worldBuyUtil.DATA_KEY);
            JSONObject submitRT = submitOrderResult.getJSONObject(worldBuyUtil.DATA_KEY);
            if(EmptyUtil.isEmpty(dataJson)|| Objects.isNull(submitRT)){
                return JsonResult.fail("当前订单存在全球购专卖子订单,订单下单失败,返回无数据");
            }
            submitWorldOrderSuccess(sodto, null, orderId, soChildDTO1, submitOrderResult);
        }catch (Exception e){
            e.printStackTrace();
            logger.info("创建订单时-订单{},子订单号:{}推送订单至全球购时异常:{}",sodto.getOrderCode(),soChildDTO.getChildCode(),e);
            return JsonResult.fail("推送订单至全球购时发生异常");
        }

        return JsonResult.success();
    }

    private void submitWorldOrderSuccess(SoDTO sodto, SoThirdpartyDTO soThirdpartyDTO, Long orderId, SoChildDTO soChildDTO, JSONObject submitOrderResult) {
        JsonResult checkResult = worldBuyUtil.checkResult(submitOrderResult);
        if(Objects.nonNull(checkResult)){
            throw new BusinessException("当前订单存在全球购子订单,订单下单失败,该订单已取消"+JSON.toJSONString(submitOrderResult));
        }
        String dataJson = submitOrderResult.getString(worldBuyUtil.DATA_KEY);
        if(StringUtils.isEmpty(dataJson)){
            throw new BusinessException("当前订单存在全球购子订单,订单下单失败,该订单已取消"+JSON.toJSONString(submitOrderResult));
        }
        WorldBuySubmitOrderResultDTO worldBuySubmitOrderResultDTO = JSONObject.parseObject(dataJson,WorldBuySubmitOrderResultDTO.class);
        WorldOrderItemsResponseDTO worldOrderItemsResponseDTO = worldBuySubmitOrderResultDTO.getOrderItems().get(0);
        String cakeOrderId = worldOrderItemsResponseDTO.getOrderNo();
        soChildDTO.setThirdpartySoChildId(Long.valueOf(cakeOrderId));
        //更新运费价格
        BigDecimal oldFree = soChildDTO.getOrdinaryDeliveryFee()==null?BigDecimal.ZERO:soChildDTO.getOrdinaryDeliveryFee();

        logger.info("全球购订单进行下单成功->"+ JSON.toJSONString(worldBuySubmitOrderResultDTO));
        BigDecimal freight = new BigDecimal(worldOrderItemsResponseDTO.getTotalTemplateDelivery()).setScale(2);
        BigDecimal orderAmount= new BigDecimal(worldOrderItemsResponseDTO.getRealTotalMoney()).setScale(2);

        soChildDTO.setThirdpartySoChildPayAmount(orderAmount);
        soChildDTO.setThirdpartySoChildStatus(Integer.valueOf(1));
        soChildWriteService.updateSoChildByCodeWithTx(soChildDTO);

        SoThirdpartyDTO oldSoThirdpartyDTO = soThirdpartyReadService.findSoThirdpartyByChild(soChildDTO.getId(),soChildDTO.getChildCode());
        if(null !=oldSoThirdpartyDTO){
            SoThirdpartyDTO editSoThirdpartyDTO = new SoThirdpartyDTO();
            editSoThirdpartyDTO.setSoChildCode(soChildDTO.getChildCode());
            editSoThirdpartyDTO.setThirdpartyId(cakeOrderId);
            editSoThirdpartyDTO.setThirdpartyPayAmount(orderAmount);
            editSoThirdpartyDTO.setThirdpartyPayTime(new Date());
            soThirdpartyWriteService.updateSoThirdpartyByCodeWithTx(editSoThirdpartyDTO);
        }
    }


    private JSONObject buildWorldOrderParam(Long orderId, SoChildDTO soChildDTO, ReceiverAddressDTO addr,String spuId,String skuId,Long userId) {
        UserDTO userDTO = userReadService.findUserByID(userId);
        String phone = (StringUtils.isEmpty(addr.getGoodReceiverPhone()) || StringUtils.isBlank(addr.getGoodReceiverPhone()))?addr.getGoodReceiverMobile():addr.getGoodReceiverPhone();
        JSONObject rtObj = new JSONObject();
        rtObj.put("orderSn",soChildDTO.getChildCode());
        rtObj.put("buyerPhone",userDTO.getMobile());
        rtObj.put("buyerRealName",userDTO.getName());
        rtObj.put("buyerIdCard",userDTO.getIdCardNo());
        rtObj.put("receiveAddress",addr.getGoodReceiverAddress());
        rtObj.put("receiveName",addr.getGoodReceiverName());
        rtObj.put("recveivePhone",phone);
        rtObj.put("province",addr.getGoodReceiverProvince());
        rtObj.put("city",addr.getGoodReceiverCity());
        rtObj.put("district",addr.getGoodReceiverCounty());
        rtObj.put("orderRemarks",soChildDTO.getRemark());
        rtObj.put("orderSource","大厨管家（上海)网络科技有限公司");
        rtObj.put("isOrderDeclare","0");
        rtObj.put("isPayDeclare","0");
        rtObj.put("discountMoney","0");
        rtObj.put("freight","0");
        JSONArray jsonArray = new JSONArray();
        BigDecimal orderAmount = addGoodsInfoToList(jsonArray,soChildDTO);
        rtObj.put("goods",jsonArray);
        rtObj.put("orderMoney",orderAmount);
        return rtObj;
    }

    private BigDecimal addGoodsInfoToList(JSONArray jsonArray,SoChildDTO soChildDTO){
        List<SoItemDTO> soItemDTOS = soItemReadService.findSoItemsBySoChild(soChildDTO.getId());
        BigDecimal totalAmt = BigDecimal.ZERO;
        for (SoItemDTO soItemDTO : soItemDTOS) {
            JSONObject goodObj = new JSONObject();
            ChannelProductDetailVO vo = getChannelProductDetailVO(soItemDTO);
            List<ChannelProductSkuDTO> skuList = vo.getSkuList();
            List<ChannelProductBatchDTO>  batchDTOList = vo.getBatchDTOList();
            ChannelProductBatchDTO batchDTO = productFacade.getCurrBatch(soItemDTO.getPuId()+"",batchDTOList);
            ChannelProductSkuDTO skuDTO = productFacade.getCurrSkuInfo(batchDTO.getLinkSkuId(),skuList);
            BigDecimal batchAmount = batchDTO.getPriceSettleMent().multiply(new BigDecimal(soItemDTO.getPuCount())).setScale(2);
            totalAmt = totalAmt.add(batchAmount);
            goodObj.put("goodsSku",skuDTO.getSkuCode());
            goodObj.put("goodsNum",soItemDTO.getPuCount());
            goodObj.put("goodsBatchNo",batchDTO.getBatchNo());
            goodObj.put("goodsSpecNum",batchDTO.getSpecNum());
            goodObj.put("goodsPrice", batchDTO.getPriceSettleMent());
            goodObj.put("startExpDate","");
            goodObj.put("endExpDate","");
            jsonArray.add(goodObj);
        }

        return totalAmt;
    }

    private ChannelProductDetailVO getChannelProductDetailVO(SoItemDTO soItemDTO){
        if(EmptyUtil.isNotEmpty(soItemDTO.getSnapshot())){
            return JSONObject.parseObject(soItemDTO.getSnapshot(),ChannelProductDetailVO.class);
        }
        return productFacade.findWorldProduct(soItemDTO.getExternalProductId(),soItemDTO.getPuId()+"");
    }
}
