package com.egeo.components.user.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.user.client.CompanyUserDisabledClient;
import com.egeo.components.user.dto.CompanyUserDisabledDTO;
import com.egeo.components.user.service.read.CompanyUserDisabledReadService;
import com.egeo.components.user.service.write.CompanyUserDisabledWriteService;

@Controller
@RequestMapping("/client/user/companyUserDisabled") 
public class CompanyUserDisabledController implements CompanyUserDisabledClient{ 

	@Autowired
	private CompanyUserDisabledReadService companyUserDisabledReadService;
	@Autowired
	private CompanyUserDisabledWriteService companyUserDisabledWriteService;


	@Override
	@RequestMapping(value = "/findRevalidationByCompanyId", method = { RequestMethod.POST })
	@ResponseBody
	public Integer findRevalidationByCompanyId(@RequestBody  Long companyId) {
		return companyUserDisabledReadService.findRevalidationByCompanyId(companyId);
	} 
 
	@Override
	@RequestMapping(value = "/deleteCompanyUserDisabledWithTx", method = { RequestMethod.POST })
	@ResponseBody
	public int deleteCompanyUserDisabledWithTx(@RequestBody CompanyUserDisabledDTO dto) {
		return companyUserDisabledWriteService.deleteCompanyUserDisabledWithTx(dto);
	} 
 
	@Override
	@RequestMapping(value = "/insertCompanyUserDisabledWithTx", method = { RequestMethod.POST })
	@ResponseBody
	public Long insertCompanyUserDisabledWithTx(@RequestBody CompanyUserDisabledDTO dto) {
		return companyUserDisabledWriteService.insertCompanyUserDisabledWithTx(dto);
	}

	@Override
	@RequestMapping(value = "/findUsersByCompanyId", method = { RequestMethod.POST })
	@ResponseBody
	public List<String> findUsersByCompanyId(@RequestBody  Long companyId) {
		// TODO Auto-generated method stub
		return com.egeo.utils.StringUtils.longsToStrings(companyUserDisabledReadService.findUsersByCompanyId(companyId));
	} 
 
}