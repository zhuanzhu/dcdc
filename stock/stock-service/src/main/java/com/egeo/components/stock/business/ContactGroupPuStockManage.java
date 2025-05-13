package com.egeo.components.stock.business;

import java.util.List;
	
import com.egeo.components.stock.dto.ContactGroupPuStockDTO;
import com.egeo.components.stock.dto.ContactGroupStockDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface ContactGroupPuStockManage {

	public ContactGroupPuStockDTO findContactGroupPuStockById(ContactGroupPuStockDTO dto);	

	public PageResult<ContactGroupPuStockDTO> findContactGroupPuStockOfPage(ContactGroupPuStockDTO dto,Pagination page);

	public List<ContactGroupPuStockDTO> findContactGroupPuStockAll(ContactGroupPuStockDTO dto);

	Long insertContactGroupPuStockWithTx(ContactGroupPuStockDTO dto);

	int updateContactGroupPuStockWithTx(ContactGroupPuStockDTO dto);

	int deleteContactGroupPuStockWithTx(ContactGroupPuStockDTO dto);


}
	