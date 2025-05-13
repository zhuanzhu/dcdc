package com.egeo.components.cms.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.cms.service.write.CmsInstCompanyWriteService;
import com.egeo.components.cms.manage.write.CmsInstCompanyWriteManage;
import com.egeo.components.cms.converter.CmsInstCompanyConverter;
import com.egeo.components.cms.dto.CmsInstCompanyDTO;
import com.egeo.components.cms.po.CmsInstCompanyPO;

@Service("cmsInstCompanyWriteService")
public class CmsInstCompanyWriteServiceImpl  implements CmsInstCompanyWriteService {
	@Autowired
	private CmsInstCompanyWriteManage cmsInstCompanyWriteManage;

	@Override
	public Long insertCmsInstCompanyWithTx(CmsInstCompanyDTO dto) {
		CmsInstCompanyPO po = CmsInstCompanyConverter.toPO(dto);
		Long rt = cmsInstCompanyWriteManage.insertCmsInstCompanyWithTx(po);		
		return rt;
	}

	@Override
	public int updateCmsInstCompanyWithTx(CmsInstCompanyDTO dto) {
		CmsInstCompanyPO po = CmsInstCompanyConverter.toPO(dto);
		int rt = cmsInstCompanyWriteManage.updateCmsInstCompanyWithTx(po);		
		return rt;
	}

	@Override
	public int deleteCmsInstCompanyWithTx(CmsInstCompanyDTO dto) {
		CmsInstCompanyPO po = CmsInstCompanyConverter.toPO(dto);
		int rt = cmsInstCompanyWriteManage.deleteCmsInstCompanyWithTx(po);		
		return rt;
	}
}
	