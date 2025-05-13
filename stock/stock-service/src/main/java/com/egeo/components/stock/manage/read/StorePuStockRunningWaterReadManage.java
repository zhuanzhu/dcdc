package com.egeo.components.stock.manage.read;

import java.util.List;

import com.egeo.components.stock.condition.StorePuStockRunningWaterCondition;
import com.egeo.components.stock.po.StorePuStockRunningWaterPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface StorePuStockRunningWaterReadManage {

	public StorePuStockRunningWaterPO findStorePuStockRunningWaterById(StorePuStockRunningWaterPO po);

	public PageResult<StorePuStockRunningWaterCondition> findStorePuStockRunningWaterOfPage(StorePuStockRunningWaterPO po,Pagination page);

	public List<StorePuStockRunningWaterPO> findStorePuStockRunningWaterAll(StorePuStockRunningWaterPO po);
}
	