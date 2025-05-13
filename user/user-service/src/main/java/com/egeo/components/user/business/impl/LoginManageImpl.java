package com.egeo.components.user.business.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.egeo.common.BusinessConstant;
import com.egeo.common.BusinessExceptionConstant;
import com.egeo.common.CacheKeyConstant;
import com.egeo.common.CompanyConstant;
import com.egeo.common.PlatformKeyConstant;
import com.egeo.components.user.bean.UniAuthUserInfo;
import com.egeo.components.user.business.LoginManage;
import com.egeo.components.user.business.RoleMenuManage;
import com.egeo.components.user.business.RoleMenuPlatformManage;
import com.egeo.components.user.business.RoleUrlManage;
import com.egeo.components.user.business.UrlWhiteListManage;
import com.egeo.components.user.business.UserCookieManage;
import com.egeo.components.user.business.UserExtendManage;
import com.egeo.components.user.business.UserManage;
import com.egeo.components.user.business.UserPlatformManage;
import com.egeo.components.user.business.UserRoleManage;
import com.egeo.components.user.business.util.CalcEncryInfoUtils;
import com.egeo.components.user.business.util.TicketUtils;
import com.egeo.components.user.common.Constants;
import com.egeo.components.user.converter.CacheUserConverter;
import com.egeo.components.user.converter.UserConverter;
import com.egeo.components.user.dto.CompanyDTO;
import com.egeo.components.user.dto.RoleMenuDTO;
import com.egeo.components.user.dto.UserCookieDTO;
import com.egeo.components.user.dto.UserDTO;
import com.egeo.components.user.dto.UserExtendDTO;
import com.egeo.components.user.dto.UserPlatformDTO;
import com.egeo.components.user.dto.UserRoleDTO;
import com.egeo.components.user.entity.EncryptEntity;
import com.egeo.components.user.vo.UserCookieVO;
import com.egeo.components.user.vo.UserTempConditionVO;
import com.egeo.components.user.vo.UserVO;
import com.egeo.components.utils.DateUtil;
import com.egeo.components.utils.UUIDUtil;
import com.egeo.entity.CacheUser;
import com.egeo.entity.Ticket;
import com.egeo.exception.BusinessException;
import com.egeo.token.AccessToken;
import com.egeo.token.TokenEntity;
import com.egeo.token.TokenManager;
import com.egeo.util.security.MD5Support;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.WorkWeChatUtil;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.utils.str.StringUtils;
import com.egeo.web.JsonResult;

@Service("login")
public class LoginManageImpl implements LoginManage{

	public Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Resource(name="user")
	private UserManage userManage;

	@Resource(name="userExtend")
	private UserExtendManage userExtendManage;

	@Resource(name="userPlatform")
	private UserPlatformManage userPlatformManage;

	@Resource(name="roleUrl")
	private RoleUrlManage roleUrlManage;

	@Resource(name="roleMenuPlatform")
	private RoleMenuPlatformManage roleMenuPlatformManage;

	@Resource
	private  JedisUtil cache;

	@Resource(name="userCookie")
	private UserCookieManage userCookieManage;

	@Resource(name="userRole")
	private UserRoleManage userRoleManage;

	@Resource(name="ticketUtils")
	private TicketUtils ticketUtils;

	@Resource(name="roleMenu")
	private RoleMenuManage roleMenuManage;

	@Resource(name="urlWhiteList")
	private UrlWhiteListManage urlWhiteListManage;

	@Autowired
	private WorkWeChatUtil workWeChatUtil;


	@Value("${token.asTokenExpire}")
	private long asTokenExpire;

	@Value("${token.rfTokenExpire}")
	private long rfTokenExpire;

    @Value("${adminUser.name}")
    private String adminUserName;

	@Autowired
	private TokenManager tokenManager;
	@Override
	public JsonResult<CacheUser> manageLogin(UserVO user) {
		JsonResult<CacheUser> rt = new JsonResult<CacheUser>();
		UserVO userVo = null;
		Long platformId = null;
		String ut = null;

		if(StringUtils.isEmpty(user.getPassword())){
			rt.setCode(BusinessExceptionConstant.USER_LOGIN_NO_PASSWORD);
			rt.setError("用户登录密码为空");
			return rt;
		}
		if(EmptyUtil.isNotEmpty(user.getPlatformId())){

			userVo = userManage.findUniqueUser(user);
			UserPlatformDTO userPlatformDTO = new UserPlatformDTO();
			userPlatformDTO.setPlatformId(user.getPlatformId());
			userPlatformDTO.setUserId(userVo.getId());
			// 获取超级管理员名称
			if(!userVo.getLoginName().equals(adminUserName)){
				if(!userPlatformManage.userIsExitPlatform(userPlatformDTO)){
					rt.setCode(BusinessExceptionConstant.USER_NOT_REGISTER_PLATFORM);
					rt.setError("此用户没有登录该平台权限");
					return rt;
				}

			}
			//以下代码废弃，后续改为判断权限
			boolean feiqi = true;
			if(!feiqi) {
				// 判断是否拥有管理员角色,有登录成功,无,登录失败
				UserRoleDTO userRoleDTO = new UserRoleDTO();
				userRoleDTO.setUserId(userVo.getId());
				List<UserRoleDTO> userRoleDTOList = userRoleManage.findUserRoleAll(userRoleDTO);
				boolean isNotAdmin = true;
				platformId = user.getPlatformId();
				//old
				/*for (UserRoleDTO userRoleDTO_ : userRoleDTOList) {
					if (BusinessConstant.PLATFORM7_ADMIN_ROLEID.equals(userRoleDTO_.getRoleId()))
						isNotAdmin = false;
				}*/
				//new
				//List<String> roleMenuList = roleMenuPlatformManage.getMenuByUserPlatform(userVo.getId(),user.getPlatformId());
				List<RoleMenuDTO> roleMenuList = roleMenuManage.getRoleMenuListByUserId(userVo.getId(),platformId);
				Integer isAdministrator = userVo.getIsAdministrator();
				logger.info("用户ID:"+userVo.getId());
				if (EmptyUtil.isNotEmpty(roleMenuList)) {
					isNotAdmin = false;
					logger.info("menu的数量 : "+roleMenuList.size());
				} else {
					logger.info("list是空");
				}
				if (isNotAdmin || isAdministrator == 0)
					return JsonResult.fail("该邮箱无登录权限");

			}
		}else{
			rt.setCode(BusinessExceptionConstant.USER_LOGIN_NOCOMPANYID_OR_NOPLATFORMID);
			rt.setError("用户登录没有带company_id或者platform_id");
			return rt;
		}


		boolean validateFlag=false;//user这边验证通过标志


    	EncryptEntity encryptEntity=new EncryptEntity();
    	encryptEntity=CalcEncryInfoUtils.calc(userVo);

    	//没有盐值,是修改之前的用户,原密码md5加密一次
        String passWordNullSalt= MD5Support.MD5(user.getPassword());
        //有old盐值,就用old盐值跟old算法加密
        String passWordWithSalt=MD5Support.MD5(user.getPassword(), (null==encryptEntity.getOldBSalt())?"":encryptEntity.getOldBSalt());

        if(StringUtils.isEmpty(encryptEntity.getOldBSalt())){//没有old盐值,是修改之前的用户,原密码md5加密一次
        	if(userVo.getPassword().equals(passWordNullSalt)){
        		validateFlag=true;
        	}
        }else{//有old盐值,就用old盐值跟old算法加密
    		if(userVo.getPassword().equals(passWordWithSalt)){
    			validateFlag=true;

        	}
        }


        if(validateFlag){
           	try {
           		CacheUser cacheUser = setCacheUser(userVo,platformId);
           		cacheUser.setName(user.getName());
           		rt.setCode(0);
           		rt.setData(cacheUser);
           		rt.setSuccess( "登录成功！");
            	try{//更新加密信息的操作不能影响正常的登录
            		Long userId=userVo.getId();

                	if(StringUtils.isNotEmpty(encryptEntity.getNewBSalt())){
                    	String salt=StringUtils.isEmpty(encryptEntity.getNewBSalt())?encryptEntity.getOldBSalt():encryptEntity.getNewBSalt();

                    	UserDTO updUser=UserConverter.toDTO(userVo);

                        String password=MD5Support.MD5(user.getPassword(),salt);
                        Date now=new Date();
                        if(StringUtils.isNotEmpty(encryptEntity.getNewBSalt())){
                        	updUser.setSalt(encryptEntity.getNewBSalt());
                        	updUser.setUpdateSalt(now);
                        }

                        updUser.setPassword(password);

                        int cnt=userManage.updateEncryptInfoByUserWithTx(updUser);

                        if(0==cnt){
                        	logger.error("更新用户加密密码失败");
                        	rt.setError("更新用户加密密码失败");
                        }

                	}
                }catch(Exception e1){
                	logger.error("更新用户加密密码失败");
                	rt.setError("更新用户加密密码失败");
               	}

            	return rt;

			} catch (Exception e) {
					logger.error(e.getMessage());
					rt.setError(e.getMessage());
		            return rt;
				}
        }else{
           	rt.setCode(BusinessExceptionConstant.USER_PASSWORD_OR_USERNAME_WRONG);
           	rt.setError("用户登录的账号或者密码错误");
            return rt;
        }
     }
	@Override
	public JsonResult<CacheUser> login(UserVO user) {
		JsonResult<CacheUser> rt = new JsonResult<CacheUser>();
		UserVO userVo = null;
		Long platformId = null;
		String ut = null;

		if(StringUtils.isEmpty(user.getPassword())){
			rt.setCode(BusinessExceptionConstant.USER_LOGIN_NO_PASSWORD);
			rt.setError("用户登录密码为空");
			return rt;
		}
		if(EmptyUtil.isNotEmpty(user.getPlatformId())){

			userVo = userManage.findUniqueUser(user);
			UserPlatformDTO userPlatformDTO = new UserPlatformDTO();
			userPlatformDTO.setPlatformId(user.getPlatformId());
			userPlatformDTO.setUserId(userVo.getId());
			// 获取超级管理员名称
			if(!userVo.getLoginName().equals(adminUserName)){
				if(!userPlatformManage.userIsExitPlatform(userPlatformDTO)){
					rt.setCode(BusinessExceptionConstant.USER_NOT_REGISTER_PLATFORM);
					rt.setError("此用户没有登录该平台权限");
					return rt;
				}

			}

			// 判断是否拥有管理员角色,有登录成功,无,登录失败
			UserRoleDTO userRoleDTO = new UserRoleDTO();
			userRoleDTO.setUserId(userVo.getId());
			List<UserRoleDTO> userRoleDTOList = userRoleManage.findUserRoleAll(userRoleDTO);
			boolean isNotAdmin = true;
			platformId = user.getPlatformId();
			//old
			/*for (UserRoleDTO userRoleDTO_ : userRoleDTOList) {
				if (BusinessConstant.PLATFORM7_ADMIN_ROLEID.equals(userRoleDTO_.getRoleId()))
					isNotAdmin = false;
			}*/
			//new
			//List<String> roleMenuList = roleMenuPlatformManage.getMenuByUserPlatform(userVo.getId(),user.getPlatformId());
			List<RoleMenuDTO> roleMenuList = roleMenuManage.getRoleMenuListByUserId(userVo.getId(),platformId);
			Integer isAdministrator = userVo.getIsAdministrator();
			logger.info("用户ID:"+userVo.getId());
			if (EmptyUtil.isNotEmpty(roleMenuList)) {
				isNotAdmin = false;
				logger.info("menu的数量 : "+roleMenuList.size());
			} else {
				logger.info("list是空");
			}
			if (isNotAdmin || isAdministrator == 0)
				return JsonResult.fail("该邮箱无登录权限");

		}else{
			rt.setCode(BusinessExceptionConstant.USER_LOGIN_NOCOMPANYID_OR_NOPLATFORMID);
			rt.setError("用户登录没有带company_id或者platform_id");
			return rt;
		}


		boolean validateFlag=false;//user这边验证通过标志


    	EncryptEntity encryptEntity=new EncryptEntity();
    	encryptEntity=CalcEncryInfoUtils.calc(userVo);

    	//没有盐值,是修改之前的用户,原密码md5加密一次
        String passWordNullSalt= MD5Support.MD5(user.getPassword());
        //有old盐值,就用old盐值跟old算法加密
        String passWordWithSalt=MD5Support.MD5(user.getPassword(), (null==encryptEntity.getOldBSalt())?"":encryptEntity.getOldBSalt());

        if(StringUtils.isEmpty(encryptEntity.getOldBSalt())){//没有old盐值,是修改之前的用户,原密码md5加密一次
        	if(userVo.getPassword().equals(passWordNullSalt)){
        		validateFlag=true;
        	}
        }else{//有old盐值,就用old盐值跟old算法加密
    		if(userVo.getPassword().equals(passWordWithSalt)){
    			validateFlag=true;

        	}
        }


        if(validateFlag){
           	try {
           		CacheUser cacheUser = setCacheUser(userVo,platformId);
           		cacheUser.setName(user.getName());
           		rt.setCode(0);
           		rt.setData(cacheUser);
           		rt.setSuccess( "登录成功！");
            	try{//更新加密信息的操作不能影响正常的登录
            		Long userId=userVo.getId();

                	if(StringUtils.isNotEmpty(encryptEntity.getNewBSalt())){
                    	String salt=StringUtils.isEmpty(encryptEntity.getNewBSalt())?encryptEntity.getOldBSalt():encryptEntity.getNewBSalt();

                    	UserDTO updUser=UserConverter.toDTO(userVo);

                        String password=MD5Support.MD5(user.getPassword(),salt);
                        Date now=new Date();
                        if(StringUtils.isNotEmpty(encryptEntity.getNewBSalt())){
                        	updUser.setSalt(encryptEntity.getNewBSalt());
                        	updUser.setUpdateSalt(now);
                        }

                        updUser.setPassword(password);

                        int cnt=userManage.updateEncryptInfoByUserWithTx(updUser);

                        if(0==cnt){
                        	logger.error("更新用户加密密码失败");
                        	rt.setError("更新用户加密密码失败");
                        }

                	}
                }catch(Exception e1){
                	logger.error("更新用户加密密码失败");
                	rt.setError("更新用户加密密码失败");
               	}

            	return rt;

			} catch (Exception e) {
					logger.error(e.getMessage());
					rt.setError(e.getMessage());
		            return rt;
				}
        }else{
           	rt.setCode(BusinessExceptionConstant.USER_PASSWORD_OR_USERNAME_WRONG);
           	rt.setError("用户登录的账号或者密码错误");
            return rt;
        }
     }


		public CacheUser setCacheUser (UserVO user, Long platformId){
		    Long userId = user.getId();
			CacheUser cacheUser = new CacheUser();
			cacheUser.setPlatformId(platformId);
			//添加用户主表信息
			CacheUserConverter.setCacheUserbyUser(cacheUser,user);
			List<String> urlPath = roleUrlManage.getUrlListByUserId(userId, platformId);
			List<String> menulist = roleMenuPlatformManage.getMenuList(userId,platformId);
			List<String> whiteUrlList = urlWhiteListManage.findUrlWhiteList(platformId);
			cacheUser.setUrlPath(urlPath);
			cacheUser.setMenuList(menulist);
			cacheUser.setWhiteUrlList(whiteUrlList);
			if(user.getEnterpriseId()!=null && user.getEnterpriseId()>0) {
				cacheUser.setEnterpriseId(user.getEnterpriseId());
			}
			if(user.getCompanyType()!=null && user.getCompanyType()>0) {
				cacheUser.setCompanyType(user.getCompanyType());
			}
			if(user.getType()!=null && user.getType()>0) {
				cacheUser.setType(user.getType());
			}

			//添加用户扩展表信息
			UserExtendDTO userExtendDTO = userExtendManage.findById(userId);
			cacheUser.setNickname(userExtendDTO.getNickname());
			cacheUser.setHeadPicUrl(userExtendDTO.getHeadPicUrl());
			cacheUser.setName(userExtendDTO.getName());
			cacheUser.setIsAdministrator(userExtendDTO.getIsAdministrator());
			return cacheUser;

		}

		public CacheUser setCacheUser (Long userId, Long platformId) throws Exception{
			UserDTO userDto = userManage.findUserByID(userId);
			CacheUser cacheUser = new CacheUser();
			cacheUser.setPlatformId(platformId);
			cacheUser.setCompanyId(userDto.getCompanyId());
			CacheUserConverter.setCacheUserbyUser(cacheUser,userDto);
			List<String> urlPath = roleUrlManage.getUrlListByUserId(userId, platformId);
			List<String> menulist = roleMenuPlatformManage.getMenuList(userId,platformId);
			List<String> whiteUrlList = urlWhiteListManage.findUrlWhiteList(platformId);

			if(userDto.getEnterpriseId()!=null && userDto.getEnterpriseId()>0) {
				cacheUser.setEnterpriseId(userDto.getEnterpriseId());
			}
			if(userDto.getType()!=null && userDto.getType()>0) {
				cacheUser.setType(userDto.getType());
			}
			cacheUser.setUrlPath(urlPath);
			cacheUser.setMenuList(menulist);
			cacheUser.setWhiteUrlList(whiteUrlList);
			return cacheUser;

		}





		@Override
		public void saveUttoCache(UserVO user,  UserCookieVO userCookie,String deviceId, Long clientId) {

			String utKey = "getUserUTKey" + "companyID:" +user.getCompanyId() + "platformID:" +user.getPlatformId()
			+ "userName:" + user.getName()+"userPassword:"+user.getPassword() + "deviceId:" + deviceId + "clientId:" + clientId;
			String ut = userCookie.getCookieValue();
			if(StringUtils.isNotEmpty(ut)){
	            Ticket t = new Ticket();
	            t.setTime(new Date().getTime());
	            t.setUt(ut);
	        	t.setTimeout(BusinessConstant.APP_USER_UT_TIMEOUT);
				cache.set(utKey, (int) t.getTimeout(), t);
			}


		}


		@Override
		public JsonResult<CacheUser> mobileLogin(UserVO user,String deviceId,Long clientId) {//added by xiaping，这里暂时没有增加 前端加密的修改

			JsonResult<CacheUser> rt = new JsonResult<CacheUser>();
			UserVO userVo = null;
			Long platformId = null;
			String ut = null;
			String utKey = null;

			if(!StringUtils.isNotEmpty(user.getPassword())){
				rt.setCode(BusinessExceptionConstant.USER_LOGIN_NO_PASSWORD);
				rt.setError("用户登录密码为空");
				return rt;
			}
			if(EmptyUtil.isNotEmpty(user.getPlatformId())){
				utKey = "getUserUTKey" + "companyID:" +user.getCompanyId() + "platformID:" +user.getPlatformId()
				+ "userName:" + user.getName()+"userPassword:"+user.getPassword() + "deviceId:" + deviceId + "clientId:" + clientId + "weiXinOpenId" + user.getWeiXinOpenId();
				//先根据用户信息查询tokenId（ut）
				ut =  ticketUtils.getUt(utKey);
				logger.info("[mobileLogin方法中:]ut="+ut);
				if(StringUtils.isNotEmpty(ut)){
					//1.如果有tokenId 的话，可以根据tokenId查到tokenBody，这里保存了用户信息
					CacheUser cacheuser = ticketUtils.getUser(ut);
					if(EmptyUtil.isNotEmpty(cacheuser)){
						cacheuser.setTickeExist(true);
						cacheuser.setCookieValue(ut);
						//是否为缓存返回数据：0、不是 1、是
						cacheuser.setIsRedis(1);
						rt.setCode(0);
						rt.setData(cacheuser);
						return rt;
					}else{
						cache.del(utKey);
					}
				}
				//2.如果没有tokenId说明没登录，或者过期了

				userVo = userManage.findUniqueUser(user);
				userVo.setPlatformId(user.getPlatformId());
				UserPlatformDTO userPlatformDTO = new UserPlatformDTO();
				userPlatformDTO.setPlatformId(user.getPlatformId());
				userPlatformDTO.setUserId(userVo.getId());
				if(!userPlatformManage.userIsExitPlatform(userPlatformDTO)){
					rt.setCode(BusinessExceptionConstant.USER_NOT_REGISTER_PLATFORM);
					rt.setError("此用户没有登录该平台权限");
					return rt;
				}
				platformId = user.getPlatformId();

			}else{
				rt.setCode(BusinessExceptionConstant.USER_LOGIN_NOCOMPANYID_OR_NOPLATFORMID);
				rt.setError("用户登录没有带company_id或者platform_id");
				return rt;
			}


			boolean validateFlag=false;//user这边验证通过标志


	    	EncryptEntity encryptEntity=new EncryptEntity();
	    	encryptEntity=CalcEncryInfoUtils.calc(userVo);

	    	//没有盐值,是修改之前的用户,原密码md5加密一次
	        String passWordNullSalt= MD5Support.MD5(user.getPassword());
	        //有old盐值,就用old盐值跟old算法加密
	        String passWordWithSalt=MD5Support.MD5(user.getPassword(), (null==encryptEntity.getOldBSalt())?"":encryptEntity.getOldBSalt());

	        if(StringUtils.isEmpty(encryptEntity.getOldBSalt())){//没有old盐值,是修改之前的用户,原密码md5加密一次
	        	if(userVo.getPassword().equals(passWordNullSalt)){
	        		validateFlag=true;
	        	}
	        }else{//有old盐值,就用old盐值跟old算法加密   数据库存储的密码=md5（jsmd5(密码明文)+salt） 这里jsmd5(密码明文)有前端加密并传输到后端
	    		if(userVo.getPassword().equals(passWordWithSalt)){
	    			validateFlag=true;

	        	}
	        }


	            if(true==validateFlag){
	            	try {
	            		CacheUser cacheUser = setMobileCacheUser(userVo,platformId);
	            		cacheUser.setName(user.getName());
	            		cacheUser.setCookieName(utKey);
	        			updateUT(userVo, deviceId, clientId);

	            		rt.setCode(0);
	            		rt.setData(cacheUser);
	            		rt.setSuccess( "登录成功！");

		            	try{//更新加密信息的操作不能影响正常的登录
		            	Long userId=userVo.getId();

		                	if(StringUtils.isNotEmpty(encryptEntity.getNewBSalt())){
		                    	String salt=StringUtils.isEmpty(encryptEntity.getNewBSalt())?encryptEntity.getOldBSalt():encryptEntity.getNewBSalt();

		                    	UserDTO updUser=UserConverter.toDTO(userVo);

		                        String password=MD5Support.MD5(user.getPassword(),salt);
		                        Date now=new Date();
		                        if(StringUtils.isNotEmpty(encryptEntity.getNewBSalt())){
		                        	updUser.setSalt(encryptEntity.getNewBSalt());
		                        	updUser.setUpdateSalt(now);
		                        }

		                        updUser.setPassword(password);

		                        int cnt=userManage.updateEncryptInfoByUserWithTx(updUser);

		                        if(0==cnt){
		                        	logger.error("更新用户加密密码失败");
		                        	rt.setError("更新用户加密密码失败");
		                        }

		                	}
		                }catch(Exception e1){
		                	logger.error("更新用户加密密码失败");
		                	rt.setError("更新用户加密密码失败");
		                	}

		            	return rt;

					} catch (Exception e) {
						logger.error(e.getMessage());
						rt.setError(e.getMessage());
			            return rt;
					}
	            }else{
	            	rt.setCode(BusinessExceptionConstant.USER_PASSWORD_OR_USERNAME_WRONG);
	            	rt.setError("用户登录的账号或者密码错误");
	                return rt;
	            }
	        }


		private CacheUser setMobileCacheUser(UserVO userVo, Long platformId) {
		    Long userId = userVo.getId();
			CacheUser cacheUser = new CacheUser();
			cacheUser.setPlatformId(platformId);
			CacheUserConverter.setCacheUserbyUser(cacheUser,userVo);
			/*List<String> urlPath = roleUrlManage.getUrlListByUserId(userId, platformId);
			cacheUser.setUrlPath(urlPath);*/

			return cacheUser;

		}
/*

		*//**
		 * 生成accessToken
		 *
		 * @param user   用户
		 * @param mobile 手机号
		 *//*
		private TokenEntity gengerAccessToken(String sysCode, UniAuthUserInfo user, String mobile, String deviceType,
				String userDataUUid, String deviceId) {

			// syscode1:toke1,syscode2:token2,syscode3:token3,…………

			// tokenKey
			String tokenKey = UUIDUtil.randomUUID();
			// 前缀:系统编码&用户uuid
			String accessUserUUidKey = Constants.UNIAUTH_USER_ACCESS_TOKEN + sysCode + ":" + user.getUuId();
			// accessToken失效时间
			long tokenExpire = asTokenExpire * 1000;
			long tokenEndMills = DateUtil.getSecondTimestampTwo(tokenExpire);
			// token的Value
			AccessToken token = new AccessToken(user.getUuId(), mobile, deviceType, userDataUUid, deviceId, "",
					System.currentTimeMillis(), 0, 0, 0, "appCode", "productCode");
			token.setCreatMills(
					user.getCreatedDate() == null ? System.currentTimeMillis() : user.getCreatedDate().getTime());
			TokenEntity tokenEntity = new TokenEntity(tokenKey, tokenEndMills, token);
			try {
				// 登录成功生成Token
				tokenManager.addTokenEntity(accessUserUUidKey, tokenKey, tokenEntity, asTokenExpire, true);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return tokenEntity;
		}

		*/

	@Override
	public JsonResult<CacheUser> mobileLogin(String type, String mobile, String identityCode, Long platformId, String deviceId,
											 Long clientId, String weiXinOpenId, String channelName, String campaignCode,
											 String registerShopCode, Integer deviceType, String version) {
		return mobileLogin(type,mobile,identityCode,platformId,deviceId,
				clientId,weiXinOpenId,channelName,campaignCode,
				registerShopCode, deviceType, version,true);
	}

	/**
		 * 生成refreshToken
		 *
		 * @param userUuid 用户UUID
		 * @param mobile   手机号
		 *//*
		private TokenEntity gengerRefreshToken(String sysCode, String userUuid, String mobile, String deviceType,
				String deviceId) {

			// tokenKey
			String tokenKey = UUIDUtil.randomUUID();
			// 前缀:系统编码&用户uuid
			String accessUserUUidKey = Constants.UNIAUTH_USER_REFRESH_TOKEN + sysCode + ":" + userUuid;
			// refreshToken失效时间
			long tokenExpire = rfTokenExpire * 1000;
			long tokenEndMills = DateUtil.getSecondTimestampTwo(tokenExpire);
			// token的Value
			AccessToken token = new AccessToken(userUuid, mobile, deviceType, sysCode, deviceId, "",
					System.currentTimeMillis(), 0, 0, 0, "appCode", "productCode");
			TokenEntity tokenEntity = new TokenEntity(tokenKey, tokenEndMills, token);
			try {
				// 登录成功生成refreshToken
				tokenManager.addTokenEntity(accessUserUUidKey, tokenKey, tokenEntity, rfTokenExpire, true);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return tokenEntity;
		}*/
		@Override
		public JsonResult<CacheUser> mobileLogin(String type,String mobile,
				String identityCode, Long platformId,String deviceId,Long clientId,String weiXinOpenId,
				String channelName,String campaignCode,String registerShopCode,Integer deviceType,String version,Boolean checkIdentityCode) {
			checkIdentityCode(mobile,identityCode,checkIdentityCode);
			//根据用户手机号查询用户信息
			UserVO userVO = null;
			List<UserVO> userList = userManage.findByMobileAndRegister(mobile,platformId);
			//如果用户存在,查询对应的最近登录的用户默认登录
			if(EmptyUtil.isNotEmpty(userList)){
				if(EmptyUtil.isNotEmpty(userList.get(0).getId())){
					logger.info("user存在,查询对应的最近登录的用户默认登录,"+userList.get(0).getId());
				}
				userVO = userList.get(0);
				if(PlatformKeyConstant.MYY_PLATFORM_ID.equals(platformId) && userList.size() > 1){
					UserVO latestLoginUserVO = userManage.findLatestLoginByMobile(mobile,platformId);
					if (EmptyUtil.isNotEmpty(latestLoginUserVO)) {
						userVO = latestLoginUserVO;
					}
				}
			}
			if(PlatformKeyConstant.MYY_PLATFORM_ID.equals(platformId)
					&& (EmptyUtil.isEmpty(userVO) || (userList.size() == 1 && !CompanyConstant.MYY_COMPANY_ID.equals(userVO.getCompanyId())))){
				// 新增用户、注册授权并且返回信息
				logger.info("慢有优创建用户");
				UserVO createdUserVO = createUser(mobile,platformId,deviceId,channelName,campaignCode,registerShopCode,deviceType,version);
				if (EmptyUtil.isNotEmpty(createdUserVO)) {
					//自动领取注册优惠券
					createdUserVO.setPlatformId(platformId);
					userManage.autoReceiveRegisterCoupon(createdUserVO);
				}
				if (EmptyUtil.isEmpty(userVO)) {
					userVO = createdUserVO;
				}
			} else if ("coupon_unit".equals(type) && PlatformKeyConstant.FGJ_PLATFORM_ID.equals(platformId)
					&&(EmptyUtil.isEmpty(userVO) || (userList.size() == 1 && !CompanyConstant.FGJ_COMPANY_ID.equals(userVO.getCompanyId())))){
				//福管加从优惠卷unit扫码过来的可以自动注册
				// 新增用户、注册授权并且返回信息
				logger.info("couponunit扫码领取自动创建用户");
				UserVO createdUserVO = createUser(mobile,platformId,deviceId,channelName,campaignCode,registerShopCode,deviceType,version);
				if (EmptyUtil.isNotEmpty(createdUserVO)) {
					//自动领取注册优惠券
					createdUserVO.setPlatformId(platformId);
					userManage.autoReceiveRegisterCoupon(createdUserVO);
				}
				if (EmptyUtil.isEmpty(userVO)) {
					userVO = createdUserVO;
				}
			}else if(EmptyUtil.isEmpty(userVO)&&PlatformKeyConstant.FGJ_PLATFORM_ID.equals(platformId)&&!("coupon_unit".equals(type))){
				logger.info("用户不存在以激活帐户");
				logger.info("参数:"+platformId);
				logger.info("参数:"+type);
				List<UserVO> userIdList=userManage.findUserByMobile(mobile,platformId);
				if(EmptyUtil.isNotEmpty(userIdList)){
					logger.info("查询参数:"+userIdList.size());
					//不为空则直接激活用户
					List<Long> userIds = new ArrayList<>();
					for(UserVO vo:userIdList){
						userIds.add(vo.getId());
						logger.info("userId"+vo.getId());
					}
					//激活账户
					userManage.activateUserByIds(userIds);
					//福管加门店id
					userIdList.get(0).setRegisterStoreId(1+"");
					//领取优惠券
					userManage.autoReceiveRegisterCoupon(userIdList.get(0));

					if (EmptyUtil.isEmpty(userVO)) {
						userVO = userIdList.get(0);
					}

				}
			}
			if(EmptyUtil.isEmpty(userVO)){
				throw new BusinessException(BusinessExceptionConstant.USER_MOBILE_NO_EMPTY,"请先注册");
			}
			// 使用成功之后删除缓存
			if (Objects.equals(true,checkIdentityCode)){
				cache.del(CacheKeyConstant.MOBILE_LOGIN_KEY + mobile);
			}
			userVO.setPlatformId(platformId);
			userVO.setWeiXinOpenId(weiXinOpenId);
			return verificationLoginUpdateUT(userVO,deviceId,clientId);
		}
		/**
		 * 新增用户、注册授权并且返回信息
		 * @param mobile 用户手机号
		 * @param platformId 平台id
		 * @return
		 */
		private UserVO createUser(String mobile, Long platformId,String deviceId,String channelName,String campaignCode,String registerShopCode,Integer deviceType,String version) {
			// 组织新增用户数据
			UserTempConditionVO vo = new UserTempConditionVO();
			vo.setName(mobile);
			vo.setMobile(mobile);
			if(platformId.equals(2L)){
				vo.setMail(mobile + "@" + PlatformKeyConstant.MYY_PLATFORM_DOMAIN);
				// 根据平台id查询其下员工数量
				Integer userCount = userPlatformManage.findUserCountByPlatformId(platformId);
				vo.setMemberCode(PlatformKeyConstant.MYY_PLATFORM_USER_CODE_PREFIX + "-" + userCount + 1);
				vo.setCompanyId(CompanyConstant.MYY_COMPANY_ID);
			}else if(platformId.equals(7L)){
				vo.setMail(mobile + "@" + PlatformKeyConstant.FGJ_PLATFORM_DOMAIN);
				vo.setMemberCode("FGJEC"+mobile);
				vo.setCompanyId(CompanyConstant.FGJ_COMPANY_ID);
			}

			// 是否是管理员  0：否  1：是
			vo.setIsAdministrator(0);
			// 是否是管理员是否置空 1：取是否是管理员
			vo.setCurrUserId(1L);
			vo.setPlatformId(platformId);
			vo.setRegisterShopCode(registerShopCode);
			userManage.insertOrUpdateUser(vo,null);

			// 注册
			UserVO userVO = new UserVO();
			userVO.setDeviceType(deviceType);
			userVO.setVersion(version);
			userVO.setDeviceId(deviceId);
			userVO.setMail(vo.getMail());
			if(platformId.equals(2L)){
				userVO.setPassword(PlatformKeyConstant.MYY_PLATFORM_USER_CODE_PREFIX + mobile);
			}else if(platformId.equals(7L)){
				userVO.setPassword(PlatformKeyConstant.FGJ_PLATFORM_USER_CODE_PREFIX + mobile);

			}
			userVO.setPlatformId(platformId);
			userVO.setCampaignCode(campaignCode);
			userVO.setRegisterStoreId(registerShopCode);
			//慢有优注册时传入的channelName就是ID，可以直接设置
			userVO.setChannelId(EmptyUtil.isEmpty(channelName) ? null : Long.valueOf(channelName));
			userManage.userRegister(userVO,channelName);

			//根据用户手机号查询用户信息
			UserVO user = null;
			List<UserVO> userList = userManage.findByMobileAndRegister(mobile,platformId);
			if(EmptyUtil.isNotEmpty(userList)){
				user = userList.get(0);
				user.setRegisterStoreId(registerShopCode);
				user.setCampaignCode(campaignCode);
			}
			return user;
		}

		private JsonResult<CacheUser> verificationLoginUpdateUT(UserVO userVO,String deviceId,Long clientId) {
			String ut = null;
			String utKey = null;
			CacheUser cacheuser = null;
			JsonResult<CacheUser> rt = new JsonResult<CacheUser>();
			UserPlatformDTO userPlatformDTO = new UserPlatformDTO();
			userPlatformDTO.setPlatformId(userVO.getPlatformId());
			userPlatformDTO.setUserId(userVO.getId());
			if(EmptyUtil.isNotEmpty(userVO.getPlatformId())){

				if(!userPlatformManage.userIsExitPlatform(userPlatformDTO)){
					rt.setCode(BusinessExceptionConstant.USER_NOT_REGISTER_PLATFORM);
					rt.setError("此用户没有登录该平台权限");
					return rt;
				}

			}else{
				rt.setCode(BusinessExceptionConstant.USER_LOGIN_NOCOMPANYID_OR_NOPLATFORMID);
				rt.setError("用户登录没有带company_id或者platform_id");
				return rt;
			}
			// 更新ut信息
			updateUT(userVO, deviceId, clientId);

			cacheuser = setMobileCacheUser(userVO,userVO.getPlatformId());
			rt.setCode(0);
    		rt.setData(cacheuser);
    		rt.setSuccess( "登录成功！");
			return rt;
		}
		/**
		 * 更新ut信息--删除相关的token缓存
		 * @param userVO
		 * @param deviceId
		 * @param clientId
		 */
		private void updateUT(UserVO userVO,String deviceId,Long clientId){
			// 如果是微信端登录需强迫所有手机号关联的账户下线  clientId=2为微信端
			if((clientId.equals(4L) || clientId.equals(2L)) && EmptyUtil.isNotEmpty(userVO.getMobile())){
				logger.info("微信端登录需强迫所有手机号关联的账户下线");
				//根据手机号码和平台Id查询所有的用户
				List<UserDTO> userList = userManage.findListByManage(userVO.getMobile(),userVO.getPlatformId());
				if(EmptyUtil.isNotEmpty(userList)){
					List<Long> userIds = new ArrayList<Long>();
					for (UserDTO userDTO : userList) {
						userIds.add(userDTO.getId());
					}
					List<UserCookieDTO> userCookieList = userCookieManage.findByUserIds(userIds, clientId,userVO.getPlatformId());
					if(EmptyUtil.isNotEmpty(userCookieList)){
						logger.info("本次登录的userId="+userVO.getId());
						// 删除上次登录用户ut信息
						for (UserCookieDTO userCookieDTO : userCookieList) {
							// 微信登录暂时只允许有一个
							Long userId = userCookieDTO.getUserId();
							logger.info("userId="+userId);
							// 切换用户OpenId
							userExtendManage.switchUserOpenId(userVO.getId(),userId);
							String cookieValue = userCookieDTO.getCookieValue();
							if(StringUtils.isNotEmpty(cookieValue)){
								//根据key删除缓存中的ut
								cache.del(userCookieDTO.getCookieName());
								ticketUtils.deleteCacheUserByUt(cookieValue);
								userCookieManage.deleteCookieUserbyUserId(userVO.getId());
							}
						}
					}
				}
			}
			// 如果是app端或网页端登录则强迫当前账户下线
			else if(clientId.equals(1L) || clientId.equals(3L)){
				logger.info("app端或网页端登录则强迫当前账户下线");
				logger.info("本次登陆的userId="+userVO.getId());
				logger.info("clientId="+clientId);
				//只要是手机登陆，更新ut
				UserCookieDTO userCookieDTO = userCookieManage.getbyUserIdClientId(userVO.getId(),clientId,userVO.getPlatformId());
				if(EmptyUtil.isNotEmpty(userCookieDTO)){
					String ut = userCookieDTO.getCookieValue();
					if(StringUtils.isNotEmpty(ut)){
						//根据key删除缓存中的ut
						cache.del(userCookieDTO.getCookieName());
						ticketUtils.deleteCacheUserByUt(ut);
						userCookieManage.deleteCookieUserbyUserId(userVO.getId());
					}
				}
			}
		}

		/**
		 * 权限判断并且更新ut
		 */
		@Override
		public JsonResult<CacheUser> switchoverLogin(UserVO userVO,String deviceId,Long clientId) {
			return verificationLoginUpdateUT(userVO,deviceId,clientId);
		}

		/**
		 * 根据微信OpenId查询用户信息、最新创建的已激活账号登录
		 */
		@Override
		public UserVO findByWeiXinOpenId(String openId, Long platformId) {
			UserDTO userDTO = null;
			if (PlatformKeyConstant.MYY_PLATFORM_ID.equals(platformId)) {
				userDTO = userManage.findLatestLoginByWeiXinOpenId(openId, platformId);
				if(EmptyUtil.isNotEmpty(userDTO)){
					logger.info("查询结果(最近登录的或者最新创建的已激活账号登录):userId="+userDTO.getId());
				}else{
					logger.info("查询结果(最近登录的或者最新创建的已激活账号登录):userDTO=null");
				}
			} else {
				userDTO = userManage.findByWeiXinOpenId(openId, platformId);
				if(EmptyUtil.isNotEmpty(userDTO)){
					logger.info("[查询结果,最新创建的已激活账号登录]userId="+userDTO.getId());
				}else {
					logger.info("[查询结果,最新创建的已激活账号登录]userDTO=null");
				}
			}
			return UserConverter.toVO(userDTO);
		}
		/**
		 * 绑定手机成功后更新缓存手机号信息
		 */
		@Override
		public void updateUserCache(UserVO userVO,Long platformId,String deviceId,Long clientId){
			// 根据手机号码更新所有用户缓存信息
			List<UserExtendDTO> userExtendList = userExtendManage.userExtendByMobile(userVO.getMobile(),platformId);
			for (UserExtendDTO userExtendDTO : userExtendList) {
				UserCookieDTO userCookieDTO = userCookieManage.getbyUserIdClientId(userExtendDTO.getId(),clientId,platformId);
				if(EmptyUtil.isNotEmpty(userCookieDTO)){
					String ut = userCookieDTO.getCookieValue();
					if(StringUtils.isNotEmpty(ut)){
						CacheUser userCache = null;
			            try {
			                userCache = getUser(ut);
			            } catch (Exception e) {
			                logger.warn("get user error", e);
			            }
			            if(EmptyUtil.isNotEmpty(userCache)){
			            	userCache.setMobile(userVO.getMobile());
							ticketUtils.generateTicket(userCache,ut);
			            }

					}
				}
			}
		}

		public CacheUser getUser(String ut) {

	        try {
	            if (StringUtils.isBlank(ut)) {
	                return null;
	            }

	            Ticket t = (Ticket) this.cache.get(ut);
	            return t.getUser();
	        } catch (Exception e) {
	            throw e;
	        }

	    }

		/**
		 * 根据用户id注销微信登录

		 * @return
		 */
		@Override
		public int weixinSignoutWithTx(Long userId, Long clientId, Long platformId, String mobile) {
			//查询关联账户
			int i = userExtendManage.weixinSignoutWithTx(mobile);
			//只要是手机登陆，更新ut
			UserCookieDTO userCookieDTO = userCookieManage.getbyUserIdClientId(userId,clientId,platformId);
			if(EmptyUtil.isNotEmpty(userCookieDTO)){
				String ut = userCookieDTO.getCookieValue();
				if(StringUtils.isNotEmpty(ut)){
					//根据key删除缓存中的ut
					cache.del(userCookieDTO.getCookieName());
					ticketUtils.deleteCacheUserByUt(ut);
					userCookieManage.deleteCookieUserbyUserId(userId);
				}
			}
			return i;
		}


		@Override
		public void saveWeiXinOpenId(String weiXinCode, String weiXinOpenId) {
			cache.set(weiXinCode, 60, weiXinOpenId);
		}


		@Override
		public String findAccessOpenId(String weiXinCode) {
			return (String) cache.get(weiXinCode);
		}

		/**
		 * web用户会员普通登录
		 */
		@Override
		public JsonResult<CacheUser> webUserLogin(UserVO user, String deviceId, Long clientId) {
			JsonResult<CacheUser> rt = new JsonResult<CacheUser>();
			UserVO userVo = null;
			Long platformId = null;
			String utKey = null;

			if(!StringUtils.isNotEmpty(user.getPassword())){
				rt.setCode(BusinessExceptionConstant.USER_LOGIN_NO_PASSWORD);
				rt.setError("用户登录密码为空");
				return rt;
			}
			if(EmptyUtil.isNotEmpty(user.getPlatformId())){

				userVo = userManage.findUniqueUser(user);
				userVo.setPlatformId(user.getPlatformId());
				UserPlatformDTO userPlatformDTO = new UserPlatformDTO();
				userPlatformDTO.setPlatformId(user.getPlatformId());
				userPlatformDTO.setUserId(userVo.getId());
				if(!userPlatformManage.userIsExitPlatform(userPlatformDTO)){
					rt.setCode(BusinessExceptionConstant.USER_NOT_REGISTER_PLATFORM);
					rt.setError("此用户没有登录该平台权限");
					return rt;
				}
				platformId = user.getPlatformId();

			}else{
				rt.setCode(BusinessExceptionConstant.USER_LOGIN_NOCOMPANYID_OR_NOPLATFORMID);
				rt.setError("用户登录没有带company_id或者platform_id");
				return rt;
			}


			boolean validateFlag=false;//user这边验证通过标志


	    	EncryptEntity encryptEntity=new EncryptEntity();
	    	encryptEntity=CalcEncryInfoUtils.calc(userVo);

	    	//没有盐值,是修改之前的用户,原密码md5加密一次
	        String passWordNullSalt= MD5Support.MD5(user.getPassword());
	        //有old盐值,就用old盐值跟old算法加密
	        String passWordWithSalt=MD5Support.MD5(user.getPassword(), (null==encryptEntity.getOldBSalt())?"":encryptEntity.getOldBSalt());

	        if(StringUtils.isEmpty(encryptEntity.getOldBSalt())){//没有old盐值,是修改之前的用户,原密码md5加密一次
	        	if(userVo.getPassword().equals(passWordNullSalt)){
	        		validateFlag=true;
	        	}
	        }else{//有old盐值,就用old盐值跟old算法加密
	    		if(userVo.getPassword().equals(passWordWithSalt)){
	    			validateFlag=true;

	        	}
	        }


	            if(true==validateFlag){
	            	try {
	            		CacheUser cacheUser = setMobileCacheUser(userVo,platformId);
	            		cacheUser.setName(user.getName());
	            		cacheUser.setCookieName(utKey);
	        			updateUT(userVo, deviceId, clientId);

	            		rt.setCode(0);
	            		rt.setData(cacheUser);
	            		rt.setSuccess( "登录成功！");

		            	try{//更新加密信息的操作不能影响正常的登录

		                	if(StringUtils.isNotEmpty(encryptEntity.getNewBSalt())){
		                    	String salt=StringUtils.isEmpty(encryptEntity.getNewBSalt())?encryptEntity.getOldBSalt():encryptEntity.getNewBSalt();

		                    	UserDTO updUser=UserConverter.toDTO(userVo);

		                        String password=MD5Support.MD5(user.getPassword(),salt);
		                        Date now=new Date();
		                        if(StringUtils.isNotEmpty(encryptEntity.getNewBSalt())){
		                        	updUser.setSalt(encryptEntity.getNewBSalt());
		                        	updUser.setUpdateSalt(now);
		                        }

		                        updUser.setPassword(password);

		                        int cnt=userManage.updateEncryptInfoByUserWithTx(updUser);

		                        if(0==cnt){
		                        	logger.error("更新用户加密密码失败");
		                        	rt.setError("更新用户加密密码失败");
		                        }

		                	}
		                }catch(Exception e1){
		                	logger.error("更新用户加密密码失败");
		                	rt.setError("更新用户加密密码失败");
		                	}

		            	return rt;

					} catch (Exception e) {
						logger.error(e.getMessage());
						rt.setError(e.getMessage());
			            return rt;
					}
	            }else{
	            	rt.setCode(BusinessExceptionConstant.USER_PASSWORD_OR_USERNAME_WRONG);
	            	rt.setError("用户登录的账号或者密码错误");
	                return rt;
	            }
		}


		@Override
		public JsonResult<CacheUser> adminUserAppMobileLogin(String mobile, String identityCode, Long platformId,
				String deviceId, Long clientId, Integer deviceType, String weiXinOpenId) {
			String code=(String) cache.get(CacheKeyConstant.MYY_ADMIN_USER_LOGIN_MOBILE_KEY + mobile);
			if(EmptyUtil.isEmpty(code)){
        		throw new BusinessException("验证码错误");
        	}
			if(!identityCode.equals(code)){
        		throw new BusinessException(BusinessExceptionConstant.VERIFICATIONcODE_WRONG,"验证码错误");
        	}
			//根据用户手机号查询管理员用户信息
			UserVO userVO = userManage.findAdminUserByManage(mobile,platformId);
			if(EmptyUtil.isEmpty(userVO)){
				throw new BusinessException(BusinessExceptionConstant.ADMIN_USER_MOBILE_NO_EMPTY,"暂无权限进入任何门店");
			}
			// 使用成功之后删除缓存
        	cache.del(CacheKeyConstant.MYY_ADMIN_USER_LOGIN_MOBILE_KEY + mobile);
			userVO.setPlatformId(platformId);
			userVO.setWeiXinOpenId(weiXinOpenId);
			return verificationLoginUpdateUT(userVO,deviceId,clientId);
		}


	/*@Override
	public JsonResult<CacheUser> workWechatLogin(String deviceId, UserVO user, String code, String companyId) throws UnsupportedEncodingException {
		JsonResult<CacheUser> rt = new JsonResult<CacheUser>();
		//获取用户信息（如果不存在就创建用户）
		//获取用户详情
		//1.1根据公司id获取accesstoken
		logger.info("步骤2");
		String accessToken = cache.getString(WorkWeChatUtil.PRE_JEDIS_ACCESS_TOKEN_KEY + companyId);
		if(EmptyUtil.isEmpty(accessToken)) {
			//主动查询accesstoken
			accessToken = userManage.getWorkWeChatAccessToken(cache,companyId);
			if (EmptyUtil.isEmpty(accessToken)) {
				rt.setCode(BusinessExceptionConstant.WORK_WECHAT_GET_ACCESS_TOKEN_FAIL);
				rt.setError("获取微信accesstoken失败");
				return rt;
			}
		}

		logger.info("步骤3");

		//1.2更具code获取用户userId
		String res = WorkWeChatUtil.getUserIdFromWorkWechat(code, accessToken);
		JSONObject jsonObject = JSONObject.parseObject(res);
		if(EmptyUtil.isEmpty(jsonObject)||jsonObject.getInteger("errcode")!=0){
			if(jsonObject.getInteger("errcode")==42001){
				//accesstoken过期删除
				cache.del(WorkWeChatUtil.PRE_JEDIS_ACCESS_TOKEN_KEY + companyId);
			}
			logger.error("获取用户id失败");
			rt.setCode(BusinessExceptionConstant.WORK_WECHAT_GET_USER_ID_FAIL);
			rt.setError("获取用户id失败,请重试");
			return rt;
		}
		logger.info("步骤4");

		String wxUserId = jsonObject.getString("UserId");
		deviceId = jsonObject.getString("DeviceId");
		logger.info("userId="+wxUserId);
		logger.info("accessToken="+accessToken);

		//根据userId获取openId
		String url = "https://qyapi.weixin.qq.com/cgi-bin/user/convert_to_openid?access_token="+accessToken;
		Map<String, String> param = new HashMap<>();
		param.put("userid",wxUserId);
		String jsonStr = HttpClientUtil.doPostByJson(url, "UTF-8",JSONObject.toJSONString(param));
		JSONObject parseObject = JSONObject.parseObject(jsonStr);
		String openId = "";
		if(EmptyUtil.isNotEmpty(parseObject)&&parseObject.getInteger("errcode")==0){
			openId=parseObject.getString("openid");

		}else{
			logger.error("获取用户openid失败");
			rt.setCode(BusinessExceptionConstant.WORK_WECHAT_GET_USER_ID_FAIL);
			rt.setError("获取用户openid失败,请重试");
			return rt;
		}
		user.setWeiXinOpenId(openId);

		//1.3更具userId获取用户详情
		String userInfoStr = WorkWeChatUtil.getUserInfoFromWorkWechat(wxUserId, accessToken);
		JSONObject userInfoObj = JSONObject.parseObject(userInfoStr);
		if(EmptyUtil.isEmpty(userInfoObj)||userInfoObj.getInteger("errcode")!=0){
			if(jsonObject.getInteger("errcode")==42001){
				//accesstoken过期删除
				cache.del(WorkWeChatUtil.PRE_JEDIS_ACCESS_TOKEN_KEY + companyId);
			}
			logger.error("获取用过户信息失败");
			rt.setCode(BusinessExceptionConstant.WORK_WECHAT_GET_USER_INFO_FAIL);
			rt.setError("获取用过户信息失败,请重试");
			return rt;
		}
		logger.info("步骤5");

		String name=userInfoObj.getString("name");
		String mobile=userInfoObj.getString("mobile");
		String email=userInfoObj.getString("email");
		logger.info("name:"+name);
		logger.info("mobile:"+mobile);
		logger.info("email:"+email);
		if(EmptyUtil.isEmpty(email)){
			logger.error("邮箱信息为空");
			rt.setCode(BusinessExceptionConstant.WORK_WECHAT_MAIL_USED);
			rt.setError("请在个人信息中完善邮箱信息");
			return rt;
		}
		//创建用户（已存在用户直接返回）
		logger.info("步骤6");

		user=UserConverter.toVO(userManage.createUser(name,mobile,email,companyId,deviceId));

		if(!user.getCompanyId().equals(Long.valueOf(companyId))){
			logger.error("当前用不属于该公司");
			rt.setCode(BusinessExceptionConstant.WORK_WECHAT_MAIL_USED);
			rt.setError("当前用不属于该公司");
			return rt;
		}
		//登录
		Long platformId = 7L;
		String utKey = null;
		logger.info("步骤7");

		CacheUser cacheUser = setMobileCacheUser(user,platformId);
		cacheUser.setName(user.getName());
		cacheUser.setCookieName(utKey);
		updateUT(user, deviceId, 4L);

		rt.setCode(0);
		rt.setData(cacheUser);
		rt.setSuccess( "登录成功！");

		return rt;
	}*/

	@Override
	public JsonResult<CacheUser> workWechatLogin(String deviceId, UserVO user, String code) throws UnsupportedEncodingException {
		JsonResult<CacheUser> rt = new JsonResult<CacheUser>();
		//获取用户信息（如果不存在就创建用户）
		//获取用户详情
		//1.1获取user_ticket
		logger.info("步骤2");
		String suiteAccessToken = cache.getString(WorkWeChatUtil.PRE_JEDIS_SUITE_ACCESS_TOKEN_KEY);
		if(EmptyUtil.isEmpty(suiteAccessToken)) {
			//主动查询accesstoken
			suiteAccessToken = userManage.getWorkWeChatSuiteAccessToken(cache);
			if (EmptyUtil.isEmpty(suiteAccessToken)) {
				rt.setCode(BusinessExceptionConstant.WORK_WECHAT_GET_SUITE_ACCESS_TOKEN_FAIL);
				rt.setError("获取微信SUITEaccesstoken失败");
				return rt;
			}
		}

		logger.info("步骤3");

		//1.2更具code获取用户userId
		String res = workWeChatUtil.getUserTicketFromWorkWechat(code,suiteAccessToken);
		JSONObject jsonObject = JSONObject.parseObject(res);
		if(EmptyUtil.isEmpty(jsonObject)||jsonObject.getInteger("errcode")!=0){
			if(jsonObject.getInteger("errcode")==42001){
				//accesstoken过期删除
				cache.del(WorkWeChatUtil.PRE_JEDIS_SUITE_ACCESS_TOKEN_KEY );
			}
			logger.error("获取用户id失败");
			rt.setCode(BusinessExceptionConstant.WORK_WECHAT_GET_USER_ID_FAIL);
			rt.setError("获取用户id失败,请重试");
			return rt;
		}
		logger.info("步骤4");

		String wxUserId = jsonObject.getString("UserId");
		String CorpId = jsonObject.getString("CorpId");
		String userTicket = jsonObject.getString("user_ticket");
		deviceId = jsonObject.getString("DeviceId");
		logger.info("userId="+wxUserId);
		logger.info("suiteAccessToken="+suiteAccessToken);
		logger.info("userTicket ="+userTicket );


		CompanyDTO companyDTO = new CompanyDTO();
		companyDTO.setCorpid(CorpId);
		List<CompanyDTO> list= userManage.findCompanyByParam(companyDTO);
		if(EmptyUtil.isEmpty(list)||list.size()>1){
			logger.error("当前用户所属公司不存在问题");
			rt.setCode(BusinessExceptionConstant.WORK_WECHAT_COMPANY_ERROR);
			rt.setError("当前用户所属公司不存在问题,联系管理员");
			return rt;
		}
		String permanentCode = list.get(0).getSecret();
		Long companyId = list.get(0).getId();
		//获取suiteAccessToken
		String accessTokenStr = workWeChatUtil.getAccessToken(CorpId,suiteAccessToken, permanentCode);
		JSONObject accessTokenObject = JSONObject.parseObject(accessTokenStr);
		String accessToken = "";
		if(EmptyUtil.isNotEmpty(accessTokenObject)&&EmptyUtil.isNotEmpty(accessTokenObject.getString("access_token"))){
			accessToken=accessTokenObject.getString("access_token");

		}else{
			logger.error("获取用户accessToken失败");
			rt.setCode(BusinessExceptionConstant.WORK_WECHAT_GET_ACCESS_TOKEN_FAIL);
			rt.setError("获取用户accessToken失败,请重试");
			return rt;
		}
		//根据userId 获取openId
		String jsonStr = workWeChatUtil.userIdToOpenId(wxUserId,accessToken);
		JSONObject parseObject = JSONObject.parseObject(jsonStr);
		String openId = "";
		if(EmptyUtil.isNotEmpty(parseObject)&&parseObject.getInteger("errcode")==0){
			openId=parseObject.getString("openid");

		}else{
			logger.error("获取用户openid失败");
			rt.setCode(BusinessExceptionConstant.WORK_WECHAT_GET_USER_ID_FAIL);
			rt.setError("获取用户openid失败,请重试");
			return rt;
		}
		user.setWeiXinOpenId(openId);

		//1.3更具userId获取用户详情
		String userInfoStr = workWeChatUtil.getUserInfoFromWorkWechat(wxUserId, accessToken);
		JSONObject userInfoObj = JSONObject.parseObject(userInfoStr);
		if(EmptyUtil.isEmpty(userInfoObj)||userInfoObj.getInteger("errcode")!=0){
			logger.error("获取用过户信息失败");
			rt.setCode(BusinessExceptionConstant.WORK_WECHAT_GET_USER_INFO_FAIL);
			rt.setError("获取用过户信息失败,请重试");
			return rt;
		}
		logger.info("步骤5");

		String name=userInfoObj.getString("name");
		String mobile=userInfoObj.getString("mobile");
		String email=userInfoObj.getString("email");
		logger.info("name:"+name);
		logger.info("mobile:"+mobile);
		logger.info("email:"+email);
		/*if(EmptyUtil.isEmpty(email)){
			logger.error("邮箱信息为空");
			rt.setCode(BusinessExceptionConstant.WORK_WECHAT_MAIL_USED);
			rt.setError("请在个人信息中完善邮箱信息");
			return rt;
		}*/
		//创建用户（已存在用户直接返回）
		logger.info("步骤6");

		user=UserConverter.toVO(userManage.createUser(wxUserId,name,mobile,email,companyId,deviceId));

		if(!user.getCompanyId().equals(Long.valueOf(companyId))){
			logger.error("当前用不属于该公司");
			rt.setCode(BusinessExceptionConstant.WORK_WECHAT_MAIL_USED);
			rt.setError("当前用不属于该公司");
			return rt;
		}
		//登录
		Long platformId = 7L;
		String utKey = null;
		logger.info("步骤7");

		CacheUser cacheUser = setMobileCacheUser(user,platformId);
		cacheUser.setName(user.getName());
		cacheUser.setMail(user.getMail());
		cacheUser.setCookieName(utKey);
		updateUT(user, deviceId, 4L);

		rt.setCode(0);
		rt.setData(cacheUser);
		rt.setSuccess( "登录成功！");

		return rt;
	}
	@Override
	public void saveWorkWechatAuthInfo(String s) {
		//通过临时授权码换永久授权码
		String suiteAccessToken =cache.getString(WorkWeChatUtil.PRE_JEDIS_SUITE_ACCESS_TOKEN_KEY );
		logger.info("suiteAccessToken:"+suiteAccessToken);
		if(EmptyUtil.isEmpty(suiteAccessToken)) {
			//主动查询accesstoken
			suiteAccessToken = userManage.getWorkWeChatSuiteAccessToken(cache);
			if (EmptyUtil.isEmpty(suiteAccessToken)) {
				throw new BusinessException("获取微信suiteaccesstoken失败");
			}
		}
		logger.info("suiteaccessticket:"+suiteAccessToken);
		String jsonStr=workWeChatUtil.getCompanyPermanentCode(suiteAccessToken,s);
		logger.info("授权信息："+jsonStr);
		JSONObject parseObject = JSONObject.parseObject(jsonStr);
		if(EmptyUtil.isNotEmpty(parseObject)){
			String permanentCode = parseObject.getString("permanent_code");
			if(EmptyUtil.isNotEmpty(permanentCode)){
				JSONObject info = parseObject.getJSONObject("auth_corp_info");
				String corpid = info.getString("corpid");
				CompanyDTO companyDTO = new CompanyDTO();
				companyDTO.setCorpid(corpid);
				List<CompanyDTO> list=userManage.findCompanyByParam(companyDTO);
				if(list.size()==0||list.size()>1){
					throw new BusinessException("当前企业微信id对应多个公司或无对应公司");
				}
				CompanyDTO dto = list.get(0);
				dto.setSecret(permanentCode);
				userManage.saveCompany(dto);
			}else{
				logger.error("获取企业永久授权码失败");
				throw new BusinessException("获取企业永久授权码失败:"+jsonStr);
			}

		}else{
			logger.error("获取企业永久授权码失败");
			throw new BusinessException("获取企业永久授权码失败:"+jsonStr);
		}
	}

	@Override
	public void checkIdentityCode(String mobile,String identityCode,boolean checkIdentityCode) {
		if (Objects.equals(false,checkIdentityCode)){
			return;
		}
		String code=(String) cache.get(CacheKeyConstant.MOBILE_LOGIN_KEY + mobile);
		if(EmptyUtil.isEmpty(code)){
			throw new BusinessException("验证码错误");
		}
		if(!identityCode.equals(code)){
			throw new BusinessException(BusinessExceptionConstant.VERIFICATIONcODE_WRONG,"验证码错误");
		}
	}

	@Override
	public JsonResult<CacheUser> channelUserUniqueLogin(String type,String channelUserUnique,
											 String identityCode, Long platformId,String deviceId,Long clientId,String weiXinOpenId,
											 String channelName,String campaignCode,String registerShopCode,Integer deviceType,String version,Boolean checkIdentityCode,Long companyId ) {
		//根据用户手机号查询用户信息
		UserVO userVO = null;
		List<UserVO> userList = userManage.findByChannelUserUniqueAndRegister(channelUserUnique,companyId,platformId);
		//如果用户存在,查询对应的最近登录的用户默认登录
		if(EmptyUtil.isNotEmpty(userList)){
			if(EmptyUtil.isNotEmpty(userList.get(0).getId())){
				logger.info("user存在,查询对应的最近登录的用户默认登录,"+userList.get(0).getId());
			}
			userVO = userList.get(0);
			if(PlatformKeyConstant.MYY_PLATFORM_ID.equals(platformId) && userList.size() > 1){
				UserVO latestLoginUserVO = userManage.findLatestLoginByChannelUserUnique(channelUserUnique,companyId,platformId);
				if (EmptyUtil.isNotEmpty(latestLoginUserVO)) {
					userVO = latestLoginUserVO;
				}
			}
		}
		if(PlatformKeyConstant.MYY_PLATFORM_ID.equals(platformId)
				&& (EmptyUtil.isEmpty(userVO) || (userList.size() == 1 && !CompanyConstant.MYY_COMPANY_ID.equals(userVO.getCompanyId())))){
			// 新增用户、注册授权并且返回信息
			logger.info("慢有优创建用户");
			UserVO createdUserVO = createChannelUser(channelUserUnique,platformId,deviceId,channelName,campaignCode,registerShopCode,deviceType,version);
			if (EmptyUtil.isNotEmpty(createdUserVO)) {
				//自动领取注册优惠券
				createdUserVO.setPlatformId(platformId);
				userManage.autoReceiveRegisterCoupon(createdUserVO);
			}
			if (EmptyUtil.isEmpty(userVO)) {
				userVO = createdUserVO;
			}
		} else if ("coupon_unit".equals(type) && PlatformKeyConstant.FGJ_PLATFORM_ID.equals(platformId)
				&&(EmptyUtil.isEmpty(userVO) || (userList.size() == 1 && !CompanyConstant.FGJ_COMPANY_ID.equals(userVO.getCompanyId())))){
			//福管加从优惠卷unit扫码过来的可以自动注册
			// 新增用户、注册授权并且返回信息
			logger.info("couponunit扫码领取自动创建用户");
			UserVO createdUserVO = createChannelUser(channelUserUnique,platformId,deviceId,channelName,campaignCode,registerShopCode,deviceType,version);
			if (EmptyUtil.isNotEmpty(createdUserVO)) {
				//自动领取注册优惠券
				createdUserVO.setPlatformId(platformId);
				userManage.autoReceiveRegisterCoupon(createdUserVO);
			}
			if (EmptyUtil.isEmpty(userVO)) {
				userVO = createdUserVO;
			}
		}else if(EmptyUtil.isEmpty(userVO)&&PlatformKeyConstant.FGJ_PLATFORM_ID.equals(platformId)&&!("coupon_unit".equals(type))){
			logger.info("用户不存在以激活帐户");
			logger.info("参数:"+platformId);
			logger.info("参数:"+type);
			List<UserVO> userIdList=userManage.findByChannelUserUniqueAndRegister(channelUserUnique,companyId,platformId);
			if(EmptyUtil.isNotEmpty(userIdList)){
				logger.info("查询参数:"+userIdList.size());
				//不为空则直接激活用户
				List<Long> userIds = new ArrayList<>();
				for(UserVO vo:userIdList){
					userIds.add(vo.getId());
					logger.info("userId"+vo.getId());
				}
				//激活账户
				userManage.activateUserByIds(userIds);
				//福管加门店id
				userIdList.get(0).setRegisterStoreId(1+"");
				//领取优惠券
				userManage.autoReceiveRegisterCoupon(userIdList.get(0));

				if (EmptyUtil.isEmpty(userVO)) {
					userVO = userIdList.get(0);
				}

			}
		}
		if(EmptyUtil.isEmpty(userVO)){
			throw new BusinessException(BusinessExceptionConstant.USER_MOBILE_NO_EMPTY,"请先注册");
		}
		// 使用成功之后删除缓存
		if (Objects.equals(true,checkIdentityCode)){
			cache.del(CacheKeyConstant.MOBILE_LOGIN_KEY + channelUserUnique);
		}
		userVO.setPlatformId(platformId);
		userVO.setWeiXinOpenId(weiXinOpenId);
		return verificationLoginUpdateUT(userVO,deviceId,clientId);
	}

	/**
	 * 新增用户、注册授权并且返回信息
	 * @param channelUserUnique 渠道用户标志
	 * @param platformId 平台id
	 * @return
	 */
	private UserVO createChannelUser(String channelUserUnique, Long platformId,String deviceId,String channelName,String campaignCode,String registerShopCode,Integer deviceType,String version) {
		// 组织新增用户数据
		UserTempConditionVO vo = new UserTempConditionVO();
		vo.setName(channelUserUnique);
		//vo.setMobile(mobile);
		if(platformId.equals(2L)){
			vo.setMail(channelUserUnique + "@" + PlatformKeyConstant.MYY_PLATFORM_DOMAIN);
			// 根据平台id查询其下员工数量
			Integer userCount = userPlatformManage.findUserCountByPlatformId(platformId);
			vo.setMemberCode(PlatformKeyConstant.MYY_PLATFORM_USER_CODE_PREFIX + "-" + userCount + 1);
			vo.setCompanyId(CompanyConstant.MYY_COMPANY_ID);
		}else if(platformId.equals(7L)){
			vo.setMail(channelUserUnique + "@" + PlatformKeyConstant.FGJ_PLATFORM_DOMAIN);
			vo.setMemberCode("FGJEC"+channelUserUnique);
			vo.setCompanyId(CompanyConstant.FGJ_COMPANY_ID);
		}

		// 是否是管理员  0：否  1：是
		vo.setIsAdministrator(0);
		// 是否是管理员是否置空 1：取是否是管理员
		vo.setCurrUserId(1L);
		vo.setPlatformId(platformId);
		vo.setRegisterShopCode(registerShopCode);
		userManage.insertOrUpdateUser(vo,null);

		// 注册
		UserVO userVO = new UserVO();
		userVO.setDeviceType(deviceType);
		userVO.setVersion(version);
		userVO.setDeviceId(deviceId);
		userVO.setMail(vo.getMail());
		if(platformId.equals(2L)){
			userVO.setPassword(PlatformKeyConstant.MYY_PLATFORM_USER_CODE_PREFIX + channelUserUnique);
		}else if(platformId.equals(7L)){
			userVO.setPassword(PlatformKeyConstant.FGJ_PLATFORM_USER_CODE_PREFIX + channelUserUnique);

		}
		userVO.setPlatformId(platformId);
		userVO.setCampaignCode(campaignCode);
		userVO.setRegisterStoreId(registerShopCode);
		//慢有优注册时传入的channelName就是ID，可以直接设置
		userVO.setChannelId(EmptyUtil.isEmpty(channelName) ? null : Long.valueOf(channelName));
		userManage.userRegister(userVO,channelName);

		//根据用户手机号查询用户信息
		UserVO user = null;
		List<UserVO> userList = userManage.findByChannelUserUniqueAndRegister(channelUserUnique,vo.getCompanyId(),platformId);
		if(EmptyUtil.isNotEmpty(userList)){
			user = userList.get(0);
			user.setRegisterStoreId(registerShopCode);
			user.setCampaignCode(campaignCode);
		}
		return user;
	}
}
