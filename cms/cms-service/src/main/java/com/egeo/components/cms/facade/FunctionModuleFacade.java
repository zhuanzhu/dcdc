package com.egeo.components.cms.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.cms.service.read.FunctionModuleReadService;
import com.egeo.components.cms.service.write.FunctionModuleWriteService;
import com.egeo.components.cms.dto.FunctionModuleDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class FunctionModuleFacade {
	
	@Resource
	private FunctionModuleReadService functionModuleReadService;
	
	@Resource
	private FunctionModuleWriteService functionModuleWriteService;
	
	
	public FunctionModuleDTO findFunctionModuleById(FunctionModuleDTO dto){
		
		return functionModuleReadService.findFunctionModuleById(dto);
	}

	public PageResult<FunctionModuleDTO> findFunctionModuleOfPage(FunctionModuleDTO dto,Pagination page){
		
		return functionModuleReadService.findFunctionModuleOfPage(dto, page);
		
	}

	public List<FunctionModuleDTO> findFunctionModuleAll(FunctionModuleDTO dto){
		
		return functionModuleReadService.findFunctionModuleAll(dto);
		
	}

	public Long insertFunctionModuleWithTx(FunctionModuleDTO dto){
		
		return functionModuleWriteService.insertFunctionModuleWithTx(dto);
	}

	public int updateFunctionModuleWithTx(FunctionModuleDTO dto){
		
		return functionModuleWriteService.updateFunctionModuleWithTx(dto);
	}

	public int deleteFunctionModuleWithTx(FunctionModuleDTO dto){
		
		return functionModuleWriteService.deleteFunctionModuleWithTx(dto);
		
	}

}
	