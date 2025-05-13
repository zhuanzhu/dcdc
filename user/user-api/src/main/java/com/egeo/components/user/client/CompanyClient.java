package com.egeo.components.user.client;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.egeo.components.user.dto.CompanyConfig;
import com.egeo.components.user.dto.CompanyDTO;
import com.egeo.components.user.fo.FindCompanyOfPageFO;
import com.egeo.orm.PageResult;
import com.egeo.web.JsonResult;


@FeignClient(name = "service-user-fgj",contextId="CompanyClient")
public interface CompanyClient {

	@RequestMapping(value = { "/client/user/company/findCompanyById" }, method = { RequestMethod.POST }) 
	public CompanyDTO findCompanyById(Long id); 
 
	@RequestMapping(value = { "/client/user/company/findCompanyByEnterpriseId" }, method = { RequestMethod.POST }) 
	public List<CompanyDTO> findCompanyByEnterpriseId(Long enterpriseId) ;
	
	@RequestMapping(value = { "/client/user/company/queryCompanysByPlatformId" }, method = { RequestMethod.POST }) 
	public List<CompanyDTO> queryCompanysByPlatformId(Long platformId); 
 
 
	@RequestMapping(value = { "/client/user/company/queryCompaniesByIds" }, method = { RequestMethod.POST }) 
	public List<CompanyDTO> queryCompaniesByIds(List<String> companyIds); 
 
 
	@RequestMapping(value = { "/client/user/company/findCompanyAll" }, method = { RequestMethod.POST }) 
	public List<CompanyDTO> findCompanyAll(CompanyDTO dto); 
 
 
	@RequestMapping(value = { "/client/user/company/queryCompanyByUserId" }, method = { RequestMethod.POST }) 
	public CompanyDTO queryCompanyByUserId(long userId); 
 
 
	@RequestMapping(value = { "/client/user/company/findCompanyOfPage" }, method = { RequestMethod.POST }) 
	public PageResult<CompanyDTO> findCompanyOfPage(FindCompanyOfPageFO fo); 
 
 
	@RequestMapping(value = { "/client/user/company/findCompanyAllByFuzzyName" }, method = { RequestMethod.POST }) 
	public List<CompanyDTO> findCompanyAllByFuzzyName(CompanyDTO companyDTO); 
 
 
	@RequestMapping(value = { "/client/user/company/findCompanyByCompanyIds" }, method = { RequestMethod.POST }) 
	public List<CompanyDTO> findCompanyByCompanyIds(List<String> companyIds); 
 
 
	@RequestMapping(value = { "/client/user/company/findByCompanys" }, method = { RequestMethod.POST }) 
	public List<String> findByCompanys(List<String> companyIds); 
 
 
	@RequestMapping(value = { "/client/user/company/queryCompanyByName" }, method = { RequestMethod.POST }) 
	public CompanyDTO queryCompanyByName(String name); 
	
/*
	@RequestMapping(value = { "/client/user/company/configs" }, method = { RequestMethod.POST }) 
	public List<CompanyConfig> queryCompanyconfigs(Long companyId); 
	@RequestMapping(value = { "/client/user/company/config/value" }, method = { RequestMethod.POST }) 
	public String queryCompanyconfigValue(Long companyId,String key); 
 */
 
}