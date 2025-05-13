package com.egeo.components.cms.dao.read;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.cms.po.SuListPO;
import com.egeo.orm.BaseReadDAO;

public interface SuListReadDAO extends BaseReadDAO<SuListPO>{

	/**
	 * 根据实例id查询商品列表
	 * @param instId
	 * @return
	 */
	SuListPO querySuListByInstId(@Param("instId")Long instId);
}
	