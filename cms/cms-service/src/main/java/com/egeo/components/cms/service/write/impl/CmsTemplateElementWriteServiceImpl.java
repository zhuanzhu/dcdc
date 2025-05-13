package com.egeo.components.cms.service.write.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.cms.service.write.CmsTemplateElementWriteService;
import com.egeo.components.cms.manage.write.CmsTemplateElementWriteManage;
import com.egeo.components.cms.converter.CmsTemplateElementConverter;
import com.egeo.components.cms.dto.CmsTemplateElementDTO;
import com.egeo.components.cms.po.CmsTemplateElementPO;

@Service("cmsTemplateElementWriteService")
public class CmsTemplateElementWriteServiceImpl  implements CmsTemplateElementWriteService {
	@Autowired
	private CmsTemplateElementWriteManage cmsTemplateElementWriteManage;

	@Override
	public Long insertCmsTemplateElementWithTx(CmsTemplateElementDTO dto) {
		CmsTemplateElementPO po = CmsTemplateElementConverter.toPO(dto);
		Long rt = cmsTemplateElementWriteManage.insertCmsTemplateElementWithTx(po);		
		return rt;
	}

	@Override
	public int updateCmsTemplateElementWithTx(CmsTemplateElementDTO dto) {
		CmsTemplateElementPO po = CmsTemplateElementConverter.toPO(dto);
		int rt = cmsTemplateElementWriteManage.updateCmsTemplateElementWithTx(po);		
		return rt;
	}

	@Override
	public int deleteCmsTemplateElementWithTx(CmsTemplateElementDTO dto) {
		CmsTemplateElementPO po = CmsTemplateElementConverter.toPO(dto);
		int rt = cmsTemplateElementWriteManage.deleteCmsTemplateElementWithTx(po);		
		return rt;
	}

	@Override
	public void insertBatchWithTx(List<CmsTemplateElementDTO> cmsTemplateElementDTOs) {
		List<CmsTemplateElementPO> pos = CmsTemplateElementConverter.toPO(cmsTemplateElementDTOs);
		cmsTemplateElementWriteManage.insertBatchWithTx(pos);		
	}
}
	