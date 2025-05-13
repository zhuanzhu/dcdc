package com.egeo.components.product.client;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.egeo.components.product.dto.StandardUnitMembershipDTO;


@FeignClient(name = "service-product-fgj",contextId="StandardUnitMembershipClient")
public interface StandardUnitMembershipClient {

	@RequestMapping(value = { "/client/product/standardUnitMembershipClient/findStandardUnitMembershipAll" }, method = { RequestMethod.POST }) 
	public List<StandardUnitMembershipDTO> findStandardUnitMembershipAll(StandardUnitMembershipDTO dto);

}