package com.egeo.components.user.dao.write;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.egeo.components.user.bean.UniAuthRoles;
import com.egeo.components.user.bean.UniauthRoleGroup;


public interface UniAuthRolesMapper {
	int insert(UniAuthRoles uniAuthRoles);

	int updatePart(UniAuthRoles uniAuthRoles);

	List<UniAuthRoles> listByPageInfo(UniAuthRoles uniAuthRoles);

	int findByCount(UniAuthRoles uniAuthRoles);

	List<UniAuthRoles> getByUser(@Param("parentCode") String parentCode,
			@Param("departmentCode") String departmentCode);

	UniAuthRoles getByPK(Integer rid);

	List<UniAuthRoles> all();

	List<UniAuthRoles> getRidList(@Param("uuid") String uuid);

	List<UniAuthRoles> getRoleByUuid(@Param("uuid") String uuid);
	List<UniAuthRoles> getAccountRoleByUuid(@Param("uuid") String uuid);

	/**
	 * 查询某用户拥有的所有角色和角色组
	 * 
	 * @param userUuid
	 * @return
	 */
	@Select(" SELECT rg.rid,rg.role_group_code as roleGroupCode"
			+ " FROM uniauth_user_info u,uniauth_user_role ur,uniauth_role_group rg "
			+ " where  ur.uid=u.id and  ur.rid=rg.rid and u.uuid = #{userUuid} ")
	List<UniauthRoleGroup> getRoleGroupByUserUuid(@Param("userUuid") String userUuid);

	/**
	 * @Description 根据参数查询角色列表
	 * @param params
	 * @return
	 * @author wangweijian
	 * @time 2018年12月3日 下午2:32:17
	 */
	public List<UniAuthRoles> getByParams(@Param("roleParams") String roleParams);

	@Select("select role_group_code from uniauth_role_group where rid = #{rid}")
	List<String> listRoleGroupByRole(@Param("rid") Integer rid);

	@Delete("delete from uniauth_role_group where rid = #{rid} ")
	void deleteRoleGroupByRid(@Param("rid") Integer rid);

	@Insert("insert into uniauth_role_group(role_group_code,rid) values(#{roleGroupCode},#{rid}) ")
	void insertRoleGroupByRid(UniauthRoleGroup rg);

}
