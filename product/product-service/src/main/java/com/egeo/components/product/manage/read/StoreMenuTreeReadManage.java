package com.egeo.components.product.manage.read;

import java.util.List;
	
import com.egeo.components.product.po.StoreMenuTreePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface StoreMenuTreeReadManage {

	public StoreMenuTreePO findStoreMenuTreeById(StoreMenuTreePO po);

	public PageResult<StoreMenuTreePO> findStoreMenuTreeOfPage(StoreMenuTreePO po,Pagination page);

	public List<StoreMenuTreePO> findStoreMenuTreeAll(StoreMenuTreePO po);
}
	