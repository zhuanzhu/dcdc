package com.egeo.components.user.service.write;

import com.egeo.components.user.dto.DownloadDTO;


public interface DownloadWriteService {

	public Long insertDownloadWithTx(DownloadDTO dto);

	public int updateDownloadWithTx(DownloadDTO dto);

	public int deleteDownloadWithTx(DownloadDTO dto);
	
	/**
	 * 每日凌晨刷新下载记录的日下载量
	 */
	public int refreshDownloadDailyDownloadCount();
}
	