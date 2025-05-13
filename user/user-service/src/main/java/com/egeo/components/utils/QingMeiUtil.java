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

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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

    @Value("${qm.mtLogin}")
    private String qmMtLogin;

    @Value("${qm.appSecret}")
    private String qmAppSecret;


    /**
     * 清美同登接口
     * @param userId
     * @param phone
     * @param jumpUrl
     * @return
     */
    public String mtLogin(String userId,String phone,String jumpUrl){
        Map<String,String> param = new HashMap<>();
        param.put("uid",userId);
        param.put("phone",phone);
        Map<String, String> requestParam = getRequestParam(param);
        if (EmptyUtil.isNotEmpty(jumpUrl)){
            param.put("jumpUrl", HttpUtil.urlEncode(jumpUrl,CHARSET_UTF_8));
        }
        logger.info("清美同登接口调用mtLogin,请求参数:{}" ,JSON.toJSONString(requestParam));
        String result = findMtLoginUrl(this.qmMtLogin, requestParam);
        logger.info("清美同登接口调用返回结果mtLogin,userId:{},result:{}",userId,result);
//        return getResultJsonObject(result);
        return result;
    }

    /**
     * 共通请求参数处理
     * @param param
     * @return
     */
    private Map<String, String> getRequestParam(Map<String, String> param) {
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
            String v=map.get(k);
            if (EmptyUtil.isNotEmpty(v)){
                signMap.put(k,v);
            }
        });
        Object[] key_arr = signMap.keySet().toArray();
        Arrays.sort(key_arr);
        StringBuffer sign = new StringBuffer();
        for  (Object k : key_arr) {
            sign.append(k).append(map.get(k));
        }
        sign.append(key);
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

    private String findMtLoginUrl(String url ,Map<String, String> param){
        String paramString = getUrlParam(param);
        String requestUrl = this.qmBaseUrl +url+"?"+paramString;
        logger.info("清美请求参数:" + requestUrl);
        return requestUrl;
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

    public static void main(String[] args) throws Exception {
        Map map = new HashMap();
        map.put("uid","123456");
        map.put("phone","13355667788");
        System.out.println(sign(map, "6e4b22eba606cc12ad004d6ba14ae8c2"));
//        System.out.println(HttpUtil.urlEncode("http://platform.dachuguanjia.cn/#/orderDetail/2559","utf-8"));


    }
}
