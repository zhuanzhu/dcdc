package com.egeo.components.stock.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.stock.service.read.StockDictReadService;
import com.egeo.components.stock.service.write.StockDictWriteService;
import com.egeo.components.stock.dto.StockDictDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class StockDictFacade {
	
	@Resource
	private StockDictReadService stockDictReadService;
	
	@Resource
	private StockDictWriteService stockDictWriteService;
	
	
	public StockDictDTO findStockDictById(StockDictDTO dto){
		
		return stockDictReadService.findStockDictById(dto);
	}

	public PageResult<StockDictDTO> findStockDictOfPage(StockDictDTO dto,Pagination page){
		
		return stockDictReadService.findStockDictOfPage(dto, page);
		
	}

	public List<StockDictDTO> findStockDictAll(StockDictDTO dto){
		
		return stockDictReadService.findStockDictAll(dto);
		
	}

	public Long insertStockDictWithTx(StockDictDTO dto){
		
		return stockDictWriteService.insertStockDictWithTx(dto);
	}

	public int updateStockDictWithTx(StockDictDTO dto){
		
		return stockDictWriteService.updateStockDictWithTx(dto);
	}

	public int deleteStockDictWithTx(StockDictDTO dto){
		
		return stockDictWriteService.deleteStockDictWithTx(dto);
		
	}

}
	