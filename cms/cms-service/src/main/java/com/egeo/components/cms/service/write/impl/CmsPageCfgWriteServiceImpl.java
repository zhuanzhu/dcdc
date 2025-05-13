package com.egeo.components.cms.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.cms.service.write.CmsPageCfgWriteService;
import com.egeo.components.cms.manage.write.CmsPageCfgWriteManage;
import com.egeo.components.cms.converter.CmsPageCfgConverter;
import com.egeo.components.cms.dto.CmsPageCfgDTO;
import com.egeo.components.cms.po.CmsPageCfgPO;

@Service("cmsPageCfgWriteService")
public class CmsPageCfgWriteServiceImpl  implements CmsPageCfgWriteService {
	@Autowired
	private CmsPageCfgWriteManage cmsPageCfgWriteManage;

	@Override
	public Long insertCmsPageCfgWithTx(CmsPageCfgDTO dto) {
		CmsPageCfgPO po = CmsPageCfgConverter.toPO(dto);
		Long rt = cmsPageCfgWriteManage.insertCmsPageCfgWithTx(po);		
		return rt;
	}

	@Override
	public int updateCmsPageCfgWithTx(CmsPageCfgDTO dto) {
		CmsPageCfgPO po = CmsPageCfgConverter.toPO(dto);
		int rt = cmsPageCfgWriteManage.updateCmsPageCfgWithTx(po);		
		return rt;
	}

	@Override
	public int deleteCmsPageCfgWithTx(CmsPageCfgDTO dto) {
		CmsPageCfgPO po = CmsPageCfgConverter.toPO(dto);
		int rt = cmsPageCfgWriteManage.deleteCmsPageCfgWithTx(po);		
		return rt;
	}
}
	