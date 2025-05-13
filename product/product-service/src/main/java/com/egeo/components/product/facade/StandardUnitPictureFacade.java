package com.egeo.components.product.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.StandardUnitPictureReadService;
import com.egeo.components.product.service.write.StandardUnitPictureWriteService;
import com.egeo.components.product.dto.StandardUnitPictureDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class StandardUnitPictureFacade {
	
	@Resource
	private StandardUnitPictureReadService standardUnitPictureReadService;
	
	@Resource
	private StandardUnitPictureWriteService standardUnitPictureWriteService;
	
	
	public StandardUnitPictureDTO findStandardUnitPictureById(StandardUnitPictureDTO dto){
		
		return standardUnitPictureReadService.findStandardUnitPictureById(dto);
	}

	public PageResult<StandardUnitPictureDTO> findStandardUnitPictureOfPage(StandardUnitPictureDTO dto,Pagination page){
		
		return standardUnitPictureReadService.findStandardUnitPictureOfPage(dto, page);
		
	}

	public List<StandardUnitPictureDTO> findStandardUnitPictureAll(StandardUnitPictureDTO dto){
		
		return standardUnitPictureReadService.findStandardUnitPictureAll(dto);
		
	}

	public Long insertStandardUnitPictureWithTx(StandardUnitPictureDTO dto){
		
		return standardUnitPictureWriteService.insertStandardUnitPictureWithTx(dto);
	}

	public int updateStandardUnitPictureWithTx(StandardUnitPictureDTO dto){
		
		return standardUnitPictureWriteService.updateStandardUnitPictureWithTx(dto);
	}

	public int deleteStandardUnitPictureWithTx(StandardUnitPictureDTO dto){
		
		return standardUnitPictureWriteService.deleteStandardUnitPictureWithTx(dto);
		
	}
	/**
	 * 根据suid删除su图片关系表
	 * @param merchantProdId
	 * @return
	 */
	public int deleteByStandardUnitIdWithTx(Long merchantProdId) {
		// TODO Auto-generated method stub
		return standardUnitPictureWriteService.deleteByStandardUnitIdWithTx(merchantProdId);
	}

}
	