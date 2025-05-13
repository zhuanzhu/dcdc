package com.egeo.components.user.manage.read;

import java.util.List;
	
import com.egeo.components.user.po.SessionReferrerPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface SessionReferrerReadManage {

	public SessionReferrerPO findSessionReferrerById(SessionReferrerPO po);

	public PageResult<SessionReferrerPO> findSessionReferrerOfPage(SessionReferrerPO po,Pagination page);

	public List<SessionReferrerPO> findSessionReferrerAll(SessionReferrerPO po);
}
	