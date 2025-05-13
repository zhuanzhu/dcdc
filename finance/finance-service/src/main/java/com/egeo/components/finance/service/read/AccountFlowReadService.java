package com.egeo.components.finance.service.read;


import java.util.Date;
import java.util.List;

import com.egeo.components.finance.dto.AccountFlowDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface AccountFlowReadService {

	public AccountFlowDTO findAccountFlowById(AccountFlowDTO dto);
	public List<AccountFlowDTO> findAccountFlowOfEnterprise(Long enterpriseId ,Date start ,Date end);
	public List<AccountFlowDTO> findAccountFlowOfCompany(Long companyId ,Date start ,Date end);

	public PageResult<AccountFlowDTO> findAccountFlowOfPage(AccountFlowDTO dto,Pagination page);

	public List<AccountFlowDTO> findAccountFlowAll(AccountFlowDTO dto);
	
	/**
	 * 查询批次流水分页列表
	 * @param batchId
	 * @param outflowAccount
	 * @param inflowAccount
	 * @param startTime
	 * @param endTime
	 * @param page
	 * @return
	 */
	public PageResult<AccountFlowDTO> queryAccountFlowPage(Long batchId, String outflowAccount,
			String inflowAccount, Long startTime, Long endTime, Long platformId, Pagination page);

	/**
	 * 查询账户流水分页列表
	 * @param page
	 * @param accountId
	 * @param mode
	 * @return
	 */
	public PageResult<AccountFlowDTO> queryAccountFlowPageByAccountId(Pagination page, Long accountId, Integer mode);

	/**
	 * 查询订单退款流水
	 * @param orderId
	 * @return
	 */
	public List<AccountFlowDTO> queryOrderRefundFlow(Long orderId);

	/**
	 * 用户资产流水分页列表
	 * @param accountIdList
	 * @param page
	 * @param startTime
     *@param endTime @return
	 */
	public PageResult<AccountFlowDTO> userFinFlowPage(List<Long> accountIdList, Pagination page, Date startTime, Date endTime);
}
	