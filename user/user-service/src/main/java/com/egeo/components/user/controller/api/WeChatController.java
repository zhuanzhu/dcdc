package com.egeo.components.user.controller.api;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.utils.weixin.WeiXinJsSdkUtil;
import com.egeo.components.utils.weixin.WeiXinUtil;
import com.egeo.utils.StringUtils;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;

/**
 * 微信接口类
 *
 * @author min
 *
 */

@Controller
@RequestMapping("/api/user/weChat")
public class WeChatController extends BaseSpringController {
	
	public Logger logger = LoggerFactory.getLogger(WeChatController.class);
	
	@Resource
	private  JedisUtil cache;
	
    /**
     * 获取jsapi认证的signature
     * @return
     */
    @RequestMapping(value = "getSign")
	@ResponseBody
	public JsonResult<Map<String, Object>> sign(HttpServletRequest request) {
		logger.info("获取微信签名");
		Long platformId = null;
		String str = request.getHeader("platformId");
		if (StringUtils.isNotEmpty(str)) {
			platformId = Long.valueOf(str);
		}
		Map<String, Object> ret = new HashMap<String, Object>();
		String url = request.getParameter("url");
		String jsapi_ticket = WeiXinJsSdkUtil.getJsapiTicket(platformId, cache);
		if (StringUtils.isEmpty(jsapi_ticket)) {
			return JsonResult.fail("微信扫码授权失败");
		}
		String nonce_str = create_nonce_str();
		String timestamp = create_timestamp();
		String signature = "";
		// 注意这里参数名必须全部小写，且必须有序
		String string1 = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonce_str + "&timestamp=" + timestamp + "&url="
				+ url;
		logger.info("string1:" + string1);
		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(string1.getBytes("UTF-8"));
			signature = byteToHex(crypt.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		ret.put("appId", WeiXinUtil.getWeixin(platformId).getAppId());
		ret.put("url", url);
		ret.put("jsapi_ticket", jsapi_ticket);
		ret.put("nonceStr", nonce_str);
		ret.put("timestamp", timestamp);
		ret.put("signature", signature);
		logger.info("appId:" + WeiXinUtil.getWeixin(platformId).getAppId());
		logger.info("url:" + url);
		logger.info("jsapi_ticket:" +jsapi_ticket);
		logger.info("nonceStr:" + nonce_str);
		logger.info("timestamp:" + timestamp);
		logger.info("signature:" + signature);
		return JsonResult.success(ret);
	}
    
	private static String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}

	private static String create_nonce_str() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	private static String create_timestamp() {
		return Long.toString(System.currentTimeMillis() / 1000);
	}

	/**
	 * 发送get请求
	 * 
	 * @param url
	 *            路径
	 * @return
	 */
	private String httpGet(String url) {
		// get请求返回结果
		String strResult = null;
		try {
			DefaultHttpClient client = new DefaultHttpClient();
			// 发送get请求
			HttpGet request = new HttpGet(url);
			HttpResponse response = client.execute(request);

			/** 请求发送成功，并得到响应 **/
			if (response.getStatusLine().getStatusCode() == org.apache.http.HttpStatus.SC_OK) {
				/** 读取服务器返回过来的json字符串数据 **/
				strResult = EntityUtils.toString(response.getEntity());
			} else {
				logger.error("get请求提交失败:" + url);
			}
		} catch (IOException e) {
			logger.error("get请求提交失败:" + url, e);
		}
		return strResult;
	}

	/**
	 * 校验是否关注公众号
	 */
	@RequestMapping("/checkIsFollow")
	@ResponseBody
	public JsonResult checkIsFollow(String openId,HttpServletRequest req){
		logger.info("[校验是否关注公众号]参数,openId"+openId);
		String str = req.getHeader("platformId");
		if(StringUtils.isEmpty(str)){
			return JsonResult.fail("platformId不能为空");
		}
		Long platformId = Long.valueOf(str);
		Integer isFollow = WeiXinJsSdkUtil.getIsFollow(platformId, cache, openId);
		logger.info("[校验是否关注]结果:(int)"+isFollow);
		Boolean result=(StringUtils.isNotEmpty(isFollow)&&isFollow==1) ? true : false;
		logger.info("[校验是否关注]结果:"+result);
		return JsonResult.success(result);

	}

}
