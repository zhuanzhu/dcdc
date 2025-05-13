package com.egeo.components.product.service.read;


import java.util.List;
	
import com.egeo.components.product.dto.SellPlatformDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface SellPlatformReadService {

	public SellPlatformDTO findSellPlatformById(SellPlatformDTO dto);

	public PageResult<SellPlatformDTO> findSellPlatformOfPage(SellPlatformDTO dto,Pagination page);

	public List<SellPlatformDTO> findSellPlatformAll(SellPlatformDTO dto);
}
	