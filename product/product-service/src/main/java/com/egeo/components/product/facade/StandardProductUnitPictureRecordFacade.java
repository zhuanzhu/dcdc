package com.egeo.components.product.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.StandardProductUnitPictureRecordReadService;
import com.egeo.components.product.service.write.StandardProductUnitPictureRecordWriteService;
import com.egeo.components.product.dto.StandardProductUnitPictureRecordDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class StandardProductUnitPictureRecordFacade {
	
	@Resource
	private StandardProductUnitPictureRecordReadService standardProductUnitPictureRecordReadService;
	
	@Resource
	private StandardProductUnitPictureRecordWriteService standardProductUnitPictureRecordWriteService;
	
	
	public StandardProductUnitPictureRecordDTO findStandardProductUnitPictureRecordById(StandardProductUnitPictureRecordDTO dto){
		
		return standardProductUnitPictureRecordReadService.findStandardProductUnitPictureRecordById(dto);
	}

	public PageResult<StandardProductUnitPictureRecordDTO> findStandardProductUnitPictureRecordOfPage(StandardProductUnitPictureRecordDTO dto,Pagination page){
		
		return standardProductUnitPictureRecordReadService.findStandardProductUnitPictureRecordOfPage(dto, page);
		
	}

	public List<StandardProductUnitPictureRecordDTO> findStandardProductUnitPictureRecordAll(StandardProductUnitPictureRecordDTO dto){
		
		return standardProductUnitPictureRecordReadService.findStandardProductUnitPictureRecordAll(dto);
		
	}

	public Long insertStandardProductUnitPictureRecordWithTx(StandardProductUnitPictureRecordDTO dto){
		
		return standardProductUnitPictureRecordWriteService.insertStandardProductUnitPictureRecordWithTx(dto);
	}

	public int updateStandardProductUnitPictureRecordWithTx(StandardProductUnitPictureRecordDTO dto){
		
		return standardProductUnitPictureRecordWriteService.updateStandardProductUnitPictureRecordWithTx(dto);
	}

	public int deleteStandardProductUnitPictureRecordWithTx(StandardProductUnitPictureRecordDTO dto){
		
		return standardProductUnitPictureRecordWriteService.deleteStandardProductUnitPictureRecordWithTx(dto);
		
	}

}
	