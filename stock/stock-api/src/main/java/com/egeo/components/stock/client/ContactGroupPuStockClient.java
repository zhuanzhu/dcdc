package com.egeo.components.stock.client;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.egeo.components.stock.dto.ContactGroupPuStockDTO;


@FeignClient(name = "service-stock-fgj",contextId="ContactGroupPuStockClient")
public interface ContactGroupPuStockClient {

	@RequestMapping(value = { "/client/stock/contactGroupPuStock/insertContactGroupPuStockWithTx" }, method = { RequestMethod.POST }) 
	public Long insertContactGroupPuStockWithTx(ContactGroupPuStockDTO dto); 
 
 
	@RequestMapping(value = { "/client/stock/contactGroupPuStock/findPuIdListByPuId" }, method = { RequestMethod.POST }) 
	public List<String> findPuIdListByPuId(@RequestParam("puid") Long puid); 
 
 
	@RequestMapping(value = { "/client/stock/contactGroupPuStock/findContactGroupPuStockAll" }, method = { RequestMethod.POST }) 
	public List<ContactGroupPuStockDTO> findContactGroupPuStockAll(ContactGroupPuStockDTO dto); 
 
 
}