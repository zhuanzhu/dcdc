package com.egeo.components.user.client;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.egeo.components.user.dto.ClientDTO;
import com.egeo.components.user.fo.UpdateClientPayTypeWithTxFO;


@FeignClient(name = "service-user-fgj",contextId="ClientClient")
public interface ClientClient {

	@RequestMapping(value = { "/client/user/client/findClientAll" }, method = { RequestMethod.POST }) 
	public List<ClientDTO> findClientAll(ClientDTO dto); 
 
 
	@RequestMapping(value = { "/client/user/client/findClientById" }, method = { RequestMethod.POST }) 
	public ClientDTO findClientById(ClientDTO dto); 
 
 
	@RequestMapping(value = { "/client/user/client/findClientByClientIds" }, method = { RequestMethod.POST }) 
	public List<ClientDTO> findClientByClientIds(List<String> clientIds); 
 
 
	@RequestMapping(value = { "/client/user/client/clientNameByClientIds" }, method = { RequestMethod.POST }) 
	public List<String> clientNameByClientIds(List<String> clientIdList); 
 
 
	@RequestMapping(value = { "/client/user/client/updateClientPayTypeWithTx" }, method = { RequestMethod.POST }) 
	public boolean updateClientPayTypeWithTx(UpdateClientPayTypeWithTxFO fo); 
 
 
}