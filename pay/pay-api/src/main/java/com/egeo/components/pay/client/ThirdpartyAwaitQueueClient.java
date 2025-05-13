package com.egeo.components.pay.client;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.egeo.components.pay.dto.ThirdpartyAwaitQueueDTO;


@FeignClient(name = "service-pay-fgj",contextId="ThirdpartyAwaitQueueClient")
public interface ThirdpartyAwaitQueueClient {

	@RequestMapping(value = { "/client/pay/thirdpartyAwaitQueue/findThirdpartyAwaitQueueAll" }, method = { RequestMethod.POST }) 
	public List<ThirdpartyAwaitQueueDTO> findThirdpartyAwaitQueueAll(ThirdpartyAwaitQueueDTO dto); 
 
 
	@RequestMapping(value = { "/client/pay/thirdpartyAwaitQueue/deleteThirdpartyAwaitQueueByCodeWithTx" }, method = { RequestMethod.POST }) 
	public int deleteThirdpartyAwaitQueueByCodeWithTx(ThirdpartyAwaitQueueDTO thirdpartyAwaitQueueDTO); 
 
	@RequestMapping(value = { "/client/pay/thirdpartyAwaitQueue/insertThirdpartyAwaitQueueWithTx" }, method = { RequestMethod.POST }) 
	public Long insertThirdpartyAwaitQueueWithTx(ThirdpartyAwaitQueueDTO dto);
}