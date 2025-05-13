package com.egeo.components.order.strategy.service.impl.merchant;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.egeo.components.order.business.CakeManage;
import com.egeo.components.order.common.ProductChannelCodeEnum;
import com.egeo.components.order.dto.CakeAddResultDTO;
import com.egeo.components.order.dto.OrderConfirmGoodsDTO;
import com.egeo.components.order.dto.OrderResult;
import com.egeo.components.order.dto.ReceiverAddressDTO;
import com.egeo.components.order.facade.ReceiverAddressFacade;
import com.egeo.components.order.manage.write.CakeAddressWriteManage;
import com.egeo.components.order.strategy.vo.OrderConfirmChannelDeliveryReqVO;
import com.egeo.components.order.vo.DefaultReceiverInfoVo;
import com.egeo.components.order.vo.cake.CakeRuleIdsVO;
import com.egeo.components.order.vo.cake.ChannelSkuInfoVO;
import com.egeo.components.order.vo.jd.SkuInfo;
import com.egeo.components.utils.CakeAddressUtil;
import com.egeo.components.utils.CakeUtil;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/12/1 21:53
 * @Version V1.0
 **/
@Service("orderConfirmCakeDeliveryImpl")
public class OrderConfirmCakeDeliveryImpl extends OrderConfirmChannelDeliveryBase{
    public Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Resource
    private CakeUtil cakeUtil;

    @Autowired
    private CakeAddressWriteManage cakeAddressWriteManage;

    @Resource(name = "receiverAddressFacade")
    private ReceiverAddressFacade receiverAddressFacade;

    @Resource
    private CakeManage cakeManage;

    @Override
    public String getChannelCode() {
        return ProductChannelCodeEnum.CAKE.getCode();
    }

    @Override
    public Map<String, Object> getDelivery(OrderConfirmChannelDeliveryReqVO reqVO) {
        OrderResult orderResult = reqVO.getOrderResult();
        //如果是蛋糕叔叔运营方则通过接口调用获取蛋糕叔叔的运费
        List<OrderConfirmGoodsDTO> goodsListDto = orderResult.getGoodsList();
        List<ChannelSkuInfoVO> channelSkuInfoVOList = new ArrayList<>();
        for(OrderConfirmGoodsDTO goodsDTO:goodsListDto){
            Integer skuNum = goodsDTO.getNum();
            ChannelSkuInfoVO channelSkuInfoVO = new ChannelSkuInfoVO();
            channelSkuInfoVO.setProductId(goodsDTO.getExternalProductId());
            channelSkuInfoVO.setSkuId(goodsDTO.getExternalSkuId());
            channelSkuInfoVO.setNum(skuNum);
            channelSkuInfoVO.setDistribution_rule_id(goodsDTO.getDistribution_rule_id());
            channelSkuInfoVOList.add(channelSkuInfoVO);
        }
        return buildDeliveryPriceFromCake(channelSkuInfoVOList,reqVO.getReceiverInfo());
    }

    private Map<String, Object> buildDeliveryPriceFromCake(List<ChannelSkuInfoVO> skuInfoList, DefaultReceiverInfoVo receiverInfo) {
        Map<String, Object> map = new HashMap<>();
        map.put("success", "-1");
        String userId = cakeManage.getCakeUserId();
        if(EmptyUtil.isEmpty(userId)){
            logger.error("获取运费时登录三方用户发生失败");
            map.put("jdDeliveryError", "获取运费时第三方用户发生失败");
            return map;
        }

        CakeAddResultDTO dto = null;
        ReceiverAddressDTO addr  = receiverAddressFacade.findReceiverAddressById(receiverInfo.getId());
        String cityId =null;
        try {
            dto = cakeAddressWriteManage.addOrEditCakeAddress(addr,userId);
            cityId = cakeAddressWriteManage.getCityId(CakeAddressUtil.getCityName(addr));
        }catch (Exception e){
            logger.error("获取运费时请求第三方地址失败{}",e);
            map.put("jdDeliveryError", "获取运费时请求第三方地址失败"+e.getMessage());
            return map;
        }

        List<Map<String,Object>> mapList = new ArrayList<>();
        for (ChannelSkuInfoVO skuInfo : skuInfoList) {
            Map<String,Object> productMap = new HashMap<>();
            productMap.put("product_id",skuInfo.getProductId());
            productMap.put("city_id",cityId);
            mapList.add(productMap);
        }
        Map<String,Object> productTotalMap = new HashMap<>();
        productTotalMap.put("product",mapList);
        JSONObject ruleIdsJSONObject = cakeUtil.getRuleIds(productTotalMap);
        logger.info("批量获取规则id结果:{}",JSON.toJSONString(ruleIdsJSONObject));
        JsonResult ruleIdsResult = cakeUtil.checkResult(ruleIdsJSONObject);
        if(Objects.nonNull(ruleIdsResult)){
            map.put("jdDeliveryError", "获取批量规则id规则失败"+ruleIdsResult.getError());
            return map;
        }
        JSONArray jsonArray = ruleIdsJSONObject.getJSONArray("data");
        List<CakeRuleIdsVO> cakeRuleIdsVOS = new ArrayList<>();
        Map<String,String> productRuleMap = new HashMap<>();
        // 遍历JSON数组并转换成CakeRuleIdsVO对象
        if(jsonArray !=null && jsonArray.size() >0){
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject dataObject = jsonArray.getJSONObject(i);
                productRuleMap.put(dataObject.getString("product_id"),dataObject.getString("distribution_rule_id"));
            }

            for (ChannelSkuInfoVO skuInfo : skuInfoList) {
                if(productRuleMap.containsKey(skuInfo.getProductId())){
                    skuInfo.setDistribution_rule_id(productRuleMap.get(skuInfo.getProductId()));
                }
            }
        }


        logger.info("set完规则后的sku信息：{}",JSON.toJSONString(skuInfoList));
        BigDecimal delivery_amount = BigDecimal.ZERO;
        String delivery_text = "";
        for (ChannelSkuInfoVO channelSkuInfoVO : skuInfoList) {
            if(EmptyUtil.isEmpty(channelSkuInfoVO.getDistribution_rule_id())){
                channelSkuInfoVO.setDistribution_rule_id("0");
            }
        }
        Map<String, List<ChannelSkuInfoVO>> groupedByRuleIdSkuMAP = skuInfoList.stream()
                .collect(Collectors.groupingBy(ChannelSkuInfoVO::getDistribution_rule_id));
        for (Map.Entry<String, List<ChannelSkuInfoVO>> stringListEntry : groupedByRuleIdSkuMAP.entrySet()) {
            List<ChannelSkuInfoVO> listEntryValue = stringListEntry.getValue();
            StringBuffer spceIds = new StringBuffer();
            StringBuffer quantitys = new StringBuffer();
            for (ChannelSkuInfoVO skuInfo : listEntryValue) {
                spceIds.append(skuInfo.getSkuId()).append(",");
                quantitys.append(skuInfo.getNum()).append(",");
            }
            spceIds.deleteCharAt(spceIds.length() - 1);
            quantitys.deleteCharAt(quantitys.length() - 1);
            JSONObject jsonObject = cakeUtil.getDistributionRules(cityId,dto.getId(),spceIds.toString(),quantitys.toString());
            JsonResult jsonResult = cakeUtil.checkResult(jsonObject);
            if(Objects.nonNull(jsonResult)){
                map.put("jdDeliveryError", "获取运费时第三方运费规则失败"+jsonResult.getError());
                return map;
            }
            logger.info("获取蛋糕专卖三方（规则）运费结果"+JSON.toJSONString(jsonObject));
            JSONObject data = jsonObject.getJSONObject(cakeUtil.DATA_KEY);
            JSONObject validate_same_row = null;
            if("1".equals(data.getString("can_same"))){
                try {
                    validate_same_row = data.getJSONObject("validate_same_row");
                }catch (Exception e){
                    JSONArray jsonArray1 = data.getJSONArray("validate_same_row");
                    if(jsonArray1 !=null && jsonArray1.size()>0){
                        validate_same_row = jsonArray1.getJSONObject(0);
                    }
                }
            }

            boolean validateRule = false;
            String currText ="";
            if("1".equals(data.getString("can_same")) && validate_same_row !=null && validate_same_row.containsKey("delivery_amount") && validate_same_row.get("delivery_amount") !=null){
                validateRule = true;
                delivery_amount = delivery_amount.add(validate_same_row.getBigDecimal("delivery_amount"));
                currText = data.containsKey("delivery_text")?data.getString("delivery_text"):"";
            }else if(data.getString("can_ship").equals("1")){
                validateRule = true;
                JSONArray validate_delivery_datesArr = data.getJSONArray("validate_delivery_dates");
                if(validate_delivery_datesArr !=null && validate_delivery_datesArr.size() >0){
                    JSONObject lastRecord = validate_delivery_datesArr.getJSONObject(0);
                    delivery_amount = delivery_amount.add(lastRecord.getBigDecimal("delivery_amount"));
                    String dateStr = lastRecord.getString("date");
                    JSONArray times = lastRecord.getJSONArray("validate_delivery_times");
                    if(times !=null && times.size() >0){
                        String fristTime = times.getString(0);
                        dateStr = dateStr+" "+fristTime;
                    }
                    currText =dateStr;
                }
            }

            if(!validateRule){
                map.put("jdDeliveryError", "获取运费时第三方规则失败");
                logger.info("蛋糕叔叔获取运费时validateRule=false");
                return map;
            }

            if(EmptyUtil.isNotEmpty(delivery_text)){
                delivery_text = delivery_text+","+currText;
            }else{
                delivery_text = currText;
            }

        }
        map.put("success", "1");
        map.put("deliveryPrice",delivery_amount.toPlainString());
        map.put("deliveryText",delivery_text);
        logger.info("蛋糕叔叔最终获取运费为:{}",JSON.toJSONString(map));
        return map;
    }
}
