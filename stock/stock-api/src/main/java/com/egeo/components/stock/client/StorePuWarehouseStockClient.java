package com.egeo.components.stock.client;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.egeo.components.order.dto.SoDTO;
import com.egeo.components.order.dto.SoItemDTO;
import com.egeo.components.stock.dto.StorePuStockRunningWaterDTO;
import com.egeo.components.stock.dto.StorePuWarehouseStockDTO;
import com.egeo.components.stock.dto.UpdateStorePuWarehouseStockDTO;


@FeignClient(name = "service-stock-fgj",contextId="StorePuWarehouseStockClient")
public interface StorePuWarehouseStockClient {

	@RequestMapping(value = { "/client/stock/storePuWarehouseStock/insertAllWithTx" }, method = { RequestMethod.POST }) 
	public int insertAllWithTx(List<StorePuWarehouseStockDTO> list); 
 
 
	@RequestMapping(value = { "/client/stock/storePuWarehouseStock/findStorePuWarehouseStockAll" }, method = { RequestMethod.POST }) 
	public List<StorePuWarehouseStockDTO> findStorePuWarehouseStockAll(StorePuWarehouseStockDTO dto); 
 
 
	@RequestMapping(value = { "/client/stock/storePuWarehouseStock/realStockNumByStoreProductUnitId" }, method = { RequestMethod.POST }) 
	public Long realStockNumByStoreProductUnitId(@RequestParam("storeProductUnitId") Long storeProductUnitId,@RequestParam("storeId")  Long storeId); 
 
 
	@RequestMapping(value = { "/client/stock/storePuWarehouseStock/findByStorePuId" }, method = { RequestMethod.POST }) 
	public StorePuWarehouseStockDTO findByStorePuId(Long storePuId); 
 
 
	@RequestMapping(value = { "/client/stock/storePuWarehouseStock/findPuSellOutSumStoreByPuIds" }, method = { RequestMethod.POST }) 
	public Integer findPuSellOutSumStoreByPuIds(List<String> storePuIds); 
 

	/**
	 * 更新门店pu库存信息
	 * @param type 库存流水类型:1提交订单 2支付 3 取消订单（未支付） 4 取消订单（已支付） 5 进货 6 出货
	 * @param sodto
	 * @param soItems
	 * @return
	 */
	@RequestMapping(value = { "/client/stock/storePuWarehouseStock/updateStorePuWarehouseStock" }, method = { RequestMethod.POST }) 
	public Boolean updateStorePuWarehouseStock(UpdateStorePuWarehouseStockDTO dto);
	
	
}