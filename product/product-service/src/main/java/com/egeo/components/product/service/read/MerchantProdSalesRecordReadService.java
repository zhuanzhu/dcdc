package com.egeo.components.product.service.read;


import java.util.List;

import com.egeo.components.product.dto.MerchantProdSalesRecordDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface MerchantProdSalesRecordReadService {

	
	public MerchantProdSalesRecordDTO findMerchantProdSalesRecordById(MerchantProdSalesRecordDTO dto);

	public PageResult<MerchantProdSalesRecordDTO> findMerchantProdSalesRecordOfPage(MerchantProdSalesRecordDTO dto,Pagination page);

	public List<MerchantProdSalesRecordDTO> findMerchantProdSalesRecordAll(MerchantProdSalesRecordDTO dto);
	/**
	 * 根据suid查询su销售量
	 * @param standardUnitId
	 * @return
	 */
	public Long findSalesRecordByStandardUnitId(Long standardUnitId);
	/**
	 * 根据puid查询pu销售信息
	 * @param puId
	 * @return
	 */
	public MerchantProdSalesRecordDTO findByPUId(Long puId);
}
	