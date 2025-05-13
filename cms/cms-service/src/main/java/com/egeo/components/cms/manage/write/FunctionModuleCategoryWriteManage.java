package com.egeo.components.cms.manage.write;

import java.util.List;

import com.egeo.components.cms.po.FunctionModuleCategoryPO;


public interface FunctionModuleCategoryWriteManage {

	Long insertFunctionModuleCategoryWithTx(FunctionModuleCategoryPO po);

	int updateFunctionModuleCategoryWithTx(FunctionModuleCategoryPO po);

	int deleteFunctionModuleCategoryWithTx(FunctionModuleCategoryPO po);
	/**
	 * 根据功能模块及已存在的类目节点id集合批量删除功能模块类目节点关系
	 * @param functionModuleId
	 * @param categoryTreeNodeIdList
	 * @return
	 */
	int delByFunctionModuleIdCategoryTreeNodeIds(Long functionModuleId, List<Long> categoryTreeNodeIdList);
	/**
	 * 根据功能模块id删除功能模块类目节点关系
	 * @param functionModuleId
	 * @return
	 */
	int delByFunctionModuleId(Long functionModuleId);
}
	