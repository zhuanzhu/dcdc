package com.egeo.components.product.business;

import java.util.List;
	
import com.egeo.components.product.dto.SellPlatformMerchantProdDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface SellPlatformMerchantProdManage {

	public SellPlatformMerchantProdDTO findSellPlatformMerchantProdById(SellPlatformMerchantProdDTO dto);	

	public PageResult<SellPlatformMerchantProdDTO> findSellPlatformMerchantProdOfPage(SellPlatformMerchantProdDTO dto,Pagination page);

	public List<SellPlatformMerchantProdDTO> findSellPlatformMerchantProdAll(SellPlatformMerchantProdDTO dto);

	Long insertSellPlatformMerchantProdWithTx(SellPlatformMerchantProdDTO dto);

	int updateSellPlatformMerchantProdWithTx(SellPlatformMerchantProdDTO dto);

	int deleteSellPlatformMerchantProdWithTx(SellPlatformMerchantProdDTO dto);
}
	