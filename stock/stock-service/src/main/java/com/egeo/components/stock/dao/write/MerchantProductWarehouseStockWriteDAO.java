package com.egeo.components.stock.dao.write;

import com.egeo.components.stock.po.MerchantProductWarehouseStockPO;
import com.egeo.orm.BaseWriteDAO;

public interface MerchantProductWarehouseStockWriteDAO extends BaseWriteDAO<MerchantProductWarehouseStockPO> {

    int deleteByProductId(MerchantProductWarehouseStockPO po);

	int lockMerchantProductStockWithTx(MerchantProductWarehouseStockPO po);

	int addMerchantProductStockWithTx(MerchantProductWarehouseStockPO po);

	int changeStockStatusLockToRealWithTx(MerchantProductWarehouseStockPO po);

	int unlockItemsStockWithTx(MerchantProductWarehouseStockPO po);

	int comeMerchantProductStockWithTx(MerchantProductWarehouseStockPO po);
}
	