package com.egeo.components.cms.client;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.egeo.components.cms.dto.CmsPageTabCompanyDTO;
import com.egeo.components.cms.dto.CmsPageTabDTO;


@FeignClient(name = "service-cms-fgj",contextId="CmsPageTabClient")
public interface CmsPageTabClient {

	@RequestMapping(value = { "/client/cms/cmsPageTab/findCmsPageTabAll" }, method = { RequestMethod.POST }) 
	public List<CmsPageTabDTO> findCmsPageTabAll(CmsPageTabDTO dto); 
 
 
	@RequestMapping(value = { "/client/cms/cmsPageTab/findCmsPageTabCondition" }, method = { RequestMethod.POST }) 
	public List<CmsPageTabDTO> findCmsPageTabCondition(CmsPageTabDTO dto); 
	
	@RequestMapping(value = { "/client/cms/cmsPageTab/updateCmsPageTabCompanyWithTx" }, method = { RequestMethod.POST }) 
	public int updateCmsPageTabCompanyWithTx(CmsPageTabCompanyDTO dto);
	
	@RequestMapping(value = { "/client/cms/cmsPageTab/insertCmsPageTabCompanyWithTx" }, method = { RequestMethod.POST })
	public Long insertCmsPageTabCompanyWithTx(CmsPageTabCompanyDTO dto);
}