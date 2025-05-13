package com.egeo.components.product.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.StoreMenuNodeReadService;
import com.egeo.components.product.service.write.StoreMenuNodeWriteService;
import com.egeo.components.product.dto.StoreMenuNodeDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class StoreMenuNodeFacade {
	
	@Resource
	private StoreMenuNodeReadService storeMenuNodeReadService;
	
	@Resource
	private StoreMenuNodeWriteService storeMenuNodeWriteService;
	
	
	public StoreMenuNodeDTO findStoreMenuNodeById(StoreMenuNodeDTO dto){
		
		return storeMenuNodeReadService.findStoreMenuNodeById(dto);
	}

	public PageResult<StoreMenuNodeDTO> findStoreMenuNodeOfPage(StoreMenuNodeDTO dto,Pagination page){
		
		return storeMenuNodeReadService.findStoreMenuNodeOfPage(dto, page);
		
	}

	public List<StoreMenuNodeDTO> findStoreMenuNodeAll(StoreMenuNodeDTO dto){
		
		return storeMenuNodeReadService.findStoreMenuNodeAll(dto);
		
	}

	public Long insertStoreMenuNodeWithTx(StoreMenuNodeDTO dto){
		
		return storeMenuNodeWriteService.insertStoreMenuNodeWithTx(dto);
	}

	public int updateStoreMenuNodeWithTx(StoreMenuNodeDTO dto){
		
		return storeMenuNodeWriteService.updateStoreMenuNodeWithTx(dto);
	}

	public int deleteStoreMenuNodeWithTx(StoreMenuNodeDTO dto){
		
		return storeMenuNodeWriteService.deleteStoreMenuNodeWithTx(dto);
		
	}

}
	