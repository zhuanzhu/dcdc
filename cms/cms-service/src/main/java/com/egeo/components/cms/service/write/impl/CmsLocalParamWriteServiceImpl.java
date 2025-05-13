package com.egeo.components.cms.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.cms.service.write.CmsLocalParamWriteService;
import com.egeo.components.cms.manage.write.CmsLocalParamWriteManage;
import com.egeo.components.cms.converter.CmsLocalParamConverter;
import com.egeo.components.cms.dto.CmsLocalParamDTO;
import com.egeo.components.cms.po.CmsLocalParamPO;

@Service("cmsLocalParamWriteService")
public class CmsLocalParamWriteServiceImpl  implements CmsLocalParamWriteService {
	@Autowired
	private CmsLocalParamWriteManage cmsLocalParamWriteManage;

	@Override
	public Long insertCmsLocalParamWithTx(CmsLocalParamDTO dto) {
		CmsLocalParamPO po = CmsLocalParamConverter.toPO(dto);
		Long rt = cmsLocalParamWriteManage.insertCmsLocalParamWithTx(po);		
		return rt;
	}

	@Override
	public int updateCmsLocalParamWithTx(CmsLocalParamDTO dto) {
		CmsLocalParamPO po = CmsLocalParamConverter.toPO(dto);
		int rt = cmsLocalParamWriteManage.updateCmsLocalParamWithTx(po);		
		return rt;
	}

	@Override
	public int deleteCmsLocalParamWithTx(CmsLocalParamDTO dto) {
		CmsLocalParamPO po = CmsLocalParamConverter.toPO(dto);
		int rt = cmsLocalParamWriteManage.deleteCmsLocalParamWithTx(po);		
		return rt;
	}
}
	