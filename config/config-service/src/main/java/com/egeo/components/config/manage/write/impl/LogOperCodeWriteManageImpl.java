package com.egeo.components.config.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.config.dao.write.LogOperCodeWriteDAO;
import com.egeo.components.config.manage.write.LogOperCodeWriteManage;
import com.egeo.components.config.po.LogOperCodePO;
import com.egeo.exception.BusinessException;

@Service
public class LogOperCodeWriteManageImpl implements LogOperCodeWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private LogOperCodeWriteDAO logOperCodeWriteDAO;

	@Override
	public Long insertLogOperCodeWithTx(LogOperCodePO po) {
		
		int i ;
		try {
				i = logOperCodeWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateLogOperCodeWithTx(LogOperCodePO po) {
		int i;
		i = logOperCodeWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteLogOperCodeWithTx(LogOperCodePO po) {
		int i;
		i = logOperCodeWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	