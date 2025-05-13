package com.egeo.components.utils.weixin;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.belerweb.social.http.Http;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.cache.JedisUtil;

public class WeiXinJsSdkUtil {
	
	public static Logger logger = LoggerFactory.getLogger(WeiXinJsSdkUtil.class);
	
	private static final String JSAPI_ACCESS_TOKEN_KEY = "jsapi_access_token_key";
	
	private static final String JSAPI_TICKET_KEY = "jsapi_ticket_key_";
	
	private static final int EXPIRE_SECONDS = 7100;
	
	/**
	 * 获取jsapiTicket
	 * @param platformId
	 * @param cache
	 * @return
	 */
	public static String getJsapiTicket(Long platformId, JedisUtil cache) {
		try {
			String accessToken = getAccessToken(platformId, cache);
			String jsapiTicket = (String) cache.get(JSAPI_TICKET_KEY + platformId);
			if (EmptyUtil.isEmpty(jsapiTicket)) {
				jsapiTicket = getRemoteJsapiTicket(accessToken);
				logger.info("微信获取jsapiTicket为:" + jsapiTicket);
				cache.set(JSAPI_TICKET_KEY + platformId, EXPIRE_SECONDS, jsapiTicket);
			}
			return jsapiTicket;
		} catch (Exception e) {
			logger.error("获取微信jsapi_ticket失败", e);
		}
		return null;
	}
	
	/**
	 * 获取accessToken
	 * @param platformId
	 * @param cache
	 * @return
	 */
	public static String getAccessToken(Long platformId, JedisUtil cache) {
		String accessToken = (String) cache.get(JSAPI_ACCESS_TOKEN_KEY+platformId);
		if (EmptyUtil.isEmpty(accessToken)) {
			accessToken = WeiXinUtil.getWeixin(platformId).getAccessToken().getToken();
			logger.info("微信获取accessToken为:" + accessToken);
			cache.set(JSAPI_ACCESS_TOKEN_KEY+platformId, EXPIRE_SECONDS, accessToken);
		}
		return accessToken;
	}
	
	/**
	 * 调用微信接口获取jsapi_ticket
	 * @param accessToken
	 * @return
	 * @throws Exception
	 */
	private static String getRemoteJsapiTicket(String accessToken) throws Exception {
		String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + accessToken + "&type=jsapi";
		logger.info("调用微信get_jsapi_ticket,url:" + url);
		String rsp = Http.get(url, new ArrayList<NameValuePair>());
		logger.info("微信get_jsapi_ticket返回:" + rsp);
		JSONObject rspObj = JSON.parseObject(rsp);
		if (rspObj != null) {
			if (rspObj.getIntValue("errcode") == 0) {
				return rspObj.getString("ticket");
			}
		}
		return null;
	}


	/**
	 * 调取微信接口判断用户是否已关注公众号
	 */
	public static Integer getIsFollow(Long platformId, JedisUtil cache,String openid){
		Integer subscribe = null;
		String token=getAccessToken(platformId,cache);
		logger.info("platformId="+platformId);
		logger.info("cache="+cache);
		logger.info("token="+token);
		String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token="+token+"&openid="+openid+"&lang=zh_CN";
		try {
			URL urlGet = new URL(url);
			HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
			http.setRequestMethod("GET"); // 必须是get方式请求
			http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
			http.setDoOutput(true);
			http.setDoInput(true);
			http.connect();
			InputStream is = http.getInputStream();
			int size = is.available();
			byte[] jsonBytes = new byte[size];
			is.read(jsonBytes);
			String message = new String(jsonBytes, "UTF-8");
			logger.info("message:"+message);
			JSONObject demoJson = JSONObject.parseObject(message);
			subscribe = demoJson.getInteger("subscribe");

			is.close();
		} catch (Exception e) {
			logger.info("关注校验出错",e);
			e.printStackTrace();
		}
		return subscribe;
	}

}
