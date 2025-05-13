package com.egeo.components.cms.dao.write;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.cms.po.InstCompanyPO;
import com.egeo.orm.BaseWriteDAO;

public interface InstCompanyWriteDAO extends BaseWriteDAO<InstCompanyPO> {

	/**
	 * 根据instId删除记录
	 * @param instId
	 * @return
	 */
	int deleteInstCompanyByinstId(@Param("instId")Long instId);
}
	