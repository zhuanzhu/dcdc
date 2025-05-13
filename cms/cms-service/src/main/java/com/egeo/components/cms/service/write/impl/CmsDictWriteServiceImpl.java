package com.egeo.components.cms.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.cms.service.write.CmsDictWriteService;
import com.egeo.components.cms.manage.write.CmsDictWriteManage;
import com.egeo.components.cms.converter.CmsDictConverter;
import com.egeo.components.cms.dto.CmsDictDTO;
import com.egeo.components.cms.po.CmsDictPO;

@Service("cmsDictWriteService")
public class CmsDictWriteServiceImpl  implements CmsDictWriteService {
	@Autowired
	private CmsDictWriteManage cmsDictWriteManage;

	@Override
	public Long insertCmsDictWithTx(CmsDictDTO dto) {
		CmsDictPO po = CmsDictConverter.toPO(dto);
		Long rt = cmsDictWriteManage.insertCmsDictWithTx(po);		
		return rt;
	}

	@Override
	public int updateCmsDictWithTx(CmsDictDTO dto) {
		CmsDictPO po = CmsDictConverter.toPO(dto);
		int rt = cmsDictWriteManage.updateCmsDictWithTx(po);		
		return rt;
	}

	@Override
	public int deleteCmsDictWithTx(CmsDictDTO dto) {
		CmsDictPO po = CmsDictConverter.toPO(dto);
		int rt = cmsDictWriteManage.deleteCmsDictWithTx(po);		
		return rt;
	}
}
	