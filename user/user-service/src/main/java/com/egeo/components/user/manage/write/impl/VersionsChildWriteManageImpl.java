package com.egeo.components.user.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.user.manage.write.VersionsChildWriteManage;
import com.egeo.components.user.dao.write.VersionsChildWriteDAO;
import com.egeo.components.user.po.VersionsChildPO;
import com.egeo.exception.BusinessException;

@Service
public class VersionsChildWriteManageImpl implements VersionsChildWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private VersionsChildWriteDAO versionsChildWriteDAO;

	@Override
	public Long insertVersionsChildWithTx(VersionsChildPO po) {
		
		int i ;
		try {
				i = versionsChildWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateVersionsChildWithTx(VersionsChildPO po) {
		int i;
		i = versionsChildWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteVersionsChildWithTx(VersionsChildPO po) {
		int i;
		i = versionsChildWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	