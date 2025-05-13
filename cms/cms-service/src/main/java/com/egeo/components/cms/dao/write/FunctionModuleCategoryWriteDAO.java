package com.egeo.components.cms.dao.write;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.cms.po.FunctionModuleCategoryPO;
import com.egeo.orm.BaseWriteDAO;

public interface FunctionModuleCategoryWriteDAO extends BaseWriteDAO<FunctionModuleCategoryPO> {
	/**
	 * 根据功能模块及已存在的类目节点id集合批量删除功能模块类目节点关系
	 * @param functionModuleId
	 * @param categoryTreeNodeIdList
	 * @return
	 */
	int delByFunctionModuleIdCategoryTreeNodeIds(@Param("functionModuleId")Long functionModuleId, @Param("ids")List<Long> categoryTreeNodeIdList);
}
	