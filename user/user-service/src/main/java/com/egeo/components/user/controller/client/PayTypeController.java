package com.egeo.components.user.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.user.client.PayTypeClient;
import com.egeo.components.user.dto.PayTypeDTO;
import com.egeo.components.user.service.read.PayTypeReadService;
import com.egeo.components.user.service.write.PayTypeWriteService;

@Controller
@RequestMapping("/client/user/payType") 
public class PayTypeController implements PayTypeClient{ 

	@Autowired
	private PayTypeReadService payTypeReadService;
	@Autowired
	private PayTypeWriteService payTypeWriteService;


	@Override
	@RequestMapping(value = "/findPayTypeByCode", method = { RequestMethod.POST })
	@ResponseBody
	public PayTypeDTO findPayTypeByCode(@RequestBody Integer code) {
		return payTypeReadService.findPayTypeByCode(code);
	} 
 
	@Override
	@RequestMapping(value = "/findPayTypeByCodes", method = { RequestMethod.POST })
	@ResponseBody
	public List<PayTypeDTO> findPayTypeByCodes(@RequestBody List<Integer> codes) {
		return payTypeReadService.findPayTypeByCodes(codes);
	} 
} 
