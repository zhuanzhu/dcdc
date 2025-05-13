package com.egeo.components.stock.manage.read;

import java.util.List;
	
import com.egeo.components.stock.po.ContactGroupStockPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface ContactGroupStockReadManage {

	public ContactGroupStockPO findContactGroupStockById(ContactGroupStockPO po);

	public PageResult<ContactGroupStockPO> findContactGroupStockOfPage(ContactGroupStockPO po,Pagination page);

	public List<ContactGroupStockPO> findContactGroupStockAll(ContactGroupStockPO po);
    public PageResult<ContactGroupStockPO> findContactGroupStockMapOfPage(ContactGroupStockPO po, Pagination page, List<Long> spuIds, List<Long> merchantIds);
	public ContactGroupStockPO findContactGroupStockByPuId(Long commodityProductUnitId);
	public ContactGroupStockPO findContactGroupStockByMerchantIdAndSuId(ContactGroupStockPO po, Long suId);

	public List<ContactGroupStockPO> findAllByName(ContactGroupStockPO po);}
	