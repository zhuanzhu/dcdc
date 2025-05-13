package com.egeo.components.user.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.dao.read.CompanyCallCenterReadDAO;
import com.egeo.components.user.manage.read.CompanyCallCenterReadManage;
import com.egeo.components.user.po.CompanyCallCenterPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class CompanyCallCenterReadManageImpl implements CompanyCallCenterReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CompanyCallCenterReadDAO companyCallCenterReadDAO;
	
	public CompanyCallCenterPO findCompanyCallCenterById(CompanyCallCenterPO po) {
		CompanyCallCenterPO companyCallCenterpo = new CompanyCallCenterPO();
		companyCallCenterpo.setId(po.getId());
		return companyCallCenterReadDAO.findById(companyCallCenterpo);
	}

	public PageResult<CompanyCallCenterPO> findCompanyCallCenterOfPage(CompanyCallCenterPO po, Pagination page) {
		
		PageResult<CompanyCallCenterPO> pageResult = new PageResult<CompanyCallCenterPO>();
		List<CompanyCallCenterPO> list = null;

		int cnt = companyCallCenterReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = companyCallCenterReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<CompanyCallCenterPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<CompanyCallCenterPO> findCompanyCallCenterAll(CompanyCallCenterPO po) {

		return companyCallCenterReadDAO.findAll(po,null);
	}
	
}
	