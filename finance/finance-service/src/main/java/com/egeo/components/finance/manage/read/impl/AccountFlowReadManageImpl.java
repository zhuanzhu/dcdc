package com.egeo.components.finance.manage.read.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.finance.dao.read.AccountFlowReadDAO;
import com.egeo.components.finance.manage.read.AccountFlowReadManage;
import com.egeo.components.finance.po.AccountFlowPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;

@Service
public class AccountFlowReadManageImpl implements AccountFlowReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private AccountFlowReadDAO accountFlowReadDAO;

	public AccountFlowPO findAccountFlowById(AccountFlowPO po) {
		AccountFlowPO accountFlowpo = new AccountFlowPO();
		accountFlowpo.setId(po.getId());
		return accountFlowReadDAO.findById(accountFlowpo);
	}

	public PageResult<AccountFlowPO> findAccountFlowOfPage(AccountFlowPO po, Pagination page) {

		PageResult<AccountFlowPO> pageResult = new PageResult<AccountFlowPO>();
		List<AccountFlowPO> list = null;

		int cnt = accountFlowReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = accountFlowReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<AccountFlowPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<AccountFlowPO> findAccountFlowAll(AccountFlowPO po) {

		return accountFlowReadDAO.findAll(po,null);
	}

	@Override
	public PageResult<AccountFlowPO> queryAccountFlowPage(Long batchId, String outflowAccount, String inflowAccount,
			Long startTime, Long endTime, Long platformId, Pagination page) {
		Date sd = null;
		if (startTime != null)
			sd = new Date(startTime);
		Date ed = null;
		if (endTime != null)
			ed = new Date(endTime);
		List<AccountFlowPO> poList = accountFlowReadDAO.queryAccountFlowPage(batchId, outflowAccount, inflowAccount, sd,
				ed, platformId, page);
		Integer totalCount = accountFlowReadDAO.queryAccountFlowPageTotalCount(batchId, outflowAccount, inflowAccount,
				sd, ed, platformId);
		PageResult<AccountFlowPO> poPage = new PageResult<>();
		poPage.setList(poList);
		poPage.setTotalSize(totalCount);
		poPage.copy(page);
		return poPage;
	}

	@Override
	public PageResult<AccountFlowPO> queryAccountFlowPageByAccountId(Pagination page, Long accountId, Integer mode) {

		List<AccountFlowPO> poList = accountFlowReadDAO.queryAccountFlowPageByAccountId(page,accountId,mode);
		Integer totalCount = accountFlowReadDAO.queryAccountFlowPageByAccountIdTotalSize(accountId,mode);
		PageResult<AccountFlowPO> poPage = new PageResult<>();
		poPage.setList(poList);
		poPage.setTotalSize(totalCount);
		poPage.copy(page);
		return poPage;
	}

	@Override
	public List<AccountFlowPO> queryOrderRefundFlow(Long orderId) {
		return accountFlowReadDAO.queryOrderRefundFlow(orderId);
	}

	@Override
	public PageResult<AccountFlowPO> userFinFlowPage(List<Long> accountIdList, Pagination page, Date startTime, Date endTime) {
		List<AccountFlowPO> poList = accountFlowReadDAO.userFinFlowPage(accountIdList,page,startTime,endTime);
		Integer totalCount = accountFlowReadDAO.userFinFlowPageTotalCount(accountIdList,startTime,endTime);
		PageResult<AccountFlowPO> poPage = new PageResult<>();
		poPage.setList(poList);
		poPage.setTotalSize(totalCount);
		if(EmptyUtil.isEmpty(page)){
			return poPage;
		}else{
			poPage.copy(page);
		}
		return poPage;
	}

	public List<AccountFlowPO> userFinFlowEnterpise(Long enterprise, Date startTime, Date endTime){

		List<AccountFlowPO> poList = accountFlowReadDAO.userFinFlowEnterprise(enterprise,startTime,endTime);
		return poList;
	
	}
	public List<AccountFlowPO> userFinFlowCompany(Long company, Date startTime, Date endTime){

		List<AccountFlowPO> poList = accountFlowReadDAO.userFinFlowCompany(company,startTime,endTime);
		return poList;
	
	}
}
