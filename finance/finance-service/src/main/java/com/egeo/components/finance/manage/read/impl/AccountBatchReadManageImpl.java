package com.egeo.components.finance.manage.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.finance.dao.read.AccountBatchReadDAO;
import com.egeo.components.finance.manage.read.AccountBatchReadManage;
import com.egeo.components.finance.po.AccountBatchPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class AccountBatchReadManageImpl implements AccountBatchReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private AccountBatchReadDAO accountBatchReadDAO;
	
	public AccountBatchPO findAccountBatchById(AccountBatchPO po) {
		AccountBatchPO accountBatchpo = new AccountBatchPO();
		accountBatchpo.setId(po.getId());
		return accountBatchReadDAO.findById(accountBatchpo);
	}

	public PageResult<AccountBatchPO> findAccountBatchOfPage(AccountBatchPO po, Pagination page) {
		
		PageResult<AccountBatchPO> pageResult = new PageResult<AccountBatchPO>();
		List<AccountBatchPO> list = null;

		int cnt = accountBatchReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = accountBatchReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<AccountBatchPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<AccountBatchPO> findAccountBatchAll(AccountBatchPO po) {
		return accountBatchReadDAO.findAll(po,null);
	}

	@Override
	public AccountBatchPO queryAccountBatchByFinBatch(String finBatch) {
		return accountBatchReadDAO.queryAccountBatchByFinBatch(finBatch);
	}

	@Override
	public Integer queryBatchDayCount() {
		return accountBatchReadDAO.queryBatchDayCount();
	}

	@Override
	public PageResult<AccountBatchPO> queryAccountBatchPage(Long accountId,String batchNo,Pagination page, String keyWord, Long companyId,
			Integer type,Integer status,Long platformId) {
		List<AccountBatchPO> poList=accountBatchReadDAO.queryAccountBatchPage(accountId,batchNo,page, keyWord, companyId, type, status,platformId);
		Integer totalCount=accountBatchReadDAO.queryAccountBatchPageTotalCount(accountId,batchNo,keyWord, companyId, type, status,platformId);
		PageResult<AccountBatchPO> poPage=new PageResult<>();
		poPage.setList(poList);
		poPage.copy(page);
		poPage.setTotalSize(totalCount);
		return poPage;
	}

	@Override
	public List<AccountBatchPO> querySameSumBatchInWeek(double sum) {
		return accountBatchReadDAO.querySameSumBatchInWeek(sum);
	}

	@Override
	public PageResult<AccountBatchPO> queryRechargeAccountBatchPage(Pagination page, Long companyId, String batchNo) {
		List<AccountBatchPO> poList=accountBatchReadDAO.queryRechargeAccountBatchPage(page,companyId,batchNo);
		Integer totalCount=accountBatchReadDAO.queryRechargeAccountBatchPageTotalSize(companyId,batchNo);
		PageResult<AccountBatchPO> poPage=new PageResult<>();
		poPage.setList(poList);
		poPage.setTotalSize(totalCount);
		poPage.copy(page);
		return poPage;
	}
	
}
	