package com.egeo.components.user.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.converter.SendWayTypeConverter;
import com.egeo.components.user.dto.SendWayTypeDTO;
import com.egeo.components.user.manage.write.SendWayTypeWriteManage;
import com.egeo.components.user.po.SendWayTypePO;
import com.egeo.components.user.service.write.SendWayTypeWriteService;

@Service("sendWayTypeWriteService")
public class SendWayTypeWriteServiceImpl implements SendWayTypeWriteService {
	@Autowired
	private SendWayTypeWriteManage sendWayTypeWriteManage;

	@Override
	public Long insertSendWayTypeWithTx(SendWayTypeDTO dto) {
		SendWayTypePO po = SendWayTypeConverter.toPO(dto);
		Long rt = sendWayTypeWriteManage.insertSendWayTypeWithTx(po);		
		return rt;
	}

	@Override
	public int updateSendWayTypeWithTx(SendWayTypeDTO dto) {
		SendWayTypePO po = SendWayTypeConverter.toPO(dto);
		int rt = sendWayTypeWriteManage.updateSendWayTypeWithTx(po);		
		return rt;
	}

	@Override
	public int deleteSendWayTypeWithTx(SendWayTypeDTO dto) {
		SendWayTypePO po = SendWayTypeConverter.toPO(dto);
		int rt = sendWayTypeWriteManage.deleteSendWayTypeWithTx(po);		
		return rt;
	}
}
	