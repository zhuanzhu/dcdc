package com.egeo.components.order.controller.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.order.client.MerchantProdSalesRecordCoreClient;
import com.egeo.components.order.service.write.MerchantProdSalesRecordCoreWriteService;

@Controller
@RequestMapping("/client/order/merchantProdSalesRecordCore") 
public class MerchantProdSalesRecordCoreController implements MerchantProdSalesRecordCoreClient{ 

	@Autowired
	private MerchantProdSalesRecordCoreWriteService merchantProdSalesRecordCoreWriteService;

	@Override
	@RequestMapping(value = "/recordSalesVolume", method = { RequestMethod.POST })
	@ResponseBody
	public boolean recordSalesVolume(@RequestBody Long soId) {
		// TODO Auto-generated method stub
		return merchantProdSalesRecordCoreWriteService.recordSalesVolume(soId);
	} 
 
}