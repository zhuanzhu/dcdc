package com.egeo.components.utils;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.egeo.utils.EmptyUtil;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


/**
 * @Description 仪电工具类
 * @Author lsl
 * @Date 2024/12/19 11:15
 * @Version V1.0
 **/
public class YMainTestUtil {

    // 加密字符
    private static final String ENCRYPT_CHAR = "dc888888";

    private static final String CHARSET_UTF_8="UTF-8";

    private static final String TIMESTAMP_KEY="timestamp";
    private static final String SIGN_KEY="sign";

    private static final String BaseUrl ="https://yd.jssz123.com/";
    private static final String getUserInfoUrl ="api/open/getUserInfo";

    public static void main(String[] args) {
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("user_id",String.valueOf(2));
        Map<String,Object> finalMap = getRequestMap(JSON.toJSONString(paramMap));
       String userInfo =  doGet(getUserInfoUrl, finalMap);
       System.out.println(userInfo);
    }

    private static String doGet(String url , Map<String, Object> param){
        String result= HttpUtil.get(BaseUrl +url,param,2000);
        return result;
    }

    // 生成sign值的方法
    public static String generateSign(long timestamp) {
        try {
            // 拼接加密字符与时间戳
            String toEncrypt = ENCRYPT_CHAR + timestamp;
            // 获取MD5 MessageDigest实例
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 执行加密操作
            byte[] digest = md.digest(toEncrypt.getBytes());
            // 将字节数组转换为十六进制字符串
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            // 返回最终的sign值
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            // 处理异常（这里简单抛出，实际应用中可能需要更复杂的错误处理）
            throw new RuntimeException("MD5算法不可用", e);
        }
    }

    public static Map<String,Object> getRequestMap(Object jsonString){
        Long currentTimeMillis = System.currentTimeMillis();
        // 将毫秒数转换为秒数
        long currentTimeSeconds = currentTimeMillis / 1000;
        String sign = YMainTestUtil.generateSign(currentTimeSeconds);
        Map<String,Object> finalMap = convertToMap(jsonString);
        if(finalMap == null){
            finalMap = new HashMap<>(2);
        }
        finalMap.put(TIMESTAMP_KEY,String.valueOf(currentTimeSeconds));
        finalMap.put(SIGN_KEY,sign);
        return finalMap;
    }

    public static Map<String,Object> convertToMap(Object paramObject){
        if(Objects.isNull(paramObject) || EmptyUtil.isEmpty(paramObject)){
            return null;
        }
        if(paramObject instanceof Map){
            return (Map)paramObject;
        }
        if(paramObject instanceof String){
            return JSONObject.parseObject((String)paramObject);
        }
        return null;
    }
}
