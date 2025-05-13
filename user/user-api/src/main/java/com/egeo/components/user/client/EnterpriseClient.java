package com.egeo.components.user.client;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.egeo.components.user.dto.CompanyDTO;
import com.egeo.components.user.dto.Enterprise;
import com.egeo.components.user.fo.FindCompanyOfPageFO;
import com.egeo.orm.PageResult;
import com.egeo.web.JsonResult;


@FeignClient(name = "service-user-fgj",contextId="EnterpriseClient")
public interface EnterpriseClient {

	@RequestMapping(value = { "/client/uniauth/enterprise/findById" }, method = { RequestMethod.POST }) 
	public Enterprise findById(Integer id) ; 
 
 
}