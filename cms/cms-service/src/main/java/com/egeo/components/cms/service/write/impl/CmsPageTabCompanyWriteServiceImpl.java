package com.egeo.components.cms.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.cms.service.write.CmsPageTabCompanyWriteService;
import com.egeo.components.cms.manage.write.CmsPageTabCompanyWriteManage;
import com.egeo.components.cms.converter.CmsPageTabCompanyConverter;
import com.egeo.components.cms.dto.CmsPageTabCompanyDTO;
import com.egeo.components.cms.po.CmsPageTabCompanyPO;

@Service("cmsPageTabCompanyWriteService")
public class CmsPageTabCompanyWriteServiceImpl  implements CmsPageTabCompanyWriteService {
	@Autowired
	private CmsPageTabCompanyWriteManage cmsPageTabCompanyWriteManage;

	@Override
	public Long insertCmsPageTabCompanyWithTx(CmsPageTabCompanyDTO dto) {
		CmsPageTabCompanyPO po = CmsPageTabCompanyConverter.toPO(dto);
		Long rt = cmsPageTabCompanyWriteManage.insertCmsPageTabCompanyWithTx(po);		
		return rt;
	}

	@Override
	public int updateCmsPageTabCompanyWithTx(CmsPageTabCompanyDTO dto) {
		CmsPageTabCompanyPO po = CmsPageTabCompanyConverter.toPO(dto);
		int rt = cmsPageTabCompanyWriteManage.updateCmsPageTabCompanyWithTx(po);		
		return rt;
	}

	@Override
	public int deleteCmsPageTabCompanyWithTx(CmsPageTabCompanyDTO dto) {
		CmsPageTabCompanyPO po = CmsPageTabCompanyConverter.toPO(dto);
		int rt = cmsPageTabCompanyWriteManage.deleteCmsPageTabCompanyWithTx(po);		
		return rt;
	}
}
	