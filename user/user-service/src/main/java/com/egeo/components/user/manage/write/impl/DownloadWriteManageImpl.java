package com.egeo.components.user.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.user.manage.write.DownloadWriteManage;
import com.egeo.components.user.dao.write.DownloadWriteDAO;
import com.egeo.components.user.po.DownloadPO;
import com.egeo.exception.BusinessException;

@Service
public class DownloadWriteManageImpl implements DownloadWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private DownloadWriteDAO downloadWriteDAO;

	@Override
	public Long insertDownloadWithTx(DownloadPO po) {
		
		int i ;
		try {
				i = downloadWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateDownloadWithTx(DownloadPO po) {
		int i;
		i = downloadWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteDownloadWithTx(DownloadPO po) {
		int i;
		i = downloadWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}

	@Override
	public int refreshDownloadDailyDownloadCount() {
		return downloadWriteDAO.refreshDownloadDailyDownloadCount();
	}	
}
	