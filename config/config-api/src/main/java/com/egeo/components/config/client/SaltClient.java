package com.egeo.components.config.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.egeo.components.config.dto.PaymentCodeSaltDTO;
import com.egeo.components.config.dto.SaltDTO;

@FeignClient(name = "service-config-fgj",contextId="SaltClient")
public interface SaltClient {

	@RequestMapping(value = { "/client/config/salt/querySaltByUUID" }, method = { RequestMethod.POST })
	public SaltDTO querySaltByUUID(String uuid);

	@RequestMapping(value = { "/client/config/salt/querySaltByUUIDs" }, method = { RequestMethod.POST })
	public SaltDTO querySaltByUUIDs(List<String> uuid);


	@RequestMapping(value = { "/client/config/salt/insertSaltWithTx" }, method = { RequestMethod.POST })
	public Long insertSaltWithTx(SaltDTO saltDTO);
	
}
