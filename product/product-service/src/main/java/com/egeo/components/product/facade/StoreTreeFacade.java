package com.egeo.components.product.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.StoreTreeReadService;
import com.egeo.components.product.service.write.StoreTreeWriteService;
import com.egeo.components.product.dto.StoreTreeDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class StoreTreeFacade {
	
	@Resource
	private StoreTreeReadService storeTreeReadService;
	
	@Resource
	private StoreTreeWriteService storeTreeWriteService;
	
	
	public StoreTreeDTO findStoreTreeById(StoreTreeDTO dto){
		
		return storeTreeReadService.findStoreTreeById(dto);
	}

	public PageResult<StoreTreeDTO> findStoreTreeOfPage(StoreTreeDTO dto,Pagination page){
		
		return storeTreeReadService.findStoreTreeOfPage(dto, page);
		
	}

	public List<StoreTreeDTO> findStoreTreeAll(StoreTreeDTO dto){
		
		return storeTreeReadService.findStoreTreeAll(dto);
		
	}

	public Long insertStoreTreeWithTx(StoreTreeDTO dto){
		
		return storeTreeWriteService.insertStoreTreeWithTx(dto);
	}

	public int updateStoreTreeWithTx(StoreTreeDTO dto){
		
		return storeTreeWriteService.updateStoreTreeWithTx(dto);
	}

	public int deleteStoreTreeWithTx(StoreTreeDTO dto){
		
		return storeTreeWriteService.deleteStoreTreeWithTx(dto);
		
	}

}
	