package com.egeo.components.pay.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.pay.dao.write.ThirdpartyAwaitQueueWriteDAO;
import com.egeo.components.pay.manage.write.ThirdpartyAwaitQueueWriteManage;
import com.egeo.components.pay.po.ThirdpartyAwaitQueuePO;
import com.egeo.exception.BusinessException;

@Service
public class ThirdpartyAwaitQueueWriteManageImpl implements ThirdpartyAwaitQueueWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ThirdpartyAwaitQueueWriteDAO thirdpartyAwaitQueueWriteDAO;

	@Override
	public Long insertThirdpartyAwaitQueueWithTx(ThirdpartyAwaitQueuePO po) {
		
		int i ;
		try {
				i = thirdpartyAwaitQueueWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateThirdpartyAwaitQueueWithTx(ThirdpartyAwaitQueuePO po) {
		int i;
		i = thirdpartyAwaitQueueWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteThirdpartyAwaitQueueWithTx(ThirdpartyAwaitQueuePO po) {
		int i;
		i = thirdpartyAwaitQueueWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}

	@Override
	public int deleteThirdpartyAwaitQueueByCodeWithTx(ThirdpartyAwaitQueuePO po) {
		int i;
		i = thirdpartyAwaitQueueWriteDAO.deleteByCode(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}
}
	