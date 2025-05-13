package com.egeo.components.stock.manage.read;

import java.util.List;
	
import com.egeo.components.stock.po.StoreStockChangeApplyPicturePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface StoreStockChangeApplyPictureReadManage {

	public StoreStockChangeApplyPicturePO findStoreStockChangeApplyPictureById(StoreStockChangeApplyPicturePO po);

	public PageResult<StoreStockChangeApplyPicturePO> findStoreStockChangeApplyPictureOfPage(StoreStockChangeApplyPicturePO po,Pagination page);

	public List<StoreStockChangeApplyPicturePO> findStoreStockChangeApplyPictureAll(StoreStockChangeApplyPicturePO po);
}
	