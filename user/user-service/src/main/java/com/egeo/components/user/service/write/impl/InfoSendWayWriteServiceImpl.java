package com.egeo.components.user.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.converter.InfoSendWayConverter;
import com.egeo.components.user.dto.InfoSendWayDTO;
import com.egeo.components.user.manage.write.InfoSendWayWriteManage;
import com.egeo.components.user.po.InfoSendWayPO;
import com.egeo.components.user.service.write.InfoSendWayWriteService;

@Service("infoSendWayWriteService")
public class InfoSendWayWriteServiceImpl implements InfoSendWayWriteService {
	@Autowired
	private InfoSendWayWriteManage infoSendWayWriteManage;

	@Override
	public Long insertInfoSendWayWithTx(InfoSendWayDTO dto) {
		InfoSendWayPO po = InfoSendWayConverter.toPO(dto);
		Long rt = infoSendWayWriteManage.insertInfoSendWayWithTx(po);		
		return rt;
	}

	@Override
	public int updateInfoSendWayWithTx(InfoSendWayDTO dto) {
		InfoSendWayPO po = InfoSendWayConverter.toPO(dto);
		int rt = infoSendWayWriteManage.updateInfoSendWayWithTx(po);		
		return rt;
	}

	@Override
	public int deleteInfoSendWayWithTx(InfoSendWayDTO dto) {
		InfoSendWayPO po = InfoSendWayConverter.toPO(dto);
		int rt = infoSendWayWriteManage.deleteInfoSendWayWithTx(po);		
		return rt;
	}
}
	