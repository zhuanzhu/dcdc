package com.egeo.components.pay.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.pay.client.ThirdpartyAwaitQueueClient;
import com.egeo.components.pay.dto.ThirdpartyAwaitQueueDTO;
import com.egeo.components.pay.service.read.ThirdpartyAwaitQueueReadService;
import com.egeo.components.pay.service.write.ThirdpartyAwaitQueueWriteService;

@Controller
@RequestMapping("/client/pay/thirdpartyAwaitQueue") 
public class ThirdpartyAwaitQueueController implements ThirdpartyAwaitQueueClient{ 

	@Autowired
	private ThirdpartyAwaitQueueReadService thirdpartyAwaitQueueReadService;
	@Autowired
	private ThirdpartyAwaitQueueWriteService thirdpartyAwaitQueueWriteService;


	@Override
	@RequestMapping(value = "/findThirdpartyAwaitQueueAll", method = { RequestMethod.POST })
	@ResponseBody
	public List<ThirdpartyAwaitQueueDTO> findThirdpartyAwaitQueueAll(@RequestBody ThirdpartyAwaitQueueDTO dto) {
		return thirdpartyAwaitQueueReadService.findThirdpartyAwaitQueueAll(dto);
	} 
 
	@Override
	@RequestMapping(value = "/deleteThirdpartyAwaitQueueByCodeWithTx", method = { RequestMethod.POST })
	@ResponseBody
	public int deleteThirdpartyAwaitQueueByCodeWithTx(@RequestBody ThirdpartyAwaitQueueDTO thirdpartyAwaitQueueDTO) {
		return thirdpartyAwaitQueueWriteService.deleteThirdpartyAwaitQueueByCodeWithTx(thirdpartyAwaitQueueDTO);
	}

	@Override
	@RequestMapping(value = "/insertThirdpartyAwaitQueueWithTx", method = { RequestMethod.POST })
	@ResponseBody
	public Long insertThirdpartyAwaitQueueWithTx(@RequestBody ThirdpartyAwaitQueueDTO dto) {
		// TODO Auto-generated method stub
		return thirdpartyAwaitQueueWriteService.insertThirdpartyAwaitQueueWithTx(dto);
	} 
 
}