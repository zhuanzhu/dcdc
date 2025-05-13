package com.egeo.components.user.facade;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.egeo.components.user.dto.FunctionTreeNodeUrlDTO;
import com.egeo.components.user.service.read.FunctionTreeNodeUrlReadService;
import com.egeo.components.user.service.write.FunctionTreeNodeUrlWriteService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class FunctionTreeNodeUrlFacade {
	
	@Resource
	private FunctionTreeNodeUrlReadService functionTreeNodeUrlReadService;
	
	@Resource
	private FunctionTreeNodeUrlWriteService functionTreeNodeUrlWriteService;
	
	
	public FunctionTreeNodeUrlDTO findFunctionTreeNodeUrlById(FunctionTreeNodeUrlDTO dto){
		
		return functionTreeNodeUrlReadService.findFunctionTreeNodeUrlById(dto);
	}

	public PageResult<FunctionTreeNodeUrlDTO> findFunctionTreeNodeUrlOfPage(FunctionTreeNodeUrlDTO dto,Pagination page){
		
		return functionTreeNodeUrlReadService.findFunctionTreeNodeUrlOfPage(dto, page);
		
	}

	public List<FunctionTreeNodeUrlDTO> findFunctionTreeNodeUrlAll(FunctionTreeNodeUrlDTO dto){

		return functionTreeNodeUrlReadService.findFunctionTreeNodeUrlAll(dto);

	}

	public Long insertFunctionTreeNodeUrlWithTx(FunctionTreeNodeUrlDTO dto){
		
		return functionTreeNodeUrlWriteService.insertFunctionTreeNodeUrlWithTx(dto);
	}

	public int updateFunctionTreeNodeUrlWithTx(FunctionTreeNodeUrlDTO dto){
		
		return functionTreeNodeUrlWriteService.updateFunctionTreeNodeUrlWithTx(dto);
	}

	public int deleteFunctionTreeNodeUrlWithTx(FunctionTreeNodeUrlDTO dto){
		
		return functionTreeNodeUrlWriteService.deleteFunctionTreeNodeUrlWithTx(dto);
		
	}

	public List<FunctionTreeNodeUrlDTO> findFunctionTreeNodeUrlByFunctionTreeNodeId(Long functionTreeNodeId){

		return functionTreeNodeUrlReadService.findFunctionTreeNodeUrlByFunctionTreeNodeId(functionTreeNodeId);
	}

}
	