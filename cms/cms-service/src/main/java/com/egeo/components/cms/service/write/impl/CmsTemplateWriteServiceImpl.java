package com.egeo.components.cms.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.cms.service.write.CmsTemplateWriteService;
import com.egeo.components.cms.manage.write.CmsTemplateWriteManage;
import com.egeo.components.cms.converter.CmsTemplateConverter;
import com.egeo.components.cms.dto.CmsTemplateDTO;
import com.egeo.components.cms.po.CmsTemplatePO;

@Service("cmsTemplateWriteService")
public class CmsTemplateWriteServiceImpl  implements CmsTemplateWriteService {
	@Autowired
	private CmsTemplateWriteManage cmsTemplateWriteManage;

	@Override
	public Long insertCmsTemplateWithTx(CmsTemplateDTO dto) {
		CmsTemplatePO po = CmsTemplateConverter.toPO(dto);
		Long rt = cmsTemplateWriteManage.insertCmsTemplateWithTx(po);		
		return rt;
	}

	@Override
	public int updateCmsTemplateWithTx(CmsTemplateDTO dto) {
		CmsTemplatePO po = CmsTemplateConverter.toPO(dto);
		int rt = cmsTemplateWriteManage.updateCmsTemplateWithTx(po);		
		return rt;
	}

	@Override
	public int deleteCmsTemplateWithTx(CmsTemplateDTO dto) {
		CmsTemplatePO po = CmsTemplateConverter.toPO(dto);
		int rt = cmsTemplateWriteManage.deleteCmsTemplateWithTx(po);		
		return rt;
	}
}
	