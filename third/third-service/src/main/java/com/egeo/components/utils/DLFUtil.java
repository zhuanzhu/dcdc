package com.egeo.components.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.egeo.exception.BusinessException;
import com.egeo.utils.DateUtils;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.StringUtils;
import com.egeo.utils.http.HttpClientUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;


/**
 * @Description todo
 * @Author lsl
 * @Date 2024/9/6 10:23
 * @Version V1.0
 **/
@Component
@ConditionalOnResource(
        resources = {"file:./config/DlfConfig.properties"}
)
@PropertySource(
        value = {"file:./config/DlfConfig.properties"},
        ignoreResourceNotFound = false,
        encoding = "UTF-8",
        name = "DlfConfig.properties"
)
public class DLFUtil {

    public static final  String PRIVATE_KEY="MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDcz+7TZdAC7DzB7QAgCzQF/7S9hCvLwDnTXUUhzKXCPbDbsFJjnWJRU+lqW15wSeJarZM/PeeS/MckjgBDdzH/xzUv/1vU+MOsQF+aBES0qjmgTkp5OHnplXOhlitHm7jaPAFBRPTinQQ6y1CHiYr7U7WRxhc+H8QodqBx0pd3xDrZnQ9YKl+iwjStlchjRtwS5SK4xq1pYhicR97S6WNAYCz52Mqn3AO5idK9D6cau45kFQQ+rD7pJ+YTiqjTSnefFANjbDBYcaCPBYQ4IDRJJLL1azsld4Rid1LLmfay0aT98uA1xoHUg7fQ1NJuQB6esxO6XlmaBg4BQMrlyQfdAgMBAAECggEAV6smwz/VCpQIu2YBerd3/Dm95NCpIsg7LTQG0piR50DfCxpCW9KMyoVqUxPbtvFesWng4GcPnkhGOLk4WZDlhQvujhPwvenWpnkYe+nd7Wz4XDE5WE+X96miszEQFeo5xku0o9lg8OguWbmHS4jMaWSe3jWr3Lp4Ayi2IvrdsGq3oTOamxCCi2zGSj+t/b6jgivj2iWNLQW5NRYoGmNrl+s1Ki8jP+xWKm8oYI3lt+wmMZ11dIHW1i09ibxlMXerGIsxkv87xdzA0usUUUq8OedojJ2PptMOeRbFETZduofywUSiSADLQkEN+3Gh8rPA2YxwKIw4rP6PA5kaCmsaAQKBgQD1Fn0DXxsxHv1yK0hZ639filuVsuv3NF5GMi6xbgWEff8iETGXl8XxHDCPfP8CCBOcqH+EOYjik2kT+TNss1eoBw2xlOGZwanRDjghMFNXE7lVLVnKrFwG4C5dmUICTXz8I9T8llMjU5zGYL92pSDfJIHOi/AIj8+djFlJb8bV/QKBgQDmpMBMrz0/8wevRjp12jzUKuFAdWj7/xIqEXqHPBWYNBXnjg5zfsE0RlK914R7TM2qDRcaY0fZPtck/kMYkcrIMXHaqN8OBMVOB9P3bpplmsKrY1bvvcEKja9+dvut3TACDmy4Z9AOTwl+cIUmiTGhfwU3R5MZ0FR3cMVejvKvYQKBgQCtzUvL90bmN67NpsoBElLaYth9uUtCm/161B1ur7Hz+hHSi9TaRsQVLsN1DdQdEbMutgLvtolO+/BZPLBuFmREAPSCCS1PBxow6V9+kbGYH6GfOLDjXAVKzvGyaHt5/I90ksh4cvX0KFgWCgfc0E8WTuJKocwPlkRJ2nx66mxb1QKBgQDNgVp+eV3fzxG30KFiRPQSL6rInlTOw/VRWoWMXbL4DOC/k0m5CuXnSAmNcaxvmH1f2Y4OSwvHvEBCi/MIv06plbTWmcU1XnbXEg6B1yDnC0ANKdRahP6uApSt4CJcRkryahvhK06skSvqZyOctH1uYhkpqjKdu4ynZ+nUWRZPwQKBgQCo+fNPlgIU3HpBBNZjRequPA5QbxTJOz3SZ+tdK5fQEyTx09auHq0N5zTek+Mts7S/LQYhz6FLlqUDpzvVvNlbwQF31dpHwRB1rA3xsB1xqUZ3LIsd2tI+r8M8zzi8pv970ci/VkHIzcpQTV9paIUhiD2FuHf22bEAthozE3mmXw==";
    public static final String publicKey= "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApXID5oKQ5syLHxwKqsCf5XrJohwC10EsUjYSO/23o00Pa5ZLv+elkSSJHfy6FPBufZPNtcLsmOubWCmaSoGvxy+/7ioUsZWYjZTZ7f13E0Ynhhi99W8gx+6GzxCiNdoRghobUo3nWVjMLNt5Jq5ij/CIzUhaeTkY602nDuc8cpHty3SUhDP7lcziIw52JOf8PclifCOFxWXBaOKaimgHPNGq/4KAGkTY4yhS1U7Ycf0chy/HnwwsLvTNplzHwOhnV1KRMfuJxBFiz6T/55lmkXbgqZGXBg4t4aobkwOtsooOxT/wGkZ5uxNNGkKnQPFXdYPqSnbOtq3v4TKzqs3t8wIDAQAB";

    @Value("${dlf.private.key:MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDcz+7TZdAC7DzB7QAgCzQF/7S9hCvLwDnTXUUhzKXCPbDbsFJjnWJRU+lqW15wSeJarZM/PeeS/MckjgBDdzH/xzUv/1vU+MOsQF+aBES0qjmgTkp5OHnplXOhlitHm7jaPAFBRPTinQQ6y1CHiYr7U7WRxhc+H8QodqBx0pd3xDrZnQ9YKl+iwjStlchjRtwS5SK4xq1pYhicR97S6WNAYCz52Mqn3AO5idK9D6cau45kFQQ+rD7pJ+YTiqjTSnefFANjbDBYcaCPBYQ4IDRJJLL1azsld4Rid1LLmfay0aT98uA1xoHUg7fQ1NJuQB6esxO6XlmaBg4BQMrlyQfdAgMBAAECggEAV6smwz/VCpQIu2YBerd3/Dm95NCpIsg7LTQG0piR50DfCxpCW9KMyoVqUxPbtvFesWng4GcPnkhGOLk4WZDlhQvujhPwvenWpnkYe+nd7Wz4XDE5WE+X96miszEQFeo5xku0o9lg8OguWbmHS4jMaWSe3jWr3Lp4Ayi2IvrdsGq3oTOamxCCi2zGSj+t/b6jgivj2iWNLQW5NRYoGmNrl+s1Ki8jP+xWKm8oYI3lt+wmMZ11dIHW1i09ibxlMXerGIsxkv87xdzA0usUUUq8OedojJ2PptMOeRbFETZduofywUSiSADLQkEN+3Gh8rPA2YxwKIw4rP6PA5kaCmsaAQKBgQD1Fn0DXxsxHv1yK0hZ639filuVsuv3NF5GMi6xbgWEff8iETGXl8XxHDCPfP8CCBOcqH+EOYjik2kT+TNss1eoBw2xlOGZwanRDjghMFNXE7lVLVnKrFwG4C5dmUICTXz8I9T8llMjU5zGYL92pSDfJIHOi/AIj8+djFlJb8bV/QKBgQDmpMBMrz0/8wevRjp12jzUKuFAdWj7/xIqEXqHPBWYNBXnjg5zfsE0RlK914R7TM2qDRcaY0fZPtck/kMYkcrIMXHaqN8OBMVOB9P3bpplmsKrY1bvvcEKja9+dvut3TACDmy4Z9AOTwl+cIUmiTGhfwU3R5MZ0FR3cMVejvKvYQKBgQCtzUvL90bmN67NpsoBElLaYth9uUtCm/161B1ur7Hz+hHSi9TaRsQVLsN1DdQdEbMutgLvtolO+/BZPLBuFmREAPSCCS1PBxow6V9+kbGYH6GfOLDjXAVKzvGyaHt5/I90ksh4cvX0KFgWCgfc0E8WTuJKocwPlkRJ2nx66mxb1QKBgQDNgVp+eV3fzxG30KFiRPQSL6rInlTOw/VRWoWMXbL4DOC/k0m5CuXnSAmNcaxvmH1f2Y4OSwvHvEBCi/MIv06plbTWmcU1XnbXEg6B1yDnC0ANKdRahP6uApSt4CJcRkryahvhK06skSvqZyOctH1uYhkpqjKdu4ynZ+nUWRZPwQKBgQCo+fNPlgIU3HpBBNZjRequPA5QbxTJOz3SZ+tdK5fQEyTx09auHq0N5zTek+Mts7S/LQYhz6FLlqUDpzvVvNlbwQF31dpHwRB1rA3xsB1xqUZ3LIsd2tI+r8M8zzi8pv970ci/VkHIzcpQTV9paIUhiD2FuHf22bEAthozE3mmXw==}")
    public String dlfPrivateKey;
    @Value("${dlf.public.key:MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApXID5oKQ5syLHxwKqsCf5XrJohwC10EsUjYSO/23o00Pa5ZLv+elkSSJHfy6FPBufZPNtcLsmOubWCmaSoGvxy+/7ioUsZWYjZTZ7f13E0Ynhhi99W8gx+6GzxCiNdoRghobUo3nWVjMLNt5Jq5ij/CIzUhaeTkY602nDuc8cpHty3SUhDP7lcziIw52JOf8PclifCOFxWXBaOKaimgHPNGq/4KAGkTY4yhS1U7Ycf0chy/HnwwsLvTNplzHwOhnV1KRMfuJxBFiz6T/55lmkXbgqZGXBg4t4aobkwOtsooOxT/wGkZ5uxNNGkKnQPFXdYPqSnbOtq3v4TKzqs3t8wIDAQAB}")
    public String dlfPublicKey;
    public static final String STATUS_KEY="status";
    public static final Integer SUCCESS_STATUS=1;
    private static final String VERSION="1.0";

    public static final String DATA_KEY="data";

    public static void main(String[] args) throws Exception {
        /*String jsonString ="\"{\\\"msg\\\":\\\"\\没\\有\\该\\用\\户\\\",\\\"status\\\":-1,\\\"success\\\":false,\\\"timestamp\\\":\\\"1729074230\\\",\\\"version\\\":1}\"";
        // 移除Java字符串层面的最外层引号
        //jsonString = jsonString.substring(1, jsonString.length() - 1);
        // 手动移除JSON值内部不必要的转义字符（这是一个特定的修复，不适用于一般情况）
        jsonString = jsonString.replace("\\\\\\", "");
        jsonString = jsonString.replace("\\", "");
        jsonString = jsonString.substring(1, jsonString.length() - 1);
        JSONObject jsonObject = JSON.parseObject(jsonString);
        System.out.println(jsonObject.getString("msg"));*/
        //用户余额查询
        /*Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("mobile","186160103671");
        System.out.println(JSON.toJSONString(dataMap));
        JSONObject result = send(JSON.toJSONString(dataMap),"https://dshjk.51okweb.com/?service=App.Dcgj.UserinfoApi");*/
//        System.out.println("请求结果："+result);

        //积分支付
 /*       Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("mobile","18616010367");
        dataMap.put("paymoney","0.01");
        dataMap.put("ordersn","aaaa");
        JSONObject result = send(JSON.toJSONString(dataMap),"https://dshjk.51okweb.com/?service=App.Dcgj.JidianpayApi");
        System.out.println("请求结果："+result);*/

        //饭卡支付
       /* Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("mobile","18616010367");
        dataMap.put("paymoney","0.01");
        dataMap.put("ordersn","aaaa");
        JSONObject result = send(JSON.toJSONString(dataMap),"https://dshjk.51okweb.com/?service=App.Dcgj.FankapayApi");
        System.out.println("请求结果："+result);*/

        //订单信息
     /*    Map<String,Object> dataMap = buildOrderInfoParam();
        JSONObject result = send(JSON.toJSONString(dataMap),"https://dshjk.51okweb.com/?service=App.Dcgj.GetorderinfoApi");
          System.out.println(JSON.toJSONString(result));*/
        //退款信息
       // Map<String,Object> dataMap = buildRefundOrderParam();
       /* String jsonString ="{\"childOrder\":[{\"childCode\":\"DCGJ-W-N-20241024175519024\",\"deliveryFeeAmount\":\"0.00\",\"product\":[{\"finalPromotionAver\":\"0.00\",\"num\":1,\"productName\":\"商品1\",\"salePrice\":\"52.90\",\"skuId\":\"2559\"}]}],\"createTime\":\"2024-10-24 17:55:20\",\"orderAmount\":\"52.90\",\"orderAmountPay\":\"52.90\",\"orderCode\":\"DCGJ-W-N-20241024175519024612\",\"orderDeliveryFee\":\"0.00\",\"orderPaidDetail\":[{\"paidAmount\":\"0.00\",\"type\":\"1\"},{\"paidAmount\":\"52.90\",\"type\":\"2\"},{\"paidAmount\":\"0.00\",\"type\":\"3\"}],\"orderPromotionDiscount\":\"0.00\",\"orderStatus\":1,\"payTime\":\"2024-10-24 17:55:23\",\"receiverAddress\":\"上海市市辖区宝山区想法告诉对方\",\"receiverPhone\":\"13918536672\",\"receiverUser\":\"杨峰\",\"userMobile\":\"13918536672\",\"userName\":\"13918536672\"}";
        JSONObject result = send(jsonString,"https://dshjk.51okweb.com/?service=App.Dcgj.GetorderinfoApi");
        System.out.println(JSON.toJSONString(result));*/
    }

    private static Map<String,Object> buildRefundOrderParam(){
        Map<String,Object> dataMap = new LinkedHashMap<>();
        dataMap.put("orderCode","DCGJ-W-N-20240906165232023957");
        dataMap.put("remark","");
        List<Map<String,Object>> refundDetailList = new ArrayList<>();
        Map<String,Object> refundFanKaMap = new LinkedHashMap<>();
        refundFanKaMap.put("type","1");
        refundFanKaMap.put("refundAmount","0.00");
        refundDetailList.add(refundFanKaMap);
        Map<String,Object> refundJiDianMap = new LinkedHashMap<>();
        refundJiDianMap.put("type","2");
        refundJiDianMap.put("refundAmount","198.00");
        refundDetailList.add(refundJiDianMap);
        Map<String,Object> refundWeiXinMap = new LinkedHashMap<>();
        refundWeiXinMap.put("type","3");
        refundWeiXinMap.put("refundAmount","0.00");
        refundDetailList.add(refundWeiXinMap);
        dataMap.put("refundDetail",refundDetailList);
        List<Map<String,Object>> childOrderList = new ArrayList<>();
        Map<String,Object> childOrderMap = new LinkedHashMap<>();
        childOrderMap.put("childCode","DCGJ-W-N-202409061652320239571");
        //订单状态(10已取消;0-待付款;1-已付款;2-已发货;3-已收货;4-已完成;5-预退款;6已退款;7-部分发货;8-换货中)
        childOrderMap.put("orderStatus",6);
        childOrderMap.put("refundAmount","198.00");
        childOrderMap.put("refundDeliveryFeeAmount","10.00");
        List<Map<String,Object>> productList = new ArrayList<>();
        Map<String,Object> productMap = new LinkedHashMap<>();
        productMap.put("productId","1");
        productMap.put("skuId","1");
        productMap.put("productName","蛋糕8寸");
        productMap.put("salePrice","188.00");
        productMap.put("refundNum",1);
        productMap.put("refundAmount","188.00");
        productList.add(productMap);
        childOrderMap.put("product",productList);
        childOrderList.add(childOrderMap);
        dataMap.put("childOrder",childOrderList);
        System.out.println("请求参数："+JSON.toJSONString(dataMap));
        return dataMap;
    }

    public  static  Map<String,Object> buildOrderInfoParam(){
        Map<String,Object> dataMap = new LinkedHashMap<>();
        dataMap.put("orderCode","DCGJ-W-N-20240906165232023957");
        dataMap.put("userName","18616010367");
        dataMap.put("userMobile","18616010367");
        //订单状态(10已取消;0-待付款;1-已付款;2-已发货;3-已收货;4-已完成;5-预退款;6已退款;7-部分发货;8-换货中)
        dataMap.put("orderStatus",1);
        dataMap.put("createTime", DateUtils.format("yyyy-MM-dd HH:mm:ss",new Date()));
        dataMap.put("orderDeliveryFee","10.00");
        dataMap.put("payTime",DateUtils.format("yyyy-MM-dd HH:mm:ss",new Date()));
        dataMap.put("orderAmount","198.00");
        dataMap.put("orderPromotionDiscount","0.00");
        dataMap.put("orderAmountPay",new BigDecimal(dataMap.get("orderAmount").toString()).subtract(new BigDecimal(dataMap.get("orderPromotionDiscount").toString())).doubleValue());
        dataMap.put("receiverUser","张三");
        dataMap.put("receiverPhone","18616010367");
        dataMap.put("receiverAddress","上海市浦东新区东建新村19号楼201室");
        //dataMap.put("remark","");

        //orderPaidDetail
        List<Map<String,Object>> orderPaidDetailList = new ArrayList<>();
        Map<String,Object> orderPaidFanKaMap = new LinkedHashMap<>();
        orderPaidFanKaMap.put("type","1");
        orderPaidFanKaMap.put("paidAmount","0.00");
        orderPaidDetailList.add(orderPaidFanKaMap);
        Map<String,Object> orderPaidJiDianMap = new HashMap<>();
        orderPaidJiDianMap.put("type","2");
        orderPaidJiDianMap.put("paidAmount",dataMap.get("orderAmountPay"));
        orderPaidDetailList.add(orderPaidJiDianMap);
        Map<String,Object> orderPaidWeiXinMap = new HashMap<>();
        orderPaidWeiXinMap.put("type","3");
        orderPaidWeiXinMap.put("paidAmount","0.00");
        orderPaidDetailList.add(orderPaidWeiXinMap);
        dataMap.put("orderPaidDetail",orderPaidDetailList);

        //childOrder
        List<Map<String,Object>> childOrderList = new ArrayList<>();
        for (int i = 1; i < 2; i++) {
            Map<String,Object> childOrderMap = new LinkedHashMap<>();
            childOrderMap.put("childCode",dataMap.get("orderCode")+""+i);
            childOrderMap.put("orderStatus",dataMap.get("orderStatus"));
            childOrderMap.put("deliveryTime",null);
            childOrderMap.put("logisticsCompany",null);
            childOrderMap.put("courierNumber",null);
            childOrderMap.put("deliveryFeeAmount","10.00");
            List<Map<String,Object>> productList = new ArrayList<>();
            Map<String,Object> productMap = new LinkedHashMap<>();
            productMap.put("skuId","1");
            productMap.put("productId","1");
            productMap.put("productName","蛋糕8寸");
            productMap.put("taxRate","0.1");
            productMap.put("salePrice","188.00");
            productMap.put("num","1");
            productMap.put("deliveryFeeAver","10.00");
            productMap.put("finalPromotionAver","0.00");
            productMap.put("ext",null);
            productList.add(productMap);
            childOrderMap.put("product",productList);
            childOrderList.add(childOrderMap);
        }
        dataMap.put("childOrder",childOrderList);

        System.out.println("请求参数："+JSON.toJSONString(dataMap));
        return dataMap;
    }

    private static String doPostByJson(String url,String paramString){
        return HttpClientUtil.doPostByJson(url, "utf-8",paramString);
    }

    public static Map<String,Object> getRequestMap(String jsonString,Long timestamp){
        Map<String,Object> finalMap = new LinkedHashMap<>();
        finalMap.put("data", jsonString);
        finalMap.put("timestamp",null ==timestamp?String.valueOf(System.currentTimeMillis()):String.valueOf(timestamp));
        finalMap.put("version",VERSION);
        String plainText = JSON.toJSONString(finalMap);
        String sign = null;
        try {
            sign = RsaSignUtil.signData(plainText,PRIVATE_KEY);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("签名失败");
        }
        finalMap.put("sign",sign);
        return finalMap;
    }

    public static JSONObject send(String jsonString,String url){
        //return getResponseData(sendRequest(jsonString,url));
        return sendRequest(jsonString,url);
    }


    public static JSONObject getResponseData(JSONObject jsonObject){
        if(null ==jsonObject){
            return null;
        }
       return jsonObject.containsKey(DATA_KEY)?jsonObject.getJSONObject(DATA_KEY):jsonObject;
    }

    public static JSONObject sendRequest(String jsonString,String url){
        return sendRequest(jsonString,null,url);
    }

    public static String getRequestParamString(String jsonString,Long timestamp){
        Map<String,Object> finalMap = getRequestMap(jsonString,timestamp);
        return JSON.toJSONString(finalMap);
    }

    public static JSONObject sendRequest(String jsonString,Long timestamp,String url){
        //请求参数
        String paramString = getRequestParamString(jsonString,timestamp);

        System.out.println("请求参数完整报文:"+paramString);
        //请求
        String result = doPostByJson(url,paramString);
        if(EmptyUtil.isEmpty(result) || EmptyUtil.isBlank(result)){
            throw new BusinessException("请求失败，响应为空");
        }
        System.out.println("请求结果完整报文:"+result);
        //将msg的16进制转中文
        result = RsaSignUtil.decodeUnicodeEscapes(result);
        JSONObject jsonObject = JSONObject.parseObject(result);
        if(!jsonObject.getInteger(STATUS_KEY).equals(SUCCESS_STATUS)){
            return jsonObject;
        }
        //验签
        verifyString(jsonObject);
        return jsonObject;
    }



    private static void verifyString(JSONObject jsonObject){
        //签名字符串
        String sign = jsonObject.getString("sign");

        //需要参与验证签名的字段
        Map<String,Object> finalVerifyMap = new LinkedHashMap<>();
        finalVerifyMap.put("data",jsonObject.get("data"));
        finalVerifyMap.put("timestamp",jsonObject.get("timestamp"));
        finalVerifyMap.put("version",jsonObject.get("version"));
        String resText = JSON.toJSONString(finalVerifyMap);

        //验签
        boolean verifySignResult = RsaSignUtil.verify(resText,sign,publicKey);
        System.out.println("验签结果:"+(verifySignResult?"成功":"失败"));
        if(!verifySignResult){
            throw new BusinessException("验签失败");
        }
        jsonObject.remove("sign");
    }

    public String getDlfPrivateKey() {
        return dlfPrivateKey;
    }

    public void setDlfPrivateKey(String dlfPrivateKey) {
        this.dlfPrivateKey = dlfPrivateKey;
    }

    public String getDlfPublicKey() {
        return dlfPublicKey;
    }

    public void setDlfPublicKey(String dlfPublicKey) {
        this.dlfPublicKey = dlfPublicKey;
    }
}
