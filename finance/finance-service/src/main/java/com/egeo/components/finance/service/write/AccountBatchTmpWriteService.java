package com.egeo.components.finance.service.write;

import com.egeo.components.finance.dto.AccountBatchTmpDTO;


public interface AccountBatchTmpWriteService {

	public Long insertAccountBatchTmpWithTx(AccountBatchTmpDTO dto);

	public int updateAccountBatchTmpWithTx(AccountBatchTmpDTO dto);

	public int deleteAccountBatchTmpWithTx(AccountBatchTmpDTO dto);

	/**
	 * 更改批次草稿状态
	 * @param id
	 * @param status
	 * @param reason 
	 * @return
	 */
	public int changeAccountBatchTmpStatus(Long id, int status, String reason);

}
	