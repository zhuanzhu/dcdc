package com.egeo.components.product.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.StandardUnitRecordStoreReadService;
import com.egeo.components.product.service.write.StandardUnitRecordStoreWriteService;
import com.egeo.components.product.dto.StandardUnitRecordStoreDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class StandardUnitRecordStoreFacade {
	
	@Resource
	private StandardUnitRecordStoreReadService standardUnitRecordStoreReadService;
	
	@Resource
	private StandardUnitRecordStoreWriteService standardUnitRecordStoreWriteService;
	
	
	public StandardUnitRecordStoreDTO findStandardUnitRecordStoreById(StandardUnitRecordStoreDTO dto){
		
		return standardUnitRecordStoreReadService.findStandardUnitRecordStoreById(dto);
	}

	public PageResult<StandardUnitRecordStoreDTO> findStandardUnitRecordStoreOfPage(StandardUnitRecordStoreDTO dto,Pagination page){
		
		return standardUnitRecordStoreReadService.findStandardUnitRecordStoreOfPage(dto, page);
		
	}

	public List<StandardUnitRecordStoreDTO> findStandardUnitRecordStoreAll(StandardUnitRecordStoreDTO dto){
		
		return standardUnitRecordStoreReadService.findStandardUnitRecordStoreAll(dto);
		
	}

	public Long insertStandardUnitRecordStoreWithTx(StandardUnitRecordStoreDTO dto){
		
		return standardUnitRecordStoreWriteService.insertStandardUnitRecordStoreWithTx(dto);
	}

	public int updateStandardUnitRecordStoreWithTx(StandardUnitRecordStoreDTO dto){
		
		return standardUnitRecordStoreWriteService.updateStandardUnitRecordStoreWithTx(dto);
	}

	public int deleteStandardUnitRecordStoreWithTx(StandardUnitRecordStoreDTO dto){
		
		return standardUnitRecordStoreWriteService.deleteStandardUnitRecordStoreWithTx(dto);
		
	}

}
	