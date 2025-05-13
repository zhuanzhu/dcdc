package com.egeo.components.cms.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.cms.service.read.FunctionModuleCategoryReadService;
import com.egeo.components.cms.service.write.FunctionModuleCategoryWriteService;
import com.egeo.components.cms.dto.FunctionModuleCategoryDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class FunctionModuleCategoryFacade {
	
	@Resource
	private FunctionModuleCategoryReadService functionModuleCategoryReadService;
	
	@Resource
	private FunctionModuleCategoryWriteService functionModuleCategoryWriteService;
	
	
	public FunctionModuleCategoryDTO findFunctionModuleCategoryById(FunctionModuleCategoryDTO dto){
		
		return functionModuleCategoryReadService.findFunctionModuleCategoryById(dto);
	}

	public PageResult<FunctionModuleCategoryDTO> findFunctionModuleCategoryOfPage(FunctionModuleCategoryDTO dto,Pagination page){
		
		return functionModuleCategoryReadService.findFunctionModuleCategoryOfPage(dto, page);
		
	}

	public List<FunctionModuleCategoryDTO> findFunctionModuleCategoryAll(FunctionModuleCategoryDTO dto){
		
		return functionModuleCategoryReadService.findFunctionModuleCategoryAll(dto);
		
	}

	public Long insertFunctionModuleCategoryWithTx(FunctionModuleCategoryDTO dto){
		
		return functionModuleCategoryWriteService.insertFunctionModuleCategoryWithTx(dto);
	}

	public int updateFunctionModuleCategoryWithTx(FunctionModuleCategoryDTO dto){
		
		return functionModuleCategoryWriteService.updateFunctionModuleCategoryWithTx(dto);
	}

	public int deleteFunctionModuleCategoryWithTx(FunctionModuleCategoryDTO dto){
		
		return functionModuleCategoryWriteService.deleteFunctionModuleCategoryWithTx(dto);
		
	}
	/**
	 * 批量更新类目节点信息
	 * @param functionModuleId
	 * @param categoryTreeNodeIdList
	 * @param req
	 * @return
	 */
	public int updateFunctionModuleCategoryAllWithTx(Long functionModuleId, List<Long> categoryTreeNodeIds) {
		// TODO Auto-generated method stub
		return functionModuleCategoryWriteService.updateFunctionModuleCategoryAllWithTx(functionModuleId, categoryTreeNodeIds);
	}

}
	