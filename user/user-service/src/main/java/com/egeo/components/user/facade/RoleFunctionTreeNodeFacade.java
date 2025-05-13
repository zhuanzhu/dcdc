package com.egeo.components.user.facade;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.egeo.components.user.dto.RoleFunctionTreeNodeDTO;
import com.egeo.components.user.service.read.RoleFunctionTreeNodeReadService;
import com.egeo.components.user.service.write.RoleFunctionTreeNodeWriteService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class RoleFunctionTreeNodeFacade {
	
	@Resource
	private RoleFunctionTreeNodeReadService roleFunctionTreeNodeReadService;
	
	@Resource
	private RoleFunctionTreeNodeWriteService roleFunctionTreeNodeWriteService;
	
	
	public RoleFunctionTreeNodeDTO findRoleFunctionTreeNodeById(RoleFunctionTreeNodeDTO dto){
		
		return roleFunctionTreeNodeReadService.findRoleFunctionTreeNodeById(dto);
	}

	public PageResult<RoleFunctionTreeNodeDTO> findRoleFunctionTreeNodeOfPage(RoleFunctionTreeNodeDTO dto,Pagination page){
		
		return roleFunctionTreeNodeReadService.findRoleFunctionTreeNodeOfPage(dto, page);
		
	}

	public List<RoleFunctionTreeNodeDTO> findRoleFunctionTreeNodeAll(RoleFunctionTreeNodeDTO dto){
		
		return roleFunctionTreeNodeReadService.findRoleFunctionTreeNodeAll(dto);
		
	}

	public Long insertRoleFunctionTreeNodeWithTx(RoleFunctionTreeNodeDTO dto){
		
		return roleFunctionTreeNodeWriteService.insertRoleFunctionTreeNodeWithTx(dto);
	}

	public int updateRoleFunctionTreeNodeWithTx(RoleFunctionTreeNodeDTO dto){
		
		return roleFunctionTreeNodeWriteService.updateRoleFunctionTreeNodeWithTx(dto);
	}

	public int deleteRoleFunctionTreeNodeWithTx(RoleFunctionTreeNodeDTO dto){
		
		return roleFunctionTreeNodeWriteService.deleteRoleFunctionTreeNodeWithTx(dto);
		
	}

}
	