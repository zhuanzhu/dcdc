package com.egeo.components.order.strategy.service.impl.create.split;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.egeo.components.order.business.CakeManage;
import com.egeo.components.order.common.ProductChannelCodeEnum;
import com.egeo.components.order.dto.CakeAddResultDTO;
import com.egeo.components.order.dto.ReceiverAddressDTO;
import com.egeo.components.order.dto.SoChildDTO;
import com.egeo.components.order.dto.SoItemDTO;
import com.egeo.components.order.enums.ThirdConst;
import com.egeo.components.order.manage.write.CakeAddressWriteManage;
import com.egeo.components.order.strategy.vo.SplitSoChildReqVO;
import com.egeo.components.order.strategy.vo.SplitSoChildRespVO;
import com.egeo.components.utils.CakeAddressUtil;
import com.egeo.components.utils.CakeUtil;
import com.egeo.exception.BusinessException;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.web.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/12/6 1:53
 * @Version V1.0
 **/
@Service("splitSoChildCakeImpl")
public class SplitSoChildCakeImpl extends SplitSoChildCommonBase{
    public Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private CakeAddressWriteManage cakeAddressWriteManage;
    @Resource
    private CakeUtil cakeUtil;
    @Resource
    private CakeManage cakeManage;

    @Autowired
    private JedisUtil jedisUtil;

    @Override
    public String getChannelCode() {
        return ProductChannelCodeEnum.CAKE.getCode();
    }

    @Override
    public JsonResult<SplitSoChildRespVO> splitSoChild(SplitSoChildReqVO reqVO) {
        Map<String, String> CakeChildCodeMap = new HashMap<>();
        List<SoChildDTO> soChildDTOList = new ArrayList<>();
        ReceiverAddressDTO addr = reqVO.getAddr();
        List<SoItemDTO> soItems = reqVO.getSoItems();
        String orderCode= reqVO.getOrderCode();
        int m = reqVO.getM();
        int n = reqVO.getN();
        Long mId = reqVO.getmId();
        JSONObject remarkObj = reqVO.getRemarkObj();
        //蛋糕叔叔拆单
        //获取到城市id
        String cityId = cakeAddressWriteManage.getCityId(CakeAddressUtil.getCityName(addr));
        //获取到蛋糕叔叔的用户id
        String cakeUserId = cakeManage.getCakeUserId();
        //获取到地址id
        CakeAddResultDTO dto = cakeAddressWriteManage.addOrEditCakeAddress(addr,cakeUserId);
        if(dto == null){
            logger.error("蛋糕叔叔第三方新增地址失败");
            return JsonResult.fail("第三方新增地址失败");
        }
        List<SoItemDTO> cakeItems = new ArrayList<>();
        Map<String,Object> productTotalMap = new HashMap<>();
        List<Map<String,Object>> mapList = new ArrayList<>();
        for (SoItemDTO soItemDTO : soItems) {
            if(Objects.equals(soItemDTO.getMerchantId(), ThirdConst.Merchant.CAKE)){
                //默认为0
                soItemDTO.setRuleId("0");
                cakeItems.add(soItemDTO);
                Map<String,Object> productMap = new HashMap<>();
                productMap.put("product_id",soItemDTO.getExternalProductId());
                productMap.put("city_id",cityId);
                mapList.add(productMap);
            }
        }
        productTotalMap.put("product",mapList);
        JSONObject jsonObject = cakeUtil.getRuleIds(productTotalMap);
        JsonResult jsonResult = cakeUtil.checkResult(jsonObject);
        if(Objects.nonNull(jsonResult)){
            throw new BusinessException(jsonResult.getError());
        }
        JSONArray dataJsonArr = jsonObject.getJSONArray(cakeUtil.DATA_KEY);
        Map<String,String> ruleMap = new HashMap<>();
        List<String> ruleIds = new ArrayList<>();
        if(dataJsonArr !=null && dataJsonArr.size()>0){
            for (int i = 0; i < dataJsonArr.size(); i++) {
                JSONObject dataJson = dataJsonArr.getJSONObject(i);
                ruleMap.put(dataJson.getString("product_id"),dataJson.getString("distribution_rule_id"));
                if(!ruleIds.contains(dataJson.getString("distribution_rule_id"))){
                    ruleIds.add(dataJson.getString("distribution_rule_id"));
                }
            }
            for (SoItemDTO soItemDTO : soItems) {
                if(soItemDTO.getMerchantId() ==7L){
                    //设置查询到的规则id
                    soItemDTO.setRuleId(ruleMap.get(soItemDTO.getExternalProductId()));
                }
            }
        }else{
            logger.info("订单号{}，蛋糕叔叔兼容规则id为0的情况",orderCode);
            ruleIds.add("0");
        }

        Map<String,List<SoItemDTO>> ruleGroupMap = new HashMap<>();
        for (SoItemDTO soItemDTO : soItems) {
            if(Objects.equals(soItemDTO.getMerchantId(), ThirdConst.Merchant.CAKE)){
                if(ruleGroupMap.containsKey(soItemDTO.getRuleId()) || ruleGroupMap.get(soItemDTO.getRuleId()) == null){
                    ruleGroupMap.put(soItemDTO.getRuleId(),new ArrayList<>());
                }
                ruleGroupMap.get(soItemDTO.getRuleId()).add(soItemDTO);
            }
        }
        logger.info("蛋糕叔叔订单号{}拆单规则ids:{}",orderCode,JSON.toJSONString(ruleIds));
        for (String ruleId : ruleIds) {
            SoChildDTO sc = new SoChildDTO();
            String childCode = orderCode + "-" + n+"-C-"+ruleId;
            sc.setChildCode(childCode);
            sc.setPerformingParty(mId);
            sc.setRemark(EmptyUtil.isEmpty(remarkObj) ? null : remarkObj.getString(String.valueOf(mId)));
            sc.setOrdinaryDeliveryFee(BigDecimal.ZERO);
            sc.setNeedCountDeliveryFee(1);
            List<SoItemDTO> ruleItems =  ruleGroupMap.get(ruleId);
            BigDecimal delivery_amount = BigDecimal.ZERO;
            StringBuffer spceIds = new StringBuffer();
            StringBuffer quantitys = new StringBuffer();
            for (SoItemDTO itemDTO : ruleItems) {
                spceIds.append(itemDTO.getPuId()).append(",");
                quantitys.append(itemDTO.getPuCount()).append(",");
            }
            spceIds.deleteCharAt(spceIds.length() - 1);
            quantitys.deleteCharAt(quantitys.length() - 1);
            JSONObject jsonRuleObject = cakeUtil.getDistributionRules(cityId,dto.getId(),spceIds.toString(),quantitys.toString());
            JsonResult jsonRuleResult = cakeUtil.checkResult(jsonRuleObject);
            if(Objects.nonNull(jsonRuleResult)){
                logger.info("获取蛋糕专卖三方（规则）运费结果失败："+JSON.toJSONString(jsonRuleObject));
                return JsonResult.fail("获取运费时第三方运费规则失败");
            }
            JSONObject data = jsonRuleObject.getJSONObject(cakeUtil.DATA_KEY);
            if(data ==null){
                logger.info("获取蛋糕专卖三方（规则）运费结果数据为空"+JSON.toJSONString(jsonRuleObject));
                return JsonResult.fail("获取运费时第三方运费规则失败");
            }
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

            if("1".equals(data.getString("can_same")) && validate_same_row !=null && validate_same_row.containsKey("delivery_amount") && validate_same_row.get("delivery_amount") !=null){
                delivery_amount = delivery_amount.add(validate_same_row.getBigDecimal("delivery_amount"));
            }else if(data.getString("can_ship").equals("1")){
                JSONArray validate_delivery_datesArr = data.getJSONArray("validate_delivery_dates");
                if(validate_delivery_datesArr !=null && validate_delivery_datesArr.size() >0){
                    JSONObject lastRecord = validate_delivery_datesArr.getJSONObject(0);
                    delivery_amount = delivery_amount.add(lastRecord.getBigDecimal("delivery_amount"));

                }
            }

            sc.setOrdinaryDeliveryFee(delivery_amount);
            sc.setDeliveryFee(delivery_amount);
            CakeChildCodeMap.put(ruleId, childCode);
            soChildDTOList.add(sc);
            setRedisCache(childCode,data);
        }
        for (SoItemDTO soItemDTO : soItems) {
            if(Objects.equals(soItemDTO.getMerchantId(),ThirdConst.Merchant.CAKE)) {
                soItemDTO.setChildCode(CakeChildCodeMap.get(soItemDTO.getRuleId()));
            }
        }
        SplitSoChildRespVO splitSoChildRespVO = new SplitSoChildRespVO();
        splitSoChildRespVO.setSoChildDTOList(soChildDTOList);
        splitSoChildRespVO.setmId(mId);
        return JsonResult.success(splitSoChildRespVO);
    }

    private void setRedisCache(String childCode,JSONObject data){
        try {
            jedisUtil.set(childCode,30,data);
        }catch (Exception e){
            logger.error("设置蛋糕叔叔子订单运费相关规则发生异常:{}",e);
        }
    }
}
