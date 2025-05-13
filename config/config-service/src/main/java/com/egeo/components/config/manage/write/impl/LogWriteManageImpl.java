package com.egeo.components.config.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.config.dao.write.LogWriteDAO;
import com.egeo.components.config.manage.write.LogWriteManage;
import com.egeo.components.config.po.LogPO;
import com.egeo.exception.BusinessException;

@Service
public class LogWriteManageImpl implements LogWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private LogWriteDAO logWriteDAO;

	@Override
	public Long insertLogWithTx(LogPO po) {
		
		int i ;
		try {
				i = logWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateLogWithTx(LogPO po) {
		int i;
		i = logWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteLogWithTx(LogPO po) {
		int i;
		i = logWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	