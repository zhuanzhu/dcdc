package com.egeo.components.user.dao.write;

import com.egeo.components.user.po.DownloadPO;
import com.egeo.orm.BaseWriteDAO;

public interface DownloadWriteDAO extends BaseWriteDAO<DownloadPO> {

	/**
	 * 每日凌晨刷新日下载量
	 * @return
	 */
	int refreshDownloadDailyDownloadCount();
}
	