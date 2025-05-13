package com.egeo.components.cms.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.cms.client.CmsPageTabClient;
import com.egeo.components.cms.dto.CmsPageTabCompanyDTO;
import com.egeo.components.cms.dto.CmsPageTabDTO;
import com.egeo.components.cms.service.read.CmsPageTabReadService;
import com.egeo.components.cms.service.write.CmsPageTabCompanyWriteService;
import com.egeo.components.cms.service.write.CmsPageTabWriteService;

@Controller
@RequestMapping("/client/cms/cmsPageTab") 
public class CmsPageTabController implements CmsPageTabClient{ 

	@Autowired
	private CmsPageTabReadService cmsPageTabReadService;
	@Autowired
	private CmsPageTabWriteService cmsPageTabWriteService;
	
	@Autowired
	private CmsPageTabCompanyWriteService cmsPageTabCompanyWriteService;

	@Override
	@RequestMapping(value = "/findCmsPageTabAll", method = { RequestMethod.POST })
	@ResponseBody
	public List<CmsPageTabDTO> findCmsPageTabAll(@RequestBody CmsPageTabDTO dto) {
		return cmsPageTabReadService.findCmsPageTabAll(dto);
	} 
 
	@Override
	@RequestMapping(value = "/findCmsPageTabCondition", method = { RequestMethod.POST })
	@ResponseBody
	public List<CmsPageTabDTO> findCmsPageTabCondition(@RequestBody CmsPageTabDTO dto) {
		return cmsPageTabReadService.findCmsPageTabCondition(dto);
	}

	@Override
	@RequestMapping(value = "/updateCmsPageTabCompanyWithTx", method = { RequestMethod.POST })
	@ResponseBody
	public int updateCmsPageTabCompanyWithTx(@RequestBody CmsPageTabCompanyDTO dto) {
		// TODO Auto-generated method stub
		return cmsPageTabCompanyWriteService.updateCmsPageTabCompanyWithTx(dto);
	}

	@Override
	@RequestMapping(value = "/insertCmsPageTabCompanyWithTx", method = { RequestMethod.POST })
	@ResponseBody
	public Long insertCmsPageTabCompanyWithTx(@RequestBody CmsPageTabCompanyDTO dto) {
		// TODO Auto-generated method stub
		return cmsPageTabCompanyWriteService.insertCmsPageTabCompanyWithTx(dto);
	} 
}
 
