package com.egeo.components.user.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.user.client.ClientPayTypeConfigClient;
import com.egeo.components.user.dto.ClientPayTypeConfigDTO;
import com.egeo.components.user.service.read.ClientPayTypeConfigReadService;
import com.egeo.components.user.service.write.ClientPayTypeConfigWriteService;

@Controller
@RequestMapping("/client/user/clientPayTypeConfig") 
public class ClientPayTypeConfigController implements ClientPayTypeConfigClient{ 

	@Autowired
	private ClientPayTypeConfigReadService clientPayTypeConfigReadService;
	@Autowired
	private ClientPayTypeConfigWriteService clientPayTypeConfigWriteService;


	@Override
	@RequestMapping(value = "/findClientPayTypeConfigAll", method = { RequestMethod.POST })
	@ResponseBody
	public List<ClientPayTypeConfigDTO> findClientPayTypeConfigAll(@RequestBody ClientPayTypeConfigDTO dto) {
		return clientPayTypeConfigReadService.findClientPayTypeConfigAll(dto);
	} 
} 
