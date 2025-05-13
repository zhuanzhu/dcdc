package com.egeo.components.stock.service;

import com.egeo.components.stock.dto.MerchantProductVirtualStockDTO;

/**
 * 请求异步执行的service
 * @author Administrator
 *
 */
public interface SkuVirtualStockWarehouseStockService {

	MerchantProductVirtualStockDTO findBySkuId(Long skuId);
	
	int updateMerchantProductVirtualStockDTO(MerchantProductVirtualStockDTO dto);
	
}
