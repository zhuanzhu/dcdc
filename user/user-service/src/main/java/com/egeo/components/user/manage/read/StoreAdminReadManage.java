package com.egeo.components.user.manage.read;

import java.util.List;
	
import com.egeo.components.user.po.StoreAdminPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface StoreAdminReadManage {

	public StoreAdminPO findStoreAdminById(StoreAdminPO po);

	public PageResult<StoreAdminPO> findStoreAdminOfPage(StoreAdminPO po,Pagination page);

	public List<StoreAdminPO> findStoreAdminAll(StoreAdminPO po);
}
	