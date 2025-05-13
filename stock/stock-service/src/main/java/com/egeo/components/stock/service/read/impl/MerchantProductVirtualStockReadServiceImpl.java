package com.egeo.components.stock.service.read.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.stock.service.read.MerchantProductVirtualStockReadService;
import com.egeo.components.stock.manage.read.MerchantProductVirtualStockReadManage;
import com.egeo.components.stock.converter.MerchantProductVirtualStockConverter;
import com.egeo.components.stock.dto.MerchantProductVirtualStockDTO;
import com.egeo.components.stock.po.MerchantProductVirtualStockPO;

import com.egeo.utils.cache.JedisUtil;
import com.egeo.utils.EmptyUtil;

@Service("merchantProductVirtualStockReadService")
public class MerchantProductVirtualStockReadServiceImpl  implements MerchantProductVirtualStockReadService {
	@Autowired
	private MerchantProductVirtualStockReadManage merchantProductVirtualStockReadManage;
	
	@Resource
	private  JedisUtil cache;

	@Override
	public Long queryUnitStockBySkuId(Long skuId) {
		return merchantProductVirtualStockReadManage.queryUnitStockBySkuId(skuId);
	}
	/**
	 * 根据skuId从数据库中查询最新的sku商品库存信息
	 * @param id
	 * @return
	 */
	@Override
	public MerchantProductVirtualStockDTO merchantProductVirtualStockBySkuId(Long id) {
		MerchantProductVirtualStockPO merchantProductVirtualStockPO = merchantProductVirtualStockReadManage.merchantProductVirtualStockBySkuId(id);
		return MerchantProductVirtualStockConverter.toDTO(merchantProductVirtualStockPO);
	}
	/**
	 * 将最新的sku商品库存数量，刷新到redis缓存中去
	 * @param merchantProductVirtualStockDTO
	 */
	@Override
	public void setMerchantProductVirtualStockCache(MerchantProductVirtualStockDTO merchantProductVirtualStockDTO) {
		String key = "sku:inventory:" + merchantProductVirtualStockDTO.getSkuId();
		//Object json = JSONArray.toJSON(commodityProductUnitWarehouseStockDTO);
		cache.set(key, merchantProductVirtualStockDTO);
		System.out.println("===========日志===========: 已更新商品库存的缓存，商品SkuId=" + merchantProductVirtualStockDTO.getSkuId() + ", 商品库存数量=" + (merchantProductVirtualStockDTO.getRealStockNum() - merchantProductVirtualStockDTO.getRealFrozenStockNum()) + ", key=" + key); 
		
	}
	/**
	 * 获取sku商品库存的缓存
	 * @param productId
	 * @return
	 */
	@Override
	public MerchantProductVirtualStockDTO getMerchantProductVirtualStockCache(Long skuId) {
		String key = "sku:inventory:" + skuId;
		MerchantProductVirtualStockDTO merchantProductVirtualStockDTO = (MerchantProductVirtualStockDTO)cache.get(key);
		
		if(EmptyUtil.isNotEmpty(merchantProductVirtualStockDTO)) {
			return merchantProductVirtualStockDTO;
		}
		
		return null;
	}
}
	