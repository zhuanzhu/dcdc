package com.egeo.components.cms.dao.write;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.cms.po.InstPO;
import com.egeo.orm.BaseWriteDAO;

public interface InstWriteDAO extends BaseWriteDAO<InstPO> {

	/**
	 * 重置实例的分页tab的信息
	 * @param instPO
	 * @return
	 */
	int resetInstPageTabInfo(@Param("po")InstPO instPO);
}
	