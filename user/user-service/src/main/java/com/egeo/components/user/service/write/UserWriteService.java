package com.egeo.components.user.service.write;


import java.util.List;

import com.egeo.components.user.dto.UserCookieDTO;
import com.egeo.components.user.dto.UserDTO;
import com.egeo.components.user.dto.UserExtendDTO;
import com.egeo.components.user.dto.UserTempDTO;
import com.egeo.components.user.dto.UserWelfareDTO;

public interface UserWriteService {
	/**
	 * 修改用户密码
	 * @param updUser
	 * @return
	 */
	int updateEncryptInfoByUserWithTx(UserDTO updUser);

	int updateUserInfoWithTx(UserDTO user);

	void deleteWithTx(UserDTO dto);

	void deleteByImportIdWithTx(Long id,Long importId);

	String saveOrUpdateWithTx(UserDTO dto,UserExtendDTO userExtendDTO,UserCookieDTO userCookieDTO);
	/**
     * 用户注册
     * @param dto
     * @return
     */
	Long userRegisterWithTx(UserDTO dto,String channelName);
	/**
	 * 更新用户主表信息
	 */
	int updateUserWithTx(Long userId, Long companyId);
	/**
	 * 根据邮箱修改密码
	 * @param vo
	 * @return
	 */
	int updateUserPassword(String mail, String password);
	/**
	 * 添加用户邮箱信息返回用户id
	 * @param mail
	 * @return
	 */
	Long saveUserMail(String mail,Long companyId);

	/**
	 * 给用户绑定手机号
	 * @param code
	 * @param identityCode
	 * @param userId
	 * @return
	 */
	int saveUserMobile(String mobile, Long userId,String weiXinOpenId);
	/**
	 * 根据用户邮箱设置支付密码
	 * @param mail
	 * @param password
	 * @return
	 */
	int setPaymentPassword(String mail, String md5,String paymentCodeSaltId );

	/**跟新和用户相关的表(此方法勿用)
	 * @param userList
	 * @param userOrderInfo
	 * @return
	 */
	List<UserDTO> userIdassureImportUserAboutUserWithTx(List<UserTempDTO> userList, String userOrderInfo);

	/**
	 *
	 * @param userDTO
	 * @return
	 */
	Long insertUserWithTx(UserDTO userDTO);

	/**
	 * 更新和用户相关的表
	 * @param userTempList
	 * @return
	 */
	Long updateAboutUser(List<UserTempDTO> userTempList,int type);
	/**
	 * 根据用户id修改用户密码
	 * @param userId
	 * @param password
	 * @return
	 */
	int updateUserPasswordWithTx(Long userId, String password,String originalPassword);
	/**
	 * 根据用户id修改用户密码
	 * @param userId
	 * @param password
	 * @return
	 */
	int resetManageUserPasswordWithTx(Long userId, String password);

	int updateQuitTime(UserDTO dto);
	/**
	 * 根据用户Id集合解绑用户
	 * @param req
	 * @return
	 */
	int unbindByUserIds(List<Long> userIdList,Long platformId);
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
	 * 根据导入批次id查询导入用户数据同步到正式表中
	 * @param importId
	 * @return
	 */
	int syncUserAll(Long importId);

	/**
	 * 保存用户相关信息
	 * @param userDTO
	 * @param userExtendDTO
	 * @param userWelfareDTO
	 * @return
	 */
	Long insertAllUserInfosWithTx(UserDTO userDTO, UserExtendDTO userExtendDTO, UserWelfareDTO userWelfareDTO);

	/**
	 * 更新用户信息相关表
	 * @param userDTO
	 * @param userExtendDTO
	 * @param userWelfareDTO
	 * @return
	 */
	Long updateAllUserInfosWithTx(UserDTO userDTO, UserExtendDTO userExtendDTO, UserWelfareDTO userWelfareDTO);


    Long createUserWithTx(UserDTO userDTO, UserExtendDTO extendDTO);

	int switchUserEnterpriseByCompanyId(UserDTO userDTO,Long companyNewId);
}
