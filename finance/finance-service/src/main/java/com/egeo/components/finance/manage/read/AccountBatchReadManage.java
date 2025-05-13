package com.egeo.components.finance.manage.read;

import java.util.List;

import com.egeo.components.finance.po.AccountBatchPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface AccountBatchReadManage {

	public AccountBatchPO findAccountBatchById(AccountBatchPO po);

	public PageResult<AccountBatchPO> findAccountBatchOfPage(AccountBatchPO po,Pagination page);

	public List<AccountBatchPO> findAccountBatchAll(AccountBatchPO po);

	/**
	 * 根据财务批次号查询批次
	 * @param finBatch
	 * @return
	 */
	public AccountBatchPO queryAccountBatchByFinBatch(String finBatch);

	/**
	 * 查询今天的批次数量
	 * @return
	 */
	public Integer queryBatchDayCount();

	/**
	 * 查询账户批次分页列表
	 * @param batchNo 
	 * @param page
	 * @param keyWord
	 * @param companyId
	 * @param status
	 * @param type
	 * @return
	 */
	public PageResult<AccountBatchPO> queryAccountBatchPage(Long accountId,String batchNo, Pagination page, String keyWord, Long companyId,
			Integer type,Integer status,Long platformId);

	/**
	 * 查询一周内总额为sum的批次
	 * @param sum
	 * @return
	 */
	public List<AccountBatchPO> querySameSumBatchInWeek(double sum);

	/**
	 * 查询充值记录分页列表
	 * @param page
	 * @param companyId
	 * @param batchNo
	 * @return
	 */
	public PageResult<AccountBatchPO> queryRechargeAccountBatchPage(Pagination page, Long companyId, String batchNo);
}
	