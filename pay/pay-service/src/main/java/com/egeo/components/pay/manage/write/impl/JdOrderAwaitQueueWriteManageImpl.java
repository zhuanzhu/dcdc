package com.egeo.components.pay.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.pay.dao.write.JdOrderAwaitQueueWriteDAO;
import com.egeo.components.pay.manage.write.JdOrderAwaitQueueWriteManage;
import com.egeo.components.pay.po.JdOrderAwaitQueuePO;
import com.egeo.exception.BusinessException;

@Service
public class JdOrderAwaitQueueWriteManageImpl implements JdOrderAwaitQueueWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private JdOrderAwaitQueueWriteDAO jdOrderAwaitQueueWriteDAO;

	@Override
	public Long insertJdOrderAwaitQueueWithTx(JdOrderAwaitQueuePO po) {
		
		int i ;
		try {
				i = jdOrderAwaitQueueWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateJdOrderAwaitQueueWithTx(JdOrderAwaitQueuePO po) {
		int i;
		i = jdOrderAwaitQueueWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteJdOrderAwaitQueueWithTx(JdOrderAwaitQueuePO po) {
		int i;
		i = jdOrderAwaitQueueWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	