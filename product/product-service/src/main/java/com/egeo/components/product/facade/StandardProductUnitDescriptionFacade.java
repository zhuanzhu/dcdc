package com.egeo.components.product.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.StandardProductUnitDescriptionReadService;
import com.egeo.components.product.service.write.StandardProductUnitDescriptionWriteService;
import com.egeo.components.product.dto.StandardProductUnitDescriptionDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class StandardProductUnitDescriptionFacade {
	
	@Resource
	private StandardProductUnitDescriptionReadService standardProductUnitDescriptionReadService;
	
	@Resource
	private StandardProductUnitDescriptionWriteService standardProductUnitDescriptionWriteService;
	
	
	public StandardProductUnitDescriptionDTO findStandardProductUnitDescriptionById(StandardProductUnitDescriptionDTO dto){
		
		return standardProductUnitDescriptionReadService.findStandardProductUnitDescriptionById(dto);
	}

	public PageResult<StandardProductUnitDescriptionDTO> findStandardProductUnitDescriptionOfPage(StandardProductUnitDescriptionDTO dto,Pagination page){
		
		return standardProductUnitDescriptionReadService.findStandardProductUnitDescriptionOfPage(dto, page);
		
	}

	public List<StandardProductUnitDescriptionDTO> findStandardProductUnitDescriptionAll(StandardProductUnitDescriptionDTO dto){
		
		return standardProductUnitDescriptionReadService.findStandardProductUnitDescriptionAll(dto);
		
	}

	public Long insertStandardProductUnitDescriptionWithTx(StandardProductUnitDescriptionDTO dto){
		
		return standardProductUnitDescriptionWriteService.insertStandardProductUnitDescriptionWithTx(dto);
	}

	public int updateStandardProductUnitDescriptionWithTx(StandardProductUnitDescriptionDTO dto){
		
		return standardProductUnitDescriptionWriteService.updateStandardProductUnitDescriptionWithTx(dto);
	}

	public int deleteStandardProductUnitDescriptionWithTx(StandardProductUnitDescriptionDTO dto){
		
		return standardProductUnitDescriptionWriteService.deleteStandardProductUnitDescriptionWithTx(dto);
		
	}

}
	