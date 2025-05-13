package com.egeo.components.config.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.components.config.dto.PolymallUserDTO;
import com.egeo.components.config.service.read.PolymallUserReadService;
import com.egeo.components.config.service.write.PolymallUserWriteService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class PolymallUserFacade {
	
	@Autowired
	private PolymallUserReadService polymallUserReadService;
	
	@Autowired
	private PolymallUserWriteService polymallUserWriteService;
	
	
	public PolymallUserDTO findPolymallUserById(PolymallUserDTO dto){
		
		return polymallUserReadService.findPolymallUserById(dto);
	}

	public PageResult<PolymallUserDTO> findPolymallUserOfPage(PolymallUserDTO dto,Pagination page){
		
		return polymallUserReadService.findPolymallUserOfPage(dto, page);
		
	}

	public List<PolymallUserDTO> findPolymallUserAll(PolymallUserDTO dto){
		
		return polymallUserReadService.findPolymallUserAll(dto);
		
	}

	public Long insertPolymallUserWithTx(PolymallUserDTO dto){
		
		return polymallUserWriteService.insertPolymallUserWithTx(dto);
	}

	public int updatePolymallUserWithTx(PolymallUserDTO dto){
		
		return polymallUserWriteService.updatePolymallUserWithTx(dto);
	}

	public int deletePolymallUserWithTx(PolymallUserDTO dto){
		
		return polymallUserWriteService.deletePolymallUserWithTx(dto);
		
	}

}
	