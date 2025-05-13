package com.egeo.components.cms.client;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.egeo.components.cms.dto.FunctionModuleCategoryDTO;


@FeignClient(name = "service-cms-fgj",contextId="FunctionModuleCategoryClient")
public interface FunctionModuleCategoryClient {

	@RequestMapping(value = { "/client/cms/functionModuleCategory/findFunctionModuleCategoryAll" }, method = { RequestMethod.POST }) 
	public List<FunctionModuleCategoryDTO> findFunctionModuleCategoryAll(FunctionModuleCategoryDTO dto); 
}
 
