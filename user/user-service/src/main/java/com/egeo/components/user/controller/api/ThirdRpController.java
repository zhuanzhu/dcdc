package com.egeo.components.user.controller.api;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.belerweb.social.bean.Result;
import com.belerweb.social.weixin.api.OAuth2;
import com.belerweb.social.weixin.api.Weixin;
import com.belerweb.social.weixin.bean.AccessToken;
import com.egeo.common.BusinessExceptionConstant;
import com.egeo.components.config.client.CompanyConfigClient;
import com.egeo.components.config.dto.CompanyConfigDTO;
import com.egeo.components.user.business.LoginManage;
import com.egeo.components.user.business.ThirdCompanyUserManage;
import com.egeo.components.user.business.UserCookieManage;
import com.egeo.components.user.business.UserExtendManage;
import com.egeo.components.user.business.UserLoginManage;
import com.egeo.components.user.business.UserManage;
import com.egeo.components.user.business.util.SsoClientUtil;
import com.egeo.components.user.constant.UserLoginConstant;
import com.egeo.components.user.dao.write.WxOpenidMapper;
import com.egeo.components.user.dto.User;
import com.egeo.components.user.dto.WxOpenidDTO;
import com.egeo.components.user.vo.UserCookieVO;
import com.egeo.components.user.vo.UserVO;
import com.egeo.components.utils.weixin.WeiXinUtil;
import com.egeo.entity.CacheUser;
import com.egeo.exception.BusinessException;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.GetToken;
import com.egeo.utils.SpringContextTool;
import com.egeo.utils.StringUtils;
import com.egeo.utils.Upload;
import com.egeo.utils.WorkWeChatUtil;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.utils.http.HttpClientUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;

/**
 * 登陆用户控制器。
 *
 * @author xiaping
 *
 */

@Controller
@RequestMapping("/api/user/auth/thirdRp")
public class ThirdRpController extends BaseSpringController {

	@Resource(name = "login")
	private LoginManage loginManage;

	@Resource(name = "userLogin")
	private UserLoginManage userLoginManage;

	@Resource(name = "userCookie")
	private UserCookieManage userCookieManage;

	@Resource(name = "ssoClientUtil")
	private SsoClientUtil ssoClientUtil;

	@Resource(name = "user")
	private UserManage userManage;
	
	@Resource(name = "userExtend")
	private UserExtendManage userExtendManage;
	

	@Resource/*(name = "companyConfig")*/
	private CompanyConfigClient companyConfig;

	@Resource
	private JedisUtil jedisUtil;
/*
	@Resource(name = "wechatProvider")
	private Provider provider;*/

	@Autowired
	private Upload uploadService;

	@Autowired
	private WorkWeChatUtil workWeChatUtil;

	@Autowired
	private WxOpenidMapper wxOpenIdManage;
	@Autowired
	private GetToken getToken;

	

	/**
	 * 1.微信登录界面调取,获取跳转微信授权页面的链接
	 * 
	 * @param user
	 * @param req
	 * @param resp
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "toWeiXinLogin")
	@ResponseBody
	public JsonResult<String> toWeiXinLogin(String cc,Long platformId,HttpServletRequest req, HttpServletResponse resp) throws IOException {
		logger.info("获取微信跳转页面");
		if(StringUtils.isBlank(cc)) {
			return fail("参数异常");
		}
		List<CompanyConfigDTO> configs = companyConfig.queryCompanyconfigsByCode(cc);
		logger.info("参数配置异常-没有公司code配置");

		if(configs==null||configs.size()==0) {
			return fail("参数异常");
		}
		String appId = null;
		String appSecret = null;
		String redirectUrl = null;		
		String toWeixinLoginPage = null;

		for(CompanyConfigDTO config : configs) {
			if(config.getKey().equalsIgnoreCase("wx.app.id")) {
				appId = config.getValue();
			}else if(config.getKey().equalsIgnoreCase("wx.app.secret")) {
				appSecret = config.getValue();
			}else if(config.getKey().equalsIgnoreCase("wx.rp.domain")) {
				redirectUrl = config.getValue();
			}else if(config.getKey().equalsIgnoreCase("wx.rp.page")) {
				toWeixinLoginPage = config.getValue();
			}
		}
		if(StringUtils.isBlank(appId)||StringUtils.isBlank(appSecret)||StringUtils.isBlank(redirectUrl)) {
			logger.info("参数配置异常-appid、appSecret、redirectUrl为空");

			return fail("参数异常");
		}
		Weixin weixin = new Weixin(appId, appSecret,
				redirectUrl +( StringUtils.isBlank(toWeixinLoginPage)?"":("/" + toWeixinLoginPage +"")), null);
		String url = weixin.getOAuth2().authorize();
		
		logger.info("##redirect url:" + url);
//		resp.sendRedirect(url);
		return success(url);
	}

	/**
	 * 根据微信票据微信登录
	 * 
	 * @param user
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping(value = "weiXinLogin")
	@ResponseBody
	public JsonResult<String> weiXinLoginCall(String weiXinCode,String cc,
			String loginType,Long keyMessage,
			HttpServletRequest req, HttpServletResponse resp) {
		logger.error("根据微信票据微信登录,weiXinCode:{}",weiXinCode);
		logger.info("[参数]loginType="+loginType);
		logger.info("[参数]keyMessage="+keyMessage);
		Long platformId = null;
		String str = req.getHeader("platformId");
		if (EmptyUtil.isNotEmpty(str)) {
			platformId = Long.valueOf(str);
		}
		String deviceId = req.getHeader("deviceId");
		if (EmptyUtil.isEmpty(deviceId))
			return fail("设备编号参数缺失");
		String clientId_ = req.getHeader("clientId");
		if (EmptyUtil.isEmpty(clientId_))
			return fail("客户端参数缺失");
		Long clientId = Long.valueOf(clientId_);
		if(weiXinCode == null)
			return fail("微信票据参数缺失");

		List<CompanyConfigDTO> configs = companyConfig.queryCompanyconfigsByCode(cc);

		if(configs==null||configs.size()==0) {
			logger.info("参数配置异常-没有公司code配置");
			return fail("参数异常");
		}
		
		String weiXinOpenId = null;
		String appId = null;
		String appSecret = null;
		String redirectUrl = null;		
		String toWeixinLoginPage = null;
		String thirdDataBean = null; 
		String weixinOpenIdUrl  = null; 
		for(CompanyConfigDTO config : configs) {
			if(config.getKey().equalsIgnoreCase("wx.app.id")) {
				appId = config.getValue();
			}else if(config.getKey().equalsIgnoreCase("wx.app.secret")) {
				appSecret = config.getValue();
			}else if(config.getKey().equalsIgnoreCase("wx.rp.url")) {
				redirectUrl = config.getValue();
			}else if(config.getKey().equalsIgnoreCase("wx.login.page")) {
				toWeixinLoginPage = config.getValue();
			}else if(config.getKey().equalsIgnoreCase("company.user.bean")) {
				thirdDataBean = config.getValue();
			}else if(config.getKey().equalsIgnoreCase("wx.rp.access_token.url")) {
				weixinOpenIdUrl = config.getValue();
			}
		}
		if(StringUtils.isBlank(appId)||StringUtils.isBlank(appSecret)||StringUtils.isBlank(redirectUrl)||StringUtils.isBlank(weixinOpenIdUrl)) {
			logger.info("参数配置异常-appid、appSecret、redirectUrl,weixinOpenIdUrl为空");

			return fail("参数异常");
		}
		//Weixin weixin = new Weixin(appId, appSecret,
		//		redirectUrl +( StringUtils.isBlank(toWeixinLoginPage)?"":("/" + toWeixinLoginPage +"")), null);
		//OAuth2 oAuth2 = weixin.getOAuth2();

		//Result<AccessToken> accessToken = oAuth2.accessToken(weiXinCode);
		//https://api.weixin.qq.com/sns/oauth2/access_token?appid=replaceAppId&secret=replaceSecret&code=replaceCode&state=&grant_type=authorization_code
		weixinOpenIdUrl = weixinOpenIdUrl.replace("replaceAppId", appId).replace("replaceSecret", appSecret).replace("replaceCode", weiXinCode);
		String jsonStr = HttpClientUtil.doGet(weixinOpenIdUrl);
		
		JSONObject token = JSON.parseObject(jsonStr);
		if(token == null || token.getString("openid")==null){
			weiXinOpenId = loginManage.findAccessOpenId(weiXinCode);
			if(weiXinOpenId == null)
				return fail("请重新获取微信授权");
		}else{
			loginManage.saveWeiXinOpenId(weiXinCode,token.getString("openid"));
			weiXinOpenId = token.getString("openid");
		}
		return success(redirectUrl.replace("replaceWxopenId", weiXinOpenId));
		
		
	}

	@RequestMapping(value = "weiXinLoginByWeiXinOpenId")
	@ResponseBody
	public JsonResult<String> weiXinLoginByWeiXinOpenId(String weiXinOpenId,String loginType,Long keyMessage,String cc, HttpServletRequest req, HttpServletResponse resp) {
		logger.info("根据微信OpenId微信登录,weiXinOpenId : {} ,loginType:{},keyMessage:{}",weiXinOpenId,loginType,keyMessage);
		Long platformId = null;
		String str = req.getHeader("platformId");
		if (EmptyUtil.isNotEmpty(str)) {
			platformId = Long.valueOf(str);
		}
		String deviceId = req.getHeader("deviceId");
		if (EmptyUtil.isEmpty(deviceId))
			return fail("设备编号参数缺失");
		String clientId_ = req.getHeader("clientId");
		if (EmptyUtil.isEmpty(clientId_))
			return fail("客户端参数缺失");
		if (EmptyUtil.isEmpty(cc))
			return fail("企业编码缺失");
		Long clientId = Long.valueOf(clientId_);
		if(weiXinOpenId==null) {
			weiXinOpenId = req.getHeader("weiXinOpenId");
		}
		if(weiXinOpenId == null )
			return fail("微信OpenId参数缺失,请重新获取授权");
		// 根据微信OpenId查询关联的用户信息

		List<CompanyConfigDTO> configs = companyConfig.queryCompanyconfigsByCode(cc);

		if(configs==null||configs.size()==0) {
			logger.info("参数配置异常-没有公司code配置");
			return fail("参数异常");
		}
		String appId = null;
		String appSecret = null;
		String redirectUrl = null;		
		String toWeixinLoginPage = null;
		String thirdDataBean = null; 
		String weixinOpenIdUrl  = null; 
		for(CompanyConfigDTO config : configs) {
			if(config.getKey().equalsIgnoreCase("wx.app.id")) {
				appId = config.getValue();
			}else if(config.getKey().equalsIgnoreCase("wx.app.secret")) {
				appSecret = config.getValue();
			}else if(config.getKey().equalsIgnoreCase("wx.rp.url")) {
				redirectUrl = config.getValue();
			}else if(config.getKey().equalsIgnoreCase("wx.login.page")) {
				toWeixinLoginPage = config.getValue();
			}else if(config.getKey().equalsIgnoreCase("company.user.bean")) {
				thirdDataBean = config.getValue();
			}else if(config.getKey().equalsIgnoreCase("wx.rp.access_token.url")) {
				weixinOpenIdUrl = config.getValue();
			}
		}
		if(StringUtils.isBlank(appId)||StringUtils.isBlank(appSecret)||StringUtils.isBlank(redirectUrl)||StringUtils.isBlank(weixinOpenIdUrl)) {
			logger.info("参数配置异常-appid、appSecret、redirectUrl,weixinOpenIdUrl为空");

			return fail("参数异常");
		}

		return success(redirectUrl.replace("replaceWxopenId", weiXinOpenId));
	}
}
