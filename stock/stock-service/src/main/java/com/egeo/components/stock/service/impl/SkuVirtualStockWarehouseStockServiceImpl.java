package com.egeo.components.stock.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.stock.dto.MerchantProductVirtualStockDTO;
import com.egeo.components.stock.request.SkuVirtualStockInventoryCacheRefreshRequest;
import com.egeo.components.stock.request.SkuVirtualStockInventoryDBUpdateRequest;
import com.egeo.components.stock.service.Request;
import com.egeo.components.stock.service.RequestAsyncProcessService;
import com.egeo.components.stock.service.SkuVirtualStockWarehouseStockService;
import com.egeo.components.stock.service.read.MerchantProductVirtualStockReadService;
import com.egeo.components.stock.service.write.MerchantProductVirtualStockWriteService;
@Service("skuVirtualStockWarehouseStockService")  
public class SkuVirtualStockWarehouseStockServiceImpl implements SkuVirtualStockWarehouseStockService {
	
	@Autowired
	private MerchantProductVirtualStockReadService merchantProductVirtualStockReadService;
	
	@Autowired
	private MerchantProductVirtualStockWriteService merchantProductVirtualStockWriteService;
	
	@Autowired
	private RequestAsyncProcessService requestAsyncProcessService;
	
	@Override
	public MerchantProductVirtualStockDTO findBySkuId(Long skuId) {
		MerchantProductVirtualStockDTO merchantProductVirtualStockDTO = null;
		
		try {
			Request request = new SkuVirtualStockInventoryCacheRefreshRequest(
					skuId, merchantProductVirtualStockReadService);
			requestAsyncProcessService.process(request);
			
			// 将请求扔给service异步去处理以后，就需要while(true)一会儿，在这里hang住
			// 去尝试等待前面有商品库存更新的操作，同时缓存刷新的操作，将最新的数据刷新到缓存中
			long startTime = System.currentTimeMillis();
			long endTime = 0L;
			long waitTime = 0L;
			
			// 等待超过200ms没有从缓存中获取到结果
			while(true) {
				// 一般公司里面，面向用户的读请求控制在200ms就可以了
				if(waitTime > 200) {
					break;
				}
				
				
				// 尝试去redis中读取一次商品库存的缓存数据
				merchantProductVirtualStockDTO = merchantProductVirtualStockReadService.getMerchantProductVirtualStockCache(skuId);
				
				// 如果读取到了结果，那么就返回
				if(merchantProductVirtualStockDTO != null) {
					System.out.println("===========日志===========: 在200ms内读取到了redis中的库存缓存，商品id=" + merchantProductVirtualStockDTO.getSkuId() + ", 商品库存数量=" + merchantProductVirtualStockDTO.getRealStockNum());  
					return merchantProductVirtualStockDTO;
				}
				
				// 如果没有读取到结果，那么等待一段时间
				else {
					Thread.sleep(20);
					endTime = System.currentTimeMillis();
					waitTime = endTime - startTime;
				}
			}
			
			// 直接尝试从数据库中读取数据
			merchantProductVirtualStockDTO = merchantProductVirtualStockReadService.merchantProductVirtualStockBySkuId(skuId);
			if(merchantProductVirtualStockDTO != null) {
				// 将缓存刷新一下
				merchantProductVirtualStockReadService.setMerchantProductVirtualStockCache(merchantProductVirtualStockDTO); 
				return merchantProductVirtualStockDTO;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int updateMerchantProductVirtualStockDTO(MerchantProductVirtualStockDTO dto) {
		Request request = new SkuVirtualStockInventoryDBUpdateRequest(dto, merchantProductVirtualStockWriteService);
		requestAsyncProcessService.process(request);
		return 1;
	}


}
