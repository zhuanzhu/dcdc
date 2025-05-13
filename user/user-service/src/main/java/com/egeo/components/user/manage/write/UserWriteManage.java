package com.egeo.components.user.manage.write;

import java.util.List;

import com.egeo.components.user.dto.UserDTO;
import com.egeo.components.user.po.UserCookiePO;
import com.egeo.components.user.po.UserExtendPO;
import com.egeo.components.user.po.UserPO;
import com.egeo.components.user.po.UserTempPO;
import com.egeo.components.user.po.UserWelfarePO;

public interface UserWriteManage {

    int updateEncryptInfoByUserWithTx(UserPO po);

    int updateUserInfo(UserPO po);

    String saveOrUpdateWithTx(UserPO po, UserExtendPO userExtendPO, UserCookiePO userCookiePO);

    void delete(Long id);

    void deleteByImportIdWithTx(Long id,Long importId);

    UserPO getIdUser(UserPO po);

    Long save(UserPO po);

    int update(UserPO tar);
    /**
     * 用户注册
     * @param dto
     * @return
     */
	Long userRegisterWithTx(UserPO po,String channelName);
	/**
	 * 更新用户主表信息
	 */
	int updateUserWithTx(Long userId, Long companyId);
	/**
	 * 根据邮箱修改密码
	 * @param vo
	 * @return
	 */
	int updateUserPasswordWithTx(String mail, String password,String salt);
	/**
	 * 添加用户邮箱信息返回用户id
	 * @param mail
	 * @return
	 */
	Long saveUserMailWithTx(String mail,Long companyId);

	/**
	 * 账户绑定手机号
	 * @param code
	 * @param userIds
	 * @return
	 */
	int saveUserMobileWithTx(String mobile, Long userId,String weiXinOpenId);
	/**
	 * 根据用户邮箱设置支付密码
	 * @param mail
	 * @param password
	 * @return
	 */
	int setPaymentPasswordWithTx(String mail, String md5,String paymentCodeSaltId);

	/**
	 * 更新用户相关表
	 * @param po
	 * @return
	 */
	Long updateAboutUser(List<UserTempPO> po,int type);
	/**
	 * 根据用户id修改用户密码
	 * @param userId
	 * @param password
	 * @return
	 */
	int updateUserPasswordWithTx(Long userId, String password,String originalPassword);

	int updateQuitTime(UserPO po);
	/**
	 * 根据用户Id集合解绑用户
	 * @param req
	 * @return
	 */
	int unbindByUserIdsWithTx(List<Long> userIdList);
	/**
	 * 根据用户之前手机号统一换绑手机
	 * @param mobile
	 * @param beforeMobile
	 * @param platformId
     * @return
	 */
	int bindingMobileByBeforeMobileWithTx(String mobile, String beforeMobile, Long platformId);
	/**
	 * 设置员工状态为离职和失效
	 * @param userId
	 * @return
	 */
	int updateIsAvailableAccountStatus(Long userId,Integer isAvailable);
	/**
	 * 批量保存用户信息
	 * @param userTempList
	 * @return
	 */
	List<UserPO> insertUserAllWithTx(List<UserTempPO> userTempList);
	/**
	 * 根据导入批次id查询导入用户数据同步到正式表中
	 * @param importId
	 * @return
	 */
	int syncUserAll(Long importId);

	/**
	 * 添加用户信息相关的表
	 * @param userPO
	 * @param userExtendPO
	 * @param userWelfarePO
	 * @return
	 */
	Long insertAllUserInfosWithTx(UserPO userPO, UserExtendPO userExtendPO, UserWelfarePO userWelfarePO);

	/**
	 * 更新用户相关的表
	 * @param userPO
	 * @param userExtendPO
	 * @param userWelfarePO
	 * @return
	 */
	Long updateAllUserInfosWithTx(UserPO userPO, UserExtendPO userExtendPO, UserWelfarePO userWelfarePO);


	Long createUserWithTx(UserPO userPO, UserExtendPO userExtendPO);

	public int resetManageUserPasswordWithTx(Long userId, String password);

	int switchUserEnterpriseByCompanyId(UserPO userPO,Long companyNewId);
}
