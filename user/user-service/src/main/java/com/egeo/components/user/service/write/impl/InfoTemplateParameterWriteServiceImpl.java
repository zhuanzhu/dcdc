package com.egeo.components.user.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.converter.InfoTemplateParameterConverter;
import com.egeo.components.user.dto.InfoTemplateParameterDTO;
import com.egeo.components.user.manage.write.InfoTemplateParameterWriteManage;
import com.egeo.components.user.po.InfoTemplateParameterPO;
import com.egeo.components.user.service.write.InfoTemplateParameterWriteService;

@Service("infoTemplateParameterWriteService")
public class InfoTemplateParameterWriteServiceImpl implements InfoTemplateParameterWriteService {
	@Autowired
	private InfoTemplateParameterWriteManage infoTemplateParameterWriteManage;

	@Override
	public Long insertInfoTemplateParameterWithTx(InfoTemplateParameterDTO dto) {
		InfoTemplateParameterPO po = InfoTemplateParameterConverter.toPO(dto);
		Long rt = infoTemplateParameterWriteManage.insertInfoTemplateParameterWithTx(po);		
		return rt;
	}

	@Override
	public int updateInfoTemplateParameterWithTx(InfoTemplateParameterDTO dto) {
		InfoTemplateParameterPO po = InfoTemplateParameterConverter.toPO(dto);
		int rt = infoTemplateParameterWriteManage.updateInfoTemplateParameterWithTx(po);		
		return rt;
	}

	@Override
	public int deleteInfoTemplateParameterWithTx(InfoTemplateParameterDTO dto) {
		InfoTemplateParameterPO po = InfoTemplateParameterConverter.toPO(dto);
		int rt = infoTemplateParameterWriteManage.deleteInfoTemplateParameterWithTx(po);		
		return rt;
	}
}
	