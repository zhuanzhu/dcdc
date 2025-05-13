package com.egeo.components.cms.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.cms.service.write.ExternalLinkWriteService;
import com.egeo.components.cms.manage.write.ExternalLinkWriteManage;
import com.egeo.components.cms.converter.ExternalLinkConverter;
import com.egeo.components.cms.dto.ExternalLinkDTO;
import com.egeo.components.cms.po.ExternalLinkPO;

@Service("externalLinkWriteService")
public class ExternalLinkWriteServiceImpl  implements ExternalLinkWriteService {
	@Autowired
	private ExternalLinkWriteManage externalLinkWriteManage;

	@Override
	public Long insertExternalLinkWithTx(ExternalLinkDTO dto) {
		ExternalLinkPO po = ExternalLinkConverter.toPO(dto);
		Long rt = externalLinkWriteManage.insertExternalLinkWithTx(po);		
		return rt;
	}

	@Override
	public int updateExternalLinkWithTx(ExternalLinkDTO dto) {
		ExternalLinkPO po = ExternalLinkConverter.toPO(dto);
		int rt = externalLinkWriteManage.updateExternalLinkWithTx(po);		
		return rt;
	}

	@Override
	public int deleteExternalLinkWithTx(ExternalLinkDTO dto) {
		ExternalLinkPO po = ExternalLinkConverter.toPO(dto);
		int rt = externalLinkWriteManage.deleteExternalLinkWithTx(po);		
		return rt;
	}
}
	