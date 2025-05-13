package com.egeo.components.utils;

import com.alibaba.fastjson.JSONObject;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.utils.delivery.JdUtils;
import com.egeo.utils.encrypt.MD5;
import com.egeo.utils.http.HttpClientUtil;
import com.egeo.utils.log.XLogger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@Component
@ConditionalOnResource(
        resources = {"classpath:config/JDConfig.properties"}
)
@PropertySource(
        value = {"classpath:config/JDConfig.properties"},
        ignoreResourceNotFound = false,
        encoding = "UTF-8",
        name = "JDConfig.properties"
)
public class JdUtils2 extends JdUtils {
    private static final XLogger logger = XLogger.getLogger(JdUtils2.class);
    @Value("${jd.getDetailUrl}")
    private String getDetailUrl;


    @Value("${jd.TIME_FORMAT}")
    private String TIME_FORMAT;
    @Value("${jd.getAccessTokenUrl}")
    private String getAccessTokenUrl;
    @Value("${jd.clientId}")
    private String clientId;
    @Value("${jd.clientSecret}")
    private String clientSecret;
    private static final String username = "大厨管家VOP01";
    @Value("${jd.password}")
    private String password;
    @Value("${jd.getRefreshTokenUrl}")
    private String getRefreshTokenUrl;

    @Value("${jd.JD_ACCESS_TOKEN}")
    private String JD_ACCESS_TOKEN;
    @Value("${jd.JD_REFRESH_TOKEN}")
    private String JD_REFRESH_TOKEN;


    public String TEST_TO_PRO="1";
    /**
     * queryExts 增加taxInfo
     * @param cache
     * @param skuId
     * @return
     */
    @Override
    public JSONObject getDetail(JedisUtil cache, Long skuId) {
        Map<String, String> params = new HashMap();
        params.put("token", this.getAccessToken(cache));
        params.put("sku", skuId.toString());
        params.put("queryExts", "wxintroduction,LowestBuy,pName,isToReturn,noReasonToReturn,thwa,isSelf,isJDLogistics,spuId,paramDetailJson,paramJson,taxInfo");
        String result = HttpClientUtil.doPost(this.getDetailUrl, params, "UTF-8", 30000);
        JSONObject resultObj = JSONObject.parseObject(result);
        if (EmptyUtil.isNotEmpty(resultObj)) {
            Boolean success = resultObj.getBoolean("success");
            if (EmptyUtil.isNotEmpty(success) && success) {
                return resultObj.getJSONObject("result");
            }
        }

        return null;
    }


    @Override
    public String getAccessToken(JedisUtil cache) {
        String accessToken = (String)cache.get(this.JD_ACCESS_TOKEN);
        if (EmptyUtil.isEmpty(accessToken)) {
            String refreshToken = (String)cache.get(this.JD_REFRESH_TOKEN);
            if (EmptyUtil.isEmpty(refreshToken)) {
                accessToken = this.getAccessTokenFromJD(cache);
            } else {
                accessToken = this.getAccessTokenByRefreshToken(refreshToken, cache);
            }
        }

        return accessToken;
    }

    private String getAccessTokenByRefreshToken(String refreshToken, JedisUtil cache) {
        Map<String, String> params = new HashMap(3);
        params.put("refresh_token", refreshToken);
        logger.info("refresh_token:" + refreshToken);
        params.put("client_id", this.clientId);
        logger.info("client_id:" + this.clientId);
        params.put("client_secret", this.clientSecret);
        logger.info("clientSecret:" + this.clientSecret);
        String result = HttpClientUtil.doPost(this.getRefreshTokenUrl, params, "UTF-8", (Integer)null);
        logger.info("refresh-result=" + result);
        String accessToken = null;
        JSONObject resultObj = JSONObject.parseObject(result);
        if (EmptyUtil.isNotEmpty(resultObj)) {
            Boolean success = resultObj.getBoolean("success");
            if (EmptyUtil.isNotEmpty(success) && success) {
                JSONObject data = resultObj.getJSONObject("result");
                accessToken = data.getString("access_token");
                String newRefreshToken = data.getString("refresh_token");
                long refresh_token_expires = data.getLong("refresh_token_expires");
                cache.set(this.JD_ACCESS_TOKEN, 85800, accessToken);
                Long refreshTokenExpire = (refresh_token_expires - System.currentTimeMillis()) / 1000L;
                int refreshTokenExpireSeconds = refreshTokenExpire.intValue() - 600;
                if (refreshTokenExpireSeconds > 0) {
                    cache.set(this.JD_REFRESH_TOKEN, refreshTokenExpireSeconds, newRefreshToken);
                }
            }
        }

        return accessToken;
    }


    private String getAccessTokenFromJD(JedisUtil cache) {
        Map<String, String> params = new HashMap(6);
        String grantType = "access_token";
        params.put("grant_type", grantType);
        params.put("username", username);
        logger.info("username:{}",username);
        params.put("password", MD5.encode(this.password).toLowerCase());
        String timestamp = (new SimpleDateFormat(this.TIME_FORMAT)).format(System.currentTimeMillis());
        params.put("timestamp", timestamp);
        params.put("client_id", this.clientId);
        String sign = this.clientSecret + timestamp + this.clientId + username + MD5.encode(this.password).toLowerCase() + grantType + this.clientSecret;
        logger.info("加密前的sign=" + sign);
        sign = MD5.encode(sign).toUpperCase();
        params.put("sign", sign);
        logger.info("加密后的sign=" + sign);
        String result = HttpClientUtil.doPost(this.getAccessTokenUrl, params, "UTF-8", (Integer)null);
        String accessToken = null;
        JSONObject resultObj = JSONObject.parseObject(result);
        if (EmptyUtil.isNotEmpty(resultObj)) {
            Boolean success = resultObj.getBoolean("success");
            if (EmptyUtil.isNotEmpty(success) && success) {
                JSONObject data = resultObj.getJSONObject("result");
                accessToken = data.getString("access_token");
                String refreshToken = data.getString("refresh_token");
                long refresh_token_expires = data.getLong("refresh_token_expires");
                cache.set(this.JD_ACCESS_TOKEN, 85800, accessToken);
                Long refreshTokenExpire = (refresh_token_expires - System.currentTimeMillis()) / 1000L;
                int refreshTokenExpireSeconds = refreshTokenExpire.intValue() - 600;
                if (refreshTokenExpireSeconds > 0) {
                    cache.set(this.JD_REFRESH_TOKEN, refreshTokenExpireSeconds, refreshToken);
                }
            }
        }

        return accessToken;
    }



}
