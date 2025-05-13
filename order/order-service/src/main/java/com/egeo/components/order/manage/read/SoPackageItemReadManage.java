package com.egeo.components.order.manage.read;

import java.util.List;
	
import com.egeo.components.order.po.SoPackageItemPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface SoPackageItemReadManage {

	public SoPackageItemPO findSoPackageItemById(SoPackageItemPO po);

	public PageResult<SoPackageItemPO> findSoPackageItemOfPage(SoPackageItemPO po,Pagination page);

	public List<SoPackageItemPO> findSoPackageItemAll(SoPackageItemPO po);
}
	