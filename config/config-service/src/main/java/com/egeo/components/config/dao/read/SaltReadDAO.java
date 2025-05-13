package com.egeo.components.config.dao.read;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.config.po.SaltPO;
import com.egeo.orm.BaseReadDAO;

public interface SaltReadDAO extends BaseReadDAO<SaltPO>{

	/**
	 * 根据uuid获取盐
	 * @param uuid
	 * @return
	 */
	SaltPO querySaltByUUID(@Param("uuid")String uuid);
}
	