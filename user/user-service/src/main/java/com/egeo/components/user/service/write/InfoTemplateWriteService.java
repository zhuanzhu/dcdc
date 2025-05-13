package com.egeo.components.user.service.write;

import java.util.List;

import com.egeo.components.user.dto.InfoTemplateDTO;


public interface InfoTemplateWriteService {

	public Long insertInfoTemplateWithTx(InfoTemplateDTO dto);

	public int updateInfoTemplateWithTx(InfoTemplateDTO dto,List<Long> sendWayIds);

	public int deleteInfoTemplateWithTx(InfoTemplateDTO dto);
	/**
	 * 根据消息模版id启用停用消息模版
	 * @param infoTemplateId
	 * @return
	 */
	public int isStartByIdWithTx(Long infoTemplateId);
}
	