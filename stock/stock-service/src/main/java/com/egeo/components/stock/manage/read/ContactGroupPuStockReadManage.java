package com.egeo.components.stock.manage.read;

import java.util.List;
	
import com.egeo.components.stock.po.ContactGroupPuStockPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface ContactGroupPuStockReadManage {

	public ContactGroupPuStockPO findContactGroupPuStockById(ContactGroupPuStockPO po);

	public PageResult<ContactGroupPuStockPO> findContactGroupPuStockOfPage(ContactGroupPuStockPO po,Pagination page);

	public List<ContactGroupPuStockPO> findContactGroupPuStockAll(ContactGroupPuStockPO po);
	
	public List<Long> findPuIdListByPuId(Long puid);
	
	public List<ContactGroupPuStockPO> findContactGroupPuStockBySkuId(Long skuId);
}
	