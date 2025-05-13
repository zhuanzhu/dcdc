package com.egeo.components.user.manage.write;

import com.egeo.components.user.po.UrlPO;

public interface UrlWriteManage {

	int saveWithTx(UrlPO dto);
	
	int UpdateWithTx(UrlPO dto);

	void delete(UrlPO po);

}
	