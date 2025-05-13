package com.egeo.components.user.controller.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.user.client.CompanyCoreClient;
import com.egeo.components.user.service.read.CompanyCoreReadService;

@Controller
@RequestMapping("/client/user/companyCore") 
public class CompanyCoreController implements CompanyCoreClient{ 

	@Autowired
	private CompanyCoreReadService companyCoreReadService;


	@Override
	@RequestMapping(value = "/findCompanyTypeById", method = { RequestMethod.POST })
	@ResponseBody
	public Integer findCompanyTypeById(@RequestBody Long companyId) {
		// TODO Auto-generated method stub
		return companyCoreReadService.findCompanyTypeById(companyId);
	}


	@Override
	@RequestMapping(value = "/findCompanyTypeByUserId", method = { RequestMethod.POST })
	@ResponseBody
	public Integer findCompanyTypeByUserId(@RequestBody Long userId) {
		// TODO Auto-generated method stub
		return companyCoreReadService.findCompanyTypeByUserId(userId);
	}


	@Override
	@RequestMapping(value = "/findCompanyAllIdByCompanyId", method = { RequestMethod.POST })
	@ResponseBody
	public Long findCompanyAllIdByCompanyId(@RequestBody  Long companyId) {
		// TODO Auto-generated method stub
		return companyCoreReadService.findCompanyAllIdByCompanyId(companyId);
	}

	@Override
	@RequestMapping(value = "/findEnterpriseCompanyAllId", method = { RequestMethod.POST })
	@ResponseBody
	public Long findEnterpriseCompanyAllId(@RequestBody Long companyId) {
		return companyCoreReadService.findEnterpriseCompanyAllId(companyId);
	}

	@Override
	@RequestMapping(value = "/findCompanyAllIdByCompanyType", method = { RequestMethod.POST })
	@ResponseBody
	public Long findCompanyAllIdByCompanyType(@RequestBody Integer companyType) {
		return companyCoreReadService.findCompanyAllIdByCompanyType(companyType);
	}


} 
