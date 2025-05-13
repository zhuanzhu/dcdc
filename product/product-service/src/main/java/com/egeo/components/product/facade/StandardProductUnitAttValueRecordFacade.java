package com.egeo.components.product.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.StandardProductUnitAttValueRecordReadService;
import com.egeo.components.product.service.write.StandardProductUnitAttValueRecordWriteService;
import com.egeo.components.product.dto.StandardProductUnitAttValueRecordDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class StandardProductUnitAttValueRecordFacade {
	
	@Resource
	private StandardProductUnitAttValueRecordReadService standardProductUnitAttValueRecordReadService;
	
	@Resource
	private StandardProductUnitAttValueRecordWriteService standardProductUnitAttValueRecordWriteService;
	
	
	public StandardProductUnitAttValueRecordDTO findStandardProductUnitAttValueRecordById(StandardProductUnitAttValueRecordDTO dto){
		
		return standardProductUnitAttValueRecordReadService.findStandardProductUnitAttValueRecordById(dto);
	}

	public PageResult<StandardProductUnitAttValueRecordDTO> findStandardProductUnitAttValueRecordOfPage(StandardProductUnitAttValueRecordDTO dto,Pagination page){
		
		return standardProductUnitAttValueRecordReadService.findStandardProductUnitAttValueRecordOfPage(dto, page);
		
	}

	public List<StandardProductUnitAttValueRecordDTO> findStandardProductUnitAttValueRecordAll(StandardProductUnitAttValueRecordDTO dto){
		
		return standardProductUnitAttValueRecordReadService.findStandardProductUnitAttValueRecordAll(dto);
		
	}

	public Long insertStandardProductUnitAttValueRecordWithTx(StandardProductUnitAttValueRecordDTO dto){
		
		return standardProductUnitAttValueRecordWriteService.insertStandardProductUnitAttValueRecordWithTx(dto);
	}

	public int updateStandardProductUnitAttValueRecordWithTx(StandardProductUnitAttValueRecordDTO dto){
		
		return standardProductUnitAttValueRecordWriteService.updateStandardProductUnitAttValueRecordWithTx(dto);
	}

	public int deleteStandardProductUnitAttValueRecordWithTx(StandardProductUnitAttValueRecordDTO dto){
		
		return standardProductUnitAttValueRecordWriteService.deleteStandardProductUnitAttValueRecordWithTx(dto);
		
	}

}
	