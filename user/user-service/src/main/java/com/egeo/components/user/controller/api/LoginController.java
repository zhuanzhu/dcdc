package com.egeo.components.user.controller.api;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.egeo.components.user.business.util.ChannelUserUtils;
import com.egeo.components.user.business.util.DLFUserUtils;
import com.egeo.components.user.common.Constants;
import com.egeo.components.user.enums.UserChannelSourceEnum;
import com.egeo.components.user.vo.ChannelUserUniqueRegLoginVO;
import com.egeo.config.RuntimeContext;
import com.egeo.components.user.business.util.UserLockUtils;
import org.apache.commons.io.IOUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.belerweb.social.bean.Result;
import com.belerweb.social.weixin.api.OAuth2;
import com.belerweb.social.weixin.api.Weixin;
import com.belerweb.social.weixin.bean.AccessToken;
import com.egeo.common.BusinessExceptionConstant;
import com.egeo.common.CacheKeyConstant;
import com.egeo.components.user.business.LoginManage;
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
import com.egeo.components.user.vo.UserExtendVO;
import com.egeo.components.user.vo.UserVO;
import com.egeo.components.utils.JsonUtils;
import com.egeo.components.utils.weixin.WeiXinUtil;
import com.egeo.entity.CacheUser;
import com.egeo.exception.BusinessException;
import com.egeo.util.security.MD5Support;
import com.egeo.utils.ActiveMQUtils;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.GetToken;
import com.egeo.utils.StringUtils;
import com.egeo.utils.UUIDBuild;
import com.egeo.utils.Upload;
import com.egeo.utils.WorkWeChatUtil;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;

/**
 * 登陆用户控制器。
 *
 * @author xiaping
 *
 */

@Controller
@RequestMapping("/api/user/auth/login")
public class LoginController extends BaseSpringController {

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
	private GetToken getToken;

	@Autowired
	private WxOpenidMapper wxOpenIdManage;
	@Resource
	private UserLockUtils userLockUtils;
	@Resource
	private DLFUserUtils dlfUserUtils;
	@Resource
	private ChannelUserUtils channelUserUtils;
	/**
	 * 后台登录
	 *
	 * @param user
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping(value = "login")
	@ResponseBody
	public JsonResult<User> login(UserVO user, HttpServletRequest req, HttpServletResponse resp) {

		logger.info("EgeoBusinessLoggerTrace登陆方法被调用,用户名称：" + user.getName());
		logger.info("messageLog" + "登陆方法被调用,用户名称：" + user.getName());

		checkUserName(user);
		System.out.println(user.getName().length());
		String lockMsg=userLockUtils.lockMsg(user.getName());
		if (EmptyUtil.isNotEmpty(lockMsg)){
			return fail(BusinessExceptionConstant.USER_PASSWORD_OR_USERNAME_WRONG,
					String.format("该账号密码错误超过5次,锁定30分钟,%s后解除锁定",lockMsg));
		}
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
			userLockUtils.unLock(user.getName());
			return success(userfz);
		} else {
			userLockUtils.lock(user.getName());
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
	@RequestMapping(value = "mobileLogin")
	@ResponseBody
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

			List<WxOpenidDTO> useropenids = wxOpenIdManage.findUserOpenIds(rt.getData().getId(), weiXinOpenId);
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
					wxOpenIdManage.updateUserId(openId, rt.getData().getId(),rt.getData().getCompanyId());
				}else {

					if(openids!=null && openids.size()>0) {
						String appid = openids.get(0).getWxAppid();
						WxOpenidDTO dto = new WxOpenidDTO();

						dto.setCompanyId(rt.getData().getCompanyId());
						dto.setCreateTime(new Date());
						dto.setUserId(rt.getData().getId());
						dto.setWxOpenid(weiXinOpenId);
						dto.setWxAppid(appid);
						wxOpenIdManage.insert(dto);
					}
				}
			}


			return success(saveUserCookieLoginLog(rt, user, deviceId, clientId, req, resp));
		} else {
			userLoginManage.insertLoginExceptionLogWithTx(code, user, req);
			return fail(code, rt.getError());
		}
	}


	/**
	 * 清美同登接口
	 * @param jumpUrl
	 * @return
	 */
	@RequestMapping(value = "mtLogin")
	@ResponseBody
	public JsonResult<String> mtLogin(String jumpUrl) {
		CacheUser cacheUser= RuntimeContext.cacheUser();
		String page=userLoginManage.mtLogin(cacheUser.getId(),jumpUrl);
		return success(page);
	}

		/**
         * web用户会员普通登录
         *
         * @param user
         * @param req
         * @param resp
         * @return
         */
	@RequestMapping(value = "webUserLogin")
	@ResponseBody
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
	@RequestMapping(value = "userMobileLogin")
	@ResponseBody
	public JsonResult<User> userMobileLogin(
			String type,
			String mobile,
			String identityCode,
			Boolean checkIdentityCode,
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
		if (EmptyUtil.isEmpty(checkIdentityCode)){
			checkIdentityCode=true;
		}
		// 判断手机号和验证码
		if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(identityCode) && checkIdentityCode) {
			throw new BusinessException("手机号或验证码不能为空");
		}
		if (!StringUtils.validMobile(mobile)) {
			throw new BusinessException("手机号格式错误");
		}
		logger.info("clientId="+clientId_);
		// 判断验证码是否正确
		JsonResult<CacheUser> rt = loginManage.mobileLogin(type,mobile, identityCode, platformId, deviceId,
				Long.valueOf(clientId_),weiXinOpenId,channelName,campaignCode,registerShopCode,deviceType,version,checkIdentityCode);
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

			List<WxOpenidDTO> useropenids = wxOpenIdManage.findUserOpenIds(rt.getData().getId(), weiXinOpenId);
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
					wxOpenIdManage.updateUserId(openId, rt.getData().getId(),rt.getData().getCompanyId());
				}else {

					if(openids!=null && openids.size()>0) {
						String appid = openids.get(0).getWxAppid();
						WxOpenidDTO dto = new WxOpenidDTO();

						dto.setCompanyId(rt.getData().getCompanyId());
						dto.setCreateTime(new Date());
						dto.setUserId(rt.getData().getId());
						dto.setWxOpenid(weiXinOpenId);
						dto.setWxAppid(appid);
						wxOpenIdManage.insert(dto);
					}
				}
			}



			return success(saveUserCookieLoginLog(rt, userVO, deviceId, clientId, req, resp));
		} else {
			userLoginManage.insertLoginExceptionLogWithTx(code, userVO, req);
			return fail(code, rt.getError());
		}

	}


	@RequestMapping(value = "userMobileRegisterAndLogin")
	@ResponseBody
	public JsonResult<User> userMobileRegisterAndLogin(String type, String mobile, String identityCode,Boolean checkIdentityCode,
													   String channelName, String campaignCode, String registerShopCode,
													   String loginType, Long keyMessage, Long platformId,String channelsource,
													   HttpServletRequest req, HttpServletResponse resp) {
		logger.info("用户手机登录,手机号：{},[参数]type={},[参数]mobile={},[参数]identityCode={}," +
				"[参数]campaignCode={},[参数]registerShopCode={},[参数]channelName={}," +
						"[参数]loginType={},[参数]keyMessage={},[参数]channelsource={},[参数]checkIdentityCode={}",
				mobile,type,mobile,identityCode,campaignCode,registerShopCode,channelName,loginType,
				keyMessage,channelsource,checkIdentityCode);
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
		if (EmptyUtil.isEmpty(checkIdentityCode)){
			checkIdentityCode=true;
		}
		// 判断手机号和验证码
		if (StringUtils.isEmpty(mobile) || (StringUtils.isEmpty(identityCode) && checkIdentityCode)) {
			throw new BusinessException("手机号或验证码不能为空");
		}
		if (!StringUtils.validMobile(mobile)) {
			throw new BusinessException("手机号格式错误");
		}
		logger.info("clientId="+clientId_);
		loginManage.checkIdentityCode(mobile,identityCode,checkIdentityCode);
		List<UserVO> userList = userManage.findByMobileAndRegister(mobile,platformId);
		boolean needRegister=false;
		Long companyId=null;
		if (EmptyUtil.isNotEmpty(userList)){
			if (Objects.equals(UserChannelSourceEnum.DLF.getChannelSource(),channelsource)){
				userList=userList.stream().filter(userVO -> Objects.equals(UserChannelSourceEnum.DLF.getChannelSource(),userVO.getChannelSource())).collect(Collectors.toList());
				if (EmptyUtil.isEmpty(userList) && dlfUserUtils.checkUser(mobile)){
					needRegister=true;
					companyId=DLFUserUtils.companyId;
				}
			}
		}else {
			needRegister=true;
			if (Objects.equals(UserChannelSourceEnum.DLF.getChannelSource(),channelsource) && dlfUserUtils.checkUser(mobile)){
				companyId=DLFUserUtils.companyId;
			}
		}
		if (needRegister){
			//注册
            UserVO registerUser=userManage.registDefaultCompanyUserByMobile(mobile, Constants.USER_DEFAULT_PWD,weiXinOpenId,
					platformId,companyId);
            if (EmptyUtil.isEmpty(registerUser)){
                throw new BusinessException("用户登录注册失败");
            }
		}
		// 判断验证码是否正确
		JsonResult<CacheUser> rt = loginManage.mobileLogin(type,mobile, identityCode, platformId, deviceId,
				Long.valueOf(clientId_),weiXinOpenId,channelName,campaignCode,
				registerShopCode,deviceType,version,checkIdentityCode);
		int code = rt.getCode();// code 等于 0表示登陆成功

		// 根据用户手机号查询用户信息
		userList = userManage.findByMobileAndRegister(mobile,platformId);

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
			List<WxOpenidDTO> useropenids = wxOpenIdManage.findUserOpenIds(rt.getData().getId(), weiXinOpenId);
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
					wxOpenIdManage.updateUserId(openId, rt.getData().getId(),rt.getData().getCompanyId());
				}else {
					if(openids!=null && openids.size()>0) {
						String appid = openids.get(0).getWxAppid();
						WxOpenidDTO dto = new WxOpenidDTO();

						dto.setCompanyId(rt.getData().getCompanyId());
						dto.setCreateTime(new Date());
						dto.setUserId(rt.getData().getId());
						dto.setWxOpenid(weiXinOpenId);
						dto.setWxAppid(appid);
						wxOpenIdManage.insert(dto);
					}
				}
			}
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
	@RequestMapping(value = "adminUserAppMobileLogin")
	@ResponseBody
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
	 * 企业微信授权登录后的回调
	 * @param companyId
	 * @param response
	 */

	@RequestMapping(value ="workWechatRedirectUrl",method = RequestMethod.GET)
	@ResponseBody
	public void workWechatRedirectUrl(String code,String state,HttpServletResponse resp,HttpServletRequest req){
		logger.info("企业微信授权登录后的回调");
		try {
			String url = workWeChatUtil.FORWORD_URI+ "?code="+code+"&state="+state;
			logger.info("url:"+url);
			//req.getRequestDispatcher(url).forward(req,resp);
			//HttpClientUtil.doGet(url);
			resp.sendRedirect(url);
		} catch (Exception e) {
			logger.error("企业微信授权登录后的回调请求转发异常");
		}
	}

	/**
	 * 企业微信授权登录
	 */

	@RequestMapping(value ="workWechatOauth2",method = RequestMethod.GET)
	@ResponseBody
	public void workWechatOauth2(HttpServletResponse response){
		logger.info("企业微信授权");
		//进行授权认证
		try {
			userManage.workWechatOAuth(response);
		} catch (IOException e) {
			logger.error("重定向授权url异常",e);
			throw new BusinessException("重定向授权url异常");
		}


	}
	/* 企业微信授权登录*/

	@RequestMapping(value ="workWechatLogin",method = RequestMethod.POST)
	@ResponseBody
	public JsonResult workWechatLogin(String state,String code,HttpServletRequest req,HttpServletResponse resp,Integer type){
		logger.info("企业微信授权登录");
		logger.info("参数传递state："+state);
		logger.info("参数传递code："+code);
		if(EmptyUtil.isEmpty(state)){
			logger.error("连接有误");
			return fail("连接有误");
		}
		if(EmptyUtil.isEmpty(code)){
			logger.error("企业微信授权失败");
			return fail("企业微信授权失败");

		}

		UserVO user = new UserVO();
		user.setIsWorkWechat(Integer.valueOf(1));
		String deviceId = "";
		//进行授权认证和创建/登录
		JsonResult<CacheUser> rt = null;
		logger.info("步骤一");
		//if(EmptyUtil.isNotEmpty(type)){
			try {
				rt = loginManage.workWechatLogin(deviceId,user,code);
				logger.info("登录成功");
			} catch (UnsupportedEncodingException e) {
				logger.error("构建url异常");
				throw new BusinessException("构建url异常");
			}
		//}
		int resCode = rt.getCode();// code 等于 0表示登陆成功

		if (resCode == 0) { // 登陆成功
			// 登录成功后写登录cookie、登陆日志
			return success(saveUserCookieLoginLog(rt, user, deviceId, 4L, req, resp));
		} else {
			userLoginManage.insertLoginExceptionLogWithTx(resCode, user, req);
			return fail(resCode, rt.getError());
		}
	}

	@RequestMapping("saveUserByMail")
	@ResponseBody
	public JsonResult<String> saveUserByMail(String code,String id,String mail){
		logger.info("userId="+id);
		if(EmptyUtil.isEmpty(id)){
			return fail("userId缺失");
		}
		if(	EmptyUtil.isEmpty(mail)){
			return fail("邮箱缺失");
		}
		String caseMail = null;
		//所有字母转小写
		if(EmptyUtil.isNotEmpty(mail)){
			caseMail = mail.toLowerCase();
		}

//    		String platformId_=req.getHeader("platformId");
//    		if(EmptyUtil.isEmpty(platformId_))
//    			return fail("platformId为空");
//    		Long platformId=Long.parseLong(platformId_);
		if(EmptyUtil.isEmpty(caseMail)){
			return fail("邮箱不能为空");
		}
		//根据邮箱获取放入缓存中的验证码
		String redisVerificationCode = (String) jedisUtil.get(CacheKeyConstant.CHANGE_MOBILE_KEY+caseMail);
		if(EmptyUtil.isEmpty(redisVerificationCode)){
			return fail("验证码过期、请重新发送");
		}
		if(!redisVerificationCode.equals(MD5Support.MD5(code, caseMail))){
			throw new BusinessException(BusinessExceptionConstant.VERIFICATIONcODE_WRONG,"验证码错误");
		}
		// 使用成功之后删除缓存
		jedisUtil.del(caseMail);
		userManage.saveUserByMail(id,mail);
		return success("ok");
	}

	@RequestMapping(value = "workWechatHandAuth")
	@ResponseBody
	public void workWechatHandAuth(HttpServletResponse response,HttpServletRequest req){
		//管理员手动授权
		String suiteAccessToken = jedisUtil.getString(WorkWeChatUtil.PRE_JEDIS_SUITE_ACCESS_TOKEN_KEY);
		if(EmptyUtil.isEmpty(suiteAccessToken)) {
			//主动查询accesstoken
			suiteAccessToken = userManage.getWorkWeChatSuiteAccessToken(jedisUtil);
			if (EmptyUtil.isEmpty(suiteAccessToken)) {
				throw new BusinessException("获取微信suiteaccesstoken失败");
			}
		}

		String res = workWeChatUtil.getPreAuthCode(suiteAccessToken);
		logger.info("res="+res);
		JSONObject jsonObject = JSONObject.parseObject(res);
		String preAuthCode=jsonObject.getString("pre_auth_code");
		if(EmptyUtil.isEmpty(preAuthCode)){
			throw new BusinessException("获取预授权码失败");
		}
		try {
			String url = workWeChatUtil.HAND_AUTH_URL + "suite_id="+workWeChatUtil.SuiteID+"&pre_auth_code="+preAuthCode+"&redirect_uri="+URLEncoder.encode(workWeChatUtil.PERMANENT_CODE_REDIRECT_URI,"UTF-8")+"&state=STATE";
			try {
				logger.info("重定向地址："+url);
				//req.getRequestDispatcher(url).forward(req,response);
				//HttpClientUtil.doGet(url);
				response.sendRedirect(url);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}



	}

	@RequestMapping(value = "getAuthPermanentcode")
	@ResponseBody
	public void getAuthPermanentcode(HttpServletRequest req){
		String authCode=req.getParameter("auth_code");
		logger.info("auth_code="+authCode);
		loginManage.saveWorkWechatAuthInfo(authCode);
	}



	/**
	 * 根据用户id切换登录
	 *
	 * @param userId
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "switchoverLogin")
	@ResponseBody
	public JsonResult<User> switchoverLogin(Long userId, HttpServletRequest req, HttpServletResponse resp) {
		logger.info("根据用户id切换登录,用户id：{}",userId);
		Long platformId = null;
		String str = req.getHeader("platformId");
		if (EmptyUtil.isNotEmpty(str)) {
			platformId = Long.valueOf(str);
		}else{
			return fail("平台id不能为空");
		}
		CacheUser userCache = this.getCacheUser();
		String mobile = userCache.getMobile();

		if (EmptyUtil.isEmpty(mobile)) {
			throw new BusinessException("用户手机为空，切换失败");
		}
		String deviceId = req.getHeader("deviceId");
		if (EmptyUtil.isEmpty(deviceId))
			return fail("设备编号参数缺失");
		String clientId_ = req.getHeader("clientId");
		if (EmptyUtil.isEmpty(clientId_))
			return fail("客户端参数缺失");
		Long clientId = Long.valueOf(clientId_);
		String weiXinOpenId = req.getHeader("weiXinOpenId");
		if(clientId.equals(2L) || clientId.equals(4L)){
			if(EmptyUtil.isEmpty(weiXinOpenId)){
				return fail("微信OpenId参数缺失");
			}
		}
		// 根据用户手机号及用户id判断该手机号是否绑定该用户并返回数据
		UserVO userVO = userManage.findByUserIdMobile(userId, mobile,platformId);
		userVO.setPlatformId(platformId);
		userVO.setWeiXinOpenId(weiXinOpenId);
		// 权限判断并且更新ut
		JsonResult<CacheUser> rt = loginManage.switchoverLogin(userVO, deviceId, clientId);

		int code = rt.getCode();// code 等于 0表示登陆成功

		if (code == 0) { // 登陆成功
			// 登录成功后写登录cookie、登陆日志

			List<WxOpenidDTO> useropenids = wxOpenIdManage.findUserOpenIds(rt.getData().getId(), weiXinOpenId);
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
					wxOpenIdManage.updateUserId(openId, rt.getData().getId(),rt.getData().getCompanyId());
				}else {
					if(openids!=null && openids.size()>0) {
						String appid = openids.get(0).getWxAppid();
						WxOpenidDTO dto = new WxOpenidDTO();

						dto.setCompanyId(rt.getData().getCompanyId());
						dto.setCreateTime(new Date());
						dto.setUserId(rt.getData().getId());
						dto.setWxOpenid(weiXinOpenId);
						dto.setWxAppid(appid);
						wxOpenIdManage.insert(dto);
					}
				}
			}

			User user = saveUserCookieLoginLog(rt, userVO, deviceId, clientId, req, resp);
			if(Long.valueOf(2L).equals(clientId)){
				//如果是微信端
				UserExtendVO userExtendVO = new UserExtendVO();
				userExtendVO.setId(userVO.getId());
				userExtendVO.setWeixin(userVO.getWeiXinOpenId());
				userExtendManage.updateUserExtendWithTx(userExtendVO);
			}
			return success(user);
		} else {
			userLoginManage.insertLoginExceptionLogWithTx(code, userVO, req);
			return fail(code, rt.getError());
		}
	}

	@RequestMapping(value = "signout")
	@ResponseBody
	public JsonResult<String> signout(HttpServletRequest req, HttpServletResponse res) {
		logger.info("账号退出");
		CacheUser cacheUser = this.getCacheUser();
		req.setAttribute("ut", null);
		req.getSession().invalidate();
		userExtendManage.signoutByUserId(cacheUser.getId());
		return success("退出成功");

	}

	@RequestMapping(value = "getQiNiuToken")
	@ResponseBody
	public JsonResult<String> getQiNiuToken() {
		JsonResult<String> result = new JsonResult<String>();
		logger.error("获取七牛token！");
		try {
			String string = getToken.getToken();
			result.setData(string);
			return result;
		} catch (Exception e) {
			logger.error("获取七牛token异常！", e);
			result.setCode(1);
			result.setError("获取七牛token失败:" + e.getMessage());
			return result;
		}
	}

	@RequestMapping(value = "uploading")
	@ResponseBody
	public JsonResult<String> uploading(@RequestParam(required = true) MultipartFile eCardUploadfile) {
		JsonResult<String> result = new JsonResult<String>();
		logger.error("上传文件至dfs服务器");
		// 将上传的文件写到磁盘
		String originalFilename = eCardUploadfile.getOriginalFilename();
		// 写入磁盘的文件
		File file = new File(UUIDBuild.getUUID() + originalFilename.substring(originalFilename.lastIndexOf(".")));
		String dfsUpload = uploadService.fastDFSUpload(file, originalFilename.substring(originalFilename.lastIndexOf(".")));
		result.setData(dfsUpload);
		return result;
	}

	/**
	 * 微信登录界面调取
	 *
	 * @param user
	 * @param req
	 * @param resp
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "toWeiXinLogin")
	@ResponseBody
	public JsonResult<String> toWeiXinLogin(Long platformId,HttpServletRequest req, HttpServletResponse resp) throws IOException {
		logger.info("获取微信跳转页面");
		String url = WeiXinUtil.getWeixin(platformId).getOAuth2().authorize();
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
	public JsonResult<User> weiXinLoginCall(String weiXinCode,
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

		String weiXinOpenId = null;
		Weixin wx = WeiXinUtil.getWeixin(platformId);

		OAuth2 oAuth2 = wx.getOAuth2();

		String appId = wx.getAppId();
		// String weiXinCode = req.getParameter("code");
		Result<AccessToken> accessToken = oAuth2.accessToken(weiXinCode);
		logger.info("##accessToken:" + JsonUtils.objectToJson(accessToken));
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
		if (EmptyUtil.isEmpty(userVO)) {
			JsonResult<User> result = new JsonResult<>();
			result.setCode(BusinessExceptionConstant.NO_BINDING_USER);
			result.setError("请先登录");
			User user = new User();
			user.setOpenId(weiXinOpenId);

			List<WxOpenidDTO> openids = wxOpenIdManage.findByOpenIdNotUser(weiXinOpenId);
			if(openids==null || openids.size()==0) {

				WxOpenidDTO dto = new WxOpenidDTO();

				dto.setWxOpenid(weiXinOpenId);
				dto.setWxAppid(appId);
				wxOpenIdManage.insert(dto);
			}


			result.setData(user);
			return result;
		}

		//登录的时候判断该用户的appid和openid是否存在
		List<WxOpenidDTO> wxOpenidLogs = wxOpenIdManage.findUserOpenIds(userVO.getId(),weiXinOpenId);
		if(wxOpenidLogs==null || wxOpenidLogs.size()==0) {
			//更新openid数据
			WxOpenidDTO dto = new WxOpenidDTO();
			dto.setCompanyId(userVO.getCompanyId());
			dto.setCreateTime(new Date());
			dto.setUserId(userVO.getId());
			dto.setWxOpenid(weiXinOpenId);
			dto.setWxAppid(wx.getAppId());
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
	@RequestMapping(value = "getWeiXinOpenIdByWeiXinCode")
	@ResponseBody
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
		Weixin wx = WeiXinUtil.getWeixin(platformId);
		OAuth2 oAuth2 = wx.getOAuth2();
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
		if (EmptyUtil.isEmpty(userVO)) {

			List<WxOpenidDTO> openids = wxOpenIdManage.findByOpenIdNotUser(weiXinOpenId);
			if(openids==null || openids.size()==0) {

				WxOpenidDTO dto = new WxOpenidDTO();

				dto.setWxOpenid(weiXinOpenId);
				dto.setWxAppid(wx.getAppId());
				wxOpenIdManage.insert(dto);
			}

		}
		return success(map);
	}


	/**
	 * 通过jsCode获取小程序openId
	 * @param jsCode
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "getOpenIdByMiniProgramCode")
	@ResponseBody
	public JsonResult<Map<String, Object>> getOpenIdByMiniProgramCode(String jsCode,String encryptedData,String iv,
																	  HttpServletRequest req) {
		logger.error("根据微信小程序jsCode获取openid,jsCode:{}",jsCode);
		Long platformId = null;
		String str = req.getHeader("platformId");
		if (EmptyUtil.isNotEmpty(str)) {
			platformId = Long.valueOf(str);
		}
		String deviceId = req.getHeader("deviceId");
		if (EmptyUtil.isEmpty(deviceId)){
			return fail("设备编号参数缺失");
		}
		String clientId_ = req.getHeader("clientId");
		if (EmptyUtil.isEmpty(clientId_)){
			return fail("客户端参数缺失");
		}
		if(EmptyUtil.isEmpty(jsCode)){
			return fail("微信票据参数缺失");
		}
		try {
			Map<String, Object> map=userLoginManage.getOpenIdByMiniProgramCode(jsCode,encryptedData,iv,platformId);
			return success(map);
		} catch (BusinessException businessException){
			logger.error("getOpenIdByMiniProgramCode失败{}", businessException.getMessage());
			return fail(businessException.getMessage());
		}catch (Exception e){
			logger.error("getOpenIdByMiniProgramCode失败{}", e);
			return fail("微信登录失败");
		}
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
	public JsonResult<User> weiXinLoginByWeiXinOpenId(String weiXinOpenId,String loginType,Long keyMessage, String channelsource,HttpServletRequest req, HttpServletResponse resp) {
		logger.info("根据微信OpenId微信登录,weiXinOpenId : {} ,loginType:{},keyMessage:{},channelsource:{}",
		weiXinOpenId,loginType,keyMessage,channelsource);
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
		if(weiXinOpenId==null) {
			weiXinOpenId = req.getHeader("weiXinOpenId");
		}
		if(weiXinOpenId == null )
			return fail("微信OpenId参数缺失,请重新获取授权");
		// 根据微信OpenId查询关联的用户信息
		UserVO userVO = loginManage.findByWeiXinOpenId(weiXinOpenId, platformId);

		if (EmptyUtil.isEmpty(userVO)) {
			return fail("请先登录");
		}
		if (EmptyUtil.isNotEmpty(channelsource)
				&& Objects.equals(UserChannelSourceEnum.DLF.getChannelSource(),channelsource)
				&& EmptyUtil.isNotEmpty(userVO.getMobile())){
			List<UserVO> userList = userManage.findByMobileAndRegister(userVO.getMobile(),platformId);
			if (EmptyUtil.isNotEmpty(userList)){
				userList=userList.stream().filter(user ->
								Objects.equals(UserChannelSourceEnum.DLF.getChannelSource(),user.getChannelSource()))
						.collect(Collectors.toList());
				if (EmptyUtil.isEmpty(userList) && dlfUserUtils.checkUser(userVO.getMobile())){
					//德律风用户绑卡需重新登录
					loginManage.weixinSignoutWithTx(userVO.getId(),clientId,platformId,userVO.getMobile());
					return fail("请先登录");
				}
			}
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

	/**
	 * 微信注销
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping(value = "weixinLogOut")
	@ResponseBody
	public JsonResult<Integer> weixinSignout(HttpServletRequest req, HttpServletResponse res) {
		Long platformId = null;
		String str = req.getHeader("platformId");
		if (EmptyUtil.isNotEmpty(str)) {
			platformId = Long.valueOf(str);
		}
		String clientId_ = req.getHeader("clientId");
		if (EmptyUtil.isEmpty(clientId_))
			return fail("客户端参数缺失");
		Long clientId = Long.valueOf(clientId_);
		CacheUser cacheUser = this.getCacheUser();
		logger.info("微信注销,用户id ： {}",cacheUser.getId());
		int i = loginManage.weixinSignoutWithTx(cacheUser.getId(),clientId,platformId,cacheUser.getMobile());
		return success(i);

	}


	/**
	 *企业微信获取suite_ticket推送
	 */
	@RequestMapping(value = "getSuiteTicketFromWorkWeChat")
	@ResponseBody
	public String getSuiteTicketFromWorkWeChat(HttpServletRequest req) {
		logger.info("企业微信推送消息了");

		try{
			WXBizMsgCrypt wxBizMsgCrypt =null;

			String sMsgSignature =  req.getParameter("msg_signature");
			String sTimeStamp = req.getParameter("timestamp");
			String sNonce =  req.getParameter("nonce");
			logger.info("获取参数:sMsgSignature:"+sMsgSignature);
			logger.info("获取参数:sTimeStamp:"+sTimeStamp);
			logger.info("获取参数:sNonce:"+sNonce);
			String method = req.getMethod();

			if(method.equals("POST")){
				wxBizMsgCrypt = new WXBizMsgCrypt(workWeChatUtil.Token, workWeChatUtil.EncodingAESKey, workWeChatUtil.SuiteID);
				String sPostData = getPostData(req);
				logger.info("post请求体为:"+sPostData);
				if(EmptyUtil.isNotEmpty(sPostData)){
					String sMsg = wxBizMsgCrypt.DecryptMsg(sMsgSignature, sTimeStamp, sNonce, sPostData);
					logger.info("消息明文:"+sMsg);
					if(EmptyUtil.isNotEmpty(sMsg)){
						Map<String, String> xmlMap = parseXml(sMsg);
						String infoType = xmlMap.get("InfoType");
						String suiteId=xmlMap.get("SuiteId");
						if(workWeChatUtil.INFO_TYPE_SUITE_TICKET.equals(infoType)){
							String SuiteTicket = xmlMap.get("SuiteTicket");
							if(suiteId.equals(workWeChatUtil.SuiteID)){
								jedisUtil.set("WorkWeChat_SuiteTicket",SuiteTicket);
								logger.info("ticket是:"+SuiteTicket);
								return "success";
							}
						}else if(WorkWeChatUtil.INFO_TYPE_CREATE_AUTH.equals(infoType)){
							String authCode = xmlMap.get("AuthCode");
							if(suiteId.equals(workWeChatUtil.SuiteID)){
								//由于接口比如在1s内给出相应所以使用mq进行后续的销费操作

								try {
									ActiveMQUtils.getSender("temp_auth_code_info").send(authCode);
								}catch (Exception e) {
									// TODO: handle exception
									logger.error("发送"+" temp_auth_code_info "+"消息失败："+ JSON.toJSONString(authCode));
								}
								logger.info("已成功获得临时授权码，并推送成功");
								return "success";
							}
						}
					}
				}
				return "false";



			}else if(method.equals("GET")){
				wxBizMsgCrypt = new WXBizMsgCrypt(workWeChatUtil.Token, workWeChatUtil.EncodingAESKey, workWeChatUtil.CorpID);
				String sEchoStr = req.getParameter("echostr");
				logger.info("获取参数:sEchoStr:"+sEchoStr);
				String sReplyEchoStr = wxBizMsgCrypt.VerifyURL(sMsgSignature, sTimeStamp, sNonce, sEchoStr);
				logger.info("获取参数:sReplyEchoStr:"+sReplyEchoStr);
				return sReplyEchoStr;
			}
			return "false";
		} catch (Exception e) {
			logger.error("创建微信解密器失败",e);
			return "false";
		}


	}
	/**
	 *数据回调url
	 */
	@RequestMapping(value = "dataRedirectUrl")
	@ResponseBody
	public String dataRedirectUrl(HttpServletRequest req) {
		logger.info("企业微信数据回调url");
		//return "success";
		try{
			WXBizMsgCrypt wxBizMsgCrypt =null;

			String sMsgSignature =  req.getParameter("msg_signature");
			String sTimeStamp = req.getParameter("timestamp");
			String sNonce =  req.getParameter("nonce");
			logger.info("获取参数:sMsgSignature:"+sMsgSignature);
			logger.info("获取参数:sTimeStamp:"+sTimeStamp);
			logger.info("获取参数:sNonce:"+sNonce);
			String method = req.getMethod();

			/*if(method.equals("POST")){
				wxBizMsgCrypt = new WXBizMsgCrypt(WorkWeChatUtil.Token, WorkWeChatUtil.EncodingAESKey, WorkWeChatUtil.CorpID);
				String sPostData = getPostData(req);
				logger.info("post请求体为:"+sPostData);
				if(EmptyUtil.isNotEmpty(sPostData)){
					String sMsg = wxBizMsgCrypt.DecryptMsg(sMsgSignature, sTimeStamp, sNonce, sPostData);
					logger.info("消息明文:"+sMsg);
					if(EmptyUtil.isNotEmpty(sMsg)){
						Map<String, String> xmlMap = parseXml(sMsg);
						String infoType = xmlMap.get("InfoType");
						String suiteId=xmlMap.get("SuiteId");
						if(WorkWeChatUtil.INFO_TYPE_SUITE_TICKET.equals(infoType)){
							String SuiteTicket = xmlMap.get("SuiteTicket");
							if(suiteId.equals(WorkWeChatUtil.SuiteID)){
								jedisUtil.set("WorkWeChat_SuiteTicket",SuiteTicket);
								logger.info("ticket是:"+SuiteTicket);
								return "success";
							}
						}else if(WorkWeChatUtil.INFO_TYPE_CREATE_AUTH.equals(infoType)){
							String authCode = xmlMap.get("AuthCode");
							if(suiteId.equals(WorkWeChatUtil.SuiteID)){
								//由于接口比如在1s内给出相应所以使用mq进行后续的销费操作
								provider.publish("temp_auth_code_info",authCode);
								logger.info("已成功获得临时授权码，并推送成功");
								return "success";
							}
						}
					}
					return "success";
				}
				return "false";



			}else */if(method.equals("GET")){
				wxBizMsgCrypt = new WXBizMsgCrypt(workWeChatUtil.Token, workWeChatUtil.EncodingAESKey, workWeChatUtil.CorpID);
				String sEchoStr = req.getParameter("echostr");
				logger.info("获取参数:sEchoStr:"+sEchoStr);
				String sReplyEchoStr = wxBizMsgCrypt.VerifyURL(sMsgSignature, sTimeStamp, sNonce, sEchoStr);
				logger.info("获取参数:sReplyEchoStr:"+sReplyEchoStr);
				return sReplyEchoStr;
			}
			return "false";
		} catch (Exception e) {
			logger.error("创建微信解密器失败",e);
			return "false";
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


	@RequestMapping(value = "channelUserUniqueRegisterAndLogin")
	@ResponseBody
	public JsonResult<User> channelUserUniqueRegisterAndLogin(ChannelUserUniqueRegLoginVO vo,HttpServletRequest req, HttpServletResponse resp) {
		logger.info("渠道用户登录,参数：{}",JSON.toJSONString(vo));
		String platformId_ = req.getHeader("platformId");
		if(EmptyUtil.isEmpty(vo.getPlatformId()) && EmptyUtil.isNotEmpty(platformId_)){
			vo.setPlatformId(Long.valueOf(platformId_));
		}
		Long platformId = vo.getPlatformId();
		String channelUserUnique = vo.getChannelUserUnique();
		String channelsource = vo.getChannelsource();
		String identityCode = vo.getIdentityCode();
		Boolean checkIdentityCode = vo.getCheckIdentityCode();
		String channelName = vo.getChannelName();
		String campaignCode = vo.getCampaignCode();
		String registerShopCode = vo.getRegisterShopCode();
		String loginType = vo.getLoginType();
		Long keyMessage = vo.getKeyMessage();
		String type = vo.getType();
		Long companyId = vo.getCompanyId();
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
		if (EmptyUtil.isEmpty(checkIdentityCode)){
			checkIdentityCode=true;
		}
		// 判断手机号和验证码
		if (StringUtils.isEmpty(channelUserUnique) || (StringUtils.isEmpty(identityCode) && checkIdentityCode)) {
			throw new BusinessException("登录标志或验证码不能为空");
		}
		logger.info("clientId="+clientId_);
		List<UserVO> userList = userManage.findByChannelUserUniqueAndRegister(channelUserUnique,companyId,platformId);
		boolean needRegister=false;
		if (EmptyUtil.isNotEmpty(userList)){
			if (Objects.equals(UserChannelSourceEnum.YD.getChannelSource(),channelsource)){
				userList=userList.stream().filter(userVO -> Objects.equals(UserChannelSourceEnum.YD.getChannelSource(),userVO.getChannelSource())).collect(Collectors.toList());
				if (EmptyUtil.isEmpty(userList) && channelUserUtils.checkUser(channelUserUnique,channelsource,vo)){
					needRegister=true;
					companyId=UserChannelSourceEnum.getCompanyIdByCode(channelsource);
				}
			}
		}else {
			needRegister=true;
			if (EmptyUtil.isNotEmpty(channelsource) && channelUserUtils.checkUser(channelUserUnique,channelsource,vo)){
				companyId=UserChannelSourceEnum.getCompanyIdByCode(channelsource);
			}
		}
		if (needRegister){
			//注册
			UserVO registerUser=userManage.registDefaultCompanyUserByChannelUserUnique(channelUserUnique,channelUserUnique, Constants.USER_DEFAULT_PWD,weiXinOpenId,
					platformId,companyId,vo);
			if (EmptyUtil.isEmpty(registerUser)){
				throw new BusinessException("用户登录注册失败");
			}
		}
		// 判断验证码是否正确
		JsonResult<CacheUser> rt = loginManage.channelUserUniqueLogin(type,channelUserUnique, identityCode, platformId, deviceId,
				Long.valueOf(clientId_),weiXinOpenId,channelName,campaignCode,
				registerShopCode,deviceType,version,checkIdentityCode,companyId);
		// code 等于 0表示登陆成功
		int code = rt.getCode();

		// 根据用户手机号查询用户信息
		userList = userManage.findByChannelUserUniqueAndRegister(channelUserUnique,companyId,platformId);

		// 慢有优平台用户如果存在多个则全部返回
		if(platformId == 2 && userList.size() > 1){
			UserVO latestLoginUser = userManage.findLatestLoginByChannelUserUnique(channelUserUnique,companyId, platformId);
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
			List<WxOpenidDTO> useropenids = wxOpenIdManage.findUserOpenIds(rt.getData().getId(), weiXinOpenId);
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
					wxOpenIdManage.updateUserId(openId, rt.getData().getId(),rt.getData().getCompanyId());
				}else {
					if(openids!=null && openids.size()>0) {
						String appid = openids.get(0).getWxAppid();
						WxOpenidDTO dto = new WxOpenidDTO();

						dto.setCompanyId(rt.getData().getCompanyId());
						dto.setCreateTime(new Date());
						dto.setUserId(rt.getData().getId());
						dto.setWxOpenid(weiXinOpenId);
						dto.setWxAppid(appid);
						wxOpenIdManage.insert(dto);
					}
				}
			}
			return success(saveUserCookieLoginLog(rt, userVO, deviceId, clientId, req, resp));
		} else {
			userLoginManage.insertLoginExceptionLogWithTx(code, userVO, req);
			return fail(code, rt.getError());
		}
	}

	@RequestMapping(value = "userDeviceRegisterAndLogin")
	@ResponseBody
	public JsonResult<User> userDeviceRegisterAndLogin(ChannelUserUniqueRegLoginVO vo,HttpServletRequest req, HttpServletResponse resp) {
		String deviceId = req.getHeader("deviceId");
		if(EmptyUtil.isEmpty(deviceId) || Objects.equals(deviceId,"-1")){
			return fail("设备参数缺失");
		}
		if(EmptyUtil.isEmpty(vo.getDeviceId()) || Objects.equals(vo.getDeviceId(),"-1")){
			return fail("登录标识缺失");
		}
		vo.setChannelUserUnique(vo.getDeviceId());
		logger.info("用户设备登录,参数：{}",JSON.toJSONString(vo));
		if(EmptyUtil.isEmpty(vo.getCheckIdentityCode())){
			vo.setCheckIdentityCode(false);
		}
		String platformId_ = req.getHeader("platformId");
		if(EmptyUtil.isEmpty(vo.getPlatformId()) && EmptyUtil.isNotEmpty(platformId_)){
			vo.setPlatformId(Long.valueOf(platformId_));
		}
		Long platformId = vo.getPlatformId();
		String channelUserUnique = vo.getChannelUserUnique();
		String channelsource = vo.getChannelsource();
		String identityCode = vo.getIdentityCode();
		Boolean checkIdentityCode = vo.getCheckIdentityCode();
		String channelName = vo.getChannelName();
		String campaignCode = vo.getCampaignCode();
		String registerShopCode = vo.getRegisterShopCode();
		String loginType = vo.getLoginType();
		Long keyMessage = vo.getKeyMessage();
		Long companyId = vo.getCompanyId();
		String type = vo.getType();
		String weiXinOpenId = req.getHeader("weiXinOpenId");
		logger.info("[参数]weiXinOpenId="+weiXinOpenId);
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

		if(platformId == null)
			return fail("平台id参数缺失");
		if (EmptyUtil.isEmpty(checkIdentityCode)){
			checkIdentityCode=true;
		}
		// 判断手机号和验证码
		if (StringUtils.isEmpty(channelUserUnique) || (StringUtils.isEmpty(identityCode) && checkIdentityCode)) {
			throw new BusinessException("登录标志或验证码不能为空");
		}
		if(EmptyUtil.isEmpty(companyId)){
			companyId=UserChannelSourceEnum.getCompanyIdByCode(channelsource);
		}
		if(EmptyUtil.isEmpty(companyId)){
			throw new BusinessException("企业公司id不能为空");
		}
		logger.info("clientId="+clientId_);
		List<UserVO> userList = userManage.findByChannelUserUniqueAndRegister(channelUserUnique,companyId,platformId);
		boolean needRegister=false;
		if (EmptyUtil.isEmpty(userList)){
			needRegister=true;

		}
		if (needRegister){
			//注册
			UserVO registerUser=userManage.registDefaultCompanyUserByChannelUserUnique(channelUserUnique,channelUserUnique, Constants.USER_DEFAULT_PWD,weiXinOpenId,
					platformId,companyId,vo);
			if (EmptyUtil.isEmpty(registerUser)){
				throw new BusinessException("用户登录注册失败");
			}
		}
		// 判断验证码是否正确
		JsonResult<CacheUser> rt = loginManage.channelUserUniqueLogin(type,channelUserUnique, identityCode, platformId, deviceId,
				Long.valueOf(clientId_),weiXinOpenId,channelName,campaignCode,
				registerShopCode,deviceType,version,checkIdentityCode,companyId);
		// code 等于 0表示登陆成功
		int code = rt.getCode();

		// 根据用户手机号查询用户信息
		userList = userManage.findByChannelUserUniqueAndRegister(channelUserUnique,companyId,platformId);

		// 慢有优平台用户如果存在多个则全部返回
		if(platformId == 2 && userList.size() > 1){
			UserVO latestLoginUser = userManage.findLatestLoginByChannelUserUnique(channelUserUnique,companyId, platformId);
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
			List<WxOpenidDTO> useropenids = wxOpenIdManage.findUserOpenIds(rt.getData().getId(), weiXinOpenId);
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
					wxOpenIdManage.updateUserId(openId, rt.getData().getId(),rt.getData().getCompanyId());
				}else {
					if(openids!=null && openids.size()>0) {
						String appid = openids.get(0).getWxAppid();
						WxOpenidDTO dto = new WxOpenidDTO();

						dto.setCompanyId(rt.getData().getCompanyId());
						dto.setCreateTime(new Date());
						dto.setUserId(rt.getData().getId());
						dto.setWxOpenid(weiXinOpenId);
						dto.setWxAppid(appid);
						wxOpenIdManage.insert(dto);
					}
				}
			}
			return success(saveUserCookieLoginLog(rt, userVO, deviceId, clientId, req, resp));
		} else {
			userLoginManage.insertLoginExceptionLogWithTx(code, userVO, req);
			return fail(code, rt.getError());
		}
	}


	/**
	 * 解锁对应的key
	 *
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping(value = "unLockUser")
	@ResponseBody
	public JsonResult<String> unLockUser(String key, HttpServletRequest req, HttpServletResponse resp) {

		logger.info("解锁的key=：" + key);
		if(EmptyUtil.isBlank(key) || EmptyUtil.isEmpty(key)){
			return JsonResult.fail("参数不能为空");
		}
		userLockUtils.unLock(key);
		return JsonResult.success("解锁成功");
	}
}
