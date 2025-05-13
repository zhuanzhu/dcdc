package com.egeo.components.user.manage.read;

import java.util.List;

import com.egeo.components.user.condition.FunctionTreeNodeUrlCondition;
import com.egeo.components.user.po.FunctionTreeNodeUrlPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface FunctionTreeNodeUrlReadManage {

	public FunctionTreeNodeUrlPO findFunctionTreeNodeUrlById(FunctionTreeNodeUrlPO po);

	public PageResult<FunctionTreeNodeUrlCondition> findFunctionTreeNodeUrlOfPage(FunctionTreeNodeUrlCondition po,Pagination page);

	public List<FunctionTreeNodeUrlPO> findFunctionTreeNodeUrlAll(FunctionTreeNodeUrlPO po);

	public List<FunctionTreeNodeUrlCondition> findFunctionTreeNodeUrlByFunctionTreeNodeId(Long functionTreeNodeId);
}
	