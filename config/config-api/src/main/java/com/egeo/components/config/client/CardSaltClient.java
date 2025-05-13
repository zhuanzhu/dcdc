package com.egeo.components.config.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.egeo.components.config.dto.CardSaltDTO;

@FeignClient(name = "service-config-fgj",contextId="CardSaltClient")
public interface CardSaltClient {

	@RequestMapping(value = { "/client/config/cardSalt/queryCardSaltByUUID" }, method = { RequestMethod.POST })
	public CardSaltDTO queryCardSaltByUUID(String uuid);

	@RequestMapping(value = { "/client/config/cardSalt/insertCardSaltWithTx" }, method = { RequestMethod.POST })
	public Long insertCardSaltWithTx(CardSaltDTO cardSaltDTO);
	
}
