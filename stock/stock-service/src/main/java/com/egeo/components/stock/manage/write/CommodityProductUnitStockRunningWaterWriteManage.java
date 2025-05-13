package com.egeo.components.stock.manage.write;

import java.util.List;

import com.egeo.components.stock.po.CommodityProductUnitStockRunningWaterPO;


public interface CommodityProductUnitStockRunningWaterWriteManage {

	Long insertCommodityProductUnitStockRunningWaterWithTx(CommodityProductUnitStockRunningWaterPO po);

	int updateCommodityProductUnitStockRunningWaterWithTx(CommodityProductUnitStockRunningWaterPO po);

	int deleteCommodityProductUnitStockRunningWaterWithTx(CommodityProductUnitStockRunningWaterPO po);

	/**
	 * 批量生成流水
	 * @param pos
	 * @return
	 */
	int insertBatchCommodityProductUnitStockRunningWaterWithTx(List<CommodityProductUnitStockRunningWaterPO> pos);

}
	