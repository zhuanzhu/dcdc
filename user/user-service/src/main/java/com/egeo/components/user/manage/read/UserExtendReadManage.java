package com.egeo.components.user.manage.read;

import java.util.Date;
import java.util.List;

import com.egeo.components.user.condition.UserExtendCondition;

import com.egeo.components.user.po.DepMemberPO;
import com.egeo.components.user.po.UserExtendPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface UserExtendReadManage {

	UserExtendPO findById(Long id);

	public PageResult<UserExtendPO> findPage(Pagination page, UserExtendPO po);

	/**
	 * 批量查询用户
	 * 
	 * @param userIds
	 * @return
	 */
	List<UserExtendPO> queryUserExtendsByIds(List<Long> userIds);

	/**
	 * 查询即将生日的用户列表
	 * 
	 * @param companyId
	 * @return
	 */
	List<UserExtendPO> querySoonBirthdayUsers(Long companyId);

	/**
	 * 查询即将入职纪念日的用户列表
	 * 
	 * @param companyId
	 * @return
	 */
	List<UserExtendPO> querySoonEntrydayUsers(Long companyId);
	UserExtendCondition findCompanyAdmin(Long companyId);
	/**
	 * 根据用户id查询修改用户头像、真实姓名、公司等信息
	 * 
	 * @param dto
	 * @return
	 */
	UserExtendCondition userExtendByUserId(Long userId);

	/**
	 * 根据用户id查询个人信息接口
	 * 
	 * @param dto
	 * @return
	 */
	UserExtendCondition userByUserId(Long userId);

	/**
	 * 根据公司id按条件查询所有用户信息
	 * 
	 * @param companyId
	 * @param name
	 * @param departmentId
	 * @param sex
	 * @param birthdayStartTime
	 * @param birthdayFinishTime
	 * @param entryStartTime
	 * @param entryFinishTime
	 * @param page
	 * @param req
	 * @return
	 */
	PageResult<UserExtendCondition> userAllOfPage(UserExtendPO po, Pagination page);

	/**
	 * 根据公司id按条件查询所有用户信息
	 * 
	 * @param companyId
	 * @param name
	 * @param departmentId
	 * @param sex
	 * @param birthdayStartTime
	 * @param birthdayFinishTime
	 * @param entryStartTime
	 * @param entryFinishTime
	 * @param page
	 * @param req
	 * @return
	 */
	List<UserExtendCondition> userAll(UserExtendPO po);

	/**
	 * 查询除当前用户之外的部门用户分页列表
	 * 
	 * @param page
	 * @param userId
	 * @return
	 */
	PageResult<DepMemberPO> queryDepMemberPageExceptThisId(Pagination page, Long depId, Long userId);

	/**
	 * 模糊搜索用户 排除userid 约束公司 约束姓名
	 * 
	 * @param keyWord
	 * @param userId
	 * @param companyId
	 * @return
	 */
	List<DepMemberPO> searchUsers(String keyWord, Long userId, Long companyId);

	/**
	 * 查询公司中最近将过生日的用户列表
	 * 
	 * @param companyId
	 * @return
	 */
	List<UserExtendPO> queryNearestBirthdayUsers(Long companyId);

	/**
	 * 根据邮箱传用户userExtend
	 * 
	 * @param email
	 * @return
	 */
	UserExtendPO queryUserExtendsByEmail(String email);

	/**
	 * 条件查询
	 * 
	 * @param po
	 * @param page
	 * @return
	 */
	PageResult<UserExtendCondition> userExtendAllOfPage(UserExtendPO po, List<Long> companyIds, Pagination page);

	/**
	 * 条件查询
	 * 
	 * @param po
	 * @return
	 */
	List<UserExtendCondition> findAlluser(UserExtendPO po);

	/**
	 * 关联表查询用户信息
	 * @param page
	 * @param poCondition
	 * @return
	 */
	PageResult<UserExtendPO> queryFullUserExtendPage(Pagination page, UserExtendPO poCondition);
	/**
	 * 查询所有管理员用户
	 * @return
	 */
	List<UserExtendPO> userAdminAll();
	/**
	 * 根据用户手机号查询关联用户
	 * @param mobile
	 * @return
	 */
	List<UserExtendCondition> userByMobile(String mobile,Long platformId);

	/**
	 * 查询符合条件的用户
	 * @param dto
	 * @return
	 */
	List<UserExtendPO> queryUserByCondition(UserExtendPO po);

	/**
	 * 通过公司id集合查询员工分页信息
	 * @param companyList
	 * @param page
	 * @return
	 */
	PageResult<UserExtendCondition> userExtendAllByCompanyOfPage(List<Long> companyList, Pagination page);
	/**
	 * 根据是否为管理员查询所有用户
	 * @param isAdministrator
	 * @return
	 */
	List<Long> findUserIdByIsAdministrator(int isAdministrator);
	/**
	 * 根据用户id查询百度云推送ChannelId
	 * @param userId
	 * @return
	 */
	String findBaiDuChannelId(Long userId);

    List<Long> findUserList(String name, String mail, String mobile, Integer sex, Date birthday, List<Long> companyId, Long platformId);
    /**
     * 根据用户手机号查询管理员用户信息(只返回id、name)
     * @param mobile
     * @param platformId
     * @return
     */
	UserExtendPO findAdminUserByManage(String mobile, Long platformId);
	List<UserExtendCondition> getUserAdminAll(UserExtendPO po,Long storeId);
	/**
	 * 根据门店id查询用户注册数量
	 * @param storeId
	 * @param platformId
	 * @return
	 */
	Integer findUserSumByStoreId(Long storeId, Long platformId);
	/**
	 * 根据门店id查询当月新用户数量
	 * @param storeId
	 * @param platformId
	 * @return
	 */
	Integer findCurrentMonthUserSumByStoreId(Long storeId, Long platformId);
	/**
	 * 根据门店id查询当日新用户数量
	 * @param storeId
	 * @param platformId
	 * @return
	 */
	Integer findCurrentDayUserSumByStoreId(Long storeId, Long platformId);

    List<UserExtendPO> queryFullUserExtend(List<Long> userIdList);

	List<UserExtendCondition> findByUserIds(List<Long> ids);

}
