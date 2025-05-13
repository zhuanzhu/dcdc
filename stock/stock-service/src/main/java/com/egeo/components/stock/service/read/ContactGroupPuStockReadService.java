package com.egeo.components.stock.service.read;


import java.util.List;
	
import com.egeo.components.stock.dto.ContactGroupPuStockDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface ContactGroupPuStockReadService {

	public ContactGroupPuStockDTO findContactGroupPuStockById(ContactGroupPuStockDTO dto);

	public PageResult<ContactGroupPuStockDTO> findContactGroupPuStockOfPage(ContactGroupPuStockDTO dto,Pagination page);

	public List<ContactGroupPuStockDTO> findContactGroupPuStockAll(ContactGroupPuStockDTO dto);
	
	public List<Long> findPuIdListByPuId(Long puid);
}
	