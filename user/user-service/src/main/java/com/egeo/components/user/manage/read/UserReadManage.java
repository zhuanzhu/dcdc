package com.egeo.components.user.manage.read;

import java.util.List;

import com.egeo.components.user.condition.UserCondition;
import com.egeo.components.user.dto.UserDTO;
import com.egeo.components.user.po.UserCookiePO;
import com.egeo.components.user.po.UserExtendPO;
import com.egeo.components.user.po.UserPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface UserReadManage {

    UserPO findUniqueUser(UserPO po);

    UserPO findUserByID(Long userId);

    public PageResult<UserPO> findPage(Pagination page, UserPO po);
    public PageResult<UserPO> findManagePage(Pagination page, UserPO po);

    PageResult<UserCondition> findPageUser(Pagination page, UserPO po, UserExtendPO po2,
            UserCookiePO userCookiePO,Integer... types);

    PageResult<UserCondition> findPageUser(Pagination page, UserPO po, Integer... types);
    PageResult<UserCondition> findPageUser(Pagination page,String sysCode,Long rid, UserPO po, Integer... types);
    List<UserPO> userByloginName(UserPO po);

    UserPO userById(UserPO po);

    UserCondition userByMail(String mail);

	List<UserPO> userByManage(String mobile);

	/**
	 * 分页查询公司用户列表
	 * @param companyId
	 * @param page
	 * @return
	 */
	PageResult<UserExtendPO> queryUserPageByCompanyId(Long companyId, Pagination page);

	/**
	 * 分页查询部门用户列表
	 * @param departmentId
	 * @param page
	 * @return
	 */
	PageResult<UserExtendPO> queryUserPageByDepartmentId(Long departmentId, Pagination page);

	/**
	 * 通过用户id查询用户昵称和头像信息
	 * @return
	 */
	UserCondition userWelfareAppById(Long userId);

	/**
	 * 批量查询用户
	 * @param userIdList
	 * @return
	 */
	List<UserPO> queryUserByIds(List<Long> userIdList);
	/**
	 * 根据用户手机号查询用户信息
	 * @param mobile
	 * @return
	 */
	List<UserPO> findByMobileAndRegister(String mobile, Long platformId);

	/**
	 * 查询用户
	 * @param companyId
	 * @return
	 */
	List<UserPO> findUsersByCompanyId(Long companyId);

	/**查询用户
	 * @param po
	 * @return
	 */
	List<UserPO> findAll(UserPO po);

	/**
	 * 根据Email模糊查询用户
	 * @param email
	 * @return
	 */
	List<UserPO> queryUsersByEmail(String email);
	/**
	 * 根据邮箱查询用户信息
	 */
	UserCondition findByMail(String mail);
	/**
	 * 根据用户手机号及用户id判断该手机号是否绑定该用户并返回数据
	 * @param userId
	 * @param mobile
	 * @return
	 */
	UserPO findByUserIdMobile(Long userId, String mobile,Long platformId);
	/**
	 * 根据微信OpenId查询用户信息、最新创建的已激活账号登录
	 * @param openId
	 * @return
	 */
	UserPO findByWeiXinOpenId(String openId, Long platformId);
	/**
	 * 根据手机号平台id查询用户信息
	 * @param mobile
	 * @param platformId
	 * @return
	 */
	List<UserPO> findListByManage(String mobile, Long platformId);
	/**
	 * 根据导入批次id查询用户信息
	 * @param importId
	 * @return
	 */
	List<UserPO> findByImportId(Long importId);
	/**
	 * 根据公司id查询在职用户Id集合
	 * @param companyId
	 * @param isAdministrator 是否是管理员  0：否  1：是
	 * @return
	 */
	List<Long> findUserIdsByCompanyId(Long companyId, Integer isAdministrator);
	/**
	 * 根据用户手机号查询管理员用户信息
	 * @param mobile
	 * @param platformId
	 * @return
	 */
	UserPO findAdminUserByManage(String mobile, Long platformId);

	/**
	 * 根据微信OpenId查询用户信息、最近登录的或者最新创建的已激活账号登录
	 * @param openId
	 * @return
	 */
	UserPO findLatestLoginByWeiXinOpenId(String openId, Long platformId);

	/**
	 * 根据用户手机号查询最近登录的用户信息
	 * @param mobile
	 * @return
	 */
	public UserPO findLatestLoginByMobile(String mobile, Long platformId);
	/**
	 * 根据平台公司及手机判断是否存在有效用户
	 * @param mobile
	 * @param companyId
	 * @param platformId
	 * @return
	 */
	Boolean findIsExistUser(String mobile, Long companyId, Long platformId);

    List<UserPO> findUserByMobile(String mobile, Long platformId);

    UserPO findByMailAndCompany(String mail, String companyName);

    List<UserPO> findByAccountAndMobile(String account, String mobile);

    List<UserPO> findByChannelUserUniqueAndRegister(String channelUserUnique,Long companyId, Long platformId);

	/**
	 * 根据用户手机号查询最近登录的用户信息
	 * @param channelUserUnique
	 * @return
	 */
	public UserPO findLatestLoginByChannelUserUnique(String channelUserUnique,Long companyId, Long platformId);
}
