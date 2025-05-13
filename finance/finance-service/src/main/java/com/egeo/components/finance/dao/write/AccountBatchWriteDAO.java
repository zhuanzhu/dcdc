package com.egeo.components.finance.dao.write;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.finance.po.AccountBatchPO;
import com.egeo.orm.BaseWriteDAO;

public interface AccountBatchWriteDAO extends BaseWriteDAO<AccountBatchPO> {

	/**
	 * 修改批次状态
	 * @param id
	 * @param type
	 * @return
	 */
	int updateBatchStatusById(@Param("id")Long id, @Param("status")int status);

}
	