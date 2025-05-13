package com.egeo.components.stock.request;

import com.egeo.components.stock.dto.CommodityProductUnitWarehouseStockDTO;
import com.egeo.components.stock.service.Request;
import com.egeo.components.stock.service.write.CommodityProductUnitWarehouseStockWriteService;
import com.egeo.utils.log.XLogger;

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
public class ProductInventoryDBUpdateRequest  implements Request {

	private static final XLogger logger = XLogger.getLogger(ProductInventoryDBUpdateRequest.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = -5601986328155510011L;
	/**
	 * 商品库存
	 */
	private CommodityProductUnitWarehouseStockDTO commodityProductUnitWarehouseStockDTO;
	/**
	 * 商品库存Service
	 */
	private CommodityProductUnitWarehouseStockWriteService commodityProductUnitWarehouseStockWriteService;

	public ProductInventoryDBUpdateRequest(CommodityProductUnitWarehouseStockDTO commodityProductUnitWarehouseStockDTO,
			CommodityProductUnitWarehouseStockWriteService commodityProductUnitWarehouseStockWriteService) {
		this.commodityProductUnitWarehouseStockDTO = commodityProductUnitWarehouseStockDTO;
		this.commodityProductUnitWarehouseStockWriteService = commodityProductUnitWarehouseStockWriteService;
	}

	@Override
	public void process() {
		logger.info("===========日志===========: 数据库更新请求开始执行，用户名称：" + commodityProductUnitWarehouseStockDTO.getUserName() +"商品id="
				+ commodityProductUnitWarehouseStockDTO.getCommodityProductUnitId() + ", 商品库存数量="
				+ commodityProductUnitWarehouseStockDTO.getRealStockNum());
		// 删除redis中的缓存
		commodityProductUnitWarehouseStockWriteService
				.removeCommodityProductUnitWarehouseStockCache(commodityProductUnitWarehouseStockDTO);
		// 修改数据库中的库存
		/*if (commodityProductUnitWarehouseStockDTO.getType() == 1) {
			commodityProductUnitWarehouseStockWriteService.merchandiseStockWithTx(commodityProductUnitWarehouseStockDTO,
					commodityProductUnitWarehouseStockDTO.getUserId(),
					commodityProductUnitWarehouseStockDTO.getUserName(), commodityProductUnitWarehouseStockDTO.getIp(),
					commodityProductUnitWarehouseStockDTO.getMac());
		} else if (commodityProductUnitWarehouseStockDTO.getType() == 2) {
			commodityProductUnitWarehouseStockWriteService.deliverStockWithTx(commodityProductUnitWarehouseStockDTO,
					commodityProductUnitWarehouseStockDTO.getUserId(),
					commodityProductUnitWarehouseStockDTO.getUserName(), commodityProductUnitWarehouseStockDTO.getIp(),
					commodityProductUnitWarehouseStockDTO.getMac());
		}*/
	}

	/**
	 * 获取商品id
	 */
	public Long getId() {
		return commodityProductUnitWarehouseStockDTO.getCommodityProductUnitId();
	}

}
