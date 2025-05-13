package com.egeo.components.cms.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.cms.client.CmsPageTabCompanyClient;
import com.egeo.components.cms.dto.CmsPageTabCompanyDTO;
import com.egeo.components.cms.service.read.CmsPageTabCompanyReadService;
import com.egeo.components.cms.service.write.CmsPageTabCompanyWriteService;

@Controller
@RequestMapping("/client/cms/cmsPageTabCompany") 
public class CmsPageTabCompanyController implements CmsPageTabCompanyClient{ 

	@Autowired
	private CmsPageTabCompanyReadService cmsPageTabCompanyReadService;
	@Autowired
	private CmsPageTabCompanyWriteService cmsPageTabCompanyWriteService;


	@Override
	@RequestMapping(value = "/updateCmsPageTabCompanyWithTx", method = { RequestMethod.POST })
	@ResponseBody
	public int updateCmsPageTabCompanyWithTx(@RequestBody CmsPageTabCompanyDTO dto) {
		return cmsPageTabCompanyWriteService.updateCmsPageTabCompanyWithTx(dto);
	} 
 
	@Override
	@RequestMapping(value = "/insertCmsPageTabCompanyWithTx", method = { RequestMethod.POST })
	@ResponseBody
	public Long insertCmsPageTabCompanyWithTx(@RequestBody CmsPageTabCompanyDTO dto) {
		return cmsPageTabCompanyWriteService.insertCmsPageTabCompanyWithTx(dto);
	} 
 
	@Override
	@RequestMapping(value = "/deleteCmsPageTabCompanyWithTx", method = { RequestMethod.POST })
	@ResponseBody
	public int deleteCmsPageTabCompanyWithTx(@RequestBody CmsPageTabCompanyDTO dto) {
		return cmsPageTabCompanyWriteService.deleteCmsPageTabCompanyWithTx(dto);
	} 
 
	@Override
	@RequestMapping(value = "/findCmsPageTabCompanyAll", method = { RequestMethod.POST })
	@ResponseBody
	public List<CmsPageTabCompanyDTO> findCmsPageTabCompanyAll(@RequestBody CmsPageTabCompanyDTO dto) {
		return cmsPageTabCompanyReadService.findCmsPageTabCompanyAll(dto);
	} 
 
}