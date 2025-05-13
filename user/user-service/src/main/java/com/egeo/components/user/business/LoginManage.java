package com.egeo.components.user.business;

import com.egeo.components.user.vo.UserCookieVO;
import com.egeo.components.user.vo.UserVO;
import com.egeo.entity.CacheUser;
import com.egeo.web.JsonResult;

import java.io.UnsupportedEncodingException;

public interface LoginManage {

	JsonResult<CacheUser> login(UserVO user);
	JsonResult<CacheUser> manageLogin(UserVO user);

	void saveUttoCache(UserVO user, UserCookieVO userCookie,String deviceId,Long clientId);

	JsonResult<CacheUser> mobileLogin(UserVO user,String deviceId,Long clientId);

	JsonResult<CacheUser> mobileLogin(String type, String mobile,
                                      String identityCode, Long platformId, String deviceId, Long clientId, String weiXinOpenId,
                                      String channelName, String campaignCode, String registerShopCode, Integer deviceType, String version);

	JsonResult<CacheUser> mobileLogin(String type, String mobile,
									  String identityCode, Long platformId, String deviceId, Long clientId, String weiXinOpenId,
									  String channelName, String campaignCode, String registerShopCode, Integer deviceType, String version,Boolean checkIdentityCode);
	/**
	 * 权限判断并且更新ut
	 * @param userVO
	 * @return
	 */
	JsonResult<CacheUser> switchoverLogin(UserVO userVO,String deviceId,Long clientId);
	/**
	 * 根据微信OpenId查询用户信息、最新创建的已激活账号登录
	 * @param openId
	 * @return
	 */
	UserVO findByWeiXinOpenId(String openId, Long platformId);
	/**
	 * 更新用户缓存
	 * @param userVO
	 * @param platformId
	 * @param deviceId
	 * @param clientId
	 */
	void updateUserCache(UserVO userVO,Long platformId,String deviceId,Long clientId);
	/**
	 * 根据用户id注销微信登录
	 * @param id
	 * @param mobile
     * @return
	 */
	int weixinSignoutWithTx(Long userId, Long clientId, Long platformId, String mobile);

	void saveWeiXinOpenId(String weiXinCode, String weiXinOpenId);
	/**
	 * 根据微信票据获取微信openid
	 * @param weiXinCode
	 * @return
	 */
	String findAccessOpenId(String weiXinCode);
	/**
	 * web用户会员普通登录
	 * @param user
	 * @param deviceId
	 * @param clientId
	 * @return
	 */
	JsonResult<CacheUser> webUserLogin(UserVO user, String deviceId, Long clientId);

	JsonResult<CacheUser> adminUserAppMobileLogin(String mobile, String identityCode, Long platformId, String deviceId,
			Long clientId, Integer deviceType, String weiXinOpenId);

    JsonResult<CacheUser> workWechatLogin(String deviceId, UserVO user, String code) throws UnsupportedEncodingException;

    void saveWorkWechatAuthInfo(String s);

	void checkIdentityCode(String mobile,String identityCode,boolean checkIdentityCode);

	public JsonResult<CacheUser> channelUserUniqueLogin(String type,String channelUserUnique,
														String identityCode, Long platformId,String deviceId,Long clientId,String weiXinOpenId,
														String channelName,String campaignCode,String registerShopCode,Integer deviceType,String version,Boolean checkIdentityCode,Long companyId);
}
