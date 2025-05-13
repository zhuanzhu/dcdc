package com.egeo.components.user.service.write;

import com.egeo.components.user.dto.InfoTemplateParameterDTO;


public interface InfoTemplateParameterWriteService {

	public Long insertInfoTemplateParameterWithTx(InfoTemplateParameterDTO dto);

	public int updateInfoTemplateParameterWithTx(InfoTemplateParameterDTO dto);

	public int deleteInfoTemplateParameterWithTx(InfoTemplateParameterDTO dto);
}
	