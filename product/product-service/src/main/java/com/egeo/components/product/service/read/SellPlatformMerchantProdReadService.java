package com.egeo.components.product.service.read;


import java.util.List;
	
import com.egeo.components.product.dto.SellPlatformMerchantProdDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface SellPlatformMerchantProdReadService {

	public SellPlatformMerchantProdDTO findSellPlatformMerchantProdById(SellPlatformMerchantProdDTO dto);

	public PageResult<SellPlatformMerchantProdDTO> findSellPlatformMerchantProdOfPage(SellPlatformMerchantProdDTO dto,Pagination page);

	public List<SellPlatformMerchantProdDTO> findSellPlatformMerchantProdAll(SellPlatformMerchantProdDTO dto);
	/**
	 * 根据su草稿id查询su草稿比价平台信息
	 * @param sellPlatformMerchantProdDTO
	 * @return
	 */
	public List<SellPlatformMerchantProdDTO> findByMerchantProdId(
			SellPlatformMerchantProdDTO sellPlatformMerchantProdDTO);
}
	