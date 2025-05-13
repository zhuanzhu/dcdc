package com.egeo.components.user.service.write;

import com.egeo.components.user.dto.InfoTemplateSendWayDTO;


public interface InfoTemplateSendWayWriteService {

	public Long insertInfoTemplateSendWayWithTx(InfoTemplateSendWayDTO dto);

	public int updateInfoTemplateSendWayWithTx(InfoTemplateSendWayDTO dto);

	public int deleteInfoTemplateSendWayWithTx(InfoTemplateSendWayDTO dto);
}
	