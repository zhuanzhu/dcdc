package com.egeo.components.stock.manage.read;

import java.util.List;
	
import com.egeo.components.stock.po.StoreStockChangeApplyPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface StoreStockChangeApplyReadManage {

	public StoreStockChangeApplyPO findStoreStockChangeApplyById(StoreStockChangeApplyPO po);

	public PageResult<StoreStockChangeApplyPO> findStoreStockChangeApplyOfPage(StoreStockChangeApplyPO po,Pagination page);

	public List<StoreStockChangeApplyPO> findStoreStockChangeApplyAll(StoreStockChangeApplyPO po);
}
	