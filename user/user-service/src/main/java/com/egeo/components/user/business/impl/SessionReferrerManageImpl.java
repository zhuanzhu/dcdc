package com.egeo.components.user.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.user.business.SessionReferrerManage;
import com.egeo.components.user.facade.SessionReferrerFacade;
import com.egeo.components.user.dto.SessionReferrerDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("sessionReferrer")
public class SessionReferrerManageImpl implements SessionReferrerManage{

	
	@Resource(name="sessionReferrerFacade")
	private SessionReferrerFacade sessionReferrerFacade;

	@Override
	public SessionReferrerDTO findSessionReferrerById(SessionReferrerDTO dto) {
		return sessionReferrerFacade.findSessionReferrerById(dto);
	}

	@Override
	public PageResult<SessionReferrerDTO> findSessionReferrerOfPage(SessionReferrerDTO dto, Pagination page) {
		return sessionReferrerFacade.findSessionReferrerOfPage(dto, page);
	}

	@Override
	public List<SessionReferrerDTO> findSessionReferrerAll(SessionReferrerDTO dto) {
		return sessionReferrerFacade.findSessionReferrerAll(dto);
	}

	@Override
	public Long insertSessionReferrerWithTx(SessionReferrerDTO dto) {
		return sessionReferrerFacade.insertSessionReferrerWithTx(dto);
	}

	@Override
	public int updateSessionReferrerWithTx(SessionReferrerDTO dto) {
		return sessionReferrerFacade.updateSessionReferrerWithTx(dto);
	}

	@Override
	public int deleteSessionReferrerWithTx(SessionReferrerDTO dto) {
		return sessionReferrerFacade.deleteSessionReferrerWithTx(dto);
	}


}
	