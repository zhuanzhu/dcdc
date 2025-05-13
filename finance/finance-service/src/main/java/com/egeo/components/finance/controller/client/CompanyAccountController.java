package com.egeo.components.finance.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.finance.client.CompanyAccountClient;
import com.egeo.components.finance.dto.CompanyAccountDTO;
import com.egeo.components.finance.service.read.CompanyAccountReadService;
import com.egeo.components.finance.service.write.CompanyAccountWriteService;

@Controller
@RequestMapping("/client/finance/companyAccount") 
public class CompanyAccountController implements CompanyAccountClient{ 

	@Autowired
	private CompanyAccountReadService companyAccountReadService;
	@Autowired
	private CompanyAccountWriteService companyAccountWriteService;


	@Override
	@RequestMapping(value = "/insertCompanyAccountWithTx", method = { RequestMethod.POST })
	@ResponseBody
	public Long insertCompanyAccountWithTx(@RequestBody CompanyAccountDTO dto) {
		return companyAccountWriteService.insertCompanyAccountWithTx(dto);
	} 
 
	@Override
	@RequestMapping(value = "/querySpecialCompanyAccountByType", method = { RequestMethod.POST })
	@ResponseBody
	public CompanyAccountDTO querySpecialCompanyAccountByType(@RequestParam("platformId") Long platformId,@RequestParam("type")  int type) {
		return companyAccountReadService.querySpecialCompanyAccountByType(platformId, type);
	} 
 
	@Override
	@RequestMapping(value = "/findCompanyAccountAll", method = { RequestMethod.POST })
	@ResponseBody
	public List<CompanyAccountDTO> findCompanyAccountAll(@RequestBody CompanyAccountDTO dto) {
		return companyAccountReadService.findCompanyAccountAll(dto);
	}

	@Override
	@RequestMapping(value = "/queryNormalCompanyAccountByCompnayId", method = { RequestMethod.POST })
	@ResponseBody
	public CompanyAccountDTO queryNormalCompanyAccountByCompnayId(Long companyId) {
		// TODO Auto-generated method stub
		return companyAccountReadService.queryNormalCompanyAccountByCompnayId(companyId);
	} 
 
}