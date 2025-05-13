package com.egeo.components.product.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.StandardProductUnitAttNameRecordReadService;
import com.egeo.components.product.service.write.StandardProductUnitAttNameRecordWriteService;
import com.egeo.components.product.dto.StandardProductUnitAttNameRecordDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class StandardProductUnitAttNameRecordFacade {
	
	@Resource
	private StandardProductUnitAttNameRecordReadService standardProductUnitAttNameRecordReadService;
	
	@Resource
	private StandardProductUnitAttNameRecordWriteService standardProductUnitAttNameRecordWriteService;
	
	
	public StandardProductUnitAttNameRecordDTO findStandardProductUnitAttNameRecordById(StandardProductUnitAttNameRecordDTO dto){
		
		return standardProductUnitAttNameRecordReadService.findStandardProductUnitAttNameRecordById(dto);
	}

	public PageResult<StandardProductUnitAttNameRecordDTO> findStandardProductUnitAttNameRecordOfPage(StandardProductUnitAttNameRecordDTO dto,Pagination page){
		
		return standardProductUnitAttNameRecordReadService.findStandardProductUnitAttNameRecordOfPage(dto, page);
		
	}

	public List<StandardProductUnitAttNameRecordDTO> findStandardProductUnitAttNameRecordAll(StandardProductUnitAttNameRecordDTO dto){
		
		return standardProductUnitAttNameRecordReadService.findStandardProductUnitAttNameRecordAll(dto);
		
	}

	public Long insertStandardProductUnitAttNameRecordWithTx(StandardProductUnitAttNameRecordDTO dto){
		
		return standardProductUnitAttNameRecordWriteService.insertStandardProductUnitAttNameRecordWithTx(dto);
	}

	public int updateStandardProductUnitAttNameRecordWithTx(StandardProductUnitAttNameRecordDTO dto){
		
		return standardProductUnitAttNameRecordWriteService.updateStandardProductUnitAttNameRecordWithTx(dto);
	}

	public int deleteStandardProductUnitAttNameRecordWithTx(StandardProductUnitAttNameRecordDTO dto){
		
		return standardProductUnitAttNameRecordWriteService.deleteStandardProductUnitAttNameRecordWithTx(dto);
		
	}

}
	