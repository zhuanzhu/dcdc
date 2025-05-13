package com.egeo.components.user.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.manage.read.FunctionModuleReadManage;
import com.egeo.components.user.dao.read.FunctionModuleReadDAO;
import com.egeo.components.user.po.FunctionModulePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class FunctionModuleReadManageImpl implements FunctionModuleReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private FunctionModuleReadDAO functionModuleReadDAO;
	
	public FunctionModulePO findFunctionModuleById(FunctionModulePO po) {
		FunctionModulePO functionModulepo = new FunctionModulePO();
		functionModulepo.setId(po.getId());
		return functionModuleReadDAO.findById(functionModulepo);
	}

	public PageResult<FunctionModulePO> findFunctionModuleOfPage(FunctionModulePO po, Pagination page) {
		
		PageResult<FunctionModulePO> pageResult = new PageResult<FunctionModulePO>();
		List<FunctionModulePO> list = null;

		int cnt = functionModuleReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = functionModuleReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<FunctionModulePO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<FunctionModulePO> findFunctionModuleAll(FunctionModulePO po) {

		return functionModuleReadDAO.findAll(po,null);
	}
	
}
	