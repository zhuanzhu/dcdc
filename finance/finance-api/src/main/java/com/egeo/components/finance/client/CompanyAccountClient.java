package com.egeo.components.finance.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.egeo.components.finance.dto.CompanyAccountDTO;

@FeignClient(name = "service-finance-fgj",contextId="CompanyAccountClient")
public interface CompanyAccountClient {

	@RequestMapping(value = { "/client/finance/companyAccount/insertCompanyAccountWithTx" }, method = { RequestMethod.POST })
	public Long insertCompanyAccountWithTx(CompanyAccountDTO companyAccountDTO);

	@RequestMapping(value = { "/client/finance/companyAccount/querySpecialCompanyAccountByType" }, method = { RequestMethod.POST })
	public CompanyAccountDTO querySpecialCompanyAccountByType(@RequestParam("platformId") Long platformId,@RequestParam("type")  int type);

	@RequestMapping(value = { "/client/finance/companyAccount/findCompanyAccountAll" }, method = { RequestMethod.POST })
	public List<CompanyAccountDTO> findCompanyAccountAll(CompanyAccountDTO companyAccountDTO);
	
	@RequestMapping(value = { "/client/finance/companyAccount/queryNormalCompanyAccountByCompnayId" }, method = { RequestMethod.POST })
	public CompanyAccountDTO queryNormalCompanyAccountByCompnayId(Long companyId);
/*
	@RequestMapping(value = { "/client/pay/salt/insertSaltWithTx" }, method = { RequestMethod.POST })
	public Long insertSaltWithTx(SaltDTO saltDTO);*/
	
}
