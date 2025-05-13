package com.egeo.components.product.manage.read;

import java.util.List;
	
import com.egeo.components.product.po.SellPlatformStandardUnitPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface SellPlatformStandardUnitReadManage {

	public SellPlatformStandardUnitPO findSellPlatformStandardUnitById(SellPlatformStandardUnitPO po);

	public PageResult<SellPlatformStandardUnitPO> findSellPlatformStandardUnitOfPage(SellPlatformStandardUnitPO po,Pagination page);

	public List<SellPlatformStandardUnitPO> findSellPlatformStandardUnitAll(SellPlatformStandardUnitPO po);
}
	