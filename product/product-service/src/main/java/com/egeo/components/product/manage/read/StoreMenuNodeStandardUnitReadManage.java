package com.egeo.components.product.manage.read;

import java.util.List;
	
import com.egeo.components.product.po.StoreMenuNodeStandardUnitPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface StoreMenuNodeStandardUnitReadManage {

	public StoreMenuNodeStandardUnitPO findStoreMenuNodeStandardUnitById(StoreMenuNodeStandardUnitPO po);

	public PageResult<StoreMenuNodeStandardUnitPO> findStoreMenuNodeStandardUnitOfPage(StoreMenuNodeStandardUnitPO po,Pagination page);

	public List<StoreMenuNodeStandardUnitPO> findStoreMenuNodeStandardUnitAll(StoreMenuNodeStandardUnitPO po);
}
	