package com.egeo.components.product.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.StandardUnitRecordReadService;
import com.egeo.components.product.service.write.StandardUnitRecordWriteService;
import com.egeo.components.product.dto.StandardUnitRecordDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class StandardUnitRecordFacade {
	
	@Resource
	private StandardUnitRecordReadService standardUnitRecordReadService;
	
	@Resource
	private StandardUnitRecordWriteService standardUnitRecordWriteService;
	
	
	public StandardUnitRecordDTO findStandardUnitRecordById(StandardUnitRecordDTO dto){
		
		return standardUnitRecordReadService.findStandardUnitRecordById(dto);
	}

	public PageResult<StandardUnitRecordDTO> findStandardUnitRecordOfPage(StandardUnitRecordDTO dto,Pagination page){
		
		return standardUnitRecordReadService.findStandardUnitRecordOfPage(dto, page);
		
	}

	public List<StandardUnitRecordDTO> findStandardUnitRecordAll(StandardUnitRecordDTO dto){
		
		return standardUnitRecordReadService.findStandardUnitRecordAll(dto);
		
	}

	public Long insertStandardUnitRecordWithTx(StandardUnitRecordDTO dto){
		
		return standardUnitRecordWriteService.insertStandardUnitRecordWithTx(dto);
	}

	public int updateStandardUnitRecordWithTx(StandardUnitRecordDTO dto){
		
		return standardUnitRecordWriteService.updateStandardUnitRecordWithTx(dto);
	}

	public int deleteStandardUnitRecordWithTx(StandardUnitRecordDTO dto){
		
		return standardUnitRecordWriteService.deleteStandardUnitRecordWithTx(dto);
		
	}

}
	