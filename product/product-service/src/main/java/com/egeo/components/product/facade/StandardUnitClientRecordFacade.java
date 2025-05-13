package com.egeo.components.product.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.StandardUnitClientRecordReadService;
import com.egeo.components.product.service.write.StandardUnitClientRecordWriteService;
import com.egeo.components.product.dto.StandardUnitClientRecordDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class StandardUnitClientRecordFacade {
	
	@Resource
	private StandardUnitClientRecordReadService standardUnitClientRecordReadService;
	
	@Resource
	private StandardUnitClientRecordWriteService standardUnitClientRecordWriteService;
	
	
	public StandardUnitClientRecordDTO findStandardUnitClientRecordById(StandardUnitClientRecordDTO dto){
		
		return standardUnitClientRecordReadService.findStandardUnitClientRecordById(dto);
	}

	public PageResult<StandardUnitClientRecordDTO> findStandardUnitClientRecordOfPage(StandardUnitClientRecordDTO dto,Pagination page){
		
		return standardUnitClientRecordReadService.findStandardUnitClientRecordOfPage(dto, page);
		
	}

	public List<StandardUnitClientRecordDTO> findStandardUnitClientRecordAll(StandardUnitClientRecordDTO dto){
		
		return standardUnitClientRecordReadService.findStandardUnitClientRecordAll(dto);
		
	}

	public Long insertStandardUnitClientRecordWithTx(StandardUnitClientRecordDTO dto){
		
		return standardUnitClientRecordWriteService.insertStandardUnitClientRecordWithTx(dto);
	}

	public int updateStandardUnitClientRecordWithTx(StandardUnitClientRecordDTO dto){
		
		return standardUnitClientRecordWriteService.updateStandardUnitClientRecordWithTx(dto);
	}

	public int deleteStandardUnitClientRecordWithTx(StandardUnitClientRecordDTO dto){
		
		return standardUnitClientRecordWriteService.deleteStandardUnitClientRecordWithTx(dto);
		
	}

}
	