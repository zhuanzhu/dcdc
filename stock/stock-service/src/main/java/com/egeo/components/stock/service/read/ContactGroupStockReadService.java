package com.egeo.components.stock.service.read;


import java.util.List;
	
import com.egeo.components.stock.dto.ContactGroupStockDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface ContactGroupStockReadService {

	public ContactGroupStockDTO findContactGroupStockById(ContactGroupStockDTO dto);

	public PageResult<ContactGroupStockDTO> findContactGroupStockOfPage(ContactGroupStockDTO dto,Pagination page);

	public List<ContactGroupStockDTO> findContactGroupStockAll(ContactGroupStockDTO dto);

    public PageResult<ContactGroupStockDTO> findContactGroupStockMapOfPage(ContactGroupStockDTO dto, Pagination page, List<Long> spuIds, List<Long> merchantIds);

	public ContactGroupStockDTO findContactGroupStockByMerchantIdAndSuId(ContactGroupStockDTO dto, Long suId);

	public List<ContactGroupStockDTO> findAllByName(ContactGroupStockDTO contactDto);
}
	