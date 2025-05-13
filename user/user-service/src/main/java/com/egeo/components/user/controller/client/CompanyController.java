package com.egeo.components.user.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.user.client.CompanyClient;
import com.egeo.components.user.dao.write.CompanyConfigMapper;
import com.egeo.components.user.dto.CompanyConfig;
import com.egeo.components.user.dto.CompanyDTO;
import com.egeo.components.user.fo.FindCompanyOfPageFO;
import com.egeo.components.user.service.read.CompanyReadService;
import com.egeo.components.user.service.write.CompanyWriteService;
import com.egeo.orm.PageResult;
import com.egeo.web.JsonResult;

@Controller
@RequestMapping("/client/user/company") 
public class CompanyController implements CompanyClient{ 

	@Autowired
	private CompanyReadService companyReadService;
	@Autowired
	private CompanyConfigMapper companyConfigService;


	@Override
	@RequestMapping(value = "/findCompanyById", method = { RequestMethod.POST })
	@ResponseBody
	public CompanyDTO findCompanyById(@RequestBody Long id) {
		return companyReadService.findCompanyById(id);
	} 
	// 业务方法：
	@RequestMapping(value = "/findCompanyByEnterpriseId")
	@ResponseBody
	public List<CompanyDTO> findCompanyByEnterpriseId(@RequestBody Long enterpriseId) {
		CompanyDTO dto = new CompanyDTO();
		dto.setEnterpriseId(enterpriseId);
		List<CompanyDTO> rt = companyReadService.findCompanyAll(dto);
		return rt;
	}
	@Override
	@RequestMapping(value = "/queryCompanysByPlatformId", method = { RequestMethod.POST })
	@ResponseBody
	public List<CompanyDTO> queryCompanysByPlatformId(@RequestBody Long platformId) {
		return companyReadService.queryCompanysByPlatformId(platformId);
	} 
 
	@Override
	@RequestMapping(value = "/queryCompaniesByIds", method = { RequestMethod.POST })
	@ResponseBody
	public List<CompanyDTO> queryCompaniesByIds(@RequestBody  List<String> companyIds) {
		return companyReadService.queryCompaniesByIds(com.egeo.utils.StringUtils.stringsToLongs(companyIds));
	} 
 
	@Override
	@RequestMapping(value = "/findCompanyAll", method = { RequestMethod.POST })
	@ResponseBody
	public List<CompanyDTO> findCompanyAll(@RequestBody CompanyDTO dto) {
		return companyReadService.findCompanyAll(dto);
	} 
 
	@Override
	@RequestMapping(value = "/queryCompanyByUserId", method = { RequestMethod.POST })
	@ResponseBody
	public CompanyDTO queryCompanyByUserId(@RequestBody long userId) {
		return companyReadService.queryCompanyByUserId(userId);
	} 
 
	@Override
	@RequestMapping(value = "/findCompanyOfPage", method = { RequestMethod.POST })
	@ResponseBody
	public PageResult<CompanyDTO> findCompanyOfPage(@RequestBody FindCompanyOfPageFO fo) {
		return companyReadService.findCompanyOfPage(fo.getDto(),fo.getPage(),fo.getCompanyIdList());
	} 
 
	@Override
	@RequestMapping(value = "/findCompanyAllByFuzzyName", method = { RequestMethod.POST })
	@ResponseBody
	public List<CompanyDTO> findCompanyAllByFuzzyName(@RequestBody CompanyDTO companyDTO) {
		return companyReadService.findCompanyAllByFuzzyName(companyDTO);
	} 
 
	@Override
	@RequestMapping(value = "/findCompanyByCompanyIds", method = { RequestMethod.POST })
	@ResponseBody
	public List<CompanyDTO> findCompanyByCompanyIds(@RequestBody List<String> companyIds) {
		return companyReadService.findCompanyByCompanyIds(com.egeo.utils.StringUtils.stringsToLongs(companyIds));
	} 
 
	@Override
	@RequestMapping(value = "/findByCompanys", method = { RequestMethod.POST })
	@ResponseBody
	public List<String> findByCompanys(@RequestBody List<String> companyIds) {
		return companyReadService.findByCompanys(com.egeo.utils.StringUtils.stringsToLongs(companyIds));
	} 
 
	@Override
	@RequestMapping(value = "/queryCompanyByName", method = { RequestMethod.POST })
	@ResponseBody
	public CompanyDTO queryCompanyByName(@RequestBody String name) {
		return companyReadService.queryCompanyByName(name);
	}
}
