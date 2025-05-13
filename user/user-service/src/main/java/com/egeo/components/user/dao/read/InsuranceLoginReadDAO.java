package com.egeo.components.user.dao.read;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.user.po.InsuranceLoginPO;
import com.egeo.orm.BaseReadDAO;

public interface InsuranceLoginReadDAO extends BaseReadDAO<InsuranceLoginPO>{

	/**
	 * 查询用户的第三方保险登陆信息
	 * @param userId
	 * @return
	 */
	InsuranceLoginPO queryInsuranceLoginByUserId(@Param("userId")Long userId);
}
	