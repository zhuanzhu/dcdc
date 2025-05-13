package com.egeo.components.order.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.order.client.ReceiverAddressClient;
import com.egeo.components.order.dto.ReceiverAddressDTO;
import com.egeo.components.order.service.read.ReceiverAddressReadService;
import com.egeo.components.order.service.write.ReceiverAddressWriteService;

@Controller
@RequestMapping("/client/order/receiverAddress")
public class ReceiverAddressController implements ReceiverAddressClient{

	@Autowired
	private ReceiverAddressReadService receiverAddressReadService;
	@Autowired
	private ReceiverAddressWriteService receiverAddressWriteService;


	@Override
	@RequestMapping(value = "/findReceiverAddressAll", method = { RequestMethod.POST })
	@ResponseBody
	public List<ReceiverAddressDTO> findReceiverAddressAll(@RequestBody ReceiverAddressDTO dto) {
		return receiverAddressReadService.findReceiverAddressAll(dto);
	}

	@Override
	@RequestMapping(value = "/findReceiverAddressById", method = { RequestMethod.POST })
	@ResponseBody
	public ReceiverAddressDTO findReceiverAddressById(@RequestBody Long id) {
		return receiverAddressReadService.findReceiverAddressById(id);
	}

	@Override
	@RequestMapping(value = "/findDefaultReceiverAddress", method = { RequestMethod.POST })
	@ResponseBody
	public ReceiverAddressDTO findDefaultReceiverAddress(@RequestBody ReceiverAddressDTO dto) {
		return receiverAddressReadService.queryDefaultReceiverAddress(dto.getUserId(),dto.getPlatformId());
	}
}
