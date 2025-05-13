package com.egeo.components.config.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.config.converter.PolymallUserConverter;
import com.egeo.components.config.dto.PolymallUserDTO;
import com.egeo.components.config.manage.write.PolymallUserWriteManage;
import com.egeo.components.config.po.PolymallUserPO;
import com.egeo.components.config.service.write.PolymallUserWriteService;

@Service("polymallUserWriteService")
public class PolymallUserWriteServiceImpl implements PolymallUserWriteService {
	@Autowired
	private PolymallUserWriteManage polymallUserWriteManage;

	@Override
	public Long insertPolymallUserWithTx(PolymallUserDTO dto) {
		PolymallUserPO po = PolymallUserConverter.toPO(dto);
		Long rt = polymallUserWriteManage.insertPolymallUserWithTx(po);		
		return rt;
	}

	@Override
	public int updatePolymallUserWithTx(PolymallUserDTO dto) {
		PolymallUserPO po = PolymallUserConverter.toPO(dto);
		int rt = polymallUserWriteManage.updatePolymallUserWithTx(po);		
		return rt;
	}

	@Override
	public int deletePolymallUserWithTx(PolymallUserDTO dto) {
		PolymallUserPO po = PolymallUserConverter.toPO(dto);
		int rt = polymallUserWriteManage.deletePolymallUserWithTx(po);		
		return rt;
	}
}
	