package com.egeo.components.user.dao.write;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;


public interface UniAuthRolesResMapper {

	@Delete("delete from uniauth_role_res  where role_uuid = #{rid}   ")
	 public int deleteByRid(@Param("rid")int rid) throws Exception;
	
	@Insert("insert into uniauth_role_res(role_uuid, res_uuid) values(#{role_uuid}, #{res_uuid})")
	public int insert(@Param("role_uuid")Integer role_uuid,@Param("res_uuid")Integer  res_uuid) throws Exception;
}
