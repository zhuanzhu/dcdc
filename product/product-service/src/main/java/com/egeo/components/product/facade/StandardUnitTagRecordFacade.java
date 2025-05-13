package com.egeo.components.product.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.StandardUnitTagRecordReadService;
import com.egeo.components.product.service.write.StandardUnitTagRecordWriteService;
import com.egeo.components.product.dto.StandardUnitTagRecordDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class StandardUnitTagRecordFacade {
	
	@Resource
	private StandardUnitTagRecordReadService standardUnitTagRecordReadService;
	
	@Resource
	private StandardUnitTagRecordWriteService standardUnitTagRecordWriteService;
	
	
	public StandardUnitTagRecordDTO findStandardUnitTagRecordById(StandardUnitTagRecordDTO dto){
		
		return standardUnitTagRecordReadService.findStandardUnitTagRecordById(dto);
	}

	public PageResult<StandardUnitTagRecordDTO> findStandardUnitTagRecordOfPage(StandardUnitTagRecordDTO dto,Pagination page){
		
		return standardUnitTagRecordReadService.findStandardUnitTagRecordOfPage(dto, page);
		
	}

	public List<StandardUnitTagRecordDTO> findStandardUnitTagRecordAll(StandardUnitTagRecordDTO dto){
		
		return standardUnitTagRecordReadService.findStandardUnitTagRecordAll(dto);
		
	}

	public Long insertStandardUnitTagRecordWithTx(StandardUnitTagRecordDTO dto){
		
		return standardUnitTagRecordWriteService.insertStandardUnitTagRecordWithTx(dto);
	}

	public int updateStandardUnitTagRecordWithTx(StandardUnitTagRecordDTO dto){
		
		return standardUnitTagRecordWriteService.updateStandardUnitTagRecordWithTx(dto);
	}

	public int deleteStandardUnitTagRecordWithTx(StandardUnitTagRecordDTO dto){
		
		return standardUnitTagRecordWriteService.deleteStandardUnitTagRecordWithTx(dto);
		
	}

}
	