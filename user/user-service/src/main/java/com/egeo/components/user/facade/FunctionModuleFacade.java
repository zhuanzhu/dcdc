package com.egeo.components.user.facade;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.egeo.components.user.dto.FunctionModuleDTO;
import com.egeo.components.user.service.read.FunctionModuleReadService;
import com.egeo.components.user.service.write.FunctionModuleWriteService;
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
	