package com.egeo.components.stock.service.read;


import java.util.List;
	
import com.egeo.components.stock.dto.StockDictDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface StockDictReadService {

	public StockDictDTO findStockDictById(StockDictDTO dto);

	public PageResult<StockDictDTO> findStockDictOfPage(StockDictDTO dto,Pagination page);

	public List<StockDictDTO> findStockDictAll(StockDictDTO dto);
}
	