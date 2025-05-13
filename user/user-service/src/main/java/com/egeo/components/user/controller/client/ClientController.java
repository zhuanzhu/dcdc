package com.egeo.components.user.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.user.client.ClientClient;
import com.egeo.components.user.dto.ClientDTO;
import com.egeo.components.user.fo.UpdateClientPayTypeWithTxFO;
import com.egeo.components.user.service.read.ClientReadService;
import com.egeo.components.user.service.write.ClientWriteService;

@Controller
@RequestMapping("/client/user/client") 
public class ClientController implements ClientClient{ 

	@Autowired
	private ClientReadService clientReadService;
	@Autowired
	private ClientWriteService clientWriteService;


	@Override
	@RequestMapping(value = "/findClientAll", method = { RequestMethod.POST })
	@ResponseBody
	public List<ClientDTO> findClientAll(@RequestBody ClientDTO dto) {
		return clientReadService.findClientAll(dto);
	} 
 
	@Override
	@RequestMapping(value = "/findClientById", method = { RequestMethod.POST })
	@ResponseBody
	public ClientDTO findClientById(@RequestBody ClientDTO dto) {
		return clientReadService.findClientById(dto);
	} 
 
	@Override
	@RequestMapping(value = "/findClientByClientIds", method = { RequestMethod.POST })
	@ResponseBody
	public List<ClientDTO> findClientByClientIds(@RequestBody List<String> clientIds) {
		return clientReadService.findClientByClientIds(com.egeo.utils.StringUtils.stringsToLongs(clientIds));
	} 
 
	@Override
	@RequestMapping(value = "/clientNameByClientIds", method = { RequestMethod.POST })
	@ResponseBody
	public List<String> clientNameByClientIds(@RequestBody List<String> clientIdList) {
		return clientReadService.clientNameByClientIds(com.egeo.utils.StringUtils.stringsToLongs(clientIdList));
	} 
 
	@Override
	@RequestMapping(value = "/updateClientPayTypeWithTx", method = { RequestMethod.POST })
	@ResponseBody
	public boolean updateClientPayTypeWithTx(@RequestBody UpdateClientPayTypeWithTxFO dto) {
		return clientWriteService.updateClientPayTypeWithTx(dto.getClientDTO(),dto.getClientPayTypeConfigDTOList());
	} 
 
}