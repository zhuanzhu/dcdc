package com.egeo.components.user.manage.write;

import com.egeo.components.user.po.DownloadPO;


public interface DownloadWriteManage {

	Long insertDownloadWithTx(DownloadPO po);

	int updateDownloadWithTx(DownloadPO po);

	int deleteDownloadWithTx(DownloadPO po);

	int refreshDownloadDailyDownloadCount();
}
	