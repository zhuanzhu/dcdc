package com.egeo.components.pay.client;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.egeo.components.pay.dto.JdOrderAwaitQueueDTO;


@FeignClient(name = "service-pay-fgj",contextId="JdOrderAwaitQueueClient")
public interface JdOrderAwaitQueueClient {

	@RequestMapping(value = { "/client/pay/jdOrderAwaitQueue/findJdOrderAwaitQueueAll" }, method = { RequestMethod.POST }) 
	public List<JdOrderAwaitQueueDTO> findJdOrderAwaitQueueAll(JdOrderAwaitQueueDTO dto); 

	@RequestMapping(value = { "/client/pay/jdOrderAwaitQueue/insertJdOrderAwaitQueueWithTx" }, method = { RequestMethod.POST }) 
	public Long insertJdOrderAwaitQueueWithTx(JdOrderAwaitQueueDTO dto);
	
	@RequestMapping(value = { "/client/pay/jdOrderAwaitQueue/deleteJdOrderAwaitQueueWithTx" }, method = { RequestMethod.POST }) 
	public int deleteJdOrderAwaitQueueWithTx(JdOrderAwaitQueueDTO dto);
}
