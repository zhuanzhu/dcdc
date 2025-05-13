package com.egeo.components.stock.manage.read;

import java.util.List;
	
import com.egeo.components.stock.po.ContactGroupSkuStockPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface ContactGroupSkuStockReadManage {

	public ContactGroupSkuStockPO findContactGroupSkuStockById(ContactGroupSkuStockPO po);

	public PageResult<ContactGroupSkuStockPO> findContactGroupSkuStockOfPage(ContactGroupSkuStockPO po,Pagination page);

	public List<ContactGroupSkuStockPO> findContactGroupSkuStockAll(ContactGroupSkuStockPO po);

	public List<ContactGroupSkuStockPO> findContactGroupSkuStockBySuId(Long suId);
}
	