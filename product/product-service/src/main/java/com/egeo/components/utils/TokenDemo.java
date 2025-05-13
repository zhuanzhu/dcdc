package com.egeo.components.utils;


import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author ${User}
 * @date 2019/3/4 17:32
 */
public class TokenDemo {

    /**日志*/
    private static final Logger logger = LoggerFactory.getLogger(TokenDemo.class);

    /**
     * 时间格式
     */
    private static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * main方法
     * @param args
     */
    public static void main(String[] args) {
        String url = "https://bizapi.jd.com/oauth2/access_token";
        Map<String, String> params = new HashMap<String, String>(6);
        // 固定值 access_token
        String grantType = "access_token";
        params.put("grant_type", grantType);
        // 用户名
        String username = "vv";
        params.put("username", username);
        // 加密后的密码
        String password = getMD5Str("jd.com");
        params.put("password", password);
        // 当前时间 格式yyyy-MM-dd HH:mm:ss
        String timestamp = DateFormatUtils.format(System.currentTimeMillis(), TIME_FORMAT);
        params.put("timestamp", timestamp);
        // 对接账号
        String clientId="fsfd3222f";
        params.put("client_id", clientId);
        // 对接账号密钥
        String clientSecret="fsfd3222f";
        // 认证标识
        String sign = clientSecret + timestamp + clientId+ username + password + grantType + clientSecret;
        System.out.println("加密前的sign="+sign);
        sign = getMD5Str(sign).toUpperCase();
        params.put("sign", sign);
        System.out.println("加密后的sign="+sign);
        // 执行请求
        String rs = httpPost(url, params);
        System.out.println("获取token返回结果="+rs);
    }

    /**
     * Md5加密
     * @param str
     * @return
     */
    public static String getMD5Str(String str) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(str.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            logger.error("NoSuchAlgorithmException caught!");
            System.exit(-1);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        byte[] byteArray = messageDigest.digest();
        StringBuilder md5StrBuff = new StringBuilder();

        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1) {
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
            } else {
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
            }
        }
        return md5StrBuff.toString();
    }

    /**
     * 执行HttpPost请求
     * @param url 请求路径
     * @param params 参数
     * @return 结果
     */
    public static String httpPost(String url, Map<String, String> params){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost =   new HttpPost(url);
        CloseableHttpResponse response = null;
        String result = null;
        try {
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            if (null != params && params.size() > 0) {
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    if (key == null || value == null) {
                        continue;
                    }
                    NameValuePair pair = new BasicNameValuePair(key, value);
                    nameValuePairs.add(pair);
                }
            }
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs,"UTF-8"));
            httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
            // 执行请求
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if (null != entity) {
                result = EntityUtils.toString(entity, ContentType.getOrDefault(entity).getCharset());
                logger.info("执行请求完毕：" + result);
                EntityUtils.consume(entity);
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                logger.error("请求通信[\" + reqURL + \"]时网络时，关闭异常,堆栈轨迹如下", e);
            }
            httpPost.releaseConnection();
        }
        return result;
    }
}

