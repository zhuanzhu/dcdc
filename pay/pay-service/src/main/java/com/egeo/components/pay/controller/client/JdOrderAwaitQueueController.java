package com.egeo.components.pay.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.pay.client.JdOrderAwaitQueueClient;
import com.egeo.components.pay.dto.JdOrderAwaitQueueDTO;
import com.egeo.components.pay.service.read.JdOrderAwaitQueueReadService;
import com.egeo.components.pay.service.write.JdOrderAwaitQueueWriteService;

@Controller
@RequestMapping("/client/pay/jdOrderAwaitQueue") 
public class JdOrderAwaitQueueController implements JdOrderAwaitQueueClient{ 

	@Autowired
	private JdOrderAwaitQueueReadService jdOrderAwaitQueueReadService;
	@Autowired
	private JdOrderAwaitQueueWriteService jdOrderAwaitQueueWriteService;


	@Override
	@RequestMapping(value = "/findJdOrderAwaitQueueAll", method = { RequestMethod.POST })
	@ResponseBody
	public List<JdOrderAwaitQueueDTO> findJdOrderAwaitQueueAll(@RequestBody JdOrderAwaitQueueDTO dto) {
		return jdOrderAwaitQueueReadService.findJdOrderAwaitQueueAll(dto);
	}


	@Override
	@RequestMapping(value = "/insertJdOrderAwaitQueueWithTx", method = { RequestMethod.POST })
	@ResponseBody
	public Long insertJdOrderAwaitQueueWithTx(@RequestBody JdOrderAwaitQueueDTO dto) {
		// TODO Auto-generated method stub
		return jdOrderAwaitQueueWriteService.insertJdOrderAwaitQueueWithTx(dto);
	}


	@Override
	@RequestMapping(value = "/deleteJdOrderAwaitQueueWithTx", method = { RequestMethod.POST })
	@ResponseBody
	public int deleteJdOrderAwaitQueueWithTx(@RequestBody JdOrderAwaitQueueDTO dto) {
		// TODO Auto-generated method stub
		return jdOrderAwaitQueueWriteService.deleteJdOrderAwaitQueueWithTx(dto);
	} 
}  
