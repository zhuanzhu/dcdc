package com.egeo.components.user.service.write.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.converter.InfoTemplateConverter;
import com.egeo.components.user.dto.InfoTemplateDTO;
import com.egeo.components.user.manage.write.InfoTemplateWriteManage;
import com.egeo.components.user.po.InfoTemplatePO;
import com.egeo.components.user.service.write.InfoTemplateWriteService;


@Service("infoTemplateWriteService")
public class InfoTemplateWriteServiceImpl implements InfoTemplateWriteService {
	@Autowired
	private InfoTemplateWriteManage infoTemplateWriteManage;

	@Override
	public Long insertInfoTemplateWithTx(InfoTemplateDTO dto) {
		InfoTemplatePO po = InfoTemplateConverter.toPO(dto);
		Long rt = infoTemplateWriteManage.insertInfoTemplateWithTx(po);		
		return rt;
	}

	@Override
	public int updateInfoTemplateWithTx(InfoTemplateDTO dto,List<Long> sendWayIds) {
		InfoTemplatePO po = InfoTemplateConverter.toPO(dto);
		int rt = infoTemplateWriteManage.updateInfoTemplateWithTx(po,sendWayIds);		
		return rt;
	}

	@Override
	public int deleteInfoTemplateWithTx(InfoTemplateDTO dto) {
		InfoTemplatePO po = InfoTemplateConverter.toPO(dto);
		int rt = infoTemplateWriteManage.deleteInfoTemplateWithTx(po);		
		return rt;
	}

	@Override
	public int isStartByIdWithTx(Long infoTemplateId) {
		// TODO Auto-generated method stub
		return infoTemplateWriteManage.isStartByIdWithTx(infoTemplateId);
	}
}
	