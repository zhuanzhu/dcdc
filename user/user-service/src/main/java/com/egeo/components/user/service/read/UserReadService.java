package com.egeo.components.user.service.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.user.condition.UserExtendCondition;
import com.egeo.components.user.dto.UserCookieDTO;
import com.egeo.components.user.dto.UserDTO;
import com.egeo.components.user.dto.UserExtendDTO;
import com.egeo.components.user.dto.UserWelfareApp;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface UserReadService {

	UserDTO findUniqueUser(UserDTO dto);

	UserDTO findUserByID(Long userId);

	public PageResult<UserDTO> findPage(Pagination page,UserDTO dto,UserExtendDTO userExtendDTO,UserCookieDTO userCookieDTO,Integer... types);

	public PageResult<UserDTO> findPage(Pagination page,UserDTO dto,Integer... types);

	UserDTO userById(UserDTO dto);

	PageResult<UserDTO> findProductUser(Pagination page, UserDTO dto);
	/**
	 * 通过用户id查询用户昵称和头像信息
	 * @return
	 */
	UserWelfareApp userWelfareAppById(Long userId);
	/**
	 * 根据邮箱查询用户
	 * @param mail
	 * @return
	 */
	UserDTO userByMail(String mail);

	/**
	 * 分页查询公司用户列表
	 * @param companyId
	 * @param page
	 * @return
	 */
	PageResult<UserExtendDTO> queryUserListByCompanyId(Long companyId, Pagination page);

	/**
	 * 分页查询部门用户列表
	 * @param departmentId
	 * @param page
	 * @return
	 */
	PageResult<UserExtendDTO> queryUserListByDepartmentId(Long departmentId, Pagination page);

	/**
	 * 批量查询用户信息
	 * @param userIdList
	 * @return
	 */
	List<UserDTO> queryUserByIds(List<Long> userIdList);
	/**
	 * 根据用户手机号查询用户信息
	 * @param mobile
	 * @return
	 */
	List<UserDTO> findByMobileAndRegister(String mobile,Long platformId);

	/**
	 * 根据公司id查询user
	 * @param id
	 * @return
	 */
	List<UserDTO> findUsersByCompanyId(Long id);


	UserExtendCondition findCompanyAdmin(Long companyId);

	/**查询用户
	 * @param userDTO
	 * @return
	 */
	List<UserDTO> findUser(UserDTO userDTO);

	/**
	 * 根据Email模糊查询用户
	 * @param email
	 * @return
	 */
	List<UserDTO> queryUsersByEmail(String email);
	/**
	 * 根据邮箱查询用户信息
	 */
	UserDTO findByMail(String mail);
	/**
	 * 根据用户手机号及用户id判断该手机号是否绑定该用户并返回数据
	 * @param userId
	 * @param mobile
	 * @return
	 */
	UserDTO findByUserIdMobile(Long userId, String mobile,Long platformId);
	/**
	 * 根据微信OpenId查询用户信息、最新创建的已激活账号登录
	 * @param openId
	 * @return
	 */
	UserDTO findByWeiXinOpenId(String openId, Long platformId);
	/**
	 * 根据手机号平台id查询用户信息
	 */
	List<UserDTO> findListByManage(String mobile, Long platformId);
	/**
	 * 根据导入批次id查询用户信息
	 * @param importId
	 * @return
	 */
	List<UserDTO> findByImportId(Long importId);

	/**
	 * 根据公司id查询在职用户Id集合
	 * @param companyId
	 * @param isAdministrator 是否是管理员  0：否  1：是
	 * @return
	 */
	List<Long> findUserIdsByCompanyId(Long companyId,Integer isAdministrator);
	/**
	 * 根据用户手机号查询管理员用户信息
	 * @param mobile
	 * @param platformId
	 * @return
	 */
	UserDTO findAdminUserByManage(String mobile, Long platformId);

	/**
	 * 根据微信OpenId查询用户信息、最近登录的或者最新创建的已激活账号登录
	 * @param openId
	 * @return
	 */
	UserDTO findLatestLoginByWeiXinOpenId(String openId, Long platformId);

	/**
	 * 根据用户手机号查询最近登录的用户信息
	 *
	 * @param mobile
	 * @return
	 */
	public UserDTO findLatestLoginByMobile(String mobile,Long platformId);
	/**
	 * 根据平台公司及手机判断是否存在有效用户
	 * @param mobile
	 * @param companyId
	 * @param platformId
	 * @return
	 */
	Boolean findIsExistUser(String mobile, Long companyId, Long platformId);

    List<UserDTO> findUserByMobile(String mobile, Long platformId);

    UserDTO findByMailAndCompany(String mail, String companyName);
    public List<UserDTO> findByAccountAndMobile(String account, String mobile) ;
    public PageResult<UserDTO> findManagePage(Pagination page, UserDTO dto);

	List<UserDTO> findByChannelUserUniqueAndRegister(String channelUserUnique,Long companyId,Long platformId);

	/**
	 * 根据用户手机号查询最近登录的用户信息
	 *
	 * @param channelUserUnique
	 * @return
	 */
	public UserDTO findLatestLoginByChannelUserUnique(String channelUserUnique,Long companyId,Long platformId);

}
