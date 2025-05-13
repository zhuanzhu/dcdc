package com.egeo.components.stock.service.read;

import com.egeo.components.stock.dto.MerchantProductVirtualStockDTO;

public interface MerchantProductVirtualStockReadService {

	/**
	 * 查询unit实际库存
	 * @param puId
	 * @return
	 */
	Long queryUnitStockBySkuId(Long puId);
	/**
	 * 根据skuId从数据库中查询最新的sku商品库存信息
	 * @param id
	 * @return
	 */
	MerchantProductVirtualStockDTO merchantProductVirtualStockBySkuId(Long skuId);
	/**
	 * 将最新的sku商品库存数量，刷新到redis缓存中去
	 * @param merchantProductVirtualStockDTO
	 */
	void setMerchantProductVirtualStockCache(MerchantProductVirtualStockDTO merchantProductVirtualStockDTO);
	/**
	 * 获取sku商品库存的缓存
	 * @param productId
	 * @return
	 */
	MerchantProductVirtualStockDTO getMerchantProductVirtualStockCache(Long skuId);
}
	