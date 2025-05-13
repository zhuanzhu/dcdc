package com.egeo.components.utils;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.egeo.utils.EmptyUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


/**
 * @Description 仪电工具类
 * @Author lsl
 * @Date 2024/12/19 11:15
 * @Version V1.0
 **/
@Component
@ConditionalOnResource(
        resources = {"file:./config/YDConfig.properties"}
)
@PropertySource(
        value = {"file:./config/YDConfig.properties"},
        ignoreResourceNotFound = false,
        encoding = "UTF-8",
        name = "YDConfig.properties"
)
public class YDUtil {

    // 加密字符
    @Value("${yd.encrypt.key:dc888888}")
    private String encryptString;


    private static final String TIMESTAMP_KEY="timestamp";
    private static final String SIGN_KEY="sign";


    // 生成sign值的方法
    public  String generateSign(long timestamp) {
        try {
            // 拼接加密字符与时间戳
            String toEncrypt = getEncryptString() + timestamp;
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

    public  Map<String,Object> getRequestMap(Object jsonString){
        Long currentTimeMillis = System.currentTimeMillis();
        // 将毫秒数转换为秒数
        long currentTimeSeconds = currentTimeMillis / 1000;
        String sign = generateSign(currentTimeSeconds);
        Map<String,Object> finalMap = convertToMap(jsonString);
        if(finalMap == null){
            finalMap = new HashMap<>(2);
        }
        finalMap.put(TIMESTAMP_KEY,String.valueOf(currentTimeSeconds));
        finalMap.put(SIGN_KEY,sign);
        return finalMap;
    }

    public  Map<String,Object> convertToMap(Object paramObject){
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

    public String getEncryptString() {
        return encryptString;
    }

    public void setEncryptString(String encryptString) {
        this.encryptString = encryptString;
    }
}
