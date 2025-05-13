package com.egeo.components.finance.service.read;


import java.util.List;

import com.egeo.components.finance.dto.AccountBatchTmpDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface AccountBatchTmpReadService {

	public AccountBatchTmpDTO findAccountBatchTmpById(AccountBatchTmpDTO dto);

	public PageResult<AccountBatchTmpDTO> findAccountBatchTmpOfPage(AccountBatchTmpDTO dto,Pagination page);

	public List<AccountBatchTmpDTO> findAccountBatchTmpAll(AccountBatchTmpDTO dto);

	/**
	 * 根据财务批次号查询账户批次草稿
	 * @param finBatch
	 * @return
	 */
	public AccountBatchTmpDTO queryAccountBatchTmpByFinBatch(String finBatch);

	/**
	 * 查询账户批次草稿分页列表
	 * @param page
	 * @param keyWord
	 * @param companyId
	 * @param status
	 * @param type
	 * @return
	 */
	public PageResult<AccountBatchTmpDTO> queryAccountBatchTmpPage(Pagination page, String keyWord, Long companyId,
			Integer status, Integer type,Long platformId);
}
	