package com.egeo.components.pay.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.pay.client.AwaitQueueClient;
import com.egeo.components.pay.dto.AwaitQueueDTO;
import com.egeo.components.pay.service.read.AwaitQueueReadService;
import com.egeo.components.pay.service.write.AwaitQueueWriteService;

@Controller
@RequestMapping("/client/pay/awaitQueue") 
public class AwaitQueueController implements AwaitQueueClient{ 

	@Autowired
	private AwaitQueueReadService awaitQueueReadService;
	@Autowired
	private AwaitQueueWriteService awaitQueueWriteService;


	@Override
	@RequestMapping(value = "/delByOrderIdWithTx", method = { RequestMethod.POST })
	@ResponseBody
	public int delByOrderIdWithTx(@RequestBody  Long orderId) {
		return awaitQueueWriteService.delByOrderIdWithTx(orderId);
	} 
 
	@Override
	@RequestMapping(value = "/deleteAwaitQueueWithTx", method = { RequestMethod.POST })
	@ResponseBody
	public int deleteAwaitQueueWithTx(@RequestBody AwaitQueueDTO dto) {
		return awaitQueueWriteService.deleteAwaitQueueWithTx(dto);
	} 
 
	@Override
	@RequestMapping(value = "/findByOrderCode", method = { RequestMethod.POST })
	@ResponseBody
	public AwaitQueueDTO findByOrderCode(@RequestBody String orderCode) {
		return awaitQueueReadService.findByOrderCode(orderCode);
	} 
 
	@Override
	@RequestMapping(value = "/queryAwaitQueueByOrderId", method = { RequestMethod.POST })
	@ResponseBody
	public AwaitQueueDTO queryAwaitQueueByOrderId(@RequestBody  Long orderId) {
		return awaitQueueReadService.queryAwaitQueueByOrderId(orderId);
	} 
 
	@Override
	@RequestMapping(value = "/revocationOrderPay", method = { RequestMethod.POST })
	@ResponseBody
	public boolean revocationOrderPay(@RequestBody AwaitQueueDTO awaitQueueDTO,@RequestParam("platformId")  Long platformId) {
		return awaitQueueReadService.revocationOrderPay(awaitQueueDTO, platformId);
	} 
 
	@Override
	@RequestMapping(value = "/findAwaitQueueAll", method = { RequestMethod.POST })
	@ResponseBody
	public List<AwaitQueueDTO> findAwaitQueueAll(@RequestBody AwaitQueueDTO dto) {
		return awaitQueueReadService.findAwaitQueueAll(dto);
	} 
 
}