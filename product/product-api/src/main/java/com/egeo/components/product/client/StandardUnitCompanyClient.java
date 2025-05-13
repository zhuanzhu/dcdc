package com.egeo.components.product.client;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.egeo.components.product.dto.FindSuCompanyByCompanyIdAndTypeAndSuIdsDTO;
import com.egeo.components.product.dto.StandardUnitCompanyDTO;


@FeignClient(name = "service-product-fgj",contextId="StandardUnitCompanyClient")
public interface StandardUnitCompanyClient {

	@RequestMapping(value = { "/client/product/standardUnitCompany/findStandardUnitCompanyAll" }, method = { RequestMethod.POST }) 
	public List<StandardUnitCompanyDTO> findStandardUnitCompanyAll(StandardUnitCompanyDTO dto); 
 
 
	@RequestMapping(value = { "/client/product/standardUnitCompany/findSuCompanyByCompanyIdAndTypeAndSuIds" }, method = { RequestMethod.POST }) 
	public List<StandardUnitCompanyDTO> findSuCompanyByCompanyIdAndTypeAndSuIds(FindSuCompanyByCompanyIdAndTypeAndSuIdsDTO dto); 
 
	
	@RequestMapping(value = { "/client/product/standardUnitCompany/querySuCompanyAvailable" }, method = { RequestMethod.POST }) 
	public boolean querySuCompanyAvailable(@RequestParam("suId") Long suId, @RequestParam("companyId") Long companyId,@RequestParam("clientId") Long clientId, @RequestParam("companyType") Integer companyType);

	 
}
