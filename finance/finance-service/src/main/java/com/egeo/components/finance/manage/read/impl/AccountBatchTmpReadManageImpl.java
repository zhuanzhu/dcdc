package com.egeo.components.finance.manage.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.finance.dao.read.AccountBatchTmpReadDAO;
import com.egeo.components.finance.manage.read.AccountBatchTmpReadManage;
import com.egeo.components.finance.po.AccountBatchTmpPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class AccountBatchTmpReadManageImpl implements AccountBatchTmpReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private AccountBatchTmpReadDAO accountBatchTmpReadDAO;
	
	public AccountBatchTmpPO findAccountBatchTmpById(AccountBatchTmpPO po) {
		AccountBatchTmpPO accountBatchTmppo = new AccountBatchTmpPO();
		accountBatchTmppo.setId(po.getId());
		return accountBatchTmpReadDAO.findById(accountBatchTmppo);
	}

	public PageResult<AccountBatchTmpPO> findAccountBatchTmpOfPage(AccountBatchTmpPO po, Pagination page) {
		
		PageResult<AccountBatchTmpPO> pageResult = new PageResult<AccountBatchTmpPO>();
		List<AccountBatchTmpPO> list = null;

		int cnt = accountBatchTmpReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = accountBatchTmpReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<AccountBatchTmpPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<AccountBatchTmpPO> findAccountBatchTmpAll(AccountBatchTmpPO po) {

		return accountBatchTmpReadDAO.findAll(po,null);
	}

	@Override
	public AccountBatchTmpPO queryAccountBatchTmpByFinBatch(String finBatch) {
		
		return accountBatchTmpReadDAO.queryAccountBatchTmpByFinBatch(finBatch);
	}

	@Override
	public PageResult<AccountBatchTmpPO> queryAccountBatchTmpPage(Pagination page, String keyWord, Long companyId,
			Integer status, Integer type,Long platformId) {
		List<AccountBatchTmpPO> poList=accountBatchTmpReadDAO.queryAccountBatchTmpPage(page,keyWord,companyId,status,type,platformId);
		Integer totalCount=accountBatchTmpReadDAO.queryAccountBatchTmpPageTotalSize(keyWord,companyId,status,type,platformId);
		PageResult<AccountBatchTmpPO> poPage=new PageResult<>();
		poPage.setList(poList);
		poPage.setTotalSize(totalCount);
		poPage.copy(page);
		return poPage;
	}
	
}
	