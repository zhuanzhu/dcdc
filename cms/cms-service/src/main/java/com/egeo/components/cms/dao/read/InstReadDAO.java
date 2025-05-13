package com.egeo.components.cms.dao.read;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.cms.po.InstPO;
import com.egeo.orm.BaseReadDAO;

public interface InstReadDAO extends BaseReadDAO<InstPO>{

	/**
	 * 根据组件id拆查询实例
	 * @param elementId
	 * @return
	 */
	InstPO queryInstByElementId(@Param("elementId")Long elementId);
}
	