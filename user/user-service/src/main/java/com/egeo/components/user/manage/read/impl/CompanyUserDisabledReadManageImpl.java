package com.egeo.components.user.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.dao.read.CompanyUserDisabledReadDAO;
import com.egeo.components.user.manage.read.CompanyUserDisabledReadManage;
import com.egeo.components.user.po.CompanyUserDisabledPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class CompanyUserDisabledReadManageImpl implements CompanyUserDisabledReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CompanyUserDisabledReadDAO companyUserDisabledReadDAO;
	
	public CompanyUserDisabledPO findCompanyUserDisabledById(CompanyUserDisabledPO po) {
		CompanyUserDisabledPO companyUserDisabledpo = new CompanyUserDisabledPO();
		companyUserDisabledpo.setId(po.getId());
		return companyUserDisabledReadDAO.findById(companyUserDisabledpo);
	}

	public PageResult<CompanyUserDisabledPO> findCompanyUserDisabledOfPage(CompanyUserDisabledPO po, Pagination page) {
		
		PageResult<CompanyUserDisabledPO> pageResult = new PageResult<CompanyUserDisabledPO>();
		List<CompanyUserDisabledPO> list = null;

		int cnt = companyUserDisabledReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = companyUserDisabledReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<CompanyUserDisabledPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<CompanyUserDisabledPO> findCompanyUserDisabledAll(CompanyUserDisabledPO po) {

		return companyUserDisabledReadDAO.findAll(po,null);
	}

	public Integer findRevalidationByCompanyId(Long companyId) {
		return companyUserDisabledReadDAO.findRevalidationByCompanyId(companyId);
	}

	public List<Long> findUsersByCompanyId(Long companyId) {

		return companyUserDisabledReadDAO.findUsersByCompanyId(companyId);
	}
}
	