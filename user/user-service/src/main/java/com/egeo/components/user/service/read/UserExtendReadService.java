package com.egeo.components.user.service.read;

import java.util.Date;
import java.util.List;

import com.egeo.components.user.dto.DepMemberDTO;
import com.egeo.components.user.dto.UserExtendDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface UserExtendReadService {

	UserExtendDTO findById(Long id);

	PageResult<UserExtendDTO> findProductUser(Pagination page, UserExtendDTO dto2);

	/**
	 * 批量查询用户
	 * @param userIds
	 * @return
	 */
	List<UserExtendDTO> queryUserExtendsByIds(List<Long> userIds);

	/**
	 * 查询即将生日的用户列表
	 * @param companyId
	 * @return
	 */
	List<UserExtendDTO> querySoonBirthdayUsers(Long companyId);

	/**
	 * 查询即将入职纪念日的用户列表
	 * @param companyId
	 * @return
	 */
	List<UserExtendDTO> querySoonEntrydayUsers(Long companyId);
	/**
     * 根据用户id查询修改用户头像、真实姓名、公司等信息
     * @param dto
     * @return
     */
	UserExtendDTO userExtendByUserId(Long userId);
	/**
     * 根据用户id查询个人信息接口
     * @param dto
     * @return
     */
	UserExtendDTO userByUserId(Long userId);
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
	PageResult<UserExtendDTO> userAllOfPage(UserExtendDTO dto, Pagination page);
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
	List<UserExtendDTO> userAll(UserExtendDTO dto);
	
	/**
	 * 条件查询
	 * @param dto
	 * @param page
	 * @return
	 */
	PageResult<UserExtendDTO> userExtendAllOfPage(UserExtendDTO dto, List<Long> companyIds, Pagination page);

	/**
	 * 查询除当前用户之外的部门用户分页列表
	 * @param page
	 * @param userId
	 * @return
	 */
	PageResult<DepMemberDTO> queryDepMemberPageExceptThisId(Pagination page,Long depId, Long userId);

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
	List<DepMemberDTO> searchUsers(String keyWord, Long userId, Long companyId);

	/**
	 * 查询公司中最近将过生日的用户列表
	 * @param companyId
	 * @return
	 */
	List<UserExtendDTO> queryNearestBirthdayUsers(Long companyId);

	/**
	 * 根据邮箱查询userExtend
	 * @param email
	 * @return
	 */
	UserExtendDTO queryUserExtendsByEmail(String email);

	/**
	 * 根据条件查询用户
	 * @param userExtendDTO
	 * @return
	 */
	List<UserExtendDTO> findAlluser(UserExtendDTO userExtendDTO);

	/**
	 * 关联表查询用户信息
	 * @param page
	 * @param condition
	 * @return
	 */
	PageResult<UserExtendDTO> queryFullUserExtendPage(Pagination page, UserExtendDTO condition);
	/**
	 * 查询所有管理员用户
	 * @return
	 */
	List<UserExtendDTO> userAdminAll();
	/**
	 * 根据用户手机号查询关联用户
	 * @param mobile
	 * @return
	 */
	List<UserExtendDTO> userByMobile(String mobile,Long platformId);

	/**
	 * 查询符合条件的用户
	 * @param dto
	 * @return
	 */
	List<UserExtendDTO> queryUserByCondition(UserExtendDTO dto);

	/**
	 * 通过公司id集合查询员工分页信息
	 * @param companyList
	 * @param page
	 * @return
	 */
	PageResult<UserExtendDTO> userExtendAllByCompanyOfPage(List<Long> companyList, Pagination page);
	/**
	 * 根据是否为管理员查询所有用户
	 * @param isAdministrator
	 * @return
	 */
	List<Long> findUserIdByIsAdministrator(int isAdministrator);


	List<Long> findUserList(String name, String mail, String mobile, Integer sex, Date birthday, List<Long> companyId, Long platformId);
	/**
	 * 根据用户手机号查询管理员用户信息(只返回id、name)
	 * @param mobile
	 * @param platformId
	 * @return
	 */
	UserExtendDTO findAdminUserByManage(String mobile, Long platformId);

	List<UserExtendDTO> getUserAdminAll(UserExtendDTO userExtendDTO);
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


    List<UserExtendDTO> queryFullUserExtend(List<Long> userIdList);

	List<UserExtendDTO> findByUserIds(List<Long> ids);

}
	