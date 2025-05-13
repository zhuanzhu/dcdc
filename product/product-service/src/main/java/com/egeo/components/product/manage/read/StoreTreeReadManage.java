package com.egeo.components.product.manage.read;

import java.util.List;
	
import com.egeo.components.product.po.StoreTreePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface StoreTreeReadManage {

	public StoreTreePO findStoreTreeById(StoreTreePO po);

	public PageResult<StoreTreePO> findStoreTreeOfPage(StoreTreePO po,Pagination page);

	public List<StoreTreePO> findStoreTreeAll(StoreTreePO po);
}
	