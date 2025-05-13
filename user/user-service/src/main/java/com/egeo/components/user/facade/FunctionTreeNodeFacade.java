package com.egeo.components.user.facade;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.egeo.components.user.dto.FunctionTreeNodeDTO;
import com.egeo.components.user.dto.UrlDTO;
import com.egeo.components.user.service.read.FunctionTreeNodeReadService;
import com.egeo.components.user.service.write.FunctionTreeNodeWriteService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class FunctionTreeNodeFacade {
	
	@Resource
	private FunctionTreeNodeReadService functionTreeNodeReadService;
	
	@Resource
	private FunctionTreeNodeWriteService functionTreeNodeWriteService;
	
	
	public FunctionTreeNodeDTO findFunctionTreeNodeById(FunctionTreeNodeDTO dto){
		
		return functionTreeNodeReadService.findFunctionTreeNodeById(dto);
	}

	public PageResult<FunctionTreeNodeDTO> findFunctionTreeNodeOfPage(FunctionTreeNodeDTO dto,Pagination page){
		
		return functionTreeNodeReadService.findFunctionTreeNodeOfPage(dto, page);
		
	}

	public List<FunctionTreeNodeDTO> findFunctionTreeNodeAll(FunctionTreeNodeDTO dto){
		
		return functionTreeNodeReadService.findFunctionTreeNodeAll(dto);
		
	}

	public Long insertFunctionTreeNodeWithTx(FunctionTreeNodeDTO dto){
		
		return functionTreeNodeWriteService.insertFunctionTreeNodeWithTx(dto);
	}

	public int updateFunctionTreeNodeWithTx(FunctionTreeNodeDTO dto){
		
		return functionTreeNodeWriteService.updateFunctionTreeNodeWithTx(dto);
	}

	public int deleteFunctionTreeNodeWithTx(FunctionTreeNodeDTO dto){
		
		return functionTreeNodeWriteService.deleteFunctionTreeNodeWithTx(dto);
		
	}

	public List<UrlDTO> findUrlList(Long id) {
		return functionTreeNodeReadService.findUrlList(id);
	}

	public Boolean hasLeaf(Long id) {
		return functionTreeNodeReadService.hasLeaf(id);
	}
}
	