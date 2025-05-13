package com.egeo.components.cms.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.cms.service.write.CmsPageCompanyWriteService;
import com.egeo.components.cms.manage.write.CmsPageCompanyWriteManage;
import com.egeo.components.cms.converter.CmsPageCompanyConverter;
import com.egeo.components.cms.dto.CmsPageCompanyDTO;
import com.egeo.components.cms.po.CmsPageCompanyPO;

@Service("cmsPageCompanyWriteService")
public class CmsPageCompanyWriteServiceImpl  implements CmsPageCompanyWriteService {
	@Autowired
	private CmsPageCompanyWriteManage cmsPageCompanyWriteManage;

	@Override
	public Long insertCmsPageCompanyWithTx(CmsPageCompanyDTO dto) {
		CmsPageCompanyPO po = CmsPageCompanyConverter.toPO(dto);
		Long rt = cmsPageCompanyWriteManage.insertCmsPageCompanyWithTx(po);		
		return rt;
	}

	@Override
	public int updateCmsPageCompanyWithTx(CmsPageCompanyDTO dto) {
		CmsPageCompanyPO po = CmsPageCompanyConverter.toPO(dto);
		int rt = cmsPageCompanyWriteManage.updateCmsPageCompanyWithTx(po);		
		return rt;
	}

	@Override
	public int deleteCmsPageCompanyWithTx(CmsPageCompanyDTO dto) {
		CmsPageCompanyPO po = CmsPageCompanyConverter.toPO(dto);
		int rt = cmsPageCompanyWriteManage.deleteCmsPageCompanyWithTx(po);		
		return rt;
	}
}
	