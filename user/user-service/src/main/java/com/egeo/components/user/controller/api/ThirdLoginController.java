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
@RequestMapping("/api/user/auth/thirdLogin")
public class ThirdLoginController extends BaseSpringController {

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
	 * 后台登录
	 *
	 * @param user
	 * @param req
	 * @param resp
	 * @return
	 */
	/*@RequestMapping(value = "login")
	@ResponseBody*/
	public JsonResult<User> login(UserVO user, HttpServletRequest req, HttpServletResponse resp) {

		logger.info("EgeoBusinessLoggerTrace登陆方法被调用,用户名称：" + user.getName());
		logger.info("messageLog" + "登陆方法被调用,用户名称：" + user.getName());

		checkUserName(user);
		System.out.println(user.getName().length());
		JsonResult<CacheUser> rt = loginManage.login(user);
		int code = rt.getCode();// code 等于 0表示登陆成功

		if (code == 0) { // 登陆成功
			UserCookieVO userCookie = null;
			CacheUser cacheUser = (CacheUser) rt.getData();

			// 写缓存 cookie
			userCookie = ssoClientUtil.login(cacheUser, req, resp);

			// 写登陆日志
			userLoginManage.insertLoginLogWithTx(rt, req,UserLoginConstant.LOGIN_TYPE_NORMAL.getStatus(),user.getKeyMessage(),user.getStoreId());

			User userfz = new User();
			userfz.setCookieValue(userCookie.getCookieValue());
			userfz.setName(rt.getData().getName());
			userfz.setId(rt.getData().getId());
			userfz.setType(rt.getData().getType());
			userfz.setPlatformId(user.getPlatformId());
			// 设置当前用户是否是超级管理员
			userfz.setIsSuperAdministrator(rt.getData().getId().equals(1L) ? 1 : 0);
			return success(userfz);
		} else {
			userLoginManage.insertLoginExceptionLogWithTx(code, user, req);
			return fail(code, rt.getError());
		}
	}


	/**
	 * 用户会员普通登录
	 *
	 * @param user
	 * @param req
	 * @param resp
	 * @return
	 */
	/*@RequestMapping(value = "mobileLogin")
	@ResponseBody*/
	public JsonResult<User> mobileLogin(UserVO user, HttpServletRequest req, HttpServletResponse resp) {

		logger.info(" [用户会员普通登录mobileLogin],用户名称：" + user.getName());
		String deviceId = req.getHeader("deviceId");
		if (EmptyUtil.isEmpty(deviceId))
			return fail("设备编号参数缺失");
		String clientId_ = req.getHeader("clientId");
		if (EmptyUtil.isEmpty(clientId_))
			return fail("客户端参数缺失");
		Long clientId = Long.valueOf(clientId_);
		String weiXinOpenId = req.getHeader("weiXinOpenId");
		logger.info("微信openId="+weiXinOpenId);
		checkUserName(user);
		user.setWeiXinOpenId(weiXinOpenId);
		JsonResult<CacheUser> rt = loginManage.mobileLogin(user, deviceId, clientId);
		int code = rt.getCode();// code 等于 0表示登陆成功

		if (code == 0) { // 登陆成功
			// 登录成功后写登录cookie、登陆日志
			logger.info("[mobileLogin登录成功]");
			return success(saveUserCookieLoginLog(rt, user, deviceId, clientId, req, resp));
		} else {
			userLoginManage.insertLoginExceptionLogWithTx(code, user, req);
			return fail(code, rt.getError());
		}
	}

	/**
	 * web用户会员普通登录
	 *
	 * @param user
	 * @param req
	 * @param resp
	 * @return
	 */
	/*@RequestMapping(value = "webUserLogin")
	@ResponseBody*/
	public JsonResult<User> webUserLogin(UserVO user, HttpServletRequest req, HttpServletResponse resp) {

		logger.info(" 登陆方法被调用,用户名称：" + user.getName());
		String deviceId = req.getHeader("deviceId");
		if (EmptyUtil.isEmpty(deviceId))
			return fail("设备编号参数缺失");
		String clientId_ = req.getHeader("clientId");
		/*if (EmptyUtil.isEmpty(clientId_))
			return fail("客户端参数缺失");*/
		Long clientId = Long.valueOf(clientId_);
		checkUserName(user);
		JsonResult<CacheUser> rt = loginManage.webUserLogin(user, deviceId, clientId);
		int code = rt.getCode();// code 等于 0表示登陆成功

		if (code == 0) { // 登陆成功
			// 登录成功后写登录cookie、登陆日志
			return success(saveUserCookieLoginLog(rt, user, deviceId, clientId, req, resp));
		} else {
			userLoginManage.insertLoginExceptionLogWithTx(code, user, req);
			return fail(code, rt.getError());
		}
	}

	private void checkUserName(UserVO user) {

		String name = user.getName();
		if (StringUtils.isEmpty(name)) {
			throw new BusinessException("登录用户名不能为空");
		}
		if (name.contains("@")) {// 邮箱登陆
			// 所有字母转小写
			name = name.toLowerCase();
			user.setMail(name);

		} else if (StringUtils.validMobile(name)) {// 手机号登陆
			user.setMobile(name);
		} else {// 用户名登陆
			user.setLoginName(name);
		}

	}

	@RequestMapping(value = "indexLogin")
	@ResponseBody
	public JsonResult<String> indexLogin() {
		return success("登录成功");

	}

	/**
	 * 用户手机登录
	 * @param mobile 手机号
	 * @param identityCode 验证码
	 * @param companyId 公司id app端登录不传查询所有用户，慢有优微信登录传递 是否为个人：0否1是
	 * @param channelName
	 * @param campaignCode
	 * @param registerShopCode
	 * @param platformId
	 * @param req
	 * @param resp
	 * @return
	 */
	/*@RequestMapping(value = "userMobileLogin")
	@ResponseBody*/
	public JsonResult<User> userMobileLogin(
			String type,
			String mobile,
			String identityCode,
			Long companyId,
			String channelName,
			String campaignCode,
			String registerShopCode,
			String loginType,
			Long keyMessage,
			Long platformId, HttpServletRequest req,
			HttpServletResponse resp) {
		logger.info("用户手机登录,手机号：{}", mobile);
		logger.info("[参数]type="+type);
		logger.info("[参数]mobile="+mobile);
		logger.info("[参数]identityCode="+identityCode);
		logger.info("[参数]campaignCode="+campaignCode);
		logger.info("[参数]registerShopCode="+registerShopCode);
		logger.info("[参数]channelName="+channelName);
		logger.info("[参数]loginType="+loginType);
		logger.info("[参数]keyMessage="+keyMessage);
		String weiXinOpenId = req.getHeader("weiXinOpenId");
		logger.info("[参数]weiXinOpenId="+weiXinOpenId);

		String deviceId = req.getHeader("deviceId");

		String clientId_ = req.getHeader("clientId");
		if (EmptyUtil.isEmpty(clientId_))
			return fail("客户端参数缺失");
		Long clientId = Long.valueOf(clientId_);

		String device_type = req.getHeader("f");
		Integer deviceType = null;
    	if(StringUtils.isNotFigure(device_type)){
    		deviceType = Integer.valueOf(device_type);
    	}else{
    		return fail("设备类型参数缺失");
    	}

    	String version = req.getHeader("v");
    	if(EmptyUtil.isEmpty(version)){
    		return fail("版本号参数缺失");
    	}

		if(clientId.equals(2L) || clientId.equals(4L)){
			if(EmptyUtil.isEmpty(weiXinOpenId)){
				return fail("微信OpenId参数缺失");
			}
		}
		if(platformId == null)
			return fail("平台id参数缺失");
		// 判断手机号和验证码
		if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(identityCode)) {
			throw new BusinessException("手机号或验证码不能为空");
		}
		if (!StringUtils.validMobile(mobile)) {
			throw new BusinessException("手机号格式错误");
		}
		logger.info("clientId="+clientId_);
		// 判断验证码是否正确
		JsonResult<CacheUser> rt = loginManage.mobileLogin(type,mobile, identityCode, platformId, deviceId,
				Long.valueOf(clientId_),weiXinOpenId,channelName,campaignCode,registerShopCode,deviceType,version);
		int code = rt.getCode();// code 等于 0表示登陆成功

		// 根据用户手机号查询用户信息
		List<UserVO> userList = userManage.findByMobileAndRegister(mobile,platformId);

		// 慢有优平台用户如果存在多个则全部返回
		if(platformId == 2 && userList.size() > 1){
			UserVO latestLoginUser = userManage.findLatestLoginByMobile(mobile, platformId);
			if (EmptyUtil.isEmpty(latestLoginUser)) {
				latestLoginUser = userList.get(0);
				if(EmptyUtil.isNotEmpty(latestLoginUser)){
					logger.info("latestLoginUser="+latestLoginUser.getId());
				}
			}
			latestLoginUser.setWeiXinOpenId(weiXinOpenId);
			JsonResult<User> result = new JsonResult<>();
			User user2 = saveUserCookieLoginLog(rt, latestLoginUser, deviceId, clientId, req, resp);
			User user = new User();
			user.setId(user2.getId());
			user.setName(user2.getName());
			user.setCookieValue(user2.getCookieValue());
			user.setOpenId(user2.getOpenId());
			List<Map<String, Object>> userMapList = new ArrayList<>(userList.size());
			for (UserVO userVO : userList) {
				Map<String, Object> map = new HashMap<>();
				map.put("id", userVO.getId());
				map.put("companyId", userVO.getCompanyId());
				userMapList.add(map);
			}
			user.setUserList(userMapList);
			result.setData(user);
			result.setCode(1);
			return result;
		}
		UserVO userVO = userList.get(0);
		userVO.setLoginType(loginType);
		userVO.setKeyMessage(keyMessage);
		userVO.setWeiXinOpenId(weiXinOpenId);
		if (code == 0) { // 登陆成功
			// 登录成功后写登录cookie、登陆日志
			return success(saveUserCookieLoginLog(rt, userVO, deviceId, clientId, req, resp));
		} else {
			userLoginManage.insertLoginExceptionLogWithTx(code, userVO, req);
			return fail(code, rt.getError());
		}

	}

	/*private UserVO getEnterpriseUser(List<UserVO> userList) {
		UserVO userVO = null;
		if (EmptyUtil.isNotEmpty(userList)) {
			for (UserVO user : userList) {
				if (EmptyUtil.isEmpty(user.getCompanyId()) || !user.getCompanyId().equals(2L)) {
					userVO = user;
				}
			}
		}
		return userVO;
	}*/

	private void fugjLoginFlow() {
		// TODO Auto-generated method stub

	}

	/**
	 * 管理员用户App用户手机登录
	 *
	 * @param mobile
	 * @param identityCode
	 * @param platformId
	 * @param req
	 * @param resp
	 * @return
	 */
	/*@RequestMapping(value = "adminUserAppMobileLogin")
	@ResponseBody*/
	public JsonResult<User> adminUserAppMobileLogin(String mobile, String identityCode, Long platformId, HttpServletRequest req,
			HttpServletResponse resp) {
		logger.info("用户手机登录,手机号：{}", mobile);
		String deviceId = req.getHeader("deviceId");

		String clientId_ = req.getHeader("clientId");
		if (EmptyUtil.isEmpty(clientId_))
			return fail("客户端参数缺失");
		Long clientId = Long.valueOf(clientId_);

		String device_type = req.getHeader("f");
		Integer deviceType = null;
    	if(StringUtils.isNotFigure(device_type)){
    		deviceType = Integer.valueOf(device_type);
    	}else{
    		return fail("设备类型参数缺失");
    	}

		String weiXinOpenId = req.getHeader("weiXinOpenId");
		if(platformId == null)
			return fail("平台id参数缺失");
		// 判断手机号和验证码
		if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(identityCode)) {
			throw new BusinessException("手机号或验证码不能为空");
		}
		if (!StringUtils.validMobile(mobile)) {
			throw new BusinessException("手机号格式错误");
		}
		// 判断验证码是否正确
		JsonResult<CacheUser> rt = loginManage.adminUserAppMobileLogin(mobile, identityCode, platformId, deviceId,
				Long.valueOf(clientId_),deviceType,weiXinOpenId);
		int code = rt.getCode();// code 等于 0表示登陆成功

		// 根据用户手机号查询用户信息
		UserVO userVO = userManage.findAdminUserByManage(mobile,platformId);
		userVO.setWeiXinOpenId(weiXinOpenId);
		if (code == 0) { // 登陆成功
			// 登录成功后写登录cookie、登陆日志
			return success(saveUserCookieLoginLog(rt, userVO, deviceId, clientId, req, resp));
		} else {
			userLoginManage.insertLoginExceptionLogWithTx(code, userVO, req);
			return fail(code, rt.getError());
		}
	}

	// 登录成功后写登录cookie、登陆日志
	private User saveUserCookieLoginLog(JsonResult<CacheUser> rt, UserVO userVO, String deviceId, Long clientId,
										HttpServletRequest req, HttpServletResponse resp) {
		logger.info("登录成功后写登录cookie、登陆日志");
		UserCookieVO userCookie = null;
		User userfz = new User();
		CacheUser cacheUser = (CacheUser) rt.getData();
		logger.info("IsRedis="+cacheUser.getIsRedis());
		// 是否为缓存返回数据：0、不是 1、是
		if (cacheUser.getIsRedis() != 1) {
			// 写缓存 cookie  若cacheUser不是缓存中来的
			//这里写入的是tokenkey  --  tokenBody的缓存
			userCookie = ssoClientUtil.login(cacheUser, req, resp);

			// 对于会员，使ut和用户信息在cache中进行关联
			//这里写入的是userUuid  --- tokenkey 的缓存，可以通过用户信息查到tokenkey
			loginManage.saveUttoCache(userVO, userCookie, deviceId, clientId);
			// 写登陆cookie表
			userCookie.setUserId(cacheUser.getId());
			userCookie.setCompanyId(cacheUser.getCompanyId());
			userCookie.setPlatformId(cacheUser.getPlatformId());
			userCookie.setClientId(clientId);
			userCookieManage.saveUserCookieWithTx(userCookie, req);

			userfz.setCookieValue(userCookie.getCookieValue());
		} else {
			userCookie = new UserCookieVO();
			userCookie.setCookieValue(cacheUser.getCookieValue());
			// 对于会员，使ut和用户信息在cache中进行关联
			loginManage.saveUttoCache(userVO, userCookie, deviceId, clientId);
			userfz.setCookieValue(cacheUser.getCookieValue());
		}

		if(userVO.getLoginType() == null) {
			userVO.setLoginType(UserLoginConstant.LOGIN_TYPE_NORMAL.getStatus());
		}
		// 写登陆日志
		userLoginManage.insertLoginLogWithTx(rt, req,userVO.getLoginType(),userVO.getKeyMessage(),userVO.getStoreId());
		if(userVO.getIsWorkWechat()==1){
			userfz.setIsWorkWechat(Integer.valueOf(1));
		}
		userfz.setOpenId(userVO.getWeiXinOpenId());
		logger.info("openId="+userVO.getWeiXinOpenId());
		userfz.setName(rt.getData().getName());
		userfz.setId(rt.getData().getId());
		userfz.setMail(rt.getData().getMail());
		return userfz;
	}



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
			}else if(config.getKey().equalsIgnoreCase("wx.domain.name")) {
				redirectUrl = config.getValue();
			}else if(config.getKey().equalsIgnoreCase("wx.login.page")) {
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
	public JsonResult<User> weiXinLoginCall(String weiXinCode,String cc,
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
		for(CompanyConfigDTO config : configs) {
			if(config.getKey().equalsIgnoreCase("wx.app.id")) {
				appId = config.getValue();
			}else if(config.getKey().equalsIgnoreCase("wx.app.secret")) {
				appSecret = config.getValue();
			}else if(config.getKey().equalsIgnoreCase("wx.domain.name")) {
				redirectUrl = config.getValue();
			}else if(config.getKey().equalsIgnoreCase("wx.login.page")) {
				toWeixinLoginPage = config.getValue();
			}else if(config.getKey().equalsIgnoreCase("company.user.bean")) {
				thirdDataBean = config.getValue();
			}
		}
		if(StringUtils.isBlank(appId)||StringUtils.isBlank(appSecret)||StringUtils.isBlank(redirectUrl)) {
			logger.info("参数配置异常-appid、appSecret、redirectUrl为空");

			return fail("参数异常");
		}
		//Weixin weixin = new Weixin(appId, appSecret,
		//		redirectUrl +( StringUtils.isBlank(toWeixinLoginPage)?"":("/" + toWeixinLoginPage +"")), null);
		//OAuth2 oAuth2 = weixin.getOAuth2();

		//Result<AccessToken> accessToken = oAuth2.accessToken(weiXinCode);
		String weixinOpenIdUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+appId+"&secret="+appSecret+"&code="+weiXinCode+"&state=&grant_type=authorization_code";
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
		loginManage.saveWeiXinOpenId(weiXinCode,weiXinOpenId);

		// 根据微信OpenId查询关联的用户信息
		UserVO userVO = loginManage.findByWeiXinOpenId(weiXinOpenId, platformId);
		if (EmptyUtil.isEmpty(userVO)) {
			//如果没有用户信息，则获取第三方数据
			if(!StringUtils.isBlank(thirdDataBean)) {
				ThirdCompanyUserManage thirdCompanyUserManage = SpringContextTool.getBean(thirdDataBean);
				userVO = thirdCompanyUserManage.getUserInfoByOpenId(weiXinOpenId, configs,keyMessage+"");
			}
			if(EmptyUtil.isEmpty(userVO)) {
				JsonResult<User> result = new JsonResult<>();
				result.setCode(BusinessExceptionConstant.NO_BINDING_USER);
				result.setError("请先登录");
				User user = new User();
				user.setOpenId(weiXinOpenId);
				result.setData(user);
				return result;
			}
		}

		List<WxOpenidDTO> userOpenids= wxOpenIdManage.findUserOpenIds(userVO.getId(), weiXinOpenId);
		if(userOpenids==null || userOpenids.size()==0) {
			WxOpenidDTO dto = new WxOpenidDTO();

			dto.setCompanyId(userVO.getCompanyId());
			dto.setCreateTime(new Date());
			dto.setUserId(userVO.getId());
			dto.setWxOpenid(weiXinOpenId);
			dto.setWxAppid(appId);
			wxOpenIdManage.insert(dto);
		}
		userVO.setKeyMessage(keyMessage);
		if(loginType != null) {
			userVO.setLoginType(loginType);
		}else {
			userVO.setLoginType(UserLoginConstant.LOGIN_TYPE_FREE.getStatus());
		}
		return judgeBindingUser(userVO,deviceId,clientId,platformId,weiXinOpenId,req,resp);
	}


	/**
	 * 根据微信票据获取微信OpenId
	 *
	 * @param user
	 * @param req
	 * @param resp
	 * @return
	 */
	/*@RequestMapping(value = "getWeiXinOpenIdByWeiXinCode")
	@ResponseBody*/
	public JsonResult<Map<String, Object>> getWeiXinOpenIdByWeiXinCode(String weiXinCode, HttpServletRequest req, HttpServletResponse resp) {
		logger.error("根据微信票据微信登录,weiXinCode:{}",weiXinCode);
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

		String weiXinOpenId = null;
		OAuth2 oAuth2 = WeiXinUtil.getWeixin(platformId).getOAuth2();
		Result<AccessToken> accessToken = oAuth2.accessToken(weiXinCode);
		AccessToken token = accessToken.getResult();
		// 获取微信OpenId
		// 判断token是否为空、如果空则说明打了2次、微信票据已使用，微信票据只能使用一次
		if(token == null){
			weiXinOpenId = loginManage.findAccessOpenId(weiXinCode);
			if(weiXinOpenId == null)
				return fail("请重新获取微信授权");
		}
		// 微信token不为空保存redis缓存，设置一分钟过期
		else{
			/*Message message = new Message(MsgType.TEXT);
		    message.setToUser(token.getOpenId());
		    message.setContent("恭喜你，登录成功");
		    Result<Boolean> result1 = WeiXinUtil.getWeixin().sendCustomMessage(message);
		    logger.info("微信通知：" + JSONArray.toJSONString(result1));*/
			loginManage.saveWeiXinOpenId(weiXinCode,token.getOpenId());
			weiXinOpenId = token.getOpenId();

		}
		loginManage.saveWeiXinOpenId(weiXinCode,weiXinOpenId);
		// 根据微信OpenId查询关联的用户信息
		UserVO userVO = loginManage.findByWeiXinOpenId(weiXinOpenId, platformId);
		// 返回微信OpenId和是否有效
		Map<String, Object> map = new HashMap<>();
		map.put("weiXinOpenId", weiXinOpenId);
		map.put("isValid", EmptyUtil.isNotEmpty(userVO));
		return success(map);
	}

	/**
	 * 根据微信OpenId微信登录
	 *
	 * @param user
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping(value = "weiXinLoginByWeiXinOpenId")
	@ResponseBody
	public JsonResult<User> weiXinLoginByWeiXinOpenId(String weiXinOpenId,String loginType,Long keyMessage,String cc, HttpServletRequest req, HttpServletResponse resp) {
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


		UserVO userVO = loginManage.findByWeiXinOpenId(weiXinOpenId, platformId);

		if (EmptyUtil.isEmpty(userVO)) {
			return fail("请先登录");
		}



		List<WxOpenidDTO> useropenids = wxOpenIdManage.findUserOpenIds(userVO.getId(), weiXinOpenId);
		boolean hasOpenId = false;

		Long openId = null;
		if(useropenids==null || useropenids.size()==0) {
			List<WxOpenidDTO> openids = wxOpenIdManage.findByOpenId(weiXinOpenId);
			for(WxOpenidDTO openid :  openids) {
				if(!hasOpenId && openid.getUserId()==null) {
					hasOpenId = true;
					openId = openid.getId();
				}
			}
			if(hasOpenId) {
				wxOpenIdManage.updateUserId(openId, userVO.getId(),userVO.getCompanyId());
			}else {

				if(openids!=null && openids.size()>0) {
					String appid = openids.get(0).getWxAppid();
					WxOpenidDTO dto = new WxOpenidDTO();

					dto.setCompanyId(userVO.getCompanyId());
					dto.setCreateTime(new Date());
					dto.setUserId(userVO.getId());
					dto.setWxOpenid(weiXinOpenId);
					dto.setWxAppid(appid);
					wxOpenIdManage.insert(dto);
				}
			}
		}

		userVO.setLoginType(loginType);
		userVO.setKeyMessage(keyMessage);
		return judgeBindingUser(userVO,deviceId,clientId,platformId,weiXinOpenId,req,resp);
	}
	/**
	 * 微信是否绑定用户
	 * @param userVO
	 * @param deviceId
	 * @param clientId
	 * @param req
	 * @param resp
	 * @return
	 */
	private JsonResult<User> judgeBindingUser(UserVO userVO,String deviceId,Long clientId,Long platformId,String weiXinOpenId,HttpServletRequest req, HttpServletResponse resp) {
		logger.info("微信是否绑定用户");
		if(EmptyUtil.isNotEmpty(userVO)){
			userVO.setPlatformId(platformId);
			userVO.setWeiXinOpenId(weiXinOpenId);
			// 权限判断并且更新ut
			JsonResult<CacheUser> rt = loginManage.switchoverLogin(userVO, deviceId, clientId);

			int code = rt.getCode();// code 等于 0表示登陆成功

			if (code == 0) { // 登陆成功
				// 登录成功后写登录cookie、登陆日志
				User user = saveUserCookieLoginLog(rt, userVO, deviceId, clientId, req, resp);
				user.setOpenId(userVO.getWeiXinOpenId());
				return success(user);
			} else {
				userLoginManage.insertLoginExceptionLogWithTx(code, userVO, req);
				return fail(code, rt.getError());
			}
		}else{
			JsonResult<User> result = new JsonResult<>();
			result.setCode(BusinessExceptionConstant.NO_BINDING_USER);
			result.setError("微信未绑定激活账号，请绑定账号");
			User user = new User();
			user.setOpenId(weiXinOpenId);
			result.setData(user);
			return result;
		}
	}



	public static Map<String, String> parseXml(String request) throws Exception {
		// 将解析结果存储在HashMap中
		Map<String, String> map = new HashMap<String, String>();

		// 从request中取得输入流
		InputStream inputStream = new ByteArrayInputStream(request.getBytes("UTF-8"));
		// 读取输入流
		SAXReader reader = new SAXReader();
		Document document = reader.read(inputStream);
		// 得到xml根元素
		Element root = document.getRootElement();
		// 得到根元素的所有子节点
		List<Element> elementList = root.elements();

		// 遍历所有子节点
		for (Element e : elementList)
			map.put(e.getName(), e.getText());

		// 释放资源
		inputStream.close();
		inputStream = null;

		return map;
	}


	private String getPostData(HttpServletRequest req){
		try {
			//从请求中读取整个post数据
			InputStream inputStream = req.getInputStream();
			//commons.io.jar 方法
			String post = IOUtils.toString(inputStream, "UTF-8");

			logger.info("post为:"+post);
			return post;
		}catch (Exception e){

			logger.error("获取post请求体异常:",e);
			return "";
		}
	}


	/**
	 * 根据微信票据微信用户注册登录
	 *
	 * @param user
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping(value = "weiXinCodeLoginAndResitUser")
	@ResponseBody
	public JsonResult<User> weiXinCodeLoginAndResitUser(String weiXinCode,String cc,
											String loginType,String keyMessage,
											HttpServletRequest req, HttpServletResponse resp) {
		logger.error("根据微信票据微信登录,cc:{},weiXinCode:{},loginType:{},keyMessage:【{}】",cc,weiXinCode,loginType,keyMessage);
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
		for(CompanyConfigDTO config : configs) {
			if(config.getKey().equalsIgnoreCase("wx.app.id")) {
				appId = config.getValue();
			}else if(config.getKey().equalsIgnoreCase("wx.app.secret")) {
				appSecret = config.getValue();
			}else if(config.getKey().equalsIgnoreCase("wx.domain.name")) {
				redirectUrl = config.getValue();
			}else if(config.getKey().equalsIgnoreCase("wx.login.page")) {
				toWeixinLoginPage = config.getValue();
			}else if(config.getKey().equalsIgnoreCase("company.user.bean")) {
				thirdDataBean = config.getValue();
			}
		}
		if(StringUtils.isBlank(appId)||StringUtils.isBlank(appSecret)||StringUtils.isBlank(redirectUrl)) {
			logger.info("参数配置异常-appid、appSecret、redirectUrl为空");

			return fail("参数异常");
		}
		//Weixin weixin = new Weixin(appId, appSecret,
		//		redirectUrl +( StringUtils.isBlank(toWeixinLoginPage)?"":("/" + toWeixinLoginPage +"")), null);
		//OAuth2 oAuth2 = weixin.getOAuth2();

		//Result<AccessToken> accessToken = oAuth2.accessToken(weiXinCode);
		String weixinOpenIdUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+appId+"&secret="+appSecret+"&code="+weiXinCode+"&state=&grant_type=authorization_code";
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
		loginManage.saveWeiXinOpenId(weiXinCode,weiXinOpenId);

		// 根据微信OpenId查询关联的用户信息
		UserVO userVO = loginManage.findByWeiXinOpenId(weiXinOpenId, platformId);
		if (EmptyUtil.isEmpty(userVO)) {
			//如果没有用户信息，则获取第三方数据
			if(!StringUtils.isBlank(thirdDataBean)) {
				ThirdCompanyUserManage thirdCompanyUserManage = SpringContextTool.getBean(thirdDataBean);
				userVO = thirdCompanyUserManage.getUserInfoByOpenId(weiXinOpenId, configs,keyMessage);
			}
			if(EmptyUtil.isEmpty(userVO)) {
				JsonResult<User> result = new JsonResult<>();
				result.setCode(BusinessExceptionConstant.NO_BINDING_USER);
				result.setError("请先登录");
				User user = new User();
				user.setOpenId(weiXinOpenId);
				result.setData(user);
				return result;
			}
		}

		List<WxOpenidDTO> userOpenids= wxOpenIdManage.findUserOpenIds(userVO.getId(), weiXinOpenId);
		if(userOpenids==null || userOpenids.size()==0) {
			WxOpenidDTO dto = new WxOpenidDTO();

			dto.setCompanyId(userVO.getCompanyId());
			dto.setCreateTime(new Date());
			dto.setUserId(userVO.getId());
			dto.setWxOpenid(weiXinOpenId);
			dto.setWxAppid(appId);
			wxOpenIdManage.insert(dto);
		}
		//userVO.setKeyMessage(keyMessage);
		if(loginType != null) {
			userVO.setLoginType(loginType);
		}else {
			userVO.setLoginType(UserLoginConstant.LOGIN_TYPE_FREE.getStatus());
		}
		return judgeBindingUser(userVO,deviceId,clientId,platformId,weiXinOpenId,req,resp);
	}
}
