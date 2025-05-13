package com.egeo.components.product.service.read;


import java.util.List;
	
import com.egeo.components.product.dto.SellPlatformStandardUnitDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface SellPlatformStandardUnitReadService {

	public SellPlatformStandardUnitDTO findSellPlatformStandardUnitById(SellPlatformStandardUnitDTO dto);

	public PageResult<SellPlatformStandardUnitDTO> findSellPlatformStandardUnitOfPage(SellPlatformStandardUnitDTO dto,Pagination page);

	public List<SellPlatformStandardUnitDTO> findSellPlatformStandardUnitAll(SellPlatformStandardUnitDTO dto);
}
	