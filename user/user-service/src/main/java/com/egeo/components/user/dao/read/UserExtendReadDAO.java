package com.egeo.components.user.dao.read;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.user.condition.UserExtendCondition;
import com.egeo.components.user.po.DepMemberPO;
import com.egeo.components.user.po.UserExtendPO;
import com.egeo.orm.BaseReadDAO;
import com.egeo.orm.Pagination;

public interface UserExtendReadDAO extends BaseReadDAO<UserExtendPO>{

	/**
	 * 根据公司id查询用户分页列表
	 * @param companyId
	 * @param page
	 * @return
	 */
	List<UserExtendPO> queryUserPageByCompanyId(@Param("companyId")Long companyId, @Param("page")Pagination page);

	/**
	 * 查询分页列表总记录数
	 * @param companyId
	 * @return
	 */
	Integer queryUserPageTotalCountByCompanyId(@Param("companyId")Long companyId);

	/**
	 * 根据部门id查询用户分页列表
	 * @param companyId
	 * @param page
	 * @return
	 */
	List<UserExtendPO> queryUserPageByDepartmentId(@Param("departmentId")Long departmentId, @Param("page")Pagination page);

	/**
	 * 查询分页列表总记录数
	 * @param departmentId
	 * @return
	 */
	Integer queryUserPageTotalCountByDepartmentId(Long departmentId);

	/**
	 * 批量查询用户列表
	 * @param userIds
	 * @return
	 */
	List<UserExtendPO> queryUserExtendsByIds(@Param("ids")List<Long> userIds);

	/**
	 * 查询即将生日的用户列表
	 * @param companyId
	 * @return
	 */
	List<UserExtendPO> querySoonBirthdayUsers(@Param("companyId")Long companyId);

	/**
	 * 查询即将入职纪念日的用户列表
	 * @param companyId
	 * @return
	 */
	List<UserExtendPO> querySoonEntrydayUsers(@Param("companyId")Long companyId);
	/**
     * 根据用户id查询修改用户头像、真实姓名、公司等信息
     * @param dto
     * @return
     */
	UserExtendCondition userExtendByUserId(@Param("userId")Long userId);
	/**
     * 根据用户id查询个人信息接口
     * @param dto
     * @return
     */
	UserExtendCondition userByUserId(@Param("userId")Long userId);
	/**
	 * 根据公司id按条件查询所有用户信息
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
	List<UserExtendCondition> userAllOfPage(@Param("po")UserExtendPO po, @Param("page")Pagination page);
	/**
	 * 根据公司id按条件查询所有用户信息总条数
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
	int countuserAllOfPage(@Param("po")UserExtendPO po);
	
	/**
	 * 
	 * @param po
	 * @return
	 */
	int countuserExtendAllOfPage(@Param("po")UserExtendPO po,@Param("companyIds") List<Long> companyIds , @Param("page") Pagination page);

	/**
	 * @param po
	 * @param page
	 * @return
	 */
	List<UserExtendCondition> userExtendAllOfPage(@Param("po")UserExtendPO po,@Param("companyIds") List<Long> companyIds,@Param("page")Pagination page);

	/**
	 * 根据公司id按条件查询所有用户信息
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
	List<UserExtendCondition> userAll(@Param("po")UserExtendPO po);

	/**
	 * 查询除当前用户之外的部门用户分页列表
	 * @param page
	 * @param userId
	 * @return
	 */
	List<DepMemberPO> queryDepMemberPageExceptThisId(@Param("page")Pagination page,@Param("depId")Long depId, @Param("userId")Long userId);

	/**
	 * 查询除当前用户之外的部门用户分页列表总记录数
	 * @param depId
	 * @param userId
	 * @return
	 */
	Integer queryDepMemberTotalCountExceptThisId(@Param("depId")Long depId, @Param("userId")Long userId);

	/**
	 * 模糊搜索用户
	 * 排除userid
	 * 约束公司
	 * 约束姓名
	 * @param keyWord
	 * @param userId
	 * @param companyId
	 * @return
	 */
	List<DepMemberPO> searchUsers(@Param("keyWord")String keyWord, @Param("userId")Long userId, @Param("companyId")Long companyId);


	/**
	 * 查询今年最近生日的日期
	 * 返回格式为 MM-dd
	 * @param companyId
	 * @return
	 */
	Date queryNearestBirthDateThisYear(@Param("companyId")Long companyId);

	/**
	 * 查询每年最早的生日日期
	 * @param companyId
	 * @return
	 */
	Date queryEarliestBirthDate(@Param("companyId")Long companyId);

	/**
	 * 根据生日日期查询用户列表
	 * @param targetDate
	 * @return
	 */
	List<UserExtendPO> queryUserExtendsByBirthDate(@Param("birthDate")Date birthDate,@Param("companyId")Long companyId);

	/**
	 * 根据邮箱查询邮箱
	 * @param email
	 * @return
	 */
	UserExtendPO queryUserExtendsByEmail(@Param("email")String email);

	/**
	 * 条件查询
	 * @param po
	 * @return
	 */
	List<UserExtendCondition> findAlluser(@Param("po")UserExtendPO po);


	/**
	 * 关联查询user信息分页列表
	 * @param page
	 * @param condition
	 * @return
	 */
	List<UserExtendPO> queryFullUserExtendPage(@Param("page")Pagination page, @Param("condition")UserExtendPO condition);

	/**
	 * 关联查询user信息分页列表总记录数
	 * @param condition
	 * @return
	 */
	Integer queryFullUserExtendPageTotalSize(@Param("condition")UserExtendPO condition);
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
	List<UserExtendCondition> userByMobile(@Param("mobile")String mobile,@Param("platformId")Long platformId);
	
	List<UserExtendCondition> userAdminCompany(@Param("companyId")Long companyId);

	/**
	 * 查询符合条件的用户
	 * @param dto
	 * @return
	 */
	List<UserExtendPO> queryUserByCondition(@Param("po")UserExtendPO po);

	/**
	 * 通过公司id集合查询员工分页信息
	 * @param companyList
	 * @return
	 */
	int countUserExtendAllByCompanyOfPage(@Param("companyList")List<Long> companyList);

	/**
	 * 通过公司id集合查询员工分页信息
	 * @param companyList
	 * @param page
	 * @return
	 */
	List<UserExtendCondition> userExtendAllByCompanyOfPage(@Param("companyList")List<Long> companyList, @Param("page")Pagination page);
	/**
	 * 根据是否为管理员查询所有用户
	 * @param isAdministrator
	 * @return
	 */
	List<Long> findUserIdByIsAdministrator(@Param("isAdministrator")int isAdministrator);
	/**
	 * 根据用户id集合查询用户及关联信息
	 * @param userIds
	 * @return
	 */
	List<UserExtendCondition> findUserExtendCByUserIds(@Param("ids")List<Long> userIds);
	/**
	 * 根据用户id查询百度云推送ChannelId
	 * @param userId
	 * @return
	 */
	String findBaiDuChannelId(@Param("userId")Long userId);


    List<Long> findUserLists(@Param("name") String name, @Param("mail") String mail,
							@Param("mobile") String mobile,@Param("sex") Integer sex,
							@Param("birthday") Date birthday,@Param("companyId") List<Long> companyId,
							@Param("platformId") Long platformId);

	List<Long> findUserList(@Param("name") String name,@Param("mail")  String mail, @Param("mobile") String mobile,
							@Param("sex") Integer sex,@Param("birthday") Date birthday,@Param("platformId") Long platformId);

	List<UserExtendCondition> getUserAdminAll(@Param("po") UserExtendPO po);
	List<UserExtendCondition> getUserStoreAdminAll(@Param("po") UserExtendPO po,@Param("storeId") Long storeId);	/**
	/**
	 * 只返回id和name
	 * @param mobile
	 * @param platformId
	 * @return
	 */
	UserExtendPO findAdminUserByManage(@Param("mobile")String mobile,@Param("platformId")Long platformId);
	/**
	 * 根据门店id查询用户注册数量
	 * @param storeId
	 * @param platformId
	 * @return
	 */
	Integer findUserSumByStoreId(
			@Param("storeId")Long storeId, 
			@Param("platformId")Long platformId);
	/**
	 * 根据门店id查询当月新用户数量
	 * @param storeId
	 * @param date
	 * @param platformId
	 * @return
	 */
	Integer findCurrentMonthUserSumByStoreId(
			@Param("storeId")Long storeId, 
			@Param("date")Date date, 
			@Param("platformId")Long platformId);
	/**
	 * 根据门店id查询今日订单总额
	 * @param storeId
	 * @param platformId
	 * @return
	 */
	Integer findCurrentDayUserSumByStoreId(
			@Param("storeId")Long storeId, 
			@Param("date")Date date, 
			@Param("platformId")Long platformId);

    List<UserExtendPO> queryFullUserExtend(@Param("userIdList")List<Long> userIdList);

	List<UserExtendCondition> findByUserIds(@Param("userIds")List<Long> ids);
}
	
