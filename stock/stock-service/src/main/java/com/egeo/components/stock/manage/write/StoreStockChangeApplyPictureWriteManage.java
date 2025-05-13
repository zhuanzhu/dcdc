package com.egeo.components.stock.manage.write;

import com.egeo.components.stock.po.StoreStockChangeApplyPicturePO;


public interface StoreStockChangeApplyPictureWriteManage {

	Long insertStoreStockChangeApplyPictureWithTx(StoreStockChangeApplyPicturePO po);

	int updateStoreStockChangeApplyPictureWithTx(StoreStockChangeApplyPicturePO po);

	int deleteStoreStockChangeApplyPictureWithTx(StoreStockChangeApplyPicturePO po);
}
	