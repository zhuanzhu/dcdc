package com.egeo.components.user.dao.write;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.user.po.VersionsPO;
import com.egeo.orm.BaseWriteDAO;

public interface VersionsWriteDAO extends BaseWriteDAO<VersionsPO> {

	int updateVersionsOfficialByTypeWithTx(@Param("user")Integer user);
}
	