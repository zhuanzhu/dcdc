package com.egeo.components.user.dao.write;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.user.po.UserPO;
import com.egeo.orm.BaseWriteDAO;

public interface UserWriteDAO extends BaseWriteDAO<UserPO> {
	/**
	 * 根据邮箱修改密码
	 * @param vo
	 * @return
	 */
	int updateUserPassword(@Param("mail")String mail, @Param("password")String password,@Param("salt")String salt);

	/**
	 * 绑定手机号码
	 * @param mobile
	 * @param userId
	 * @return
	 */
	int saveUserMobile(@Param("mobile")String mobile, @Param("id")Long userId);
	/**
     * 用户注册
     * @param dto
     * @return
     */
	int userRegister(@Param("po")UserPO po, @Param("regtime")Date date);
	/**
	 * 根据用户邮箱设置支付密码
	 * @param mail
	 * @param password
	 * @return
	 */
	int setPaymentPassword(@Param("mail")String mail, @Param("paymentCode")String md5,@Param("paymentCodeUuid")String paymentCodeSaltId);
	/**
	 * 根据用户id修改用户密码
	 * @param userId
	 * @param password
	 * @return
	 */
	int updateUserPasswordWithTx(@Param("userId")Long userId, @Param("password")String password, @Param("salt")String salt,@Param("updateSalt")Date date);

	int updateQuitTime(@Param("po")UserPO po);
	/**
	 * 根据用户Id集合解绑用户
	 * @param req
	 * @return
	 */
	int unbindByUserIdsWithTx(@Param("ids")List<Long> userIdList);


	int updateManageAccountStatus(@Param("ids")List<Long> userIdList, @Param("isAvailable")Integer isAvailable);
	int deleteManageAccountStatus(@Param("ids")List<Long> userIdList);


	/**
	 * 根据用户之前手机号统一换绑手机
	 * @param mobile
	 * @param beforeMobile
	 * @param platformId
	 * @return
	 */
	int bindingMobileByBeforeMobileWithTx(@Param("mobile") String mobile, @Param("beforeMobile") String beforeMobile, @Param("platformId")Long platformId);
	/**
	 * 设置员工状态为离职和失效
	 * @param userId
	 * @return
	 */
	int updateIsAvailableAccountStatus(@Param("userId")Long userId, @Param("isAvailable")Integer isAvailable);
	/**
	 * 根据导入批次id查询导入用户数据同步到正式表中
	 * @param importId
	 * @return
	 */
	int syncUserAll(@Param("importId")Long importId);

	int deleteByImportIdWithTx(@Param("id")Long id,@Param("importId")Long importId);

	int switchUserEnterpriseByCompanyId(@Param("enterpriseId")Long enterpriseId,@Param("companyId")Long CompanyId,@Param("userId")Long userId,@Param("companyNewId")Long companyNewId);
}
