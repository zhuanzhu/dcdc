package com.egeo.components.cms.service.write.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.cms.service.write.CmsInstWriteService;
import com.egeo.components.cms.manage.write.CmsInstWriteManage;
import com.egeo.components.cms.converter.CmsInstConverter;
import com.egeo.components.cms.dto.CmsInstDTO;
import com.egeo.components.cms.po.CmsInstPO;

@Service("cmsInstWriteService")
public class CmsInstWriteServiceImpl  implements CmsInstWriteService {
	@Autowired
	private CmsInstWriteManage cmsInstWriteManage;

	@Override
	public Long insertCmsInstWithTx(CmsInstDTO dto) {
		CmsInstPO po = CmsInstConverter.toPO(dto);
		Long rt = cmsInstWriteManage.insertCmsInstWithTx(po);		
		return rt;
	}

	@Override
	public int updateCmsInstWithTx(CmsInstDTO dto) {
		CmsInstPO po = CmsInstConverter.toPO(dto);
		int rt = cmsInstWriteManage.updateCmsInstWithTx(po);		
		return rt;
	}

	@Override
	public int deleteCmsInstWithTx(CmsInstDTO dto) {
		CmsInstPO po = CmsInstConverter.toPO(dto);
		int rt = cmsInstWriteManage.deleteCmsInstWithTx(po);		
		return rt;
	}

	@Override
	public Long insertCommonCmsInstWithTx(CmsInstDTO dto, List<Long> companyIdList, String configJson) {
		CmsInstPO po = CmsInstConverter.toPO(dto);
		return cmsInstWriteManage.insertCommonCmsInstWithTx(po, companyIdList, configJson);
	}
}
	