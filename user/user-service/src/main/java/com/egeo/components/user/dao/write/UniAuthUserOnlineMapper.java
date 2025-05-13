package com.egeo.components.user.dao.write;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.egeo.components.user.bean.UniAuthUserOnlineLog;
import com.egeo.components.user.bean.UniAuthUserOnlineSta;


public interface UniAuthUserOnlineMapper {
	@Select("select id, user_uuid as userUuid, sta_date as staDate, token_expire_at as tokenExpireAt,login_duration as loginDuration,"
			+ "login_date as loginDate, logout_date as logoutDate, login_status as loginStatus, create_date as createDate, update_date as updateDate "
			+ "from uniauth_user_online_log where user_uuid = #{userUuid} and login_status = #{loginStatus} order by login_date desc limit 1")
	UniAuthUserOnlineLog selectLogByUserUuid(UniAuthUserOnlineLog onlineLogParams);

	@Update("update uniauth_user_online_log set token_expire_at = #{tokenExpireAt},login_duration = #{loginDuration},logout_date = #{logoutDate},"
			+ "login_status = #{loginStatus},update_date = #{updateDate} where id = #{id}")
	int updateOnlineLog(UniAuthUserOnlineLog onlineLog);

	@Insert("insert into uniauth_user_online_log(user_uuid,sta_date,token_expire_at,login_duration,login_date,login_status,create_date) "
			+ "values(#{userUuid},#{staDate},#{tokenExpireAt},#{loginDuration},#{loginDate},#{loginStatus},#{createDate})")
	int insertOnlineLog(UniAuthUserOnlineLog currentOnlineLog);

	// update uniauth_user_online_log
	// set
	// logout_date=token_expire_at,login_duration=token_expire_at-login_date,login_status=0,update_date=当前时间
	// where login_status = 1 and token_expire_at<当前时间
	@Update("update uniauth_user_online_log set "
			+ " logout_date = token_expire_at, login_duration = token_expire_at-login_date, login_status = 0, update_date = #{currentTimeMillis}"
			+ " where login_status = 1 and token_expire_at  <= #{currentTimeMillis}")
	int updateOnlineLogForTimeout(@Param("currentTimeMillis") long currentTimeMillis);

	// update uniauth_user_online_log
	// set login_duration=当前时间-login_date,update_date=当前时间
	// where login_status = 1
	@Update("update uniauth_user_online_log "
			+ "set login_duration =${currentTimeMillis}-login_date,update_date = #{currentTimeMillis} "
			+ "where login_status = 1")
	int updateOnlineLogForRefreshLoginDuration(@Param("currentTimeMillis") long currentTimeMillis);

	// select user_uuid,sta_date,sum(login_duration),count(id) as
	// login_times,count(case when login_status=0 then id else null end) as
	// logout_times,
	// max(login_status) as login_status
	// from uniauth_user_online_log
	// where sta_date='2018-11-08'
	// group by user_uuid,sta_date
	@Select("select user_uuid as userUuid,sta_date as staDate,sum(login_duration) as loginDuration,count(id) as loginTimes,"
			+ "count(case when login_status=0 then id else null end) as logoutTimes, max(login_status) as loginStatus,"
			+ "#{currentTimeMillis} as createDate,#{currentTimeMillis} as updateDate "
			+ "from uniauth_user_online_log where sta_date = #{currentDate} group by user_uuid,sta_date")
	List<UniAuthUserOnlineSta> selectLogForSta(@Param("currentDate") java.sql.Date currentDate,
			@Param("currentTimeMillis") long currentTimeMillis);
	
	
	@Select("select user_uuid as userUuid,sta_date as staDate,sum(login_duration) as loginDuration,count(id) as loginTimes,"
			+ "count(case when login_status=0 then id else null end) as logoutTimes, max(login_status) as loginStatus,"
			+ "#{currentTimeMillis} as createDate,#{currentTimeMillis} as updateDate "
			+ "from uniauth_user_online_log where sta_date = #{currentDate} and user_uuid = #{userUuid} group by user_uuid,sta_date")
	UniAuthUserOnlineSta selectLogForStaByOne(@Param("currentDate") java.sql.Date currentDate,
			@Param("userUuid") String userUuid,@Param("currentTimeMillis") long currentTimeMillis);
	
	@Update("update uniauth_user_online_sta set login_duration=#{loginDuration},login_times=#{loginTimes},logout_times=#{logoutTimes},"
			+ " login_status=#{loginStatus},update_date=#{updateDate}"
			+ " where user_uuid=#{userUuid} and sta_date=#{staDate}")
	int updateOnlineSta(UniAuthUserOnlineSta sta);
}