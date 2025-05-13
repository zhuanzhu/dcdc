package com.egeo.components.user.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.user.po.UserTempPO;
import com.egeo.orm.BaseReadDAO;

public interface UserTempReadDAO extends BaseReadDAO<UserTempPO>{
	/**
	 * 根据用户id查询预导入数据id
	 * @param createUserid 用户id
	 * @param platformId 平台id
	 * @return
	 */
	List<Long> findIdsByCreateUserid(@Param("createUserid")Long createUserid, @Param("platformId")Long platformId);
}
	