package com.egeo.components.product.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.StoreMenuTreeReadService;
import com.egeo.components.product.service.write.StoreMenuTreeWriteService;
import com.egeo.components.product.dto.StoreMenuTreeDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class StoreMenuTreeFacade {
	
	@Resource
	private StoreMenuTreeReadService storeMenuTreeReadService;
	
	@Resource
	private StoreMenuTreeWriteService storeMenuTreeWriteService;
	
	
	public StoreMenuTreeDTO findStoreMenuTreeById(StoreMenuTreeDTO dto){
		
		return storeMenuTreeReadService.findStoreMenuTreeById(dto);
	}

	public PageResult<StoreMenuTreeDTO> findStoreMenuTreeOfPage(StoreMenuTreeDTO dto,Pagination page){
		
		return storeMenuTreeReadService.findStoreMenuTreeOfPage(dto, page);
		
	}

	public List<StoreMenuTreeDTO> findStoreMenuTreeAll(StoreMenuTreeDTO dto){
		
		return storeMenuTreeReadService.findStoreMenuTreeAll(dto);
		
	}

	public Long insertStoreMenuTreeWithTx(StoreMenuTreeDTO dto){
		
		return storeMenuTreeWriteService.insertStoreMenuTreeWithTx(dto);
	}

	public int updateStoreMenuTreeWithTx(StoreMenuTreeDTO dto){
		
		return storeMenuTreeWriteService.updateStoreMenuTreeWithTx(dto);
	}

	public int deleteStoreMenuTreeWithTx(StoreMenuTreeDTO dto){
		
		return storeMenuTreeWriteService.deleteStoreMenuTreeWithTx(dto);
		
	}

}
	