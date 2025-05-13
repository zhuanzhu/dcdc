package com.egeo.components.user.client;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.egeo.components.user.dto.CompanyUserDisabledDTO;


@FeignClient(name = "service-user-fgj",contextId="CompanyUserDisabledClient")
public interface CompanyUserDisabledClient {

	@RequestMapping(value = { "/client/user/companyUserDisabled/findRevalidationByCompanyId" }, method = { RequestMethod.POST }) 
	public Integer findRevalidationByCompanyId(Long companyId); 
 
 
	@RequestMapping(value = { "/client/user/companyUserDisabled/deleteCompanyUserDisabledWithTx" }, method = { RequestMethod.POST }) 
	public int deleteCompanyUserDisabledWithTx(CompanyUserDisabledDTO dto); 
 
 
	@RequestMapping(value = { "/client/user/companyUserDisabled/insertCompanyUserDisabledWithTx" }, method = { RequestMethod.POST }) 
	public Long insertCompanyUserDisabledWithTx(CompanyUserDisabledDTO dto); 
 

	@RequestMapping(value = { "/client/user/companyUserDisabled/findUsersByCompanyId" }, method = { RequestMethod.POST }) 
	public List<String> findUsersByCompanyId (Long companyId);
}