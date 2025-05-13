package com.egeo.components.cms.service.write;

import java.util.List;

import com.egeo.components.cms.dto.FunctionModuleCategoryDTO;


public interface FunctionModuleCategoryWriteService {

	public Long insertFunctionModuleCategoryWithTx(FunctionModuleCategoryDTO dto);

	public int updateFunctionModuleCategoryWithTx(FunctionModuleCategoryDTO dto);

	public int deleteFunctionModuleCategoryWithTx(FunctionModuleCategoryDTO dto);
	/**
	 * 批量更新类目节点信息
	 * @param functionModuleId
	 * @param categoryTreeNodeIdList
	 * @param req
	 * @return
	 */
	public int updateFunctionModuleCategoryAllWithTx(Long functionModuleId, List<Long> categoryTreeNodeIds);
}
	