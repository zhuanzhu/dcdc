package com.egeo.components.pay.client;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.egeo.components.pay.dto.AwaitQueueDTO;


@FeignClient(name = "service-pay-fgj",contextId="AwaitQueueClient")
public interface AwaitQueueClient {

	@RequestMapping(value = { "/client/pay/awaitQueue/delByOrderIdWithTx" }, method = { RequestMethod.POST }) 
	public int delByOrderIdWithTx(Long orderId); 
 
 
	@RequestMapping(value = { "/client/pay/awaitQueue/deleteAwaitQueueWithTx" }, method = { RequestMethod.POST }) 
	public int deleteAwaitQueueWithTx(AwaitQueueDTO dto); 
 
 
	@RequestMapping(value = { "/client/pay/awaitQueue/findByOrderCode" }, method = { RequestMethod.POST }) 
	public AwaitQueueDTO findByOrderCode(String orderCode); 
 
 
	@RequestMapping(value = { "/client/pay/awaitQueue/queryAwaitQueueByOrderId" }, method = { RequestMethod.POST }) 
	public AwaitQueueDTO queryAwaitQueueByOrderId(Long orderId); 
 
 
	@RequestMapping(value = { "/client/pay/awaitQueue/revocationOrderPay" }, method = { RequestMethod.POST }) 
	public boolean revocationOrderPay(@RequestBody AwaitQueueDTO awaitQueueDTO,@RequestParam("platformId")  Long platformId); 
 
 
	@RequestMapping(value = { "/client/pay/awaitQueue/findAwaitQueueAll" }, method = { RequestMethod.POST }) 
	public List<AwaitQueueDTO> findAwaitQueueAll(AwaitQueueDTO dto); 
 
 
}