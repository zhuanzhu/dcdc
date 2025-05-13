package com.egeo.components.finance.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.components.config.client.SaltClient;
import com.egeo.components.config.dto.SaltDTO;

@Component
public class SecretFacade {

	@Autowired
	private SaltClient secretReadService;

	public SaltDTO querySaltByUUID(String uuid) {
		
		return secretReadService.querySaltByUUID(uuid);
	}
}
