package com.egeo.components.stock.manage.write;

import java.util.List;

import com.egeo.components.stock.po.MerchantProductWarehouseStockPO;

public interface MerchantProductWarehouseStockWriteManage {

    Long saveMerchantProductWarehouseStock(MerchantProductWarehouseStockPO po);

    Long updateMerchantProductWarehouseStock(MerchantProductWarehouseStockPO po);

    int deleteByProductId(MerchantProductWarehouseStockPO po);

	boolean lockMerchantProductStockWithTx(MerchantProductWarehouseStockPO tar);

	boolean batchLockMerchantProductStockWithTx(List<MerchantProductWarehouseStockPO> mpsPoList);

	boolean addMerchantProductStockWithTx(MerchantProductWarehouseStockPO converDTOToPO);

	boolean batchChangeStockStatusLockToRealWithTx(List<MerchantProductWarehouseStockPO> mpsPoList);
	boolean changeStockStatusLockToRealWithTx(MerchantProductWarehouseStockPO po);

	boolean batchUnlockItemsStockWithTx(List<MerchantProductWarehouseStockPO> mpsPoList);
	boolean unlockItemsStockWithTx(MerchantProductWarehouseStockPO po);

	int deleteByPara(MerchantProductWarehouseStockPO po);

	int comeMerchantProductStockWithTx(MerchantProductWarehouseStockPO tar);
}
	