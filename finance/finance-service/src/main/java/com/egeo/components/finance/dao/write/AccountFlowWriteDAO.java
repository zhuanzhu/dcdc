package com.egeo.components.finance.dao.write;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.finance.po.AccountFlowPO;
import com.egeo.orm.BaseWriteDAO;

public interface AccountFlowWriteDAO extends BaseWriteDAO<AccountFlowPO> {

	/**
	 * 批量插入账户流水
	 * @param afs
	 * @return
	 */
	int batchInsert(@Param("afs")List<AccountFlowPO> afs);
	
	void updateAccountFlowReadStatus(@Param("ids")List<Long> ids);
}
	