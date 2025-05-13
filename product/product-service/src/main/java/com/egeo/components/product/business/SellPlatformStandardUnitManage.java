package com.egeo.components.product.business;

import java.util.List;
	
import com.egeo.components.product.dto.SellPlatformStandardUnitDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface SellPlatformStandardUnitManage {

	public SellPlatformStandardUnitDTO findSellPlatformStandardUnitById(SellPlatformStandardUnitDTO dto);	

	public PageResult<SellPlatformStandardUnitDTO> findSellPlatformStandardUnitOfPage(SellPlatformStandardUnitDTO dto,Pagination page);

	public List<SellPlatformStandardUnitDTO> findSellPlatformStandardUnitAll(SellPlatformStandardUnitDTO dto);

	Long insertSellPlatformStandardUnitWithTx(SellPlatformStandardUnitDTO dto);

	int updateSellPlatformStandardUnitWithTx(SellPlatformStandardUnitDTO dto);

	int deleteSellPlatformStandardUnitWithTx(SellPlatformStandardUnitDTO dto);
}
	