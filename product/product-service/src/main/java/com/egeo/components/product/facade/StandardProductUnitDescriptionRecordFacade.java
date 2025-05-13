package com.egeo.components.product.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.StandardProductUnitDescriptionRecordReadService;
import com.egeo.components.product.service.write.StandardProductUnitDescriptionRecordWriteService;
import com.egeo.components.product.dto.StandardProductUnitDescriptionRecordDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class StandardProductUnitDescriptionRecordFacade {
	
	@Resource
	private StandardProductUnitDescriptionRecordReadService standardProductUnitDescriptionRecordReadService;
	
	@Resource
	private StandardProductUnitDescriptionRecordWriteService standardProductUnitDescriptionRecordWriteService;
	
	
	public StandardProductUnitDescriptionRecordDTO findStandardProductUnitDescriptionRecordById(StandardProductUnitDescriptionRecordDTO dto){
		
		return standardProductUnitDescriptionRecordReadService.findStandardProductUnitDescriptionRecordById(dto);
	}

	public PageResult<StandardProductUnitDescriptionRecordDTO> findStandardProductUnitDescriptionRecordOfPage(StandardProductUnitDescriptionRecordDTO dto,Pagination page){
		
		return standardProductUnitDescriptionRecordReadService.findStandardProductUnitDescriptionRecordOfPage(dto, page);
		
	}

	public List<StandardProductUnitDescriptionRecordDTO> findStandardProductUnitDescriptionRecordAll(StandardProductUnitDescriptionRecordDTO dto){
		
		return standardProductUnitDescriptionRecordReadService.findStandardProductUnitDescriptionRecordAll(dto);
		
	}

	public Long insertStandardProductUnitDescriptionRecordWithTx(StandardProductUnitDescriptionRecordDTO dto){
		
		return standardProductUnitDescriptionRecordWriteService.insertStandardProductUnitDescriptionRecordWithTx(dto);
	}

	public int updateStandardProductUnitDescriptionRecordWithTx(StandardProductUnitDescriptionRecordDTO dto){
		
		return standardProductUnitDescriptionRecordWriteService.updateStandardProductUnitDescriptionRecordWithTx(dto);
	}

	public int deleteStandardProductUnitDescriptionRecordWithTx(StandardProductUnitDescriptionRecordDTO dto){
		
		return standardProductUnitDescriptionRecordWriteService.deleteStandardProductUnitDescriptionRecordWithTx(dto);
		
	}

}
	