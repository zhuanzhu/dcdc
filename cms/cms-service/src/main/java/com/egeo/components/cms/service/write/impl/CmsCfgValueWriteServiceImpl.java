package com.egeo.components.cms.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.cms.service.write.CmsCfgValueWriteService;
import com.egeo.components.cms.manage.write.CmsCfgValueWriteManage;
import com.egeo.components.cms.converter.CmsCfgValueConverter;
import com.egeo.components.cms.dto.CmsCfgValueDTO;
import com.egeo.components.cms.po.CmsCfgValuePO;

@Service("cmsCfgValueWriteService")
public class CmsCfgValueWriteServiceImpl  implements CmsCfgValueWriteService {
	@Autowired
	private CmsCfgValueWriteManage cmsCfgValueWriteManage;

	@Override
	public Long insertCmsCfgValueWithTx(CmsCfgValueDTO dto) {
		CmsCfgValuePO po = CmsCfgValueConverter.toPO(dto);
		Long rt = cmsCfgValueWriteManage.insertCmsCfgValueWithTx(po);		
		return rt;
	}

	@Override
	public int updateCmsCfgValueWithTx(CmsCfgValueDTO dto) {
		CmsCfgValuePO po = CmsCfgValueConverter.toPO(dto);
		int rt = cmsCfgValueWriteManage.updateCmsCfgValueWithTx(po);		
		return rt;
	}

	@Override
	public int deleteCmsCfgValueWithTx(CmsCfgValueDTO dto) {
		CmsCfgValuePO po = CmsCfgValueConverter.toPO(dto);
		int rt = cmsCfgValueWriteManage.deleteCmsCfgValueWithTx(po);		
		return rt;
	}
}
	