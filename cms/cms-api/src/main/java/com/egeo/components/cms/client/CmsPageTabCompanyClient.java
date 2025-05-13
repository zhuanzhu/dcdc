package com.egeo.components.cms.client;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.egeo.components.cms.dto.CmsPageTabCompanyDTO;


@FeignClient(name = "service-cms-fgj",contextId="CmsPageTabCompanyClient")
public interface CmsPageTabCompanyClient {

	@RequestMapping(value = { "/client/cms/cmsPageTabCompany/updateCmsPageTabCompanyWithTx" }, method = { RequestMethod.POST }) 
	public int updateCmsPageTabCompanyWithTx(CmsPageTabCompanyDTO dto); 
 
 
	@RequestMapping(value = { "/client/cms/cmsPageTabCompany/insertCmsPageTabCompanyWithTx" }, method = { RequestMethod.POST }) 
	public Long insertCmsPageTabCompanyWithTx(CmsPageTabCompanyDTO dto); 
 
 
	@RequestMapping(value = { "/client/cms/cmsPageTabCompany/deleteCmsPageTabCompanyWithTx" }, method = { RequestMethod.POST }) 
	public int deleteCmsPageTabCompanyWithTx(CmsPageTabCompanyDTO dto); 
 
 
	@RequestMapping(value = { "/client/cms/cmsPageTabCompany/findCmsPageTabCompanyAll" }, method = { RequestMethod.POST }) 
	public List<CmsPageTabCompanyDTO> findCmsPageTabCompanyAll(CmsPageTabCompanyDTO dto); 
 
 
}