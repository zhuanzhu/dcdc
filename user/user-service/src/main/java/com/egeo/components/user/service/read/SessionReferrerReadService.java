package com.egeo.components.user.service.read;


import java.util.List;
	
import com.egeo.components.user.dto.SessionReferrerDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface SessionReferrerReadService {

	public SessionReferrerDTO findSessionReferrerById(SessionReferrerDTO dto);

	public PageResult<SessionReferrerDTO> findSessionReferrerOfPage(SessionReferrerDTO dto,Pagination page);

	public List<SessionReferrerDTO> findSessionReferrerAll(SessionReferrerDTO dto);
}
	