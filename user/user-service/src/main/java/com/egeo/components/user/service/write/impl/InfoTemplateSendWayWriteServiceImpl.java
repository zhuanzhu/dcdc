package com.egeo.components.user.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.converter.InfoTemplateSendWayConverter;
import com.egeo.components.user.dto.InfoTemplateSendWayDTO;
import com.egeo.components.user.manage.write.InfoTemplateSendWayWriteManage;
import com.egeo.components.user.po.InfoTemplateSendWayPO;
import com.egeo.components.user.service.write.InfoTemplateSendWayWriteService;

@Service("infoTemplateSendWayWriteService")
public class InfoTemplateSendWayWriteServiceImpl implements InfoTemplateSendWayWriteService {
	@Autowired
	private InfoTemplateSendWayWriteManage infoTemplateSendWayWriteManage;

	@Override
	public Long insertInfoTemplateSendWayWithTx(InfoTemplateSendWayDTO dto) {
		InfoTemplateSendWayPO po = InfoTemplateSendWayConverter.toPO(dto);
		Long rt = infoTemplateSendWayWriteManage.insertInfoTemplateSendWayWithTx(po);		
		return rt;
	}

	@Override
	public int updateInfoTemplateSendWayWithTx(InfoTemplateSendWayDTO dto) {
		InfoTemplateSendWayPO po = InfoTemplateSendWayConverter.toPO(dto);
		int rt = infoTemplateSendWayWriteManage.updateInfoTemplateSendWayWithTx(po);		
		return rt;
	}

	@Override
	public int deleteInfoTemplateSendWayWithTx(InfoTemplateSendWayDTO dto) {
		InfoTemplateSendWayPO po = InfoTemplateSendWayConverter.toPO(dto);
		int rt = infoTemplateSendWayWriteManage.deleteInfoTemplateSendWayWithTx(po);		
		return rt;
	}
}
	