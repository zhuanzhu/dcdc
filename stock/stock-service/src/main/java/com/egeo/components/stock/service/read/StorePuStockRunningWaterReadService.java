package com.egeo.components.stock.service.read;


import java.util.List;
	
import com.egeo.components.stock.dto.StorePuStockRunningWaterDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface StorePuStockRunningWaterReadService {

	public StorePuStockRunningWaterDTO findStorePuStockRunningWaterById(StorePuStockRunningWaterDTO dto);

	public PageResult<StorePuStockRunningWaterDTO> findStorePuStockRunningWaterOfPage(StorePuStockRunningWaterDTO dto,Pagination page);

	public List<StorePuStockRunningWaterDTO> findStorePuStockRunningWaterAll(StorePuStockRunningWaterDTO dto);
}
	