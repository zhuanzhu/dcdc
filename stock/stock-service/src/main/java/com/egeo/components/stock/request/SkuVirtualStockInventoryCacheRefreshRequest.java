package com.egeo.components.stock.request;

import com.egeo.components.stock.dto.MerchantProductVirtualStockDTO;
import com.egeo.components.stock.service.Request;
import com.egeo.components.stock.service.read.MerchantProductVirtualStockReadService;

/**
 * 重新加载商品sku库存的缓存
 * @author Administrator
 *
 */
public class SkuVirtualStockInventoryCacheRefreshRequest implements Request {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2758978331537458194L;
	/**
	 * 商品id
	 */
	private Long id;
	/**
	 * 商品sku库存Service
	 */
	private MerchantProductVirtualStockReadService merchantProductVirtualStockReadService;
	
	public SkuVirtualStockInventoryCacheRefreshRequest(Long skuId,
			MerchantProductVirtualStockReadService merchantProductVirtualStockReadService) {
		this.id = skuId;
		this.merchantProductVirtualStockReadService = merchantProductVirtualStockReadService;
	}
	
	@Override
	public void process() {
		// 根据skuId从数据库中查询最新的sku商品库存信息
		MerchantProductVirtualStockDTO merchantProductVirtualStockDTO = merchantProductVirtualStockReadService.merchantProductVirtualStockBySkuId(id);
		System.out.println("===========日志===========: 已查询到商品最新的库存数量，pu商品id=" + id + ", 商品库存数量=" + merchantProductVirtualStockDTO.getRealStockNum());  
		// 将最新的sku商品库存数量，刷新到redis缓存中去
		merchantProductVirtualStockReadService.setMerchantProductVirtualStockCache(merchantProductVirtualStockDTO);
		System.out.println("从数据库中查询最新的商品库存数量");
	}
	
	public Long getId() {
		return id;
	}
	
}
