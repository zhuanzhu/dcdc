package com.egeo.components.user.client;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.egeo.components.user.dto.ClientDTO;
import com.egeo.components.user.fo.UpdateClientPayTypeWithTxFO;


@FeignClient(name = "service-user-fgj",contextId="InfoTemplateSendWayClient")
public interface InfoTemplateSendWayClient {

	@RequestMapping(value = { "/client/user/clientTemplateSendWay/findClientAll" }, method = { RequestMethod.POST }) 
	public List<ClientDTO> findClientAll(ClientDTO dto); 
 
 
	@RequestMapping(value = { "/client/user/clientTemplateSendWay/findClientById" }, method = { RequestMethod.POST }) 
	public ClientDTO findClientById(ClientDTO dto); 
 
 
	@RequestMapping(value = { "/client/user/clientTemplateSendWay/findClientByClientIds" }, method = { RequestMethod.POST }) 
	public List<ClientDTO> findClientByClientIds(List<String> clientIds); 
 
 
	@RequestMapping(value = { "/client/user/clientTemplateSendWay/clientNameByClientIds" }, method = { RequestMethod.POST }) 
	public List<String> clientNameByClientIds(List<String> clientIdList); 
 
 
	@RequestMapping(value = { "/client/user/clientTemplateSendWay/updateClientPayTypeWithTx" }, method = { RequestMethod.POST }) 
	public boolean updateClientPayTypeWithTx(UpdateClientPayTypeWithTxFO fo); 
 
 
}