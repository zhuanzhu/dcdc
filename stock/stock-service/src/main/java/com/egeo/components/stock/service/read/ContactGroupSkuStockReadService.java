package com.egeo.components.stock.service.read;


import java.util.List;

import com.egeo.components.stock.dto.ContactGroupSkuStockDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface ContactGroupSkuStockReadService {

	public ContactGroupSkuStockDTO findContactGroupSkuStockById(ContactGroupSkuStockDTO dto);

	public PageResult<ContactGroupSkuStockDTO> findContactGroupSkuStockOfPage(ContactGroupSkuStockDTO dto,Pagination page);

	public List<ContactGroupSkuStockDTO> findContactGroupSkuStockAll(ContactGroupSkuStockDTO dto);
	
	/**
	 * 根据suId 查询 当前的sku列表
	 * @param suId
	 * @return
	 */
	public List<ContactGroupSkuStockDTO> findContactGroupSkuStockBySuId(Long suId);
	
}
	