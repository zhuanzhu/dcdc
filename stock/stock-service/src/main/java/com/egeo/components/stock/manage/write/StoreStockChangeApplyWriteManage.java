package com.egeo.components.stock.manage.write;

import java.util.List;

import com.egeo.components.stock.po.StoreStockChangeApplyPO;


public interface StoreStockChangeApplyWriteManage {

	Long insertStoreStockChangeApplyWithTx(StoreStockChangeApplyPO po,List<String> pictures);

	int updateStoreStockChangeApplyWithTx(StoreStockChangeApplyPO po);

	int deleteStoreStockChangeApplyWithTx(StoreStockChangeApplyPO po);
}
	