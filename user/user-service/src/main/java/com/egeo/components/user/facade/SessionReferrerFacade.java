package com.egeo.components.user.facade;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.egeo.components.user.dto.SessionReferrerDTO;
import com.egeo.components.user.service.read.SessionReferrerReadService;
import com.egeo.components.user.service.write.SessionReferrerWriteService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class SessionReferrerFacade {
	
	@Resource
	private SessionReferrerReadService sessionReferrerReadService;
	
	@Resource
	private SessionReferrerWriteService sessionReferrerWriteService;
	
	
	public SessionReferrerDTO findSessionReferrerById(SessionReferrerDTO dto){
		
		return sessionReferrerReadService.findSessionReferrerById(dto);
	}

	public PageResult<SessionReferrerDTO> findSessionReferrerOfPage(SessionReferrerDTO dto,Pagination page){
		
		return sessionReferrerReadService.findSessionReferrerOfPage(dto, page);
		
	}

	public List<SessionReferrerDTO> findSessionReferrerAll(SessionReferrerDTO dto){
		
		return sessionReferrerReadService.findSessionReferrerAll(dto);
		
	}

	public Long insertSessionReferrerWithTx(SessionReferrerDTO dto){
		
		return sessionReferrerWriteService.insertSessionReferrerWithTx(dto);
	}

	public int updateSessionReferrerWithTx(SessionReferrerDTO dto){
		
		return sessionReferrerWriteService.updateSessionReferrerWithTx(dto);
	}

	public int deleteSessionReferrerWithTx(SessionReferrerDTO dto){
		
		return sessionReferrerWriteService.deleteSessionReferrerWithTx(dto);
		
	}

}
	