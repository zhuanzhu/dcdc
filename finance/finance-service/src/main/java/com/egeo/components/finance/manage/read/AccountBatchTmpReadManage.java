package com.egeo.components.finance.manage.read;

import java.util.List;

import com.egeo.components.finance.po.AccountBatchTmpPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface AccountBatchTmpReadManage {

	public AccountBatchTmpPO findAccountBatchTmpById(AccountBatchTmpPO po);

	public PageResult<AccountBatchTmpPO> findAccountBatchTmpOfPage(AccountBatchTmpPO po,Pagination page);

	public List<AccountBatchTmpPO> findAccountBatchTmpAll(AccountBatchTmpPO po);

	/**
	 * 根据财务批次号查询账户批次草稿
	 * @param finBatch
	 * @return
	 */
	public AccountBatchTmpPO queryAccountBatchTmpByFinBatch(String finBatch);

	/**
	 * 查询账户批次草稿分页列表
	 * @param page
	 * @param keyWord
	 * @param companyId
	 * @param status
	 * @param type
	 * @return
	 */
	public PageResult<AccountBatchTmpPO> queryAccountBatchTmpPage(Pagination page, String keyWord, Long companyId,
			Integer status, Integer type,Long platformId);
}
	