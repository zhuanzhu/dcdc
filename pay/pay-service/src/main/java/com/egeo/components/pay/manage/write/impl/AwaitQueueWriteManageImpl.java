package com.egeo.components.pay.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.pay.dao.write.AwaitQueueWriteDAO;
import com.egeo.components.pay.manage.write.AwaitQueueWriteManage;
import com.egeo.components.pay.po.AwaitQueuePO;
import com.egeo.exception.BusinessException;

@Service
public class AwaitQueueWriteManageImpl implements AwaitQueueWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private AwaitQueueWriteDAO awaitQueueWriteDAO;

	@Override
	public Long insertAwaitQueueWithTx(AwaitQueuePO po) {
		
		int i ;
		try {
				i = awaitQueueWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateAwaitQueueWithTx(AwaitQueuePO po) {
		int i;
		i = awaitQueueWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteAwaitQueueWithTx(AwaitQueuePO po) {
		int i;
		i = awaitQueueWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}
	/**
	 * 根据订单id删除订单等待队列
	 * @param orderId
	 * @return
	 */
	@Override
	public int delByOrderIdWithTx(Long orderId) {
		AwaitQueuePO awaitQueuePO = new AwaitQueuePO();
		awaitQueuePO.setSoId(orderId);
		return awaitQueueWriteDAO.deleteByPara(awaitQueuePO);
	}

	@Override
	public int updateAwaitQueueBySoIdWithTx(AwaitQueuePO po) {
		return awaitQueueWriteDAO.updateAwaitQueueBySoIdWithTx(po);
	}	
}
	