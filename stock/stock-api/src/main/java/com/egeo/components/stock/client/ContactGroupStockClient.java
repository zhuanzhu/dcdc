package com.egeo.components.stock.client;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.egeo.components.stock.dto.ContactGroupStockDTO;


@FeignClient(name = "service-stock-fgj",contextId="ContactGroupStockClient")
public interface ContactGroupStockClient {

	@RequestMapping(value = { "/client/stock/contactGroupStock/findContactGroupStockAll" }, method = { RequestMethod.POST }) 
	public List<ContactGroupStockDTO> findContactGroupStockAll(ContactGroupStockDTO dto); 
 
 
	 
	}