package com.egeo.components.cms.service.write.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.cms.service.write.CmsTemplateCfgWriteService;
import com.egeo.components.cms.manage.write.CmsTemplateCfgWriteManage;
import com.egeo.components.cms.converter.CmsTemplateCfgConverter;
import com.egeo.components.cms.dto.CmsTemplateCfgDTO;
import com.egeo.components.cms.po.CmsTemplateCfgPO;

@Service("cmsTemplateCfgWriteService")
public class CmsTemplateCfgWriteServiceImpl  implements CmsTemplateCfgWriteService {
	@Autowired
	private CmsTemplateCfgWriteManage cmsTemplateCfgWriteManage;

	@Override
	public Long insertCmsTemplateCfgWithTx(CmsTemplateCfgDTO dto) {
		CmsTemplateCfgPO po = CmsTemplateCfgConverter.toPO(dto);
		Long rt = cmsTemplateCfgWriteManage.insertCmsTemplateCfgWithTx(po);		
		return rt;
	}

	@Override
	public int updateCmsTemplateCfgWithTx(CmsTemplateCfgDTO dto) {
		CmsTemplateCfgPO po = CmsTemplateCfgConverter.toPO(dto);
		int rt = cmsTemplateCfgWriteManage.updateCmsTemplateCfgWithTx(po);		
		return rt;
	}

	@Override
	public int deleteCmsTemplateCfgWithTx(CmsTemplateCfgDTO dto) {
		CmsTemplateCfgPO po = CmsTemplateCfgConverter.toPO(dto);
		int rt = cmsTemplateCfgWriteManage.deleteCmsTemplateCfgWithTx(po);		
		return rt;
	}

	@Override
	public int insertBatchWithTx(List<CmsTemplateCfgDTO> dtos) {
		List<CmsTemplateCfgPO> pos = CmsTemplateCfgConverter.toPO(dtos);
		return cmsTemplateCfgWriteManage.insertBatchWithTx(pos);
	}
}
	