package com.egeo.components.user.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.dao.read.CompanyPageReadDAO;
import com.egeo.components.user.manage.read.CompanyPageReadManage;
import com.egeo.components.user.po.CompanyPagePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class CompanyPageReadManageImpl implements CompanyPageReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CompanyPageReadDAO companyPageReadDAO;
	
	public CompanyPagePO findCompanyPageById(CompanyPagePO po) {
		CompanyPagePO companyPagepo = new CompanyPagePO();
		companyPagepo.setId(po.getId());
		return companyPageReadDAO.findById(companyPagepo);
	}

	public PageResult<CompanyPagePO> findCompanyPageOfPage(CompanyPagePO po, Pagination page) {
		
		PageResult<CompanyPagePO> pageResult = new PageResult<CompanyPagePO>();
		List<CompanyPagePO> list = null;

		int cnt = companyPageReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = companyPageReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<CompanyPagePO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<CompanyPagePO> findCompanyPageAll(CompanyPagePO po) {

		return companyPageReadDAO.findAll(po,null);
	}
	
}
	