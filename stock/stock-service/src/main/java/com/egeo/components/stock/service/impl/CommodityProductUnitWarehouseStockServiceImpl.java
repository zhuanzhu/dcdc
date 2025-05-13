package com.egeo.components.stock.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.stock.dto.CommodityProductUnitWarehouseStockDTO;
import com.egeo.components.stock.request.ProductInventoryDBUpdateRequest;
import com.egeo.components.stock.service.CommodityProductUnitWarehouseStockService;
import com.egeo.components.stock.service.Request;
import com.egeo.components.stock.service.RequestAsyncProcessService;
import com.egeo.components.stock.service.read.CommodityProductUnitWarehouseStockReadService;
import com.egeo.components.stock.service.write.CommodityProductUnitWarehouseStockWriteService;
import com.egeo.utils.log.XLogger;

@Service("commodityProductUnitWarehouseStockService")
public class CommodityProductUnitWarehouseStockServiceImpl  implements CommodityProductUnitWarehouseStockService {

	private static final XLogger logger = XLogger.getLogger(CommodityProductUnitWarehouseStockServiceImpl.class);
	@Autowired
	private CommodityProductUnitWarehouseStockReadService commodityProductUnitWarehouseStockReadService;
	
	@Autowired
	private CommodityProductUnitWarehouseStockWriteService commodityProductUnitWarehouseStockWriteService;
	
	@Autowired
	private RequestAsyncProcessService requestAsyncProcessService;
	
	@Override
	public CommodityProductUnitWarehouseStockDTO findByProductUnitId(Long productUnitId) {
		CommodityProductUnitWarehouseStockDTO commodityProductUnitWarehouseStockDTO = null;
		
		try {
			/*Request request = new ProductInventoryCacheRefreshRequest(
					productUnitId, commodityProductUnitWarehouseStockReadService);
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
				commodityProductUnitWarehouseStockDTO = commodityProductUnitWarehouseStockReadService.getCommodityProductUnitWarehouseStockCache(productUnitId);
				
				// 如果读取到了结果，那么就返回
				if(commodityProductUnitWarehouseStockDTO != null) {
					System.out.println("===========日志===========: 在200ms内读取到了redis中的库存缓存，商品id=" + commodityProductUnitWarehouseStockDTO.getCommodityProductUnitId() + ", 商品库存数量=" + commodityProductUnitWarehouseStockDTO.getRealStockNum());  
					return commodityProductUnitWarehouseStockDTO;
				}
				
				// 如果没有读取到结果，那么等待一段时间
				else {
					Thread.sleep(20);
					endTime = System.currentTimeMillis();
					waitTime = endTime - startTime;
				}
			}*/
			
			// 直接尝试从数据库中读取数据
			return commodityProductUnitWarehouseStockDTO = commodityProductUnitWarehouseStockReadService.findCommodityProductUnitWarehouseStockByPuId(productUnitId);
			/*if(commodityProductUnitWarehouseStockDTO != null) {
				// 将缓存刷新一下
				commodityProductUnitWarehouseStockReadService.setCommodityProductUnitWarehouseStockCache(commodityProductUnitWarehouseStockDTO); 
				return commodityProductUnitWarehouseStockDTO;
			}*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int updateCommodityProductUnitWarehouseStockDTO(CommodityProductUnitWarehouseStockDTO dto) {
		
		//修改库存类型：1、进货 2、出货
		if(dto.getType() == 1){
			logger.info("用户名称：" + dto.getUserName() + "进货：puId:" + dto.getCommodityProductUnitId() + ",数量:"+ dto.getRealStockNum());
		}else if(dto.getType() == 2){
			logger.info("用户名称：" + dto.getUserName() + "出货：puId:" + dto.getCommodityProductUnitId() + ",数量:"+ dto.getRealStockNum());
		}
		Request request = new ProductInventoryDBUpdateRequest(dto, commodityProductUnitWarehouseStockWriteService);
		requestAsyncProcessService.process(request);
		//修改库存类型：1、进货 2、出货
		if(dto.getType() == 1){
			logger.info("用户名称：" + dto.getUserName() + "进货：puId:" + dto.getCommodityProductUnitId() + ",数量:"+ dto.getRealStockNum() + "加入队列成功");
		}else if(dto.getType() == 2){
			logger.info("用户名称：" + dto.getUserName() + "出货：puId:" + dto.getCommodityProductUnitId() + ",数量:"+ dto.getRealStockNum() + "加入队列成功");
		}
		return 1;
	}


}
