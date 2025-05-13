package com.egeo.components.config.client;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.egeo.components.config.dto.CompanyConfigDTO;


@FeignClient(name = "service-config-fgj",contextId="CompanyConfigClient")
public interface CompanyConfigClient {

	

	@RequestMapping(value = { "/client/config/company/configs" }, method = { RequestMethod.POST }) 
	public List<CompanyConfigDTO> queryCompanyconfigs(@RequestParam("companyId") Long companyId); 
	@RequestMapping(value = { "/client/config/company/config/value" }, method = { RequestMethod.POST }) 
	public String queryCompanyconfigValue(@RequestParam("companyId") Long companyId,@RequestParam("key") String key); 
	

	@RequestMapping(value = { "/client/config/company/config/by/code" }, method = { RequestMethod.POST }) 
	public List<CompanyConfigDTO> queryCompanyconfigsByCode(@RequestParam("code") String code); 
 
 
}