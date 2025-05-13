package com.egeo.components.product.manage.read;

import java.util.List;
	
import com.egeo.components.product.po.StoreMenuNodePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface StoreMenuNodeReadManage {

	public StoreMenuNodePO findStoreMenuNodeById(StoreMenuNodePO po);

	public PageResult<StoreMenuNodePO> findStoreMenuNodeOfPage(StoreMenuNodePO po,Pagination page);

	public List<StoreMenuNodePO> findStoreMenuNodeAll(StoreMenuNodePO po);
}
	