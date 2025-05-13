package com.egeo.components.finance.dao.write;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.finance.po.AccountBatchTmpPO;
import com.egeo.orm.BaseWriteDAO;

public interface AccountBatchTmpWriteDAO extends BaseWriteDAO<AccountBatchTmpPO> {

	/**
	 * 改变批次草稿状态
	 * @param id
	 * @param status
	 * @param reason 
	 * @return
	 */
	int changeAccountBatchTmpStatus(@Param("id")Long id, @Param("status")int status, @Param("reason")String reason);

}
	