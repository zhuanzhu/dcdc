package com.egeo.components.user.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.dao.read.CompanyEditRecordReadDAO;
import com.egeo.components.user.manage.read.CompanyEditRecordReadManage;
import com.egeo.components.user.po.CompanyEditRecordPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class CompanyEditRecordReadManageImpl implements CompanyEditRecordReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CompanyEditRecordReadDAO companyEditRecordReadDAO;
	
	public CompanyEditRecordPO findCompanyEditRecordById(CompanyEditRecordPO po) {
		CompanyEditRecordPO companyEditRecordpo = new CompanyEditRecordPO();
		companyEditRecordpo.setId(po.getId());
		return companyEditRecordReadDAO.findById(companyEditRecordpo);
	}

	public PageResult<CompanyEditRecordPO> findCompanyEditRecordOfPage(CompanyEditRecordPO po, Pagination page) {
		
		PageResult<CompanyEditRecordPO> pageResult = new PageResult<CompanyEditRecordPO>();
		List<CompanyEditRecordPO> list = null;

		int cnt = companyEditRecordReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = companyEditRecordReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<CompanyEditRecordPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<CompanyEditRecordPO> findCompanyEditRecordAll(CompanyEditRecordPO po) {

		return companyEditRecordReadDAO.findAll(po,null);
	}
	
}
	