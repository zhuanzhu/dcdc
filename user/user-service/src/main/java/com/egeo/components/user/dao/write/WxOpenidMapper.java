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
import com.egeo.components.user.dto.WxOpenidDTO;
import com.egeo.components.user.vo.UserBaseInfoResponse;


/**
 * 管理员 Mapper
 * 
 * @author zhou_k 2017年5月25日
 */

public interface WxOpenidMapper {



	@Select("select  t.id as id,t.user_id as userId,t.wx_appid as wxAppid ,t.wx_openid as wxOpenid ,t.company_id as companyId ,t.create_time as createTime from u_wx_openid t where user_id =#{userId} ")
	public List<WxOpenidDTO> findUserAppids(@Param("userId") Long userId );
	


	@Select("select  t.id as id,t.user_id as userId,t.wx_appid as wxAppid ,t.wx_openid as wxOpenid ,t.company_id as companyId ,t.create_time as createTime  from u_wx_openid t  where wx_openid=#{wxOpenid} ")
	public List<WxOpenidDTO> findByOpenId(@Param("wxOpenid") String  wxOpenid);


	@Select("select  t.id as id,t.user_id as userId,t.wx_appid as wxAppid ,t.wx_openid as wxOpenid ,t.company_id as companyId ,t.create_time as createTime  from u_wx_openid t where user_id is null and wx_openid=#{wxOpenid} ")
	public List<WxOpenidDTO> findByOpenIdNotUser(@Param("wxOpenid") String  wxOpenid);

    @Select("select  t.id as id,t.user_id as userId,t.wx_appid as wxAppid ,t.wx_openid as wxOpenid ,t.company_id as companyId ,t.create_time as createTime  from u_wx_openid t where user_id is not null and wx_openid=#{wxOpenid} ")
    List<WxOpenidDTO> findByOpenIdHasUser(@Param("wxOpenid") String  wxOpenid);

	@Select("select count(*) from u_wx_openid t  where user_id =#{userId} and wx_openid=#{wxOpenid} ")
	public int count(@Param("userId") Long userId ,@Param("wxOpenid") String  wxOpenid);

	@Select("select t.id as id,t.user_id as userId,t.wx_appid as wxAppid ,t.wx_openid as wxOpenid ,t.company_id as companyId ,t.create_time as createTime from u_wx_openid t where user_id =#{userId} and wx_openid=#{wxOpenid} order by id")
	public List<WxOpenidDTO> findUserOpenIds(@Param("userId") Long userId ,@Param("wxOpenid") String  wxOpenid);
	
	@Options(useGeneratedKeys = true, keyProperty = "id")
	@Insert("insert into u_wx_openid(user_id, wx_appid, wx_openid, company_id) "
			+ " values(#{userId},#{wxAppid},#{wxOpenid},#{companyId}) ")
	public int insert(WxOpenidDTO data);

	@Update("update u_wx_openid t set t.user_id =#{userId} ,company_id =#{companyId} where t.id=#{id} and t.user_id is null")
	public int updateUserId(@Param("id") Long id,@Param("userId") Long userId ,@Param("companyId") Long companyId );
	
	
}