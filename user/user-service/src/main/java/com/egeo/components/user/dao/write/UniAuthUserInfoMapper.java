package com.egeo.components.user.dao.write;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.egeo.components.user.bean.UniAuthRoles;
import com.egeo.components.user.bean.UniAuthUserInfo;
import com.egeo.components.user.bean.UniAuthUserRole;
import com.egeo.components.user.bean.UserDataPermission;
import com.egeo.components.user.controller.views.request.UniAuthUserInfoParam;
import com.egeo.components.user.vo.UserBaseInfoResponse;


/**
 * 管理员 Mapper
 * 
 * @author zhou_k 2017年5月25日
 */

public interface UniAuthUserInfoMapper {

	public List<UniAuthUserInfo> selectAdmin(String userName);

	public List<UniAuthUserInfo> getUserAll();

	public UniAuthUserInfo select(int id);

	public UniAuthUserInfo selectUuid(String uuid);

	public UniAuthUserInfo selectOperator(@Param("userMobile") String userMobile,
			@Param("roleGroupCode") String roleGroupCode);

	public int update(UniAuthUserInfo admin);

	int insert(UniAuthUserInfo admin);

	int updatePart(UniAuthUserInfo admin);

	int findCountByRid(Integer rid);

	int updateStatus(UniAuthUserInfo admin);

	UniAuthUserInfo getByPK(Integer id);

	UniAuthUserInfo getByUuid(String uuid);

	public List<UniAuthUserInfo> getUserList();

	public int insertRole(UniAuthRoles role);

	public int insertUserRole(UniAuthUserRole role);

	public UniAuthUserInfo selectUserId(String userId);

	public int updatePwd(UniAuthUserInfo admin);

	public List<UserBaseInfoResponse> queryUserAll(@Param("uniAuthUserInfo") UniAuthUserInfo admin);

	public UniAuthUserInfo selectUserIsNotId(String userId);

	public int updateNumber(UniAuthUserInfo admin);

	public UniAuthUserInfo selectUserName(String adminName);

	public List<UniAuthUserInfo> getUserInfoTask();

	void deleteUserRole(Integer uid);

	public List<UniAuthUserInfo> getUserByRole(Integer rid);

	public List<UserBaseInfoResponse> getUniauthUserList(@Param("userParam") UniAuthUserInfoParam userParam);

	public List<UniAuthUserInfo> getCompanyStatus(UniAuthUserInfo admins);

	List<UniAuthUserInfo> listByPageInfo(@Param("uniAuthUserInfo") UniAuthUserInfoParam uniAuthUserInfoParam);

	List<String> getAllCompany(@Param("departmentParentCode") String departmentParentCode,
			@Param("departmentCode") String departmentCode);

	/**
	 * @Description 根据菜单权限获取拥有该权限的所有用户
	 * @param menuId
	 * @return
	 * @author wangweijian
	 * @time 2018年12月3日 下午5:05:12
	 */
	public List<UniAuthUserInfo> getUserByMenuId(@Param("menuId") Integer menuId);

	public List<UniAuthUserInfo> getCompanyByRoles(@Param("roleIds") Integer[] roleIds);

	UniAuthUserInfo selectByUserMobile(@Param("userMobile") String userMobile);

	List<UniAuthUserInfo> selectByLoanABussType(@Param("roleCodeList") List<String> roleCodeList, @Param("bussType") String bussType);
	List<UniAuthUserInfo> selectByUuidList(@Param("uuidList") List<String> uuidList);

	List<UniAuthUserInfo> selectByLoanAid(@Param("roleCodeList") List<String> roleCodeList);

	@Select("select user_uuid as userUuid,type,state,codes from uniauth_user_data_permission where state=1")
	public List<UserDataPermission> getAllUserDataPermission();

	@Select("select user_uuid as userUuid,type,state,codes from uniauth_user_data_permission where user_uuid = #{userUuid}")
	public List<UserDataPermission> getUserDataPermissionByUser(@Param("userUuid") String userUuid);

	@Select("select user_uuid as userUuid,type,state,codes from uniauth_user_data_permission where user_uuid = #{userUuid} and type = #{type}")
	public UserDataPermission getUserDataPermissionOne(@Param("userUuid") String userUuid, @Param("type") int type);

	@Insert("insert into uniauth_user_data_permission(user_uuid, type, state, codes) "
			+ " values(#{userUuid},#{type},#{state},#{codesStr}) "
			+ " on duplicate key update state = #{state}, codes = #{codesStr}")
	public int insertUserDataPermission(UserDataPermission udp);
}