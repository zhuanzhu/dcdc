package com.egeo.components.cms.facade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.cms.service.read.CmsCfgKeyReadService;
import com.egeo.components.cms.service.read.CmsCfgValueReadService;
import com.egeo.components.cms.service.read.CmsInstCompanyReadService;
import com.egeo.components.cms.service.read.CmsInstReadService;
import com.egeo.components.cms.service.write.CmsCfgKeyWriteService;
import com.egeo.components.cms.service.write.CmsInstWriteService;
import com.egeo.components.cms.dto.CmsInstCompanyDTO;
import com.egeo.components.cms.dto.CmsInstDTO;
import com.egeo.utils.EmptyUtil;


@Component
public class CmsInstFacade {
	
	@Resource
	private CmsCfgKeyReadService cmsCfgKeyReadService;
	
	@Resource
	private CmsCfgKeyWriteService cmsCfgKeyWriteService;
	
	@Resource
	private CmsCfgValueReadService cmsCfgValueReadService;
	
	@Resource
	private CmsInstWriteService cmsInstWriteService;
	
	@Resource
	private CmsInstCompanyReadService cmsInstCompanyReadService;
	
	@Resource
	private CmsInstReadService cmsInstReadService;
	
	public Long insertCommonCmsInstWithTx(CmsInstDTO dto, List<Long> companyIdList, String configJson) {
		return cmsInstWriteService.insertCommonCmsInstWithTx(dto, companyIdList, configJson);
	}
	
	public List<Long> findInstCompany(Long instId) {
		List<Long> companyIdList = new ArrayList<>();
		CmsInstCompanyDTO dto = new CmsInstCompanyDTO();
		dto.setInstId(instId);
		List<CmsInstCompanyDTO> instCompanyList = cmsInstCompanyReadService.findCmsInstCompanyAll(dto);
		for (CmsInstCompanyDTO ic : instCompanyList) {
			companyIdList.add(ic.getCompanyId());
		}
		return companyIdList;
	}
	
	public List<Map<String, Object>> findCommonInstByElementId(Long elementId) {
		List<Map<String, Object>> commonInst = new ArrayList<>();
		CmsInstDTO dto  = new CmsInstDTO();
		dto.setCmsPageId(-1L);
		dto.setCmsElementId(elementId);
		List<CmsInstDTO> commonInstList = cmsInstReadService.findCmsInstAll(dto);
		if (EmptyUtil.isNotEmpty(commonInstList)) {
			for (CmsInstDTO ci : commonInstList) {
				Map<String, Object> instMap = new HashMap<>();
				instMap.put("id", ci.getId());
				instMap.put("name", ci.getName());
				commonInst.add(instMap);
			}
		}
		return commonInst;
	}
	
}
	