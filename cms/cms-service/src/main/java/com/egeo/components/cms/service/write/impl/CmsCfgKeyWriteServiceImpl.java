package com.egeo.components.cms.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.cms.service.write.CmsCfgKeyWriteService;
import com.egeo.components.cms.manage.write.CmsCfgKeyWriteManage;
import com.egeo.components.cms.converter.CmsCfgKeyConverter;
import com.egeo.components.cms.dto.CmsCfgKeyDTO;
import com.egeo.components.cms.po.CmsCfgKeyPO;

@Service("cmsCfgKeyWriteService")
public class CmsCfgKeyWriteServiceImpl  implements CmsCfgKeyWriteService {
	@Autowired
	private CmsCfgKeyWriteManage cmsCfgKeyWriteManage;

	@Override
	public Long insertCmsCfgKeyWithTx(CmsCfgKeyDTO dto) {
		CmsCfgKeyPO po = CmsCfgKeyConverter.toPO(dto);
		Long rt = cmsCfgKeyWriteManage.insertCmsCfgKeyWithTx(po);		
		return rt;
	}

	@Override
	public int updateCmsCfgKeyWithTx(CmsCfgKeyDTO dto) {
		CmsCfgKeyPO po = CmsCfgKeyConverter.toPO(dto);
		int rt = cmsCfgKeyWriteManage.updateCmsCfgKeyWithTx(po);		
		return rt;
	}

	@Override
	public int deleteCmsCfgKeyWithTx(CmsCfgKeyDTO dto) {
		CmsCfgKeyPO po = CmsCfgKeyConverter.toPO(dto);
		int rt = cmsCfgKeyWriteManage.deleteCmsCfgKeyWithTx(po);		
		return rt;
	}
}
	