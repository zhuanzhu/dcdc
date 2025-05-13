package com.egeo.components.order.strategy.service.impl.create.push;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.egeo.components.order.business.CakeManage;
import com.egeo.components.order.client.SoItemClient;
import com.egeo.components.order.common.ProductChannelCodeEnum;
import com.egeo.components.order.dto.*;
import com.egeo.components.order.facade.ProductFacade;
import com.egeo.components.order.manage.write.CakeAddressWriteManage;
import com.egeo.components.order.service.read.SoChildReadService;
import com.egeo.components.order.service.read.SoThirdpartyReadService;
import com.egeo.components.order.service.write.SoChildWriteService;
import com.egeo.components.order.service.write.SoThirdpartyWriteService;
import com.egeo.components.order.service.write.SoWriteService;
import com.egeo.components.order.strategy.vo.SyncCreateThirdPartyOrderReqVO;
import com.egeo.components.order.strategy.vo.SyncCreateThirdPartyOrderRespVO;
import com.egeo.components.user.client.UserClient;
import com.egeo.components.utils.CakeAddressUtil;
import com.egeo.components.utils.CakeUtil;
import com.egeo.components.utils.JsonUtils;
import com.egeo.exception.BusinessException;
import com.egeo.utils.DateUtils;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.StringUtils;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.web.JsonResult;
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
 * @Description 创建（推送给）蛋糕叔叔订单
 * @Author lsl
 * @Date 2024/12/6 13:48
 * @Version V1.0
 **/
@Service("syncCreateThirdPartyOrderCakeImpl")
public class SyncCreateThirdPartyOrderCakeImpl extends SyncCreateThirdPartyOrderBaseImpl{

    public Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Resource
    private SoThirdpartyWriteService soThirdpartyWriteService;

    @Resource
    private SoChildReadService soChildReadService;

    @Resource
    private SoChildWriteService soChildWriteService;

    @Resource
    private SoThirdpartyReadService soThirdpartyReadService;

    @Resource
    private JedisUtil jedisUtil;

    @Resource
    private CakeUtil cakeUtil;

    @Resource(name = "productFacade")
    private ProductFacade productFacade;

    @Autowired
    private SoItemClient soItemReadService;


    @Resource
    private CakeManage cakeManage;

    @Autowired
    private CakeAddressWriteManage cakeAddressWriteManage;

    @Override
    public String getChannelCode() {
        return ProductChannelCodeEnum.CAKE.getCode();
    }

    @Override
    public JsonResult<SyncCreateThirdPartyOrderRespVO> syncThirdPartyOrder(SyncCreateThirdPartyOrderReqVO reqVO) {
        SoChildDTO soChildDTO = reqVO.getSoChildDTO();
        ReceiverAddressDTO addr = reqVO.getAddr();

        SoDTO sodto = reqVO.getSodto();
        Long orderId = reqVO.getOrderId();
        SoChildDTO soChildDTO1 = soChildReadService.querySoChildByChildCode(soChildDTO.getChildCode());
        try {
            String reqData = buildCakeOrderParam(orderId,soChildDTO,addr,null,null);
            JSONObject submitOrderResult = cakeUtil.submitOrder(reqData);
            logger.info("母订单id:{},子订单code:{}提交创建蛋糕叔叔订单请求结果:{}",orderId,soChildDTO.getChildCode(),submitOrderResult !=null? JSON.toJSONString(submitOrderResult):"响应为空");
            //若是订单取消会抛出异常，不用担心往下继续执行订单提交成功的流程
            JsonResult checkResult = cakeUtil.checkResult(submitOrderResult);
            //检查订单是否成功，是否需要渠道订单
            if(Objects.nonNull(checkResult)){
                throw new BusinessException("当前订单存在蛋糕叔叔子订单,订单下单失败"+checkResult.getError()+",该订单已取消");
            }
            JSONObject submitRT = submitOrderResult.getJSONObject(cakeUtil.DATA_KEY);
            if(Objects.isNull(submitRT)){
                return JsonResult.fail("当前订单存在蛋糕叔叔子订单,订单下单失败,返回无数据");
            }
            //若未取消则订单提交成功
            submitCakeOrderSuccess(sodto, null, orderId, soChildDTO1, submitOrderResult);
        }catch (Exception e){
           // cancelOrderJd(sodto, userId, ip, userName, mac, req);
            e.printStackTrace();
            logger.info("订单{},子订单号:{}推送蛋糕叔叔时异常:{}",sodto.getOrderCode(),soChildDTO.getChildCode(),e);
            return JsonResult.fail("提交订单至蛋糕叔叔时异常");
        }
        return JsonResult.success();
    }


    private String buildCakeOrderParam(Long orderId, SoChildDTO soChildDTO, ReceiverAddressDTO addr,String spuId,String skuId) {
        JSONObject rtObj = new JSONObject();
        String phone = (StringUtils.isEmpty(addr.getGoodReceiverPhone()) || StringUtils.isBlank(addr.getGoodReceiverPhone()))?addr.getGoodReceiverMobile():addr.getGoodReceiverPhone();
        //预存信息
        putPreInfo(rtObj,soChildDTO,phone);
        //蛋糕叔叔用户id
        putUserInfo(rtObj);
        //蛋糕叔叔城市id
        putCityId(rtObj,addr);
        //蛋糕叔叔地址id以及其对应的信息
        putAddr(rtObj,addr);
        //蛋糕叔叔规则id
        putRuteInfo(rtObj,soChildDTO);
        return JSON.toJSONString(rtObj);
    }



    private void putOtherInfo(JSONObject rtObj,SoChildDTO soChildDTO){

    }

    private void putRuteInfo(JSONObject rtObj,SoChildDTO soChildDTO){
        String city_id =  rtObj.getString("city_id");
        SoChildDTO soChildDTO1 = soChildReadService.querySoChildByChildCode(soChildDTO.getChildCode());

        String ruleIds = getRuleId(rtObj, city_id, soChildDTO1);
        rtObj.put("rule_ids",ruleIds);

        setDistributionRules(rtObj, city_id, ruleIds,soChildDTO.getChildCode());
    }

    private void setDistributionRules(JSONObject rtObj, String city_id, String ruleIds,String childCode) {
        JSONObject rulesObject = getRulesObjectByCache(childCode);
        rulesObject = ifNullGetRemote(rtObj, city_id, rulesObject,childCode);
        //是否可配送 0不可配送、1可配送
        String is_distribution = rulesObject.getString("is_distribution");
        //是否支持快递配送 1-支持,0-不支持
        String can_same = rulesObject.getString("can_same");
        String can_ship = rulesObject.getString("can_ship");
//		if(Objects.equals(is_distribution,"0") || Objects.equals(can_same,"0")){
//			throw new BusinessException("提交蛋糕叔叔订单时存在不支持快递配送的商品"+JSON.toJSONString(rulesObject));
//		}
        if(Objects.equals(is_distribution,"0")){
            throw new BusinessException("提交蛋糕叔叔订单时存在不支持快递或配送的商品");
        }
        if(Objects.equals(can_same,"0") && Objects.equals(can_ship,"0")){
            throw new BusinessException("提交蛋糕叔叔订单时存在不支持快递或配送的商品");
        }
        JSONArray jsonArray = rulesObject.getJSONArray("validate_delivery_dates");
        Object ship_date = DateUtils.getDate();
        String ship_type = "same";
        String delivery_text =rulesObject.containsKey("delivery_text")?rulesObject.getString("delivery_text"):"";
        JSONObject groupChild = new JSONObject();
        if(Objects.equals(can_same,"1")){
            ship_date = false;
        }else if(Objects.equals(can_ship,"1")){
            //商品配送
            ship_type = "delivery";
            if(jsonArray !=null && jsonArray.size()>0){
                JSONObject deliveryJson = jsonArray.getJSONObject(0);
                ship_date=deliveryJson.getString("date");
                JSONArray validate_delivery_times = deliveryJson.getJSONArray("validate_delivery_times");
                if(validate_delivery_times !=null && validate_delivery_times.size() >0){
                    delivery_text =  validate_delivery_times.getString(validate_delivery_times.size()-1);
                }
            }
        }

        JSONObject group = new JSONObject();
        List<String> list = Arrays.asList(ruleIds.split(","));
        groupChild.put("ship_type",ship_type);
        groupChild.put("ship_date", ship_date);
        groupChild.put("ship_time_text",delivery_text);
        for (String s : list) {
            group.put(s,groupChild);
        }
        rtObj.put("group",group);
    }

    private JSONObject ifNullGetRemote(JSONObject rtObj, String city_id, JSONObject rulesObject,String childCode) {
        if(rulesObject !=null){
            logger.info("子订单{}从缓存中获取是否获取到配送规则,无需再次请求接口", childCode);
            return rulesObject;
        }
        //配送规则接口（最新版）
        String specIds =  rtObj.getString("spec_ids");
        String quantitys =  rtObj.getString("quantitys");
        String addr_id =  rtObj.getString("addr_id");
        JSONObject distributionRulesRT = cakeUtil.getDistributionRules(city_id,addr_id,specIds,quantitys);
        JsonResult checkdistributionRulesRT = cakeUtil.checkResult(distributionRulesRT);
        if(Objects.nonNull(checkdistributionRulesRT)){
            throw new BusinessException("提交蛋糕叔叔订单时获取配送规则信息失败"+JSON.toJSONString(checkdistributionRulesRT));
        }
        rulesObject = distributionRulesRT.getJSONObject(cakeUtil.DATA_KEY);
        if(null == rulesObject){
            throw new BusinessException("提交蛋糕叔叔订单时获取配送规则信息data失败");
        }
        logger.info("配送规则接口（最新版）响应结果{}",JSON.toJSONString(distributionRulesRT));
        return rulesObject;
    }

    private JSONObject getRulesObjectByCache(String childCode) {
        JSONObject rulesObject = null;
        try {
            rulesObject = (JSONObject)jedisUtil.get(childCode);

        }catch (Exception e){
            logger.error("子订单{}从缓存中获取配送规则发生异常:{}", childCode,e);
        }finally {
            try {
                jedisUtil.del(childCode);
                logger.info("成功从缓存中删除子订单:{}对应的配送规则数据", childCode);
            }catch (Exception e){
                logger.info("从缓存中删除子订单:{}对应的配送规则数据发生异常:{}", childCode,e);
            }
        }
        return rulesObject;
    }

    private String getRuleId(JSONObject rtObj, String city_id, SoChildDTO soChildDTO1) {
        String childCode = soChildDTO1.getChildCode();

        StringBuffer sb = new StringBuffer();
        if (EmptyUtil.isNotEmpty(childCode)) {
            int startIndex = childCode.lastIndexOf("-");
            if (startIndex != -1 && startIndex < childCode.length() - 1) {
                // 确保有字符在最后一个 '-' 之后
                //return childCode.substring(startIndex + 1);
                String ruleId =  childCode.substring(startIndex + 1);
                String specIds =  rtObj.getString("spec_ids");

                if(EmptyUtil.isNotEmpty(specIds)){
                    if(specIds.indexOf(",") !=-1){
                        String specIdArr[] = specIds.split(",");
                        for (String s : specIdArr) {
                            sb.append(ruleId).append(",");
                        }
                        if(sb.length() >0){
                            sb.deleteCharAt(sb.length() - 1);
                        }
                        String ruleIds = sb.toString();
                        return ruleIds;
                    }else{
                        return childCode.substring(startIndex + 1);
                    }
                }
            }
        }
        List<String> productIds = soItemReadService.findProductIdsSoChild(soChildDTO1.getId());
        JSONArray array = new JSONArray();
        for (String id : productIds) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("product_id",id);
            jsonObject.put("city_id", city_id);
            array.add(jsonObject);
        }
        logger.info("批量查询规则ids的参数:"+array.toString());
        Map<String,Object> p = new HashMap<>();
        p.put("product",array);
        JSONObject ruleIdsRT =  cakeUtil.getRuleIds(p);
        JsonResult checkRT = cakeUtil.checkResult(ruleIdsRT);
        if(Objects.nonNull(checkRT)){
            throw new BusinessException("提交蛋糕叔叔订单时获取配送规则ID失败，发生异常"+JSON.toJSONString(checkRT));
        }
        logger.info("批量查询规则ids的结果:{}",JSON.toJSONString(ruleIdsRT));
        JSONArray dataList = ruleIdsRT.getJSONArray(cakeUtil.DATA_KEY);
        if(null == dataList || dataList.size() ==0){
            throw new BusinessException("提交蛋糕叔叔订单时获取配送规则ID失败，发生异常"+JSON.toJSONString(checkRT));
        }
        //StringBuffer sb = new StringBuffer();
        for (int i = 0; i < dataList.size(); i++) {
            JSONObject rtObject =dataList.getJSONObject(i);
            sb.append(rtObject.getString("distribution_rule_id")).append(",");
        }
        if(sb.length() >0){
            sb.deleteCharAt(sb.length() - 1);
        }
        String ruleIds = sb.toString();

        return ruleIds;
    }

    private void putCityId(JSONObject rtObj,ReceiverAddressDTO addr){
        String cityId = cakeAddressWriteManage.getCityId(CakeAddressUtil.getCityName(addr));
        rtObj.put("city_id",cityId);
    }

    private void putAddr(JSONObject rtObj,ReceiverAddressDTO addr){
        CakeAddResultDTO dto = cakeAddressWriteManage.addOrEditCakeAddress(addr,rtObj.getString("user_id"));
        String detail = dto.getAddr().contains(dto.getCity())?dto.getAddr():dto.getCity()+dto.getAddr();
        detail = detail.contains(dto.getProvince())?detail:dto.getProvince()+detail;
        JSONObject rtAddrObj = new JSONObject();
        rtAddrObj.put("id",dto.getId());
        rtAddrObj.put("name",dto.getName());
        rtAddrObj.put("phone",dto.getPhone());
        rtAddrObj.put("detail",detail);
        rtObj.put("addr",rtAddrObj);
    }

    private void putUserInfo(JSONObject rtObj){
        String userId = cakeManage.getCakeUserId();
        rtObj.put("user_id",userId);
    }

    private void putPreInfo(JSONObject rtObj,SoChildDTO soChildDTO,String phone){
        rtObj.put("out_order_no",soChildDTO.getChildCode());
        rtObj.put("pay_type",cakeUtil.getCakeChannelNo());
        rtObj.put("buyer_phone",phone);
        rtObj.put("buyer_msg","请尽快送达");
        List<SoChildDTO.SkuInfoDTO>  list = soChildDTO.getSkuInfoList();
        if(!CollectionUtils.isEmpty(list)){
            StringBuilder specIds = new StringBuilder();
            StringBuilder quantitys = new StringBuilder();
            for (SoChildDTO.SkuInfoDTO skuInfoDTO : list) {
                specIds.append(skuInfoDTO.getSkuId()).append(",");
                quantitys.append(skuInfoDTO.getNum()).append(",");
            }
            if(specIds.charAt(specIds.length() - 1) == ','){
                // 删除最后一个字符
                specIds.deleteCharAt(specIds.length() - 1);
            }
            if(quantitys.charAt(quantitys.length() - 1) == ','){
                // 删除最后一个字符
                quantitys.deleteCharAt(quantitys.length() - 1);
            }
            rtObj.put("spec_ids",specIds.toString());
            rtObj.put("quantitys",quantitys.toString());
        }
    }

    private void checkCakeOrCancelOrder(HttpServletRequest req, SoDTO sodto, Long userId, String userName, String ip, String mac, JSONObject submitOrderResult) {
        JsonResult checkResult = cakeUtil.checkResult(submitOrderResult);
        //检查订单是否成功，是否需要渠道订单
        if(Objects.nonNull(checkResult)){
            //cancelOrderJd(sodto, userId, ip, userName, mac, req);
            throw new BusinessException("当前订单存在蛋糕叔叔子订单,订单下单失败"+checkResult.getError()+",该订单已取消");
        }
        JSONObject submitRT = submitOrderResult.getJSONObject(cakeUtil.DATA_KEY);
        if(Objects.nonNull(submitRT)){
            return;
        }
        //蛋糕叔叔
        //cancelOrderJd(sodto, userId, ip, userName, mac, req);
        throw new BusinessException("当前订单存在蛋糕叔叔子订单,订单下单失败,该订单已取消"+JSON.toJSONString(submitOrderResult));

    }


    private void submitCakeOrderSuccess(SoDTO sodto, SoThirdpartyDTO soThirdpartyDTO, Long orderId, SoChildDTO soChildDTO, JSONObject submitOrderResult) {
        JsonResult checkResult = cakeUtil.checkResult(submitOrderResult);
        if(Objects.nonNull(checkResult)){
            throw new BusinessException("当前订单存在蛋糕叔叔子订单,订单下单失败,该订单已取消"+JSON.toJSONString(submitOrderResult));
        }
        String submitRT = submitOrderResult.getString(cakeUtil.DATA_KEY);
        if(StringUtils.isEmpty(submitRT)){
            throw new BusinessException("当前订单存在蛋糕叔叔子订单,订单下单失败,该订单已取消"+JSON.toJSONString(submitOrderResult));
        }
        CakeSubmitOrderResultDTO cakeSubmitOrderResultDTO = JsonUtils.jsonToPojo(submitRT,CakeSubmitOrderResultDTO.class);
        String cakeOrderId = cakeSubmitOrderResultDTO.getOrder_no();
        soChildDTO.setThirdpartySoChildId(Long.valueOf(cakeOrderId));
        //更新运费价格
        BigDecimal oldFree = soChildDTO.getOrdinaryDeliveryFee();

        logger.info("蛋糕叔叔订单进行创建下单成功->"+ JSON.toJSONString(cakeSubmitOrderResultDTO));
        BigDecimal freight = new BigDecimal(cakeSubmitOrderResultDTO.getShip_amount()).setScale(2);
        BigDecimal orderAmount= new BigDecimal(cakeSubmitOrderResultDTO.getOrder().getFinal_amount()).setScale(2);

        soChildDTO.setThirdpartySoChildPayAmount(orderAmount);
        soChildDTO.setThirdpartySoChildStatus(Integer.valueOf(1));
        soChildWriteService.updateSoChildByCodeWithTx(soChildDTO);

        SoThirdpartyDTO oldSoThirdpartyDTO = soThirdpartyReadService.findSoThirdpartyByChild(soChildDTO.getId(),soChildDTO.getChildCode());
        if(null !=oldSoThirdpartyDTO){
            SoThirdpartyDTO editSoThirdpartyDTO = new SoThirdpartyDTO();
            editSoThirdpartyDTO.setThirdpartyId(cakeOrderId);
            editSoThirdpartyDTO.setThirdpartyPayAmount(orderAmount);
            editSoThirdpartyDTO.setThirdpartyPayTime(new Date());
            editSoThirdpartyDTO.setSoChildCode(soChildDTO.getChildCode());
            soThirdpartyWriteService.updateSoThirdpartyByCodeWithTx(editSoThirdpartyDTO);
        }

    }
}
