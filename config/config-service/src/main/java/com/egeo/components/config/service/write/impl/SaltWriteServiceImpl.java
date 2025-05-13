package com.egeo.components.config.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.config.converter.SaltConverter;
import com.egeo.components.config.dto.SaltDTO;
import com.egeo.components.config.manage.write.SaltWriteManage;
import com.egeo.components.config.po.SaltPO;
import com.egeo.components.config.service.write.SaltWriteService;

@Service("saltWriteService")
public class SaltWriteServiceImpl  implements SaltWriteService {
	@Autowired
	private SaltWriteManage saltWriteManage;

	@Override
	public Long insertSaltWithTx(SaltDTO dto) {
		SaltPO po = SaltConverter.toPO(dto);
		Long rt = saltWriteManage.insertSaltWithTx(po);		
		return rt;
	}

	@Override
	public int updateSaltWithTx(SaltDTO dto) {
		SaltPO po = SaltConverter.toPO(dto);
		int rt = saltWriteManage.updateSaltWithTx(po);		
		return rt;
	}

	@Override
	public int deleteSaltWithTx(SaltDTO dto) {
		SaltPO po = SaltConverter.toPO(dto);
		int rt = saltWriteManage.deleteSaltWithTx(po);		
		return rt;
	}
}
	