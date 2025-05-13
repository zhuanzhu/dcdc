package com.egeo.components.user.dao.write;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.egeo.components.user.bean.UniAuthRoles;
import com.egeo.components.user.bean.UniAuthUserInfo;
import com.egeo.components.user.bean.UniAuthUserRole;
import com.egeo.components.user.bean.UserDataPermission;
import com.egeo.components.user.controller.views.request.UniAuthUserInfoParam;
import com.egeo.components.user.dto.Enterprise;
import com.egeo.components.user.vo.UserBaseInfoResponse;


/**
 * 管理员 Mapper
 * 
 * @author zhou_k 2017年5月25日
 */

public interface SupplierMapper {

	/**
	 * @return  企业修改信息
	 */
	@Update("update u_supplier t set t.audit_status=0, t.name=#{name},t.address=#{address},t.contact=#{contact} ,t.update_millis=#{updateMillis} where t.id=#{id}")
	public int update(Enterprise data);

	//2.查询所有审核通过且合作中的代理商
	@Select("select us.id,us.name,address,contact,audit_status as auditStatus,cooperation_state as cooperationState,uu.login_name as adminLoginName, admin_id as adminId,create_millis as createMillis,update_millis as updateMillis ,area from u_supplier us LEFT JOIN u_user uu ON uu.`id` = us.`admin_id` where uu.type=5 and us.audit_status=1 and us.cooperation_state=1 and uu.is_deleted=0")
	public List<Enterprise> findActiveEnterprise();
	@Select("select us.id,us.name,address,contact,audit_status as auditStatus,cooperation_state as cooperationState,uu.login_name as adminLoginName, admin_id as adminId,create_millis as createMillis,update_millis as updateMillis ,area from u_supplier us LEFT JOIN u_user uu ON uu.`id` = us.`admin_id` where uu.type=5 and  us.audit_status=1 and us.cooperation_state=0 and uu.is_deleted=0")
	public List<Enterprise> findUnActiveEnterprise();
	@Select("select us.id,us.name,address,contact,audit_status as auditStatus,cooperation_state as cooperationState,uu.login_name as adminLoginName, admin_id as adminId,create_millis as createMillis,update_millis as updateMillis ,area  from u_supplier us LEFT JOIN u_user uu ON uu.`id` = us.`admin_id` where uu.type=5 and uu.is_deleted=0")
	public List<Enterprise> findAllEnterprises();

	//2.查询代理商
	@Select("select * from u_supplier where id=#{id}") 	
	public Enterprise findById(@Param("id") Integer id);

	//2.查询代理商
	@Select("select * from u_supplier where name=#{name} and cooperation_state=1 and audit_status=1")
	public Enterprise findByName(@Param("name") String name);

	@Select("select * from u_supplier where admin_id=#{id}") 	
	public Enterprise findByAdminId(@Param("id") Long id);
	
	//3.终止以及开始  代理商合作---若为终止状态登录失败
	@Update("update u_supplier t set t.cooperation_state=0 where t.id=#{id}")
	public int cooperationStop(@Param("id") Integer id);
	@Update("update u_supplier t set t.cooperation_state=1 where t.id=#{id}")
	public int cooperationStart(@Param("id") Integer id);
	//4.新增代理商--权限平台
	@Options(useGeneratedKeys = true, keyProperty = "id")
	@Insert("insert into u_supplier(name, address, contact, audit_status,cooperation_state,create_millis,area,admin_id) "
			+ " values(#{name},#{address},#{contact},1,1,#{createMillis},#{area},#{adminId}) ")
	public long insertEnterprise(Enterprise data);
	//5.审核代理商信息
	//设置代理商管理员
	@Update("update u_supplier t set t.admin_id=#{adminUid} where t.id=#{id}")
	public int updateAdmin(@Param("id") Integer id,@Param("adminUid") Long adminUid);

	//2.查询代理商
	@Select({
			"<script> ",
				"SELECT * FROM u_supplier WHERE id IN ( ",
				"<foreach collection='ids' item='id' separator=','> ",
					"#{id}",
				" </foreach> ",
				")",
			"</script> "
	})
	List<Enterprise> findByIds(@Param("ids") List<Long> ids);
}