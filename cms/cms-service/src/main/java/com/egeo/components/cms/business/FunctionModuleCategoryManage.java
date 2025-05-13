package com.egeo.components.cms.business;

import java.util.List;
	
import com.egeo.components.cms.dto.FunctionModuleCategoryDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface FunctionModuleCategoryManage {

	public FunctionModuleCategoryDTO findFunctionModuleCategoryById(FunctionModuleCategoryDTO dto);	

	public PageResult<FunctionModuleCategoryDTO> findFunctionModuleCategoryOfPage(FunctionModuleCategoryDTO dto,Pagination page);

	public List<FunctionModuleCategoryDTO> findFunctionModuleCategoryAll(FunctionModuleCategoryDTO dto);

	Long insertFunctionModuleCategoryWithTx(FunctionModuleCategoryDTO dto);

	int updateFunctionModuleCategoryWithTx(FunctionModuleCategoryDTO dto);

	int deleteFunctionModuleCategoryWithTx(FunctionModuleCategoryDTO dto);
	/**
	 * 批量更新类目节点信息
	 * @param functionModuleId
	 * @param categoryTreeNodeIdList
	 * @param req
	 * @return
	 */
	public int updateFunctionModuleCategoryAllWithTx(Long functionModuleId, List<Long> categoryTreeNodeIds);
	/**
	 * 根据功能模版id查询所有选中的类目节点id
	 * @param vo
	 * @param req
	 * @return
	 */
	public String categoryTreeNodeIdsByFunctionModuleId(Long functionModuleId);
}
	