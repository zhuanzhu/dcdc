package com.egeo.components.user.service.read.impl;

import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.egeo.components.user.service.read.SessionReferrerReadService;
import com.egeo.components.user.dto.SessionReferrerDTO;
import com.egeo.components.user.po.SessionReferrerPO;
import com.egeo.components.user.dao.read.SessionReferrerReadDAO;
import com.egeo.components.user.converter.SessionReferrerConverter;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("sessionReferrerReadService")
public class SessionReferrerReadServiceImpl implements SessionReferrerReadService {
	@Autowired
	private SessionReferrerReadDAO sessionReferrerReadDAO ;

	@Override
	public SessionReferrerDTO findSessionReferrerById(SessionReferrerDTO dto){
		SessionReferrerPO po = SessionReferrerConverter.toPO(dto);
		SessionReferrerPO sessionReferrerpo = new SessionReferrerPO();
		sessionReferrerpo.setId(po.getId());
		SessionReferrerPO list = sessionReferrerReadDAO.findById(sessionReferrerpo);
		return SessionReferrerConverter.toDTO(list);
	}
	@Override
	public PageResult<SessionReferrerDTO> findSessionReferrerOfPage(SessionReferrerDTO dto, Pagination page) {
		SessionReferrerPO po = SessionReferrerConverter.toPO(dto);
		PageResult<SessionReferrerPO> pageResult = new PageResult<SessionReferrerPO>();
		List<SessionReferrerPO> listT = null;
		int cnt = sessionReferrerReadDAO.countOfPage(po);
		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			listT = sessionReferrerReadDAO.findOfPage(po, page);
		} else {
			listT = new ArrayList<SessionReferrerPO>();
		}
		pageResult.setList(listT);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		
        List<SessionReferrerDTO> list = SessionReferrerConverter.toDTO(pageResult.getList());
        PageResult<SessionReferrerDTO> result = new PageResult<SessionReferrerDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<SessionReferrerDTO> findSessionReferrerAll(SessionReferrerDTO dto) {
		SessionReferrerPO po = SessionReferrerConverter.toPO(dto);
		List<SessionReferrerPO> list = sessionReferrerReadDAO.findAll(po,null);
		return SessionReferrerConverter.toDTO(list);
	}
}
