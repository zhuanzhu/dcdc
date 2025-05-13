package com.egeo.components.stock.manage.write;

import com.egeo.components.stock.po.StockDictPO;


public interface StockDictWriteManage {

	Long insertStockDictWithTx(StockDictPO po);

	int updateStockDictWithTx(StockDictPO po);

	int deleteStockDictWithTx(StockDictPO po);
}
	