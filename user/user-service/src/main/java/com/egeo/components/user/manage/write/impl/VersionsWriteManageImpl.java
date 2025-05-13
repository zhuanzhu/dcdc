package com.egeo.components.user.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.user.manage.write.VersionsWriteManage;
import com.egeo.components.user.dao.write.VersionsWriteDAO;
import com.egeo.components.user.po.VersionsPO;
import com.egeo.exception.BusinessException;

@Service
public class VersionsWriteManageImpl implements VersionsWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private VersionsWriteDAO versionsWriteDAO;

	@Override
	public Long insertVersionsWithTx(VersionsPO po) {
		
		int i ;
		try {
				i = versionsWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateVersionsWithTx(VersionsPO po) {
		int i;
		i = versionsWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteVersionsWithTx(VersionsPO po) {
		int i;
		i = versionsWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}

	@Override
	public int updateVersionsOfficialByTypeWithTx(Integer user) {
		return versionsWriteDAO.updateVersionsOfficialByTypeWithTx(user);
	}	
}
	