package com.egeo.components.user.service.write;

import com.egeo.components.user.dto.SessionReferrerDTO;


public interface SessionReferrerWriteService {

	public Long insertSessionReferrerWithTx(SessionReferrerDTO dto);

	public int updateSessionReferrerWithTx(SessionReferrerDTO dto);

	public int deleteSessionReferrerWithTx(SessionReferrerDTO dto);
}
	