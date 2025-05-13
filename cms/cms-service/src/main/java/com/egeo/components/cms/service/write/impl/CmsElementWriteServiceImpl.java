package com.egeo.components.cms.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.cms.service.write.CmsElementWriteService;
import com.egeo.components.cms.manage.write.CmsElementWriteManage;
import com.egeo.components.cms.converter.CmsElementConverter;
import com.egeo.components.cms.dto.CmsElementDTO;
import com.egeo.components.cms.po.CmsElementPO;

@Service("cmsElementWriteService")
public class CmsElementWriteServiceImpl  implements CmsElementWriteService {
	@Autowired
	private CmsElementWriteManage cmsElementWriteManage;

	@Override
	public Long insertCmsElementWithTx(CmsElementDTO dto) {
		CmsElementPO po = CmsElementConverter.toPO(dto);
		Long rt = cmsElementWriteManage.insertCmsElementWithTx(po);		
		return rt;
	}

	@Override
	public int updateCmsElementWithTx(CmsElementDTO dto) {
		CmsElementPO po = CmsElementConverter.toPO(dto);
		int rt = cmsElementWriteManage.updateCmsElementWithTx(po);		
		return rt;
	}

	@Override
	public int deleteCmsElementWithTx(CmsElementDTO dto) {
		CmsElementPO po = CmsElementConverter.toPO(dto);
		int rt = cmsElementWriteManage.deleteCmsElementWithTx(po);		
		return rt;
	}
}
	