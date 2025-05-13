package com.egeo.components.cms.business.impl;
	

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.components.cms.business.FunctionModuleCategoryManage;
import com.egeo.components.cms.facade.FunctionModuleCategoryFacade;
import com.egeo.components.cms.dto.FunctionModuleCategoryDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("functionModuleCategory")
public class FunctionModuleCategoryManageImpl implements FunctionModuleCategoryManage{

	
	@Resource(name="functionModuleCategoryFacade")
	private FunctionModuleCategoryFacade functionModuleCategoryFacade;

	@Override
	public FunctionModuleCategoryDTO findFunctionModuleCategoryById(FunctionModuleCategoryDTO dto) {
		return functionModuleCategoryFacade.findFunctionModuleCategoryById(dto);
	}

	@Override
	public PageResult<FunctionModuleCategoryDTO> findFunctionModuleCategoryOfPage(FunctionModuleCategoryDTO dto, Pagination page) {
		return functionModuleCategoryFacade.findFunctionModuleCategoryOfPage(dto, page);
	}

	@Override
	public List<FunctionModuleCategoryDTO> findFunctionModuleCategoryAll(FunctionModuleCategoryDTO dto) {
		return functionModuleCategoryFacade.findFunctionModuleCategoryAll(dto);
	}

	@Override
	public Long insertFunctionModuleCategoryWithTx(FunctionModuleCategoryDTO dto) {
		return functionModuleCategoryFacade.insertFunctionModuleCategoryWithTx(dto);
	}

	@Override
	public int updateFunctionModuleCategoryWithTx(FunctionModuleCategoryDTO dto) {
		return functionModuleCategoryFacade.updateFunctionModuleCategoryWithTx(dto);
	}

	@Override
	public int deleteFunctionModuleCategoryWithTx(FunctionModuleCategoryDTO dto) {
		return functionModuleCategoryFacade.deleteFunctionModuleCategoryWithTx(dto);
	}
	/**
	 * 批量更新类目节点信息
	 * @param functionModuleId
	 * @param categoryTreeNodeIdList
	 * @param req
	 * @return
	 */
	@Override
	public int updateFunctionModuleCategoryAllWithTx(Long functionModuleId, List<Long> categoryTreeNodeIds) {
		// TODO Auto-generated method stub
		return functionModuleCategoryFacade.updateFunctionModuleCategoryAllWithTx(functionModuleId, categoryTreeNodeIds);
	}

	@Override
	public String categoryTreeNodeIdsByFunctionModuleId(Long functionModuleId) {
		FunctionModuleCategoryDTO functionModuleCategoryDTO = new FunctionModuleCategoryDTO();
		functionModuleCategoryDTO.setFunctionModuleId(functionModuleId);
		List<FunctionModuleCategoryDTO> functionModuleCategoryList = functionModuleCategoryFacade.findFunctionModuleCategoryAll(functionModuleCategoryDTO);
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < functionModuleCategoryList.size(); i++) {
			if( i >= functionModuleCategoryList.size() - 1){
				stringBuffer.append(functionModuleCategoryList.get(i).getCategoryTreeNodeId());
			}else{
				stringBuffer.append(functionModuleCategoryList.get(i).getCategoryTreeNodeId());
				stringBuffer.append(",");
			}
		}
		return stringBuffer.toString();
	}


}
	