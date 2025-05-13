package com.egeo.components.user.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.converter.VersionsChildConverter;
import com.egeo.components.user.dto.VersionsChildDTO;
import com.egeo.components.user.manage.write.VersionsChildWriteManage;
import com.egeo.components.user.po.VersionsChildPO;
import com.egeo.components.user.service.write.VersionsChildWriteService;

@Service("versionsChildWriteService")
public class VersionsChildWriteServiceImpl implements VersionsChildWriteService {
	@Autowired
	private VersionsChildWriteManage versionsChildWriteManage;

	@Override
	public Long insertVersionsChildWithTx(VersionsChildDTO dto) {
		VersionsChildPO po = VersionsChildConverter.toPO(dto);
		Long rt = versionsChildWriteManage.insertVersionsChildWithTx(po);		
		return rt;
	}

	@Override
	public int updateVersionsChildWithTx(VersionsChildDTO dto) {
		VersionsChildPO po = VersionsChildConverter.toPO(dto);
		int rt = versionsChildWriteManage.updateVersionsChildWithTx(po);		
		return rt;
	}

	@Override
	public int deleteVersionsChildWithTx(VersionsChildDTO dto) {
		VersionsChildPO po = VersionsChildConverter.toPO(dto);
		int rt = versionsChildWriteManage.deleteVersionsChildWithTx(po);		
		return rt;
	}
}
	