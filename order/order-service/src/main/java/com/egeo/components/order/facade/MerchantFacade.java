package com.egeo.components.order.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.components.product.client.MerchantClient;
import com.egeo.components.product.dto.MerchantDTO;

@Component
public class MerchantFacade {

	@Autowired
    private MerchantClient merchantReadService;
	
	public MerchantDTO findMerchantById(Long id) {
		MerchantDTO dto = new MerchantDTO();
		dto.setId(id);
		return merchantReadService.findMerchantById(dto);
	}
	
}
