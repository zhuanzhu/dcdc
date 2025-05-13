package com.egeo.components.stock.client;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.egeo.components.stock.dto.MerchantProductWarehouseStockDTO;


@FeignClient(name = "service-stock-fgj",contextId="MerchantProductWarehouseStockClient")
public interface MerchantProductWarehouseStockClient {

	@RequestMapping(value = { "/client/stock/merchantProductWarehouseStock/findAll" }, method = { RequestMethod.POST }) 
    List<MerchantProductWarehouseStockDTO> findAll(MerchantProductWarehouseStockDTO dto); 
 
 
	@RequestMapping(value = { "/client/stock/merchantProductWarehouseStock/batchLockMerchantProductStockWithTx" }, method = { RequestMethod.POST }) 
	boolean batchLockMerchantProductStockWithTx(List<MerchantProductWarehouseStockDTO> mpsDtoList); 
 
 
	@RequestMapping(value = { "/client/stock/merchantProductWarehouseStock/batchChangeStockStatusLockToRealWithTx" }, method = { RequestMethod.POST }) 
	boolean batchChangeStockStatusLockToRealWithTx(List<MerchantProductWarehouseStockDTO> mpsDtoList); 
 
 
	@RequestMapping(value = { "/client/stock/merchantProductWarehouseStock/batchUnlockItemsStockWithTx" }, method = { RequestMethod.POST }) 
	boolean batchUnlockItemsStockWithTx(List<MerchantProductWarehouseStockDTO> mpsDtoList); 
 
 
}