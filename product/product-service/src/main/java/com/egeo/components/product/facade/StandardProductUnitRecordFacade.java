package com.egeo.components.product.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.StandardProductUnitRecordReadService;
import com.egeo.components.product.service.write.StandardProductUnitRecordWriteService;
import com.egeo.components.product.dto.StandardProductUnitRecordDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class StandardProductUnitRecordFacade {
	
	@Resource
	private StandardProductUnitRecordReadService standardProductUnitRecordReadService;
	
	@Resource
	private StandardProductUnitRecordWriteService standardProductUnitRecordWriteService;
	
	
	public StandardProductUnitRecordDTO findStandardProductUnitRecordById(StandardProductUnitRecordDTO dto){
		
		return standardProductUnitRecordReadService.findStandardProductUnitRecordById(dto);
	}

	public PageResult<StandardProductUnitRecordDTO> findStandardProductUnitRecordOfPage(StandardProductUnitRecordDTO dto,Pagination page){
		
		return standardProductUnitRecordReadService.findStandardProductUnitRecordOfPage(dto, page);
		
	}

	public List<StandardProductUnitRecordDTO> findStandardProductUnitRecordAll(StandardProductUnitRecordDTO dto){
		
		return standardProductUnitRecordReadService.findStandardProductUnitRecordAll(dto);
		
	}

	public Long insertStandardProductUnitRecordWithTx(StandardProductUnitRecordDTO dto){
		
		return standardProductUnitRecordWriteService.insertStandardProductUnitRecordWithTx(dto);
	}

	public int updateStandardProductUnitRecordWithTx(StandardProductUnitRecordDTO dto){
		
		return standardProductUnitRecordWriteService.updateStandardProductUnitRecordWithTx(dto);
	}

	public int deleteStandardProductUnitRecordWithTx(StandardProductUnitRecordDTO dto){
		
		return standardProductUnitRecordWriteService.deleteStandardProductUnitRecordWithTx(dto);
		
	}

}
	