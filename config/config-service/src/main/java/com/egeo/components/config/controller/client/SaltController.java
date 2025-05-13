package com.egeo.components.config.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.config.client.SaltClient;
import com.egeo.components.config.dto.SaltDTO;
import com.egeo.components.config.service.read.SaltReadService;
import com.egeo.components.config.service.write.SaltWriteService;

@Controller
@RequestMapping("/client/config/salt") 
public class SaltController implements SaltClient{ 

	@Autowired
	private SaltReadService saltReadService;
	@Autowired
	private SaltWriteService saltWriteService;


	@Override
	@RequestMapping(value = "/querySaltByUUID", method = { RequestMethod.POST })
	@ResponseBody
	public SaltDTO querySaltByUUID(@RequestBody String uuid) {
		// TODO Auto-generated method stub
		return saltReadService.querySaltByUUID(uuid);
	}
	@Override
	@RequestMapping(value = "/querySaltByUUIDs", method = { RequestMethod.POST })
	@ResponseBody
	public SaltDTO querySaltByUUIDs(List<String> uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@RequestMapping(value = "/insertSaltWithTx", method = { RequestMethod.POST })
	@ResponseBody
	public Long insertSaltWithTx(@RequestBody SaltDTO saltDTO) {
		// TODO Auto-generated method stub
		return saltWriteService.insertSaltWithTx(saltDTO);
	}


 
}