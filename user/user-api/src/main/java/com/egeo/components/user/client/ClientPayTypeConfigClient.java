package com.egeo.components.user.client;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.egeo.components.user.dto.ClientPayTypeConfigDTO;


@FeignClient(name = "service-user-fgj",contextId="ClientPayTypeConfigClient")
public interface ClientPayTypeConfigClient {

	@RequestMapping(value = { "/client/user/clientPayTypeConfig/findClientPayTypeConfigAll" }, method = { RequestMethod.POST }) 
	public List<ClientPayTypeConfigDTO> findClientPayTypeConfigAll(ClientPayTypeConfigDTO dto); 
} 
 
