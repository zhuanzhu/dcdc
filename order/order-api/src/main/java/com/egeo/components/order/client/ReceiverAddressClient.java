package com.egeo.components.order.client;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.egeo.components.order.dto.ReceiverAddressDTO;


@FeignClient(name = "service-order-fgj",contextId="ReceiverAddressClient")
public interface ReceiverAddressClient {

	@RequestMapping(value = { "/client/order/receiverAddress/findReceiverAddressAll" }, method = { RequestMethod.POST })
	public List<ReceiverAddressDTO> findReceiverAddressAll(ReceiverAddressDTO dto);


	@RequestMapping(value = { "/client/order/receiverAddress/findReceiverAddressById" }, method = { RequestMethod.POST })
	public ReceiverAddressDTO findReceiverAddressById(Long id);

	@RequestMapping(value = { "/client/order/receiverAddress/findDefaultReceiverAddress" }, method = { RequestMethod.POST })
	public ReceiverAddressDTO findDefaultReceiverAddress(ReceiverAddressDTO dto);

}
