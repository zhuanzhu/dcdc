package com.egeo.components.product.manage.read;

import java.util.List;
	
import com.egeo.components.product.po.SellPlatformPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface SellPlatformReadManage {

	public SellPlatformPO findSellPlatformById(SellPlatformPO po);

	public PageResult<SellPlatformPO> findSellPlatformOfPage(SellPlatformPO po,Pagination page);

	public List<SellPlatformPO> findSellPlatformAll(SellPlatformPO po);
}
	