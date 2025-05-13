package com.egeo.components.product.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.StandardUnitMembershipReadService;
import com.egeo.components.product.service.write.StandardUnitMembershipWriteService;
import com.egeo.components.product.dto.StandardUnitMembershipDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class StandardUnitMembershipFacade {
	
	@Resource
	private StandardUnitMembershipReadService standardUnitMembershipReadService;
	
	@Resource
	private StandardUnitMembershipWriteService standardUnitMembershipWriteService;
	
	
	public StandardUnitMembershipDTO findStandardUnitMembershipById(StandardUnitMembershipDTO dto){
		
		return standardUnitMembershipReadService.findStandardUnitMembershipById(dto);
	}

	public PageResult<StandardUnitMembershipDTO> findStandardUnitMembershipOfPage(StandardUnitMembershipDTO dto,Pagination page){
		
		return standardUnitMembershipReadService.findStandardUnitMembershipOfPage(dto, page);
		
	}

	public List<StandardUnitMembershipDTO> findStandardUnitMembershipAll(StandardUnitMembershipDTO dto){
		
		return standardUnitMembershipReadService.findStandardUnitMembershipAll(dto);
		
	}

	public Long insertStandardUnitMembershipWithTx(StandardUnitMembershipDTO dto){
		
		return standardUnitMembershipWriteService.insertStandardUnitMembershipWithTx(dto);
	}

	public int updateStandardUnitMembershipWithTx(StandardUnitMembershipDTO dto){
		
		return standardUnitMembershipWriteService.updateStandardUnitMembershipWithTx(dto);
	}

	public int deleteStandardUnitMembershipWithTx(StandardUnitMembershipDTO dto){
		
		return standardUnitMembershipWriteService.deleteStandardUnitMembershipWithTx(dto);
		
	}

}
	