package com.egeo.components.utils;

import com.egeo.utils.log.XLogger;
import com.egeo.utils.pay.PayUtil;
import com.egeo.utils.str.UUID;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.dom4j.DocumentException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

@Component
public class PayExtUtil extends PayUtil {
    private static final XLogger logger = XLogger.getLogger(PayExtUtil.class);


    /**
     * appId传参修改(扩展)
     * @param paraMap
     * @param url
     * @param signPlatform
     * @param useCert
     * @param platformId
     * @return
     */
    @Override
    public Map<String, String> sendWeixinPostRequest(Map<String, String> paraMap, String url, Integer signPlatform, boolean useCert, Long platformId) {
        logger.info("请求微信接口公共方法,url: " + url);
        Map<String, String> result = new HashMap();
        ((Map)result).put("return_code", "FAIL");
        WxPayConfig wxPayConfig = this.wxPayConfigBySignPlatform(signPlatform, platformId);
        if (!paraMap.containsKey("appid")){
            paraMap.put("appid", wxPayConfig.getAppId());
        }
        paraMap.put("mch_id", wxPayConfig.getMchId());
        paraMap.put("nonce_str", UUID.uuid());
        paraMap.put("sign_type", wxPayConfig.getSignType());
        String privateKey = wxPayConfig.getPrivateKey();
        String sign = this.wxSign(paraMap, privateKey);
        logger.info("sign=" + sign);
        String xmlParam = wxBuildXmlParam(paraMap, sign);
        logger.info("xml请求参数:" + xmlParam.toString());
        String responseXml = this.sendWeiXinXmlPost(xmlParam, url, useCert, wxPayConfig);

        try {
            result = this.parseResponseXml2Map(responseXml);
        } catch (DocumentException var13) {
            DocumentException e = var13;
            logger.error("微信xml字符串解析错误,responseXml:" + responseXml + e);
            e.printStackTrace();
        }

        logger.info("微信请求返回日志(url=" + url + "):" + responseXml);
        return (Map)result;
    }


    private String wxBuildXmlParam(Map<String, String> paraMap, String sign) {
        List<String> keyList = new ArrayList();
        keyList.addAll(paraMap.keySet());
        Collections.sort(keyList);
        StringBuilder xmlParam = new StringBuilder();
        xmlParam.append("<xml>");
        Iterator var6 = keyList.iterator();

        while(var6.hasNext()) {
            String key = (String)var6.next();
            String value = (String)paraMap.get(key);
            xmlParam.append("<").append(key).append(">").append("<![CDATA[").append(value).append("]]>").append("</").append(key).append(">");
        }

        xmlParam.append("<sign><![CDATA[").append(sign).append("]]></sign>").append("</xml>");
        return xmlParam.toString();
    }

    private String sendWeiXinXmlPost(String xmlParam, String url, boolean useCert, WxPayConfig wxPayConfig) {
        String reponseEntity = null;
        org.apache.http.client.HttpClient httpClient = null;

        try {
            httpClient = HttpClientBuilder.create().setConnectionManager(useCert ? this.loadWeiXinCertConnectionManager(wxPayConfig) : null).build();
        } catch (Exception var13) {
            Exception e = var13;
            logger.error("加载微信双向证书错误");
            e.printStackTrace();
        }

        HttpPost httpPost = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(0).setConnectTimeout(0).build();
        httpPost.setConfig(requestConfig);
        StringEntity postEntity = new StringEntity(xmlParam, "UTF-8");
        httpPost.addHeader("Content-Type", "text/xml");
        httpPost.setEntity(postEntity);

        try {
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            reponseEntity = EntityUtils.toString(httpEntity, "UTF-8");
        } catch (IOException var12) {
            IOException e = var12;
            logger.error("发送微信请求失败," + e.getMessage());
            e.printStackTrace();
        }

        return reponseEntity;
    }
}
