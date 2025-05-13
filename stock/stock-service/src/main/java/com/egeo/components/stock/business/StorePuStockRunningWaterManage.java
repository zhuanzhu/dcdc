package com.egeo.components.stock.business;

import java.util.List;
	
import com.egeo.components.stock.dto.StorePuStockRunningWaterDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface StorePuStockRunningWaterManage {

	public StorePuStockRunningWaterDTO findStorePuStockRunningWaterById(StorePuStockRunningWaterDTO dto);	

	public PageResult<StorePuStockRunningWaterDTO> findStorePuStockRunningWaterOfPage(StorePuStockRunningWaterDTO dto, Long storeId, Pagination page);

	public List<StorePuStockRunningWaterDTO> findStorePuStockRunningWaterAll(StorePuStockRunningWaterDTO dto);

	Long insertStorePuStockRunningWaterWithTx(StorePuStockRunningWaterDTO dto);

	int updateStorePuStockRunningWaterWithTx(StorePuStockRunningWaterDTO dto);

	int deleteStorePuStockRunningWaterWithTx(StorePuStockRunningWaterDTO dto);
}
	