package com.egeo.components.cms.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.cms.service.read.CmsCfgKeyReadService;
import com.egeo.components.cms.service.read.CmsCfgValueReadService;
import com.egeo.components.cms.service.write.CmsCfgKeyWriteService;
import com.egeo.components.cms.dto.CmsCfgKeyDTO;
import com.egeo.components.cms.dto.CmsCfgValueDTO;
import com.egeo.utils.EmptyUtil;


@Component
public class CmsCfgKeyFacade {
	
	@Resource
	private CmsCfgKeyReadService cmsCfgKeyReadService;
	
	@Resource
	private CmsCfgKeyWriteService cmsCfgKeyWriteService;
	
	@Resource
	private CmsCfgValueReadService cmsCfgValueReadService;
	
	public List<CmsCfgKeyDTO> findElementCfgKeyByTemplateId(Long templateId) {
		return cmsCfgKeyReadService.findElementCfgKeyByTemplateId(templateId);
	}
	
	public List<CmsCfgKeyDTO> findTemplateCfgKeyByTemplateId(Long templateId) {
		return cmsCfgKeyReadService.findTemplateCfgKeyByTemplateId(templateId);
	}
	
	public CmsCfgValueDTO findCmsCfgValueByCode(Long cfgKeyId, String code) {
		CmsCfgValueDTO dto = new CmsCfgValueDTO();
		dto.setCfgKeyId(cfgKeyId);
		dto.setCode(code);
		List<CmsCfgValueDTO> list = cmsCfgValueReadService.findCmsCfgValueAll(dto);
		return EmptyUtil.isEmpty(list) ? new CmsCfgValueDTO() : list.get(0);
	}
	
	public List<CmsCfgKeyDTO> findElementBasicConfig() {
		CmsCfgKeyDTO cfgKey = new CmsCfgKeyDTO();
		cfgKey.setCfgKind(2);
		return cmsCfgKeyReadService.findCmsCfgKeyAll(cfgKey);
	}
	
}
	