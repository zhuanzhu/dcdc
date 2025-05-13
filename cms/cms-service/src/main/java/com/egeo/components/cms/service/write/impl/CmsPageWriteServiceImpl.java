package com.egeo.components.cms.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.cms.service.write.CmsPageWriteService;
import com.egeo.components.cms.manage.write.CmsPageWriteManage;
import com.egeo.components.cms.converter.CmsPageConverter;
import com.egeo.components.cms.dto.CmsPageDTO;
import com.egeo.components.cms.po.CmsPagePO;

@Service("cmsPageWriteService")
public class CmsPageWriteServiceImpl  implements CmsPageWriteService {
	@Autowired
	private CmsPageWriteManage cmsPageWriteManage;

	@Override
	public Long insertCmsPageWithTx(CmsPageDTO dto, String configJson) {
		CmsPagePO po = CmsPageConverter.toPO(dto);
		Long rt = cmsPageWriteManage.insertCmsPageWithTx(po, configJson, dto.getCompanyId());		
		return rt;
	}

	@Override
	public int updateCmsPageWithTx(CmsPageDTO dto, String configJson) {
		CmsPagePO po = CmsPageConverter.toPO(dto);
		int rt = cmsPageWriteManage.updateCmsPageWithTx(po, configJson, dto.getCompanyId());		
		return rt;
	}

	@Override
	public int deleteCmsPageWithTx(CmsPageDTO dto) {
		CmsPagePO po = CmsPageConverter.toPO(dto);
		int rt = cmsPageWriteManage.deleteCmsPageWithTx(po);		
		return rt;
	}

	@Override
	public int updateStatusWithTx(CmsPageDTO dto) {
		CmsPagePO po = CmsPageConverter.toPO(dto);
		return cmsPageWriteManage.updateStatusWithTx(po);
	}
}
	