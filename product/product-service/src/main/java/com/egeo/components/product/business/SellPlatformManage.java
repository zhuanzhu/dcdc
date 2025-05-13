package com.egeo.components.product.business;

import java.util.List;
	
import com.egeo.components.product.dto.SellPlatformDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface SellPlatformManage {

	public SellPlatformDTO findSellPlatformById(SellPlatformDTO dto);	

	public PageResult<SellPlatformDTO> findSellPlatformOfPage(SellPlatformDTO dto,Pagination page);

	public List<SellPlatformDTO> findSellPlatformAll(SellPlatformDTO dto);

	Long insertSellPlatformWithTx(SellPlatformDTO dto);

	int updateSellPlatformWithTx(SellPlatformDTO dto);

	int deleteSellPlatformWithTx(SellPlatformDTO dto);
}
	