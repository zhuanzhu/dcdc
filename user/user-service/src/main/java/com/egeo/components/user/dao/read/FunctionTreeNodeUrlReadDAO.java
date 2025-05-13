package com.egeo.components.user.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.user.condition.FunctionTreeNodeUrlCondition;
import com.egeo.components.user.po.FunctionTreeNodeUrlPO;
import com.egeo.orm.BaseReadDAO;
import com.egeo.orm.Pagination;

public interface FunctionTreeNodeUrlReadDAO extends BaseReadDAO<FunctionTreeNodeUrlPO>{
	
	List<FunctionTreeNodeUrlCondition> findFunctionTreeNodeUrlConditionOfPage(@Param("condition") FunctionTreeNodeUrlCondition condition, @Param("page") Pagination page);
	
	int countFunctionTreeNodeUrlConditionOfPage(@Param("condition") FunctionTreeNodeUrlCondition condition);

	List<FunctionTreeNodeUrlCondition> findFunctionTreeNodeUrlByFunctionTreeNodeId(@Param("functionTreeNodeId") Long functionTreeNodeId);

}
	