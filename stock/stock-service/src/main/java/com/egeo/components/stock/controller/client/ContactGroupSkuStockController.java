package com.egeo.components.stock.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.stock.client.ContactGroupSkuStockClient;
import com.egeo.components.stock.dto.ContactGroupSkuStockDTO;
import com.egeo.components.stock.service.read.ContactGroupSkuStockReadService;
import com.egeo.components.stock.service.write.ContactGroupSkuStockWriteService;

@Controller
@RequestMapping("/client/stock/contactGroupSkuStock") 
public class ContactGroupSkuStockController implements ContactGroupSkuStockClient{ 

	@Autowired
	private ContactGroupSkuStockReadService contactGroupSkuStockReadService;
	@Autowired
	private ContactGroupSkuStockWriteService contactGroupSkuStockWriteService;


	@Override
	@RequestMapping(value = "/insertContactGroupSkuStockListWithTx", method = { RequestMethod.POST })
	@ResponseBody
	public void insertContactGroupSkuStockListWithTx(@RequestBody List<ContactGroupSkuStockDTO> list) {
		contactGroupSkuStockWriteService.insertContactGroupSkuStockListWithTx(list);
	} 
 
	@Override
	@RequestMapping(value = "/findContactGroupSkuStockBySuId", method = { RequestMethod.POST })
	@ResponseBody
	public List<ContactGroupSkuStockDTO> findContactGroupSkuStockBySuId(@RequestParam("suId") Long suId) {
		return contactGroupSkuStockReadService.findContactGroupSkuStockBySuId(suId);
	} 
 
}