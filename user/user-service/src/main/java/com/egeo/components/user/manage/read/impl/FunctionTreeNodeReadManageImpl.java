package com.egeo.components.user.manage.read.impl;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.user.po.UrlPO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.manage.read.FunctionTreeNodeReadManage;
import com.egeo.components.user.dao.read.FunctionTreeNodeReadDAO;
import com.egeo.components.user.po.FunctionTreeNodePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class FunctionTreeNodeReadManageImpl implements FunctionTreeNodeReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private FunctionTreeNodeReadDAO functionTreeNodeReadDAO;
	
	public FunctionTreeNodePO findFunctionTreeNodeById(FunctionTreeNodePO po) {
		FunctionTreeNodePO functionTreeNodepo = new FunctionTreeNodePO();
		functionTreeNodepo.setId(po.getId());
		return functionTreeNodeReadDAO.findById(functionTreeNodepo);
	}

	public PageResult<FunctionTreeNodePO> findFunctionTreeNodeOfPage(FunctionTreeNodePO po, Pagination page) {
		
		PageResult<FunctionTreeNodePO> pageResult = new PageResult<FunctionTreeNodePO>();
		List<FunctionTreeNodePO> list = null;

		int cnt = functionTreeNodeReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = functionTreeNodeReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<FunctionTreeNodePO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<FunctionTreeNodePO> findFunctionTreeNodeAll(FunctionTreeNodePO po) {

		return functionTreeNodeReadDAO.findAll(po,null);
	}

	@Override
	public List<UrlPO> findUrlList(Long id) {
		return functionTreeNodeReadDAO.findUrlList(id);
	}

}
	