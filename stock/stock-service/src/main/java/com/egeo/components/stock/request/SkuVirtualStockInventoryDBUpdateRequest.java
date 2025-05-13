package com.egeo.components.stock.request;

import com.egeo.components.stock.dto.MerchantProductVirtualStockDTO;
import com.egeo.components.stock.service.Request;
import com.egeo.components.stock.service.write.MerchantProductVirtualStockWriteService;

/**
 * 比如说一个商品发生了交易，那么就要修改这个商品对应的库存
 * 
 * 此时就会发送请求过来，要求修改库存，那么这个可能就是所谓的data update request，数据更新请求
 * 
 * cache aside pattern
 * 
 * （1）删除缓存 （2）更新数据库
 * 
 * @author Administrator
 *
 */
public class SkuVirtualStockInventoryDBUpdateRequest implements Request {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5784223957808406642L;
	/**
	 * 商品库存
	 */
	private MerchantProductVirtualStockDTO merchantProductVirtualStockDTO;
	/**
	 * 商品库存Service
	 */
	private MerchantProductVirtualStockWriteService merchantProductVirtualStockWriteService;

	public SkuVirtualStockInventoryDBUpdateRequest(MerchantProductVirtualStockDTO merchantProductVirtualStockDTO,
			MerchantProductVirtualStockWriteService merchantProductVirtualStockWriteService) {
		this.merchantProductVirtualStockDTO = merchantProductVirtualStockDTO;
		this.merchantProductVirtualStockWriteService = merchantProductVirtualStockWriteService;
	}

	@Override
	public void process() {
		System.out.println("===========日志===========: 数据库更新请求开始执行，商品skuId="
				+ merchantProductVirtualStockDTO.getSkuId() + ", 商品库存数量="
				+ merchantProductVirtualStockDTO.getRealStockNum());
		// 删除redis中的缓存
		merchantProductVirtualStockWriteService.removeCommodityProductUnitWarehouseStockCacheWithTx(merchantProductVirtualStockDTO);
		// 修改数据库中的库存
		if (merchantProductVirtualStockDTO.getType() == 1) {
			merchantProductVirtualStockWriteService.addStockWithTx(merchantProductVirtualStockDTO.getSkuId(), merchantProductVirtualStockDTO.getRealStockNum());
		} else if (merchantProductVirtualStockDTO.getType() == 2) {
			merchantProductVirtualStockWriteService.subtractStockWithTx(merchantProductVirtualStockDTO.getSkuId(), merchantProductVirtualStockDTO.getRealStockNum());
		}
	}

	/**
	 * 获取商品id
	 */
	public Long getId() {
		return merchantProductVirtualStockDTO.getSkuId();
	}

}
