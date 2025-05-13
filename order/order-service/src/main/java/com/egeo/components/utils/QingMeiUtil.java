package com.egeo.components.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.http.HttpClientUtil;
import com.egeo.utils.log.XLogger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.*;

/**
 * 清美请求工具类
 */
@Component
@ConditionalOnResource(resources= {"file:./config/qingMeiConfig.properties"})
@PropertySource(value = {"file:./config/qingMeiConfig.properties"},ignoreResourceNotFound = false, encoding = "UTF-8", name = "qingMeiConfig.properties")
public class QingMeiUtil {
    private static final XLogger logger = XLogger.getLogger(QingMeiUtil.class);
    private final String CHARSET_UTF_8="UTF-8";


    @Value("${qm.baseUrl}")
    private String qmBaseUrl;

    @Value("${qm.appId}")
    private String qmAppId;

    @Value("${qm.appSecret}")
    private String qmAppSecret;

    @Value("${qm.payUrl}")
    public String payUrl;

    @Value("${qm.syncPayResultMaxCount:6}")
    public Integer syncPayResultMaxCount;

    @Value("${qm.syncPayMaxCancelOrder:false}")
    public Boolean syncPayMaxCancelOrder;


    /**
     * 签名校验
     * @param params
     * @return
     */
    public boolean checkSign(Map<String,String> params){
        String sign=params.remove("sign");
        String sign_ = sign(params,this.qmAppSecret);
        logger.info("sign:{},sign_:{}",sign,sign_);
        return sign.equals(sign_);
    }
    /**
     * 订单支付成功通知
     * @param notifyUrl
     * @param outTradeNo
     * @param tradeNo
     * @param userId
     * @param totalAmount
     * @param attach
     * @param payTime
     * @return
     */
    public String paySuccessNotify(String notifyUrl, String outTradeNo, String tradeNo,
                                       String userId, BigDecimal totalAmount,String attach,
                                       String payTime){
        Map<String,String> param = new HashMap<>();
        param.put("appId",this.qmAppId);
        param.put("outTradeNo",outTradeNo);
        param.put("tradeNo", tradeNo);
        param.put("openId",userId);
        param.put("totalAmount",totalAmount==null?BigDecimal.ZERO.toPlainString() : totalAmount.toPlainString());
        param.put("attach",attach);
        param.put("payTime",payTime);
        Map<String, String> requestParam = getRequestParam(param);
        logger.info("清美支付成功通知paySuccessNotify;url:{},请求参数:{}",notifyUrl,JSON.toJSONString(requestParam));
        String result = doPostByJson(notifyUrl, requestParam);
        logger.info("清美支付成功paySuccessNotify;outTradeNo:{},通知返回结果:{}",outTradeNo, result);
        return result;
    }

    /**
     * 共通请求参数处理
     * @param param
     * @return
     */
    private Map<String, String> getRequestParam(Map<String, String> param) {
        String timestamp = String.valueOf(System.currentTimeMillis());
        if(EmptyUtil.isNotEmpty(param) && param.containsKey("timestamp")){
            timestamp = String.valueOf(param.get("timestamp"));
        }
        param.put("timestamp",timestamp);
        String sign=sign(param,this.qmAppSecret);
        param.put("sign",sign);
        return param;
    }


    /**
     * sign签名处理
     * @param map
     * @param key
     * @return
     */
    public static String sign(Map<String,String> map,String key){
        Map<String,String> signMap=new HashMap<>();
        map.keySet().forEach(k->{
            Object v=map.get(k);
            if (EmptyUtil.isNotEmpty(v)){
                signMap.put(k,String.valueOf(v));
            }
        });
        Object[] key_arr = signMap.keySet().toArray();
        Arrays.sort(key_arr);
        StringBuffer sign = new StringBuffer();
        for  (Object k : key_arr) {
            sign.append(signMap.get(k));
        }
        String md5Key = getMD5(key);
        if (map.containsKey("timestamp")){
            sign.append(signMap.get("timestamp"));
        }
        sign.append(md5Key);
        logger.info("sign加签前{}",sign.toString());
        return getMD5(sign.toString());

    }


    public static String getMD5(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            String md5=new BigInteger(1, md.digest()).toString(16);
            return fillMD5(md5);
        } catch (Exception e) {
            throw new RuntimeException("MD5加密错误:"+e.getMessage(),e);
        }
    }
    public static String fillMD5(String md5){
        return md5.length()==32?md5:fillMD5("0"+md5);
    }

    private String doPostByJson(String url,Map<String, String> requestParam){
        StringBuffer requestUrl = new StringBuffer();
        if (url.contains("http")){
            requestUrl.append(url);
        }else {
            requestUrl.append(this.qmBaseUrl +url);
        }
        String result = HttpClientUtil.doPostByJson(requestUrl.toString(),CHARSET_UTF_8, JSONObject.toJSONString(requestParam));
        return result;
    }

    private String doGet(String url ,Map<String, String> param){
        String paramString = getUrlParam(param);
        StringBuffer requestUrl = new StringBuffer();
        if (url.contains("http")){
            requestUrl.append(url);
        }else {
            requestUrl.append(this.qmBaseUrl +url);
        }
        requestUrl.append("?"+paramString);
        return HttpClientUtil.doGet(requestUrl.toString());
    }

    private String getUrlParam(Map<String, String> param){
        if(EmptyUtil.isEmpty(param)) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> entry : param.entrySet()) {
            sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        // 删除最后一个字符
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    private JSONObject getResultJsonObject(String result) {
        if (EmptyUtil.isEmpty(result)) {
            return null;
        }
        return JSONObject.parseObject(result);
    }


    public static void main(String[] args){
//        Map map = new HashMap();
//        map.put("name","张三");
//        map.put("mobile","13811112222");
//        map.put("goods_list","{\"name\":\"华为手机\",\"price\":85.21}");
//        map.put("timestamp","5425432542");
//        System.out.println(sign(map, "ad884w23kkdsdsdsds"));
        System.out.println(getMD5("qingmeidachuguanjia"));
    }


    public String getPayUrl() {
        return payUrl;
    }

    public void setPayUrl(String payUrl) {
        this.payUrl = payUrl;
    }
}
