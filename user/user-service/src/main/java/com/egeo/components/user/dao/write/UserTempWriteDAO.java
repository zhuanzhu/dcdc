package com.egeo.components.user.dao.write;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.user.po.UserTempPO;
import com.egeo.orm.BaseWriteDAO;

public interface UserTempWriteDAO extends BaseWriteDAO<UserTempPO> {
	/**
	 * 批量保存预导入用户信息
	 * @param list
	 * @return
	 */
	int insertUserTempAllWithTx(@Param("poList")List<UserTempPO> list);
}
	