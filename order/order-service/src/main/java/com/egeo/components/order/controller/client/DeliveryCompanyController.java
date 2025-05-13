package com.egeo.components.order.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.order.client.DeliveryCompanyClient;
import com.egeo.components.order.dto.DeliveryCompanyDTO;
import com.egeo.components.order.service.read.DeliveryCompanyReadService;
import com.egeo.components.order.service.write.DeliveryCompanyWriteService;

@Controller
@RequestMapping("/client/order/deliveryCompany") 
public class DeliveryCompanyController implements DeliveryCompanyClient{ 

	@Autowired
	private DeliveryCompanyReadService deliveryCompanyReadService;
	@Autowired
	private DeliveryCompanyWriteService deliveryCompanyWriteService;


	@Override
	@RequestMapping(value = "/findDeliveryCompanyAll", method = { RequestMethod.POST })
	@ResponseBody
	public List<DeliveryCompanyDTO> findDeliveryCompanyAll(@RequestBody DeliveryCompanyDTO dto) {
		return deliveryCompanyReadService.findDeliveryCompanyAll(dto);
	} 
 
}