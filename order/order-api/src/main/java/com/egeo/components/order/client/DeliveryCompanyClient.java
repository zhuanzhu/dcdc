package com.egeo.components.order.client;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.egeo.components.order.dto.DeliveryCompanyDTO;


@FeignClient(name = "service-order-fgj",contextId="DeliveryCompanyClient")
public interface DeliveryCompanyClient {

	@RequestMapping(value = { "/client/order/deliveryCompany/findDeliveryCompanyAll" }, method = { RequestMethod.POST }) 
	public List<DeliveryCompanyDTO> findDeliveryCompanyAll(DeliveryCompanyDTO dto); 
 
}
