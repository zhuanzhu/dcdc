package com.egeo.components.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.log.XLogger;
import com.egeo.web.JsonResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;

/**
 * @Description 全球购请求工具类
 * @Author lsl
 * @Date 2024/5/24 14:55
 * @Version V1.0
 **/
@Component
@ConditionalOnResource(
        resources = {"file:./config/WorldBuyConfig.properties"}
)
@PropertySource(
        value = {"file:./config/WorldBuyConfig.properties"},
        ignoreResourceNotFound = false,
        encoding = "UTF-8",
        name = "WorldBuyConfig.properties"
)
public class WorldBuyUtil {
    private static final XLogger logger = XLogger.getLogger(WorldBuyUtil.class);

    @Value("${worldAppId:f}")
    private String worldAppId;

    @Value("${worldKey:faa}")
    private String worldKey;

    private final static String ENCODE_UTF="UTF-8";
    @Value("${requestBaseUrl:http://198.149.2.3:801/Queryapi/index}")
    private String requestBaseUrl;

    /**是否支持渠道直接退款0不支持 1支持**/
    @Value("${hasChannelRefund:0}")
    private String hasChannelRefund;

    public static void main(String[] args) {
       /* WorldBuyUtil worldBuyUtil = new WorldBuyUtil();
        JSONObject jsonObject = worldBuyUtil.queryGoodsBrandList(null);
        System.out.println(JSON.toJSONString(jsonObject));*/

        /*Map<String,Object> requestMap = worldBuyUtil.getRequestParamMap(null,"QueryGoodsBrandList");
        String requestString = worldBuyUtil.mapToKeyValue(requestMap);
        String resultString = HttpService.sendPost(worldBuyUtil.requestBaseUrl,requestString);
        System.out.println(resultString);*/
    }

    /**
     * @Description 商品分类接口
     *{
     *     "code":200,
     *     "msg":"获取成功",
     *     "data":{
     *         "classList":[
     *             {
     *                 "class_id":"当前数据ID",
     *                 "class_name":"当前分类名称",
     *                 "class_pid":"父级ID",
     *                 "class_level":"当前分类层级数",
     *                 "class_image":"分类LOGO"
     *             }
     *         ]
     *     }
     * }
     **/
    public JSONObject queryGoodsCategoryList(Map<String,Object> paramMap){
        return query(paramMap,"QueryGoodsCategoryList");
    }

    /**
     * @Description 品牌列表查询接口
     **/
    public JSONObject queryGoodsBrandList(Map<String,Object> paramMap){
        return query(paramMap,"QueryGoodsBrandList");
    }

    /**
     * @Description 原产国列表查询接口
     **/
    public JSONObject queryGoodsCountryList(Map<String,Object> paramMap){
        return query(paramMap,"QueryGoodsCountryList");
    }

    /**
     * @Description 创建订单
     **/
    public JSONObject createOrder(Map<String,Object> paramMap){
        return query(paramMap,"createOrder");
    }

    /**
     * @Description 订单支付清关信息回传接口
     **/
    public JSONObject orderPay(Map<String,Object> paramMap){
        return query(paramMap,"OrderPay");
    }

    /**
     * @Description 订单取消接口,未回传OrderPay情况下调用
     **/
    public JSONObject orderCancel(Map<String,Object> paramMap){
        return query(paramMap,"OrderCancel");
    }

    /**
     * @Description 订单售后接口，回传了支付接口OrderPay之后调用售后
     **/
    public JSONObject orderAfterSale(Map<String,Object> paramMap){
        return query(paramMap,"OrderAfterSale");
    }

    public JSONObject query(Map<String,Object> paramMap,String requestMethod){
        String requestString = getRequestString(paramMap,requestMethod);
        String resultString = HttpService.sendPost(requestBaseUrl,requestString);
        return getResultJsonObject(resultString);
    }

    private JSONObject getResultJsonObject(String result) {
        if (EmptyUtil.isEmpty(result)) {
            return null;
        }
        return JSONObject.parseObject(result);
    }
    private String getRequestString(Map<String,Object> paramMap,String requestMethod){
        Map<String,Object> requestMap = getRequestParamMap(paramMap,requestMethod);
        return mapToKeyValue(requestMap);
    }



    private Map<String,Object> getRequestParamMap(Map<String,Object> paramMap,String requestMethod){
        Map<String,Object> requestMap = new HashMap<>();
        requestMap.put("appid",this.worldAppId);
        requestMap.put("requestMethod",requestMethod);
        requestMap.put("requestData", CollectionUtils.isEmpty(paramMap)?"%5B%5D":getUrlEnCode(JSON.toJSONString(paramMap)));
        String paramString = getSign(requestMap);
        requestMap.put("sign", paramString);
        return requestMap;
    }

    private String getSign(Map<String,Object> paramMap){
        String keyValueString = mapToKeyValue(paramMap);
        keyValueString = keyValueString+"&key="+this.worldKey;
        String finalString = MD5ConvertUtil.getMD5(keyValueString).toUpperCase();
        return finalString;
    }

    private String getUrlEnCode(String jsonString){
        try {
            String str = java.net.URLEncoder.encode(jsonString,ENCODE_UTF).replaceAll("\\+", "%20");
            return str;
        }catch (Exception e){
            e.printStackTrace();
        }
        return jsonString;
    }

    private String sortMapToKeyValue(Map<String,Object> paramMap){
        return sortMapToKeyValue(paramMap,true);
    }

    private String sortMapToKeyValue(Map<String,Object> paramMap,boolean hasSuffix){
        if(CollectionUtils.isEmpty(paramMap)){
            return "";
        }
        List<String> keyList = new ArrayList<>(paramMap.keySet());
        Collections.sort(keyList);
        StringBuilder  sb = new StringBuilder();
        for (String key : keyList) {
            sb.append(key).append("=").append(paramMap.get(key)).append("&");
        }
        //删除最后一位
        if (sb.length() > 0 && !hasSuffix) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }


    private String mapToKeyValue(Map<String,Object> paramMap){
        if(CollectionUtils.isEmpty(paramMap)){
            return "";
        }
        StringBuilder  sb = new StringBuilder();
        Map<String, Object> sortedParams = new TreeMap<>(paramMap);
        for (Map.Entry<String, Object> entry : sortedParams.entrySet()) {
            sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        //删除最后一位
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    public boolean checkSign(Map<String,Object> requestDataMap,String sourceSign){
        String sign = getSign(requestDataMap);
        if(sourceSign.equals(sign)){
            return true;
        }
        return false;
    }

    public boolean checkSign(String requestData,String sourceSign){
        String jsonString = getUrlDeCode(requestData);
        Map<String,Object> requestDataMap  = JSON.parseObject(jsonString,Map.class);
        return checkSign(requestDataMap,sourceSign);
    }

    public static String getUrlDeCode(String encodedJsonString) {
        try {
            // 使用URLDecoder对URL编码的字符串进行解码
            String decodedJsonString = URLDecoder.decode(encodedJsonString, ENCODE_UTF);
            return decodedJsonString;
        } catch (UnsupportedEncodingException e) {
            // 因为UTF-8是Java的标准编码，所以这里通常不会抛出异常
            // 但为了完整性，我们还是处理了这个异常
            e.printStackTrace();
            // 或者你可以抛出一个自定义的异常
            return null;
        }
    }

    private JSONObject getProductNoticeString(){
        String requestString = "{\n" +
                "    \"MessageType\":\"goodsChange\",\n" +
                "    \"data\":[\n" +
                "        {\n" +
                "            \"goodsId\":\"625354762196045824\",\n" +
                "            \"goodsName\":\"虫虫test002\",\n" +
                "            \"introName\":\"虫虫test002(一般贸易)\",\n" +
                "            \"images\":[\n" +
                "                \"https:\\/\\/indoorbuydev.oss-cn-hangzhou.aliyuncs.com\\/images\\/commodityimg\\/1663832817177_1214af65.png\"\n" +
                "            ],\n" +
                "            \"description\":\"<p>11<\\/p>\",\n" +
                "            \"goodsType\":0,\n" +
                "            \"goods_extends_type\":0,\n" +
                "            \"goodsTypeName\":\"一般贸易\",\n" +
                "            \"status\":1,\n" +
                "            \"categoryId\":\"304999228520793986\",\n" +
                "            \"categoryCode\":\"102500010001\",\n" +
                "            \"categoryName\":\"口红12\",\n" +
                "            \"categoryPid\":\"304999173185341314\",\n" +
                "            \"categoryPreCode\":\"10250001\",\n" +
                "            \"categoryPreName\":\"彩妆10\",\n" +
                "            \"categoryFPid\":\"304999140624959362\",\n" +
                "            \"categoryFPreCode\":\"1025\",\n" +
                "            \"categoryFPreName\":\"美容护肤修改\",\n" +
                "            \"categoryPreId\":\"304999140624959362\",\n" +
                "            \"brandID\":\"625353839524663296\",\n" +
                "            \"brandCode\":null,\n" +
                "            \"brandName\":\"虫虫002品牌\",\n" +
                "            \"countryId\":\"190840113020796932\",\n" +
                "            \"countryCode\":\"2104191234117547\",\n" +
                "            \"countryName\":\"美国\",\n" +
                "            \"unitID\":\"199130779823972356\",\n" +
                "            \"unitCode\":\"2104191234134249\",\n" +
                "            \"unitName\":\"袋\",\n" +
                "            \"goods_level\":\"F\",\n" +
                "            \"freight_template\":{\n" +
                "                \"template_id\":1,\n" +
                "                \"init_weight\":0,\n" +
                "                \"init_money\":0,\n" +
                "                \"increase_weight\":0,\n" +
                "                \"increase_money\":0\n" +
                "            },\n" +
                "            \"isFreePostFee\":1,\n" +
                "            \"isNeedWeiXinPay\":0,\n" +
                "            \"isNeedUploadIDCard\":0,\n" +
                "            \"isNeedIDCard\":0,\n" +
                "            \"tags\":[\n" +
                "\n" +
                "            ],\n" +
                "            \"skuList\":[\n" +
                "                {\n" +
                "                    \"skuId\":\"625354762196045824\",\n" +
                "                    \"skucode\":\"2209221536338404\",\n" +
                "                    \"barcode\":\"1674684\",\n" +
                "                    \"tax_rate\":\"0.0000\",\n" +
                "                    \"weight\":\"0.0000\",\n" +
                "                    \"limit_buy_count\":0,\n" +
                "                    \"has_rate\":1,\n" +
                "                    \"status\":1,\n" +
                "                    \"price\":\"1.35\",\n" +
                "                    \"price_cost\":\"1.35\",\n" +
                "                    \"price_market\":\"1.36\",\n" +
                "                    \"price_control\":\"0.00\",\n" +
                "                    \"spu_id\":0,\n" +
                "                    \"spu_name\":\"\",\n" +
                "                    \"spu_code\":\"\",\n" +
                "                    \"specList\":null,\n" +
                "                    \"commodity_customer_info\":{\n" +
                "                        \"payDeclare\":0,\n" +
                "                        \"payDeclareEnumDescription\":\"\",\n" +
                "                        \"orderDeclare\":0,\n" +
                "                        \"orderDeclareEnumDescription\":\"\",\n" +
                "                        \"enterpriseName\":\"\",\n" +
                "                        \"enterpriseCode\":\"\",\n" +
                "                        \"declareSKUCode\":null,\n" +
                "                        \"declareCommodityName\":null,\n" +
                "                        \"function\":null,\n" +
                "                        \"declareBarCode\":null,\n" +
                "                        \"customMeasurementUnitCode\":null,\n" +
                "                        \"customMeasurementUnit\":null,\n" +
                "                        \"customCountryCode\":null,\n" +
                "                        \"customCountry\":null,\n" +
                "                        \"customCurrencyCode\":null,\n" +
                "                        \"customCurrency\":null,\n" +
                "                        \"customSpecifications\":null\n" +
                "                    },\n" +
                "                    \"storeList\":[\n" +
                "                        {\n" +
                "                            \"linkSkuID\":\"625354762196045824\",\n" +
                "                            \"store_customer_name\":\"优选仓\",\n" +
                "                            \"store_type\":0,\n" +
                "                            \"storeDisplayName\":\"优选仓\",\n" +
                "                            \"store_id\":\"591656596488630272\",\n" +
                "                            \"store_code\":\"2323\",\n" +
                "                            \"store_name\":\"谢怜仓库\",\n" +
                "                            \"store_account\":\"123\",\n" +
                "                            \"store_isCombineOrders\":true,\n" +
                "                            \"store_mode\":1,\n" +
                "                            \"supplier_skuid\":\"625354762422538240\",\n" +
                "                            \"supplier_skucode\":\"26578974\",\n" +
                "                            \"supplier_id\":\"591638161759010816\",\n" +
                "                            \"supplier_alias\":\"虫虫供应商\",\n" +
                "                            \"supplier_code\":\"34325\",\n" +
                "                            \"supplier_companystatus\":1,\n" +
                "                            \"supplier_commoditydockmode\":1,\n" +
                "                            \"supplier_stockdockmode\":0,\n" +
                "                            \"supplier_orderdockmode\":0,\n" +
                "                            \"BusinessLicenseNo\":\"66666666666888\",\n" +
                "                            \"goods_batch_list\":[\n" +
                "                                {\n" +
                "                                    \"supplier_skuid\":\"625354762422538240\",\n" +
                "                                    \"supplier_skucode\":\"2209221536338404\",\n" +
                "                                    \"defaultStock\":null,\n" +
                "                                    \"num\":999,\n" +
                "                                    \"batch_no\":\"20220922\",\n" +
                "                                    \"batch_id\":\"\",\n" +
                "                                    \"status\":1,\n" +
                "                                    \"spec_list\":[\n" +
                "                                        {\n" +
                "                                            \"spec_num\":1,\n" +
                "                                            \"specName\":\"1袋\",\n" +
                "                                            \"expired_date\":\"2022-09-22\",\n" +
                "                                            \"make_date\":\"\",\n" +
                "                                            \"price\":\"1.35\",\n" +
                "                                            \"priceControl\":\"0.00\",\n" +
                "                                            \"priceVip\":\"\",\n" +
                "                                            \"priceSettlement\":\"1.35\",\n" +
                "                                            \"priceMarket\":\"1.36\",\n" +
                "                                            \"isMinStockSpecificationItem\":true\n" +
                "                                        }\n" +
                "                                    ]\n" +
                "                                }\n" +
                "                            ]\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"commission_level_list\":[\n" +
                "\n" +
                "                    ]\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    ],\n" +
                "    \"MessageId\":\"634751551644737536\",\n" +
                "    \"sign\":\"ddddddd\",\n" +
                "    \"IsFinished\":0\n" +
                "}";
        return JSONObject.parseObject(requestString);
    }

    public  String CODE_KEY="code";
    public  String CODE_SUCCESS="200";
    public  String MSG_KEY="msg";
    public  String DATA_KEY="data";

    /**
     * @Description 检查返回值，若有错返回错误
     **/
    public JsonResult checkResult(JSONObject jSONObject){
        if(Objects.isNull(jSONObject)){
            return JsonResult.fail("查询为空");
        }
        if(!Objects.equals(jSONObject.getString(CODE_KEY),CODE_SUCCESS)){
            return JsonResult.fail("查询失败"+jSONObject.getString(MSG_KEY));
        }
        return null;
    }

    public String getHasChannelRefund() {
        return hasChannelRefund;
    }

    public void setHasChannelRefund(String hasChannelRefund) {
        this.hasChannelRefund = hasChannelRefund;
    }
}
