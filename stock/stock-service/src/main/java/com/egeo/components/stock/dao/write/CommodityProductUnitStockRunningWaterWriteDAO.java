package com.egeo.components.stock.dao.write;

import java.util.List;

import com.egeo.components.stock.po.CommodityProductUnitStockRunningWaterPO;
import com.egeo.orm.BaseWriteDAO;

public interface CommodityProductUnitStockRunningWaterWriteDAO extends BaseWriteDAO<CommodityProductUnitStockRunningWaterPO> {
	
	public int insertBatchCommodityProductUnitStockRunningWaterWithTx(List<CommodityProductUnitStockRunningWaterPO> list);

}
	