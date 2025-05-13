package com.egeo.components.product.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.StandardUnitRecordMembershipReadService;
import com.egeo.components.product.service.write.StandardUnitRecordMembershipWriteService;
import com.egeo.components.product.dto.StandardUnitRecordMembershipDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class StandardUnitRecordMembershipFacade {
	
	@Resource
	private StandardUnitRecordMembershipReadService standardUnitRecordMembershipReadService;
	
	@Resource
	private StandardUnitRecordMembershipWriteService standardUnitRecordMembershipWriteService;
	
	
	public StandardUnitRecordMembershipDTO findStandardUnitRecordMembershipById(StandardUnitRecordMembershipDTO dto){
		
		return standardUnitRecordMembershipReadService.findStandardUnitRecordMembershipById(dto);
	}

	public PageResult<StandardUnitRecordMembershipDTO> findStandardUnitRecordMembershipOfPage(StandardUnitRecordMembershipDTO dto,Pagination page){
		
		return standardUnitRecordMembershipReadService.findStandardUnitRecordMembershipOfPage(dto, page);
		
	}

	public List<StandardUnitRecordMembershipDTO> findStandardUnitRecordMembershipAll(StandardUnitRecordMembershipDTO dto){
		
		return standardUnitRecordMembershipReadService.findStandardUnitRecordMembershipAll(dto);
		
	}

	public Long insertStandardUnitRecordMembershipWithTx(StandardUnitRecordMembershipDTO dto){
		
		return standardUnitRecordMembershipWriteService.insertStandardUnitRecordMembershipWithTx(dto);
	}

	public int updateStandardUnitRecordMembershipWithTx(StandardUnitRecordMembershipDTO dto){
		
		return standardUnitRecordMembershipWriteService.updateStandardUnitRecordMembershipWithTx(dto);
	}

	public int deleteStandardUnitRecordMembershipWithTx(StandardUnitRecordMembershipDTO dto){
		
		return standardUnitRecordMembershipWriteService.deleteStandardUnitRecordMembershipWithTx(dto);
		
	}

}
	