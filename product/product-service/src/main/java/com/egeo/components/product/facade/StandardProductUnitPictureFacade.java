package com.egeo.components.product.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.StandardProductUnitPictureReadService;
import com.egeo.components.product.service.write.StandardProductUnitPictureWriteService;
import com.egeo.components.product.dto.StandardProductUnitPictureDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class StandardProductUnitPictureFacade {
	
	@Resource
	private StandardProductUnitPictureReadService standardProductUnitPictureReadService;
	
	@Resource
	private StandardProductUnitPictureWriteService standardProductUnitPictureWriteService;
	
	
	public StandardProductUnitPictureDTO findStandardProductUnitPictureById(StandardProductUnitPictureDTO dto){
		
		return standardProductUnitPictureReadService.findStandardProductUnitPictureById(dto);
	}

	public PageResult<StandardProductUnitPictureDTO> findStandardProductUnitPictureOfPage(StandardProductUnitPictureDTO dto,Pagination page){
		
		return standardProductUnitPictureReadService.findStandardProductUnitPictureOfPage(dto, page);
		
	}

	public List<StandardProductUnitPictureDTO> findStandardProductUnitPictureAll(StandardProductUnitPictureDTO dto){
		
		return standardProductUnitPictureReadService.findStandardProductUnitPictureAll(dto);
		
	}

	public Long insertStandardProductUnitPictureWithTx(StandardProductUnitPictureDTO dto){
		
		return standardProductUnitPictureWriteService.insertStandardProductUnitPictureWithTx(dto);
	}

	public int updateStandardProductUnitPictureWithTx(StandardProductUnitPictureDTO dto){
		
		return standardProductUnitPictureWriteService.updateStandardProductUnitPictureWithTx(dto);
	}

	public int deleteStandardProductUnitPictureWithTx(StandardProductUnitPictureDTO dto){
		
		return standardProductUnitPictureWriteService.deleteStandardProductUnitPictureWithTx(dto);
		
	}

}
	