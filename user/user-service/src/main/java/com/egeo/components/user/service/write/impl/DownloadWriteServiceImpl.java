package com.egeo.components.user.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.converter.DownloadConverter;
import com.egeo.components.user.dto.DownloadDTO;
import com.egeo.components.user.manage.write.DownloadWriteManage;
import com.egeo.components.user.po.DownloadPO;
import com.egeo.components.user.service.write.DownloadWriteService;

@Service("downloadWriteService")
public class DownloadWriteServiceImpl implements DownloadWriteService {
	@Autowired
	private DownloadWriteManage downloadWriteManage;

	@Override
	public Long insertDownloadWithTx(DownloadDTO dto) {
		DownloadPO po = DownloadConverter.toPO(dto);
		Long rt = downloadWriteManage.insertDownloadWithTx(po);		
		return rt;
	}

	@Override
	public int updateDownloadWithTx(DownloadDTO dto) {
		DownloadPO po = DownloadConverter.toPO(dto);
		int rt = downloadWriteManage.updateDownloadWithTx(po);		
		return rt;
	}

	@Override
	public int deleteDownloadWithTx(DownloadDTO dto) {
		DownloadPO po = DownloadConverter.toPO(dto);
		int rt = downloadWriteManage.deleteDownloadWithTx(po);		
		return rt;
	}

	@Override
	public int refreshDownloadDailyDownloadCount() {
		return downloadWriteManage.refreshDownloadDailyDownloadCount();
	}
}
	