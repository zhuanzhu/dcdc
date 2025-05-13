package com.egeo.components.finance.manage.read;

import java.util.Date;
import java.util.List;

import com.egeo.components.finance.po.AccountFlowPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface AccountFlowReadManage {

	public AccountFlowPO findAccountFlowById(AccountFlowPO po);

	public PageResult<AccountFlowPO> findAccountFlowOfPage(AccountFlowPO po,Pagination page);

	public List<AccountFlowPO> findAccountFlowAll(AccountFlowPO po);

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
	public PageResult<AccountFlowPO> queryAccountFlowPage(Long batchId, String outflowAccount,
			String inflowAccount, Long startTime, Long endTime, Long platformId, Pagination page);

	/**
	 * 查询账户流水分页列表
	 * @param page
	 * @param accountId
	 * @param mode
	 * @return
	 */
	public PageResult<AccountFlowPO> queryAccountFlowPageByAccountId(Pagination page, Long accountId, Integer mode);

	/**
	 * 查询订单退款流水
	 * @param orderId
	 * @return
	 */
	public List<AccountFlowPO> queryOrderRefundFlow(Long orderId);

	/**
	 * 用户资产流水分页列表
	 * @param accountIdList
	 * @param page
	 * @param startTime
     *@param endTime @return
	 */
	public PageResult<AccountFlowPO> userFinFlowPage(List<Long> accountIdList, Pagination page, Date startTime, Date endTime);
	/**
	 * 用户资产流水分页列表
	 * @param accountIdList
	 * @param page
	 * @param startTime
     *@param endTime @return
	 */
	public List<AccountFlowPO> userFinFlowEnterpise(Long enterprise, Date startTime, Date endTime);
	public List<AccountFlowPO> userFinFlowCompany(Long company, Date startTime, Date endTime);
}
	