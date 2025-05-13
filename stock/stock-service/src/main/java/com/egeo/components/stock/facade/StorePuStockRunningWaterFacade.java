package com.egeo.components.stock.facade;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.components.product.client.StoreClient;
import com.egeo.components.product.dto.StoreDTO;
import com.egeo.components.stock.dto.StorePuStockRunningWaterDTO;
import com.egeo.components.stock.service.read.StorePuStockRunningWaterReadService;
import com.egeo.components.stock.service.write.StorePuStockRunningWaterWriteService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;


@Component
public class StorePuStockRunningWaterFacade {
	
	@Resource
	private StorePuStockRunningWaterReadService storePuStockRunningWaterReadService;
	
	@Resource
	private StorePuStockRunningWaterWriteService storePuStockRunningWaterWriteService;
	
	@Autowired
	private StoreClient storeReadService;
	
	
	public StorePuStockRunningWaterDTO findStorePuStockRunningWaterById(StorePuStockRunningWaterDTO dto){
		
		return storePuStockRunningWaterReadService.findStorePuStockRunningWaterById(dto);
	}

	public PageResult<StorePuStockRunningWaterDTO> findStorePuStockRunningWaterOfPage(StorePuStockRunningWaterDTO dto, Long storeId,Pagination page){
		StoreDTO storeDTO = new StoreDTO();
		storeDTO.setId(storeId);
		StoreDTO findStoreDTO = storeReadService.findStoreById(storeDTO);
		PageResult<StorePuStockRunningWaterDTO> result = storePuStockRunningWaterReadService.findStorePuStockRunningWaterOfPage(dto, page);
		if (EmptyUtil.isNotEmpty(result.getList()) && EmptyUtil.isNotEmpty(findStoreDTO)) {
			for (StorePuStockRunningWaterDTO spWater : result.getList()) {
				spWater.setStoreName(findStoreDTO.getName());
			}
		}
		return result;
		
	}

	public List<StorePuStockRunningWaterDTO> findStorePuStockRunningWaterAll(StorePuStockRunningWaterDTO dto){
		
		return storePuStockRunningWaterReadService.findStorePuStockRunningWaterAll(dto);
		
	}

	public Long insertStorePuStockRunningWaterWithTx(StorePuStockRunningWaterDTO dto){
		
		return storePuStockRunningWaterWriteService.insertStorePuStockRunningWaterWithTx(dto);
	}

	public int updateStorePuStockRunningWaterWithTx(StorePuStockRunningWaterDTO dto){
		
		return storePuStockRunningWaterWriteService.updateStorePuStockRunningWaterWithTx(dto);
	}

	public int deleteStorePuStockRunningWaterWithTx(StorePuStockRunningWaterDTO dto){
		
		return storePuStockRunningWaterWriteService.deleteStorePuStockRunningWaterWithTx(dto);
		
	}

}
	