package com.egeo.components.cms.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.cms.service.write.CmsInstCfgWriteService;
import com.egeo.components.cms.manage.write.CmsInstCfgWriteManage;
import com.egeo.components.cms.converter.CmsInstCfgConverter;
import com.egeo.components.cms.dto.CmsInstCfgDTO;
import com.egeo.components.cms.po.CmsInstCfgPO;

@Service("cmsInstCfgWriteService")
public class CmsInstCfgWriteServiceImpl  implements CmsInstCfgWriteService {
	@Autowired
	private CmsInstCfgWriteManage cmsInstCfgWriteManage;

	@Override
	public Long insertCmsInstCfgWithTx(CmsInstCfgDTO dto) {
		CmsInstCfgPO po = CmsInstCfgConverter.toPO(dto);
		Long rt = cmsInstCfgWriteManage.insertCmsInstCfgWithTx(po);		
		return rt;
	}

	@Override
	public int updateCmsInstCfgWithTx(CmsInstCfgDTO dto) {
		CmsInstCfgPO po = CmsInstCfgConverter.toPO(dto);
		int rt = cmsInstCfgWriteManage.updateCmsInstCfgWithTx(po);		
		return rt;
	}

	@Override
	public int deleteCmsInstCfgWithTx(CmsInstCfgDTO dto) {
		CmsInstCfgPO po = CmsInstCfgConverter.toPO(dto);
		int rt = cmsInstCfgWriteManage.deleteCmsInstCfgWithTx(po);		
		return rt;
	}
}
	