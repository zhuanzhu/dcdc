package com.egeo.components.stock.client;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.egeo.components.stock.dto.ContactGroupSkuStockDTO;


@FeignClient(name = "service-stock-fgj",contextId="ContactGroupSkuStockClient")
public interface ContactGroupSkuStockClient {

	@RequestMapping(value = { "/client/stock/contactGroupSkuStock/insertContactGroupSkuStockListWithTx" }, method = { RequestMethod.POST }) 
	void insertContactGroupSkuStockListWithTx(List<ContactGroupSkuStockDTO> list); 
 
 
	@RequestMapping(value = { "/client/stock/contactGroupSkuStock/findContactGroupSkuStockBySuId" }, method = { RequestMethod.POST }) 
	public List<ContactGroupSkuStockDTO> findContactGroupSkuStockBySuId(Long suId); 
 
 
}