package com.egeo.components.config.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.config.dao.write.LogDictWriteDAO;
import com.egeo.components.config.manage.write.LogDictWriteManage;
import com.egeo.components.config.po.LogDictPO;
import com.egeo.exception.BusinessException;

@Service
public class LogDictWriteManageImpl implements LogDictWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private LogDictWriteDAO logDictWriteDAO;

	@Override
	public Long insertLogDictWithTx(LogDictPO po) {
		
		int i ;
		try {
				i = logDictWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateLogDictWithTx(LogDictPO po) {
		int i;
		i = logDictWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteLogDictWithTx(LogDictPO po) {
		int i;
		i = logDictWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	