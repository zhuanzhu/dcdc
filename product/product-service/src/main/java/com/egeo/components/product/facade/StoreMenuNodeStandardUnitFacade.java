package com.egeo.components.product.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.StoreMenuNodeStandardUnitReadService;
import com.egeo.components.product.service.write.StoreMenuNodeStandardUnitWriteService;
import com.egeo.components.product.dto.StoreMenuNodeStandardUnitDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class StoreMenuNodeStandardUnitFacade {
	
	@Resource
	private StoreMenuNodeStandardUnitReadService storeMenuNodeStandardUnitReadService;
	
	@Resource
	private StoreMenuNodeStandardUnitWriteService storeMenuNodeStandardUnitWriteService;
	
	
	public StoreMenuNodeStandardUnitDTO findStoreMenuNodeStandardUnitById(StoreMenuNodeStandardUnitDTO dto){
		
		return storeMenuNodeStandardUnitReadService.findStoreMenuNodeStandardUnitById(dto);
	}

	public PageResult<StoreMenuNodeStandardUnitDTO> findStoreMenuNodeStandardUnitOfPage(StoreMenuNodeStandardUnitDTO dto,Pagination page){
		
		return storeMenuNodeStandardUnitReadService.findStoreMenuNodeStandardUnitOfPage(dto, page);
		
	}

	public List<StoreMenuNodeStandardUnitDTO> findStoreMenuNodeStandardUnitAll(StoreMenuNodeStandardUnitDTO dto){
		
		return storeMenuNodeStandardUnitReadService.findStoreMenuNodeStandardUnitAll(dto);
		
	}

	public Long insertStoreMenuNodeStandardUnitWithTx(StoreMenuNodeStandardUnitDTO dto){
		
		return storeMenuNodeStandardUnitWriteService.insertStoreMenuNodeStandardUnitWithTx(dto);
	}

	public int updateStoreMenuNodeStandardUnitWithTx(StoreMenuNodeStandardUnitDTO dto){
		
		return storeMenuNodeStandardUnitWriteService.updateStoreMenuNodeStandardUnitWithTx(dto);
	}

	public int deleteStoreMenuNodeStandardUnitWithTx(StoreMenuNodeStandardUnitDTO dto){
		
		return storeMenuNodeStandardUnitWriteService.deleteStoreMenuNodeStandardUnitWithTx(dto);
		
	}

	public int insertAllWithTx(Long storeMenuNodeId,List<Long> standardUnitIds, Long platformId) {
		return storeMenuNodeStandardUnitWriteService.insertAllWithTx(storeMenuNodeId,standardUnitIds, platformId);
	}

}
	