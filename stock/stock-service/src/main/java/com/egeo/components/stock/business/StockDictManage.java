package com.egeo.components.stock.business;

import java.util.List;
import java.util.Map;

import com.egeo.components.stock.dto.StockDictDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface StockDictManage {

	public StockDictDTO findStockDictById(StockDictDTO dto);	

	public PageResult<StockDictDTO> findStockDictOfPage(StockDictDTO dto,Pagination page);

	public List<Map<String, Object>> findStockDictAll(StockDictDTO dto);

	Long insertStockDictWithTx(StockDictDTO dto);

	int updateStockDictWithTx(StockDictDTO dto);

	int deleteStockDictWithTx(StockDictDTO dto);
}
	