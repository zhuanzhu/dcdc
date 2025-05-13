package com.egeo.components.user.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.converter.VersionsConverter;
import com.egeo.components.user.dto.VersionsDTO;
import com.egeo.components.user.manage.write.VersionsWriteManage;
import com.egeo.components.user.po.VersionsPO;
import com.egeo.components.user.service.write.VersionsWriteService;

@Service("versionsWriteService")
public class VersionsWriteServiceImpl implements VersionsWriteService {
	@Autowired
	private VersionsWriteManage versionsWriteManage;

	@Override
	public Long insertVersionsWithTx(VersionsDTO dto) {
		VersionsPO po = VersionsConverter.toPO(dto);
		Long rt = versionsWriteManage.insertVersionsWithTx(po);		
		return rt;
	}

	@Override
	public int updateVersionsWithTx(VersionsDTO dto) {
		VersionsPO po = VersionsConverter.toPO(dto);
		int rt = versionsWriteManage.updateVersionsWithTx(po);		
		return rt;
	}

	@Override
	public int deleteVersionsWithTx(VersionsDTO dto) {
		VersionsPO po = VersionsConverter.toPO(dto);
		int rt = versionsWriteManage.deleteVersionsWithTx(po);		
		return rt;
	}

	@Override
	public int updateVersionsOfficialByTypeWithTx(Integer user) {
		return versionsWriteManage.updateVersionsOfficialByTypeWithTx(user);
	}
}
	