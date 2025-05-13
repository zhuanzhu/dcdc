package com.egeo.components.user.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.converter.SessionReferrerConverter;
import com.egeo.components.user.dto.SessionReferrerDTO;
import com.egeo.components.user.manage.write.SessionReferrerWriteManage;
import com.egeo.components.user.po.SessionReferrerPO;
import com.egeo.components.user.service.write.SessionReferrerWriteService;

@Service("sessionReferrerWriteService")
public class SessionReferrerWriteServiceImpl implements SessionReferrerWriteService {
	@Autowired
	private SessionReferrerWriteManage sessionReferrerWriteManage;

	@Override
	public Long insertSessionReferrerWithTx(SessionReferrerDTO dto) {
		SessionReferrerPO po = SessionReferrerConverter.toPO(dto);
		Long rt = sessionReferrerWriteManage.insertSessionReferrerWithTx(po);		
		return rt;
	}

	@Override
	public int updateSessionReferrerWithTx(SessionReferrerDTO dto) {
		SessionReferrerPO po = SessionReferrerConverter.toPO(dto);
		int rt = sessionReferrerWriteManage.updateSessionReferrerWithTx(po);		
		return rt;
	}

	@Override
	public int deleteSessionReferrerWithTx(SessionReferrerDTO dto) {
		SessionReferrerPO po = SessionReferrerConverter.toPO(dto);
		int rt = sessionReferrerWriteManage.deleteSessionReferrerWithTx(po);		
		return rt;
	}
}
	