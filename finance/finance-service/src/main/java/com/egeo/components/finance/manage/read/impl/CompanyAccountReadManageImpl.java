package com.egeo.components.finance.manage.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.finance.dao.read.CompanyAccountReadDAO;
import com.egeo.components.finance.manage.read.CompanyAccountReadManage;
import com.egeo.components.finance.po.CompanyAccountPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class CompanyAccountReadManageImpl implements CompanyAccountReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CompanyAccountReadDAO companyAccountReadDAO;
	
	public CompanyAccountPO findCompanyAccountById(CompanyAccountPO po) {
		CompanyAccountPO companyAccountpo = new CompanyAccountPO();
		companyAccountpo.setId(po.getId());
		return companyAccountReadDAO.findById(companyAccountpo);
	}

	public PageResult<CompanyAccountPO> findCompanyAccountOfPage(CompanyAccountPO po, Pagination page) {
		
		PageResult<CompanyAccountPO> pageResult = new PageResult<CompanyAccountPO>();
		List<CompanyAccountPO> list = null;

		int cnt = companyAccountReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = companyAccountReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<CompanyAccountPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<CompanyAccountPO> findCompanyAccountAll(CompanyAccountPO po) {

		return companyAccountReadDAO.findAll(po,null);
	}

	@Override
	public PageResult<CompanyAccountPO> queryCompanyAccountPage(Pagination page, String accountName, List<Long> companyId,
			Integer disabled,Long platformId) {
		List<CompanyAccountPO> poList=companyAccountReadDAO.queryCompanyAccountPage(page,accountName,companyId,disabled,platformId);
		Integer totalCount=companyAccountReadDAO.queryCompanyAccountPageTotalCount(accountName,companyId,disabled,platformId);
		PageResult<CompanyAccountPO> poPage=new PageResult<>();
		poPage.copy(page);
		poPage.setList(poList);
		poPage.setTotalSize(totalCount);
		return poPage;
	}

	@Override
	public List<CompanyAccountPO> queryNormalAccounts(Long platformId,List<Long> companyId) {
		
		return companyAccountReadDAO.queryNormalAccounts(platformId,companyId);
	}

	@Override
	public CompanyAccountPO querySpecialCompanyAccountByType(Long platformId, int type) {
		
		return companyAccountReadDAO.querySpecialCompanyAccountByType(type,platformId);
	}

	@Override
	public List<CompanyAccountPO> queryCompanyAccountsByIds(List<Long> accountIds) {
		return companyAccountReadDAO.queryCompanyAccountsByIds(accountIds);
	}

	@Override
	public CompanyAccountPO queryNormalCompanyAccountByCompnayId(Long companyId) {
		
		return companyAccountReadDAO.queryNormalCompanyAccountByCompnayId(companyId);
	}

	@Override
	public CompanyAccountPO queryCompanyAccountById(Long accountId) {
		
		return companyAccountReadDAO.queryCompanyAccountById(accountId);
	}
	
}
	