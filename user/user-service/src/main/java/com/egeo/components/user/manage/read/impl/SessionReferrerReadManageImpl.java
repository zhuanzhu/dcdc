package com.egeo.components.user.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.manage.read.SessionReferrerReadManage;
import com.egeo.components.user.dao.read.SessionReferrerReadDAO;
import com.egeo.components.user.po.SessionReferrerPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class SessionReferrerReadManageImpl implements SessionReferrerReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SessionReferrerReadDAO sessionReferrerReadDAO;
	
	public SessionReferrerPO findSessionReferrerById(SessionReferrerPO po) {
		SessionReferrerPO sessionReferrerpo = new SessionReferrerPO();
		sessionReferrerpo.setId(po.getId());
		return sessionReferrerReadDAO.findById(sessionReferrerpo);
	}

	public PageResult<SessionReferrerPO> findSessionReferrerOfPage(SessionReferrerPO po, Pagination page) {
		
		PageResult<SessionReferrerPO> pageResult = new PageResult<SessionReferrerPO>();
		List<SessionReferrerPO> list = null;

		int cnt = sessionReferrerReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = sessionReferrerReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<SessionReferrerPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<SessionReferrerPO> findSessionReferrerAll(SessionReferrerPO po) {

		return sessionReferrerReadDAO.findAll(po,null);
	}
	
}
	