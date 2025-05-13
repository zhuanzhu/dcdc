package com.egeo.components.finance.manage.write;

import com.egeo.components.finance.po.AccountBatchTmpPO;


public interface AccountBatchTmpWriteManage {

	Long insertAccountBatchTmpWithTx(AccountBatchTmpPO po);

	int updateAccountBatchTmpWithTx(AccountBatchTmpPO po);

	int deleteAccountBatchTmpWithTx(AccountBatchTmpPO po);

	/**
	 * 更改批次草稿状态
	 * @param id
	 * @param status
	 * @param reason 
	 * @return
	 */
	int changeAccountBatchTmpStatus(Long id, int status, String reason);

}	