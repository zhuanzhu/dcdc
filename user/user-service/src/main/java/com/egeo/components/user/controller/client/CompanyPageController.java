package com.egeo.components.user.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.user.client.CompanyPageClient;
import com.egeo.components.user.dto.CompanyPageDTO;
import com.egeo.components.user.service.read.CompanyPageReadService;
import com.egeo.components.user.service.write.CompanyPageWriteService;

@Controller
@RequestMapping("/client/user/companyPage") 
public class CompanyPageController implements CompanyPageClient{ 

	@Autowired
	private CompanyPageReadService companyPageReadService;
	@Autowired
	private CompanyPageWriteService companyPageWriteService;


	@Override
	@RequestMapping(value = "/findCompanyPageAll", method = { RequestMethod.POST })
	@ResponseBody
	public List<CompanyPageDTO> findCompanyPageAll(@RequestBody CompanyPageDTO dto) {
		return companyPageReadService.findCompanyPageAll(dto);
	} 
} 
