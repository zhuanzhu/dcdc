package com.egeo.components.user.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.egeo.components.user.dto.Enterprise;

import java.util.List;


@FeignClient(name = "service-user-fgj",contextId="SupplierClient")
public interface SupplierClient {

	@RequestMapping(value = { "/client/uniauth/supplier/findById" }, method = { RequestMethod.POST }) 
	Enterprise findById(Integer id) ;



	@RequestMapping(value = { "/client/uniauth/supplier/findByName" }, method = { RequestMethod.POST })
	Enterprise findByName(String supplierName) ;

	@RequestMapping(value = { "/client/uniauth/supplier/findByIds" }, method = { RequestMethod.POST })
	List<Enterprise> findByIds(List<Long> ids);
}