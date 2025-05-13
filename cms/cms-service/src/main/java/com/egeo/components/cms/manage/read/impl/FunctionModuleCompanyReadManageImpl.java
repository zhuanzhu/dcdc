package com.egeo.components.cms.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.read.FunctionModuleCompanyReadManage;
import com.egeo.components.cms.dao.read.FunctionModuleCompanyReadDAO;
import com.egeo.components.cms.po.FunctionModuleCompanyPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class FunctionModuleCompanyReadManageImpl implements FunctionModuleCompanyReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private FunctionModuleCompanyReadDAO functionModuleCompanyReadDAO;
	
	public FunctionModuleCompanyPO findFunctionModuleCompanyById(FunctionModuleCompanyPO po) {
		FunctionModuleCompanyPO functionModuleCompanypo = new FunctionModuleCompanyPO();
		functionModuleCompanypo.setId(po.getId());
		return functionModuleCompanyReadDAO.findById(functionModuleCompanypo);
	}

	public PageResult<FunctionModuleCompanyPO> findFunctionModuleCompanyOfPage(FunctionModuleCompanyPO po, Pagination page) {
		
		PageResult<FunctionModuleCompanyPO> pageResult = new PageResult<FunctionModuleCompanyPO>();
		List<FunctionModuleCompanyPO> list = null;

		int cnt = functionModuleCompanyReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = functionModuleCompanyReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<FunctionModuleCompanyPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<FunctionModuleCompanyPO> findFunctionModuleCompanyAll(FunctionModuleCompanyPO po) {

		return functionModuleCompanyReadDAO.findAll(po,null);
	}
	
}
	