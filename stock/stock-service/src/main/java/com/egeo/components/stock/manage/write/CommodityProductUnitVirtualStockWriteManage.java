package com.egeo.components.stock.manage.write;

import com.egeo.components.stock.po.CommodityProductUnitVirtualStockPO;


public interface CommodityProductUnitVirtualStockWriteManage {

	Long insertCommodityProductUnitVirtualStockWithTx(CommodityProductUnitVirtualStockPO po);

	int updateCommodityProductUnitVirtualStockWithTx(CommodityProductUnitVirtualStockPO po);

	int deleteCommodityProductUnitVirtualStockWithTx(CommodityProductUnitVirtualStockPO po);
}
	