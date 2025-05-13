package com.egeo.components.user.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.user.condition.UserCondition;
import com.egeo.components.user.po.UserPO;
import com.egeo.orm.BaseReadDAO;
import com.egeo.orm.Pagination;

public interface UserReadDAO extends BaseReadDAO<UserPO>{

    int countOfPageUser(@Param("userCondition") UserCondition userCondition);
    int countOfPageUserMore(@Param("userCondition") UserCondition userCondition);

	public UserPO findManageById(@Param("po") UserPO po);

	public List<UserPO> findManagePage(@Param("po")UserPO po, @Param("page") Pagination page);

    List<UserCondition> findOfPageUser(@Param("userCondition") UserCondition userCondition,@Param("page") Pagination page);

    List<UserCondition> findUniqueUser(@Param("po")UserPO po,@Param("page") Pagination page);


    List<UserCondition> findOfPageUserMore(@Param("userCondition") UserCondition userCondition,@Param("page") Pagination page);


    List<UserCondition> findExistManageUser(@Param("po")UserPO po);

	int count(@Param("po")UserPO po , @Param("page") Pagination page);

	int countManage(@Param("po")UserPO po , @Param("page") Pagination page);

	List<UserPO> page(@Param("po")UserPO po, @Param("page")Pagination page);
	/**
	 * 通过用户id查询用户昵称和头像信息
	 * @return
	 */
	UserCondition userWelfareAppById(@Param("userId")Long userId);

	/**
	 * 批量查询用户信息
	 * @param userIdList
	 * @return
	 */
	List<UserPO> queryUserByIds(@Param("ids")List<Long> userIdList);
	/**
	 * 根据邮箱查询用户信息
	 * @param mail
	 * @return
	 */
	List<UserCondition> userByMail(@Param("mail")String mail);

	/**
	 * 根据公司id查询用户
	 * @param companyId
	 * @return
	 */
	List<UserPO> findUsersByCompanyId(@Param("companyId")Long companyId);
	/**
	 * 根据用户id查询用户支付密码UUID
	 * @param userId
	 * @return
	 */
	String findByPaymentPasswordUUID(@Param("userId")Long userId);
	/**
	 * 根据邮箱集合查id集合
	 * @param mails
	 * @return
	 */
	List<Long> mailsToUserIds(@Param("mails")List<String> mails);

	/**
	 * 根据Email模糊查询用户
	 * @param email
	 * @return
	 */
	List<UserPO> queryUsersByEmail(@Param("email")String email);
	/**
	 * 根据邮箱查询用户信息
	 */
	List<UserCondition> findByMail(@Param("mail")String mail);
	/**
	 * 根据手机号查询会员列表
	 * @param mobile
	 * @return
	 */
	List<UserPO> findByMobile(@Param("mobile")String mobile,@Param("platformId")Long platformId);
	/**
	 * 根据微信OpenId查询用户信息、最新创建的已激活账号登录
	 * @param openId
	 * @return
	 */
	List<UserPO> findByWeiXinOpenId(@Param("weixin")String openId, @Param("platformId")Long platformId);
	/**
	 * 根据手机号查询会员列表、根据注册时间排序(已注册)
	 * @param mobile
	 * @param platformId
	 * @return
	 */
	List<UserPO> findByMobileAndRegister(@Param("mobile")String mobile,@Param("platformId")Long platformId);
	/**
	 * 根据手机号查询会员列表、按最后登录时间排序
	 * @param mobile
	 * @param platformId
	 * @return
	 */
	UserPO findLatestLoginByMobile(@Param("mobile")String mobile,@Param("platformId")Long platformId);
	/**
	 * 根据导入批次id查询用户信息
	 * @param importId
	 * @return
	 */
	List<UserPO> findByImportId(@Param("importId")Long importId);
	/**
	 * 根据公司id查询在职用户Id集合
	 * @param companyId
	 * @param isAdministrator 是否是管理员  0：否  1：是
	 * @return
	 */
	List<Long> findUserIdsByCompanyId(@Param("companyId")Long companyId, @Param("isAdministrator")Integer isAdministrator);
	/**
	 * 根据用户手机号查询管理员用户信息
	 * @param mobile
	 * @param platformId
	 * @return
	 */
	List<UserPO> findAdminUserByManage(@Param("mobile")String mobile,@Param("platformId")Long platformId);

	/**
	 * 根据微信OpenId查询用户信息、最近登录的或者最新创建的已激活账号登录
	 * @param openId
	 * @return
	 */
	List<UserPO> findLatestLoginByWeiXinOpenId(@Param("weixin")String openId, @Param("platformId")Long platformId);
	/**
	 * 根据平台公司及手机判断是否存在有效用户
	 * @param mobile
	 * @param companyId
	 * @param platformId
	 * @return
	 */
	int findIsExistUser(
			@Param("mobile")String mobile,
			@Param("isTrue")Boolean isTrue,
			@Param("platformId")Long platformId);

	List<UserPO> findUserByMobile(@Param("mobile")String mobile,@Param("platformId") Long platformId);

	UserPO findByMailAndCompany(@Param("mail")String mail, @Param("companyName")String companyName);
	List<UserPO> findByAccountAndMobile(@Param("account")String account, @Param("mobile")String mobile);

	/**
	 * 根据手机号查询会员列表、根据注册时间排序(已注册)
	 * @param channelUserUnique
	 * @param platformId
	 * @return
	 */
	List<UserPO> findByChannelUserUniqueAndRegister(@Param("channelUserUnique")String channelUserUnique,@Param("companyId")Long companyId,@Param("platformId")Long platformId);

	/**
	 * 根据手机号查询会员列表、按最后登录时间排序
	 * @param channelUserUnique
	 * @param platformId
	 * @return
	 */
	UserPO findLatestLoginByChannelUserUnique(@Param("channelUserUnique")String channelUserUnique,@Param("companyId")Long companyId,@Param("platformId")Long platformId);
}

