package com.egeo.components.product.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.StandardUnitTagReadService;
import com.egeo.components.product.service.write.StandardUnitTagWriteService;
import com.egeo.components.product.dto.StandardUnitTagDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class StandardUnitTagFacade {
	
	@Resource
	private StandardUnitTagReadService standardUnitTagReadService;
	
	@Resource
	private StandardUnitTagWriteService standardUnitTagWriteService;
	
	
	public StandardUnitTagDTO findStandardUnitTagById(StandardUnitTagDTO dto){
		
		return standardUnitTagReadService.findStandardUnitTagById(dto);
	}

	public PageResult<StandardUnitTagDTO> findStandardUnitTagOfPage(StandardUnitTagDTO dto,Pagination page){
		
		return standardUnitTagReadService.findStandardUnitTagOfPage(dto, page);
		
	}

	public List<StandardUnitTagDTO> findStandardUnitTagAll(StandardUnitTagDTO dto){
		
		return standardUnitTagReadService.findStandardUnitTagAll(dto);
		
	}

	public Long insertStandardUnitTagWithTx(StandardUnitTagDTO dto){
		
		return standardUnitTagWriteService.insertStandardUnitTagWithTx(dto);
	}

	public int updateStandardUnitTagWithTx(StandardUnitTagDTO dto){
		
		return standardUnitTagWriteService.updateStandardUnitTagWithTx(dto);
	}

	public int deleteStandardUnitTagWithTx(StandardUnitTagDTO dto){
		
		return standardUnitTagWriteService.deleteStandardUnitTagWithTx(dto);
		
	}

}
	