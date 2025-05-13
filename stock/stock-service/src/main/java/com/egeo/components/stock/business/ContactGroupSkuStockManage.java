package com.egeo.components.stock.business;

import java.util.List;
	
import com.egeo.components.stock.dto.ContactGroupSkuStockDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface ContactGroupSkuStockManage {

	public ContactGroupSkuStockDTO findContactGroupSkuStockById(ContactGroupSkuStockDTO dto);	

	public PageResult<ContactGroupSkuStockDTO> findContactGroupSkuStockOfPage(ContactGroupSkuStockDTO dto,Pagination page);

	public List<ContactGroupSkuStockDTO> findContactGroupSkuStockAll(ContactGroupSkuStockDTO dto);

	Long insertContactGroupSkuStockWithTx(ContactGroupSkuStockDTO dto);

	int updateContactGroupSkuStockWithTx(ContactGroupSkuStockDTO dto);

	int deleteContactGroupSkuStockWithTx(ContactGroupSkuStockDTO dto);
}
	