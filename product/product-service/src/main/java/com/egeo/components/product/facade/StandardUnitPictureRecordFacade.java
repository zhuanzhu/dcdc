package com.egeo.components.product.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.StandardUnitPictureRecordReadService;
import com.egeo.components.product.service.write.StandardUnitPictureRecordWriteService;
import com.egeo.components.product.dto.StandardUnitPictureRecordDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class StandardUnitPictureRecordFacade {
	
	@Resource
	private StandardUnitPictureRecordReadService standardUnitPictureRecordReadService;
	
	@Resource
	private StandardUnitPictureRecordWriteService standardUnitPictureRecordWriteService;
	
	
	public StandardUnitPictureRecordDTO findStandardUnitPictureRecordById(StandardUnitPictureRecordDTO dto){
		
		return standardUnitPictureRecordReadService.findStandardUnitPictureRecordById(dto);
	}

	public PageResult<StandardUnitPictureRecordDTO> findStandardUnitPictureRecordOfPage(StandardUnitPictureRecordDTO dto,Pagination page){
		
		return standardUnitPictureRecordReadService.findStandardUnitPictureRecordOfPage(dto, page);
		
	}

	public List<StandardUnitPictureRecordDTO> findStandardUnitPictureRecordAll(StandardUnitPictureRecordDTO dto){
		
		return standardUnitPictureRecordReadService.findStandardUnitPictureRecordAll(dto);
		
	}

	public Long insertStandardUnitPictureRecordWithTx(StandardUnitPictureRecordDTO dto){
		
		return standardUnitPictureRecordWriteService.insertStandardUnitPictureRecordWithTx(dto);
	}

	public int updateStandardUnitPictureRecordWithTx(StandardUnitPictureRecordDTO dto){
		
		return standardUnitPictureRecordWriteService.updateStandardUnitPictureRecordWithTx(dto);
	}

	public int deleteStandardUnitPictureRecordWithTx(StandardUnitPictureRecordDTO dto){
		
		return standardUnitPictureRecordWriteService.deleteStandardUnitPictureRecordWithTx(dto);
		
	}

}
	