package com.egeo.components.user.client;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.egeo.components.user.dto.CompanyPageDTO;


@FeignClient(name = "service-user-fgj",contextId="CompanyPageClient")
public interface CompanyPageClient {

	@RequestMapping(value = { "/client/user/companyPage/findCompanyPageAll" }, method = { RequestMethod.POST }) 
	public List<CompanyPageDTO> findCompanyPageAll(CompanyPageDTO dto); 
} 
 
