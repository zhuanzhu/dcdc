package com.egeo.components.stock.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.stock.service.read.StorePuWarehouseStockReadService;
import com.egeo.components.stock.service.write.StorePuWarehouseStockWriteService;
import com.egeo.components.stock.dto.StorePuWarehouseStockDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class StorePuWarehouseStockFacade {
	
	@Resource
	private StorePuWarehouseStockReadService storePuWarehouseStockReadService;
	
	@Resource
	private StorePuWarehouseStockWriteService storePuWarehouseStockWriteService;
	
	
	public StorePuWarehouseStockDTO findStorePuWarehouseStockById(StorePuWarehouseStockDTO dto){
		
		return storePuWarehouseStockReadService.findStorePuWarehouseStockById(dto);
	}

	public PageResult<StorePuWarehouseStockDTO> findStorePuWarehouseStockOfPage(StorePuWarehouseStockDTO dto,Pagination page){
		
		return storePuWarehouseStockReadService.findStorePuWarehouseStockOfPage(dto, page);
		
	}

	public List<StorePuWarehouseStockDTO> findStorePuWarehouseStockAll(StorePuWarehouseStockDTO dto){
		
		return storePuWarehouseStockReadService.findStorePuWarehouseStockAll(dto);
		
	}

	public Long insertStorePuWarehouseStockWithTx(StorePuWarehouseStockDTO dto){
		
		return storePuWarehouseStockWriteService.insertStorePuWarehouseStockWithTx(dto);
	}

	public int updateStorePuWarehouseStockWithTx(StorePuWarehouseStockDTO dto){
		
		return storePuWarehouseStockWriteService.updateStorePuWarehouseStockWithTx(dto);
	}

	public int deleteStorePuWarehouseStockWithTx(StorePuWarehouseStockDTO dto){
		
		return storePuWarehouseStockWriteService.deleteStorePuWarehouseStockWithTx(dto);
		
	}

}
	