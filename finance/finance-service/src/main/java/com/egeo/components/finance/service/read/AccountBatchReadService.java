package com.egeo.components.finance.service.read;


import java.util.List;

import com.egeo.components.finance.dto.AccountBatchDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface AccountBatchReadService {

	public AccountBatchDTO findAccountBatchById(Long id);

	public PageResult<AccountBatchDTO> findAccountBatchOfPage(AccountBatchDTO dto,Pagination page);

	public List<AccountBatchDTO> findAccountBatchAll(AccountBatchDTO dto);

	/**
	 * 根据财务批次号查询批次
	 * @param finBatch
	 * @return
	 */
	public AccountBatchDTO queryAccountBatchByFinBatch(String finBatch);

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
	public PageResult<AccountBatchDTO> queryAccountBatchPage(Long accountId,String batchNo, Pagination page, String keyWord, Long companyId,
			Integer type,Integer status,Long platformId);

	/**
	 * 查询一周内总额为sum的批次
	 * @param sum
	 * @return
	 */
	public List<AccountBatchDTO> querySameSumBatchInWeek(double sum);

	/**
	 * 查询充值记录分页列表
	 * @param page
	 * @param companyId
	 * @param batchNo
	 * @return
	 */
	public PageResult<AccountBatchDTO> queryRechargeAccountBatchPage(Pagination page, Long companyId, String batchNo);
}
	