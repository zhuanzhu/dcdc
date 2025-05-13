package com.egeo.components.user.manage.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.manage.read.FunctionTreeNodeUrlReadManage;
import com.egeo.components.user.condition.FunctionTreeNodeUrlCondition;
import com.egeo.components.user.dao.read.FunctionTreeNodeUrlReadDAO;
import com.egeo.components.user.po.FunctionTreeNodeUrlPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class FunctionTreeNodeUrlReadManageImpl implements FunctionTreeNodeUrlReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private FunctionTreeNodeUrlReadDAO functionTreeNodeUrlReadDAO;
	
	public FunctionTreeNodeUrlPO findFunctionTreeNodeUrlById(FunctionTreeNodeUrlPO po) {
		FunctionTreeNodeUrlPO functionTreeNodeUrlpo = new FunctionTreeNodeUrlPO();
		functionTreeNodeUrlpo.setId(po.getId());
		return functionTreeNodeUrlReadDAO.findById(functionTreeNodeUrlpo);
	}

	public PageResult<FunctionTreeNodeUrlCondition> findFunctionTreeNodeUrlOfPage(FunctionTreeNodeUrlCondition po, Pagination page) {
		
		PageResult<FunctionTreeNodeUrlCondition> pageResult = new PageResult<FunctionTreeNodeUrlCondition>();
		List<FunctionTreeNodeUrlCondition> list = null;

		int cnt = functionTreeNodeUrlReadDAO.countFunctionTreeNodeUrlConditionOfPage(po);
		logger.info("总的数量cnt = "+cnt);
		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = functionTreeNodeUrlReadDAO.findFunctionTreeNodeUrlConditionOfPage(po, page);
		} else {
			list = new ArrayList<FunctionTreeNodeUrlCondition>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<FunctionTreeNodeUrlPO> findFunctionTreeNodeUrlAll(FunctionTreeNodeUrlPO po) {

		return functionTreeNodeUrlReadDAO.findAll(po,null);
	}

	public List<FunctionTreeNodeUrlCondition> findFunctionTreeNodeUrlByFunctionTreeNodeId(Long functionTreeNodeId) {
        List<FunctionTreeNodeUrlCondition> conditions = functionTreeNodeUrlReadDAO.findFunctionTreeNodeUrlByFunctionTreeNodeId(functionTreeNodeId);
        /*List<FunctionTreeNodeUrlPO> poList = new ArrayList<FunctionTreeNodeUrlPO>();
        for (FunctionTreeNodeUrlCondition condition : conditions) {
            if (EmptyUtil.isNotEmpty(condition)) {
                FunctionTreeNodeUrlPO po = new FunctionTreeNodeUrlPO();
                po.setUrlId(condition.getUrlId());
                po.setUrl(condition.getUrl());
                po.setUrlName(condition.getUrlName());
                poList.add(po);
            }
        }*/
        return conditions;
    }
	
}
	