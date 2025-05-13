package com.egeo.components.cms.service.write.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.cms.service.write.TemplateWriteService;
import com.egeo.components.cms.manage.write.TemplateWriteManage;
import com.egeo.components.cms.converter.TemplateConverter;
import com.egeo.components.cms.dto.TemplateDTO;
import com.egeo.components.cms.po.TemplatePO;

@Service("templateWriteService")
public class TemplateWriteServiceImpl  implements TemplateWriteService {
	@Autowired
	private TemplateWriteManage templateWriteManage;

	@Override
	public Long insertTemplateWithTx(TemplateDTO dto) {
		TemplatePO po = TemplateConverter.toPO(dto);
		Long rt = templateWriteManage.insertTemplateWithTx(po);		
		return rt;
	}

	@Override
	public int updateTemplateWithTx(TemplateDTO dto) {
		TemplatePO po = TemplateConverter.toPO(dto);
		int rt = templateWriteManage.updateTemplateWithTx(po);		
		return rt;
	}

	@Override
	public int deleteTemplateWithTx(TemplateDTO dto) {
		TemplatePO po = TemplateConverter.toPO(dto);
		int rt = templateWriteManage.deleteTemplateWithTx(po);		
		return rt;
	}

	@Override
	public boolean useTemplate(Long platformId, Long templateId, Integer clientType, Integer type, Integer companyType) {
		
		return templateWriteManage.useTemplateWithTx(platformId,templateId, clientType, type, companyType);
	}

	@Override
	public Long createTemplate(TemplateDTO tmpl, List<Long> eleIds) {
		
		return templateWriteManage.createTemplateWithTx(TemplateConverter.toPO(tmpl),eleIds);
	}

	@Override
	public boolean editTemplate(TemplateDTO tmpl, List<Long> eleIds) {
		return templateWriteManage.editTemplateWithTx(TemplateConverter.toPO(tmpl),eleIds);
	}
}
	