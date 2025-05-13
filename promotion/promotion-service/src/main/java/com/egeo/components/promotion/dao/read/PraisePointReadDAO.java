package com.egeo.components.promotion.dao.read;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.promotion.po.PraisePointPO;
import com.egeo.orm.BaseReadDAO;

public interface PraisePointReadDAO extends BaseReadDAO<PraisePointPO>{

	/**
	 * 根据userId查询
	 * @param userId
	 * @return
	 */
	PraisePointPO queryPraisePointByUserId(@Param("userId")Long userId);
}
	