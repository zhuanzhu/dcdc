package com.egeo.components.user.manage.write;

import com.egeo.components.user.po.SessionReferrerPO;


public interface SessionReferrerWriteManage {

	Long insertSessionReferrerWithTx(SessionReferrerPO po);

	int updateSessionReferrerWithTx(SessionReferrerPO po);

	int deleteSessionReferrerWithTx(SessionReferrerPO po);
}
	