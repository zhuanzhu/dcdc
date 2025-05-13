package com.egeo.components.cms.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.read.FunctionModuleCategoryReadManage;
import com.egeo.components.cms.dao.read.FunctionModuleCategoryReadDAO;
import com.egeo.components.cms.po.FunctionModuleCategoryPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class FunctionModuleCategoryReadManageImpl implements FunctionModuleCategoryReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private FunctionModuleCategoryReadDAO functionModuleCategoryReadDAO;
	
	public FunctionModuleCategoryPO findFunctionModuleCategoryById(FunctionModuleCategoryPO po) {
		FunctionModuleCategoryPO functionModuleCategorypo = new FunctionModuleCategoryPO();
		functionModuleCategorypo.setId(po.getId());
		return functionModuleCategoryReadDAO.findById(functionModuleCategorypo);
	}

	public PageResult<FunctionModuleCategoryPO> findFunctionModuleCategoryOfPage(FunctionModuleCategoryPO po, Pagination page) {
		
		PageResult<FunctionModuleCategoryPO> pageResult = new PageResult<FunctionModuleCategoryPO>();
		List<FunctionModuleCategoryPO> list = null;

		int cnt = functionModuleCategoryReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = functionModuleCategoryReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<FunctionModuleCategoryPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<FunctionModuleCategoryPO> findFunctionModuleCategoryAll(FunctionModuleCategoryPO po) {

		return functionModuleCategoryReadDAO.findAll(po,null);
	}
	
}
	