package com.egeo.components.cms.dao.read;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.cms.po.IconGroupPO;
import com.egeo.orm.BaseReadDAO;

public interface IconGroupReadDAO extends BaseReadDAO<IconGroupPO>{

	/**
	 * 根据实例id查询icon组
	 * @param instId
	 * @return
	 */
	IconGroupPO queryIconGroupByInstId(@Param("instId")Long instId);
}
	