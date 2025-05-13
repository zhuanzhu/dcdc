package com.egeo.components.cms.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.cms.service.read.FunctionModuleCompanyReadService;
import com.egeo.components.cms.service.write.FunctionModuleCompanyWriteService;
import com.egeo.components.cms.dto.FunctionModuleCompanyDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class FunctionModuleCompanyFacade {
	
	@Resource
	private FunctionModuleCompanyReadService functionModuleCompanyReadService;
	
	@Resource
	private FunctionModuleCompanyWriteService functionModuleCompanyWriteService;
	
	
	public FunctionModuleCompanyDTO findFunctionModuleCompanyById(FunctionModuleCompanyDTO dto){
		
		return functionModuleCompanyReadService.findFunctionModuleCompanyById(dto);
	}

	public PageResult<FunctionModuleCompanyDTO> findFunctionModuleCompanyOfPage(FunctionModuleCompanyDTO dto,Pagination page){
		
		return functionModuleCompanyReadService.findFunctionModuleCompanyOfPage(dto, page);
		
	}

	public List<FunctionModuleCompanyDTO> findFunctionModuleCompanyAll(FunctionModuleCompanyDTO dto){
		
		return functionModuleCompanyReadService.findFunctionModuleCompanyAll(dto);
		
	}

	public Long insertFunctionModuleCompanyWithTx(FunctionModuleCompanyDTO dto){
		
		return functionModuleCompanyWriteService.insertFunctionModuleCompanyWithTx(dto);
	}

	public int updateFunctionModuleCompanyWithTx(FunctionModuleCompanyDTO dto){
		
		return functionModuleCompanyWriteService.updateFunctionModuleCompanyWithTx(dto);
	}

	public int deleteFunctionModuleCompanyWithTx(FunctionModuleCompanyDTO dto){
		
		return functionModuleCompanyWriteService.deleteFunctionModuleCompanyWithTx(dto);
		
	}

}
	