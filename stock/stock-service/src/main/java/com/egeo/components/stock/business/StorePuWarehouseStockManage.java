package com.egeo.components.stock.business;

import java.util.List;
	
import com.egeo.components.stock.dto.StorePuWarehouseStockDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface StorePuWarehouseStockManage {

	public StorePuWarehouseStockDTO findStorePuWarehouseStockById(StorePuWarehouseStockDTO dto);	

	public PageResult<StorePuWarehouseStockDTO> findStorePuWarehouseStockOfPage(StorePuWarehouseStockDTO dto,Pagination page);

	public List<StorePuWarehouseStockDTO> findStorePuWarehouseStockAll(StorePuWarehouseStockDTO dto);

	Long insertStorePuWarehouseStockWithTx(StorePuWarehouseStockDTO dto);

	int updateStorePuWarehouseStockWithTx(StorePuWarehouseStockDTO dto);

	int deleteStorePuWarehouseStockWithTx(StorePuWarehouseStockDTO dto);
}
	