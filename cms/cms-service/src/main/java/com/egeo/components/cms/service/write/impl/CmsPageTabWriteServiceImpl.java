package com.egeo.components.cms.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.cms.service.write.CmsPageTabWriteService;
import com.egeo.components.cms.manage.write.CmsPageTabWriteManage;
import com.egeo.components.cms.converter.CmsPageTabConverter;
import com.egeo.components.cms.dto.CmsPageTabDTO;
import com.egeo.components.cms.po.CmsPageTabPO;

@Service("cmsPageTabWriteService")
public class CmsPageTabWriteServiceImpl  implements CmsPageTabWriteService {
	@Autowired
	private CmsPageTabWriteManage cmsPageTabWriteManage;

	@Override
	public Long insertCmsPageTabWithTx(CmsPageTabDTO dto) {
		CmsPageTabPO po = CmsPageTabConverter.toPO(dto);
		Long rt = cmsPageTabWriteManage.insertCmsPageTabWithTx(po);		
		return rt;
	}

	@Override
	public int updateCmsPageTabWithTx(CmsPageTabDTO dto) {
		CmsPageTabPO po = CmsPageTabConverter.toPO(dto);
		int rt = cmsPageTabWriteManage.updateCmsPageTabWithTx(po);		
		return rt;
	}

	@Override
	public int deleteCmsPageTabWithTx(CmsPageTabDTO dto) {
		CmsPageTabPO po = CmsPageTabConverter.toPO(dto);
		int rt = cmsPageTabWriteManage.deleteCmsPageTabWithTx(po);		
		return rt;
	}
}
	