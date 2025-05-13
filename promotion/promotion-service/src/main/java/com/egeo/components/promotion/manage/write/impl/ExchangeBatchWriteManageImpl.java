package com.egeo.components.promotion.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.dao.write.ExchangeBatchWriteDAO;
import com.egeo.components.promotion.manage.write.ExchangeBatchWriteManage;
import com.egeo.components.promotion.po.ExchangeBatchPO;
import com.egeo.exception.BusinessException;

@Service
public class ExchangeBatchWriteManageImpl implements ExchangeBatchWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ExchangeBatchWriteDAO exchangeBatchWriteDAO;

	@Override
	public Long insertExchangeBatchWithTx(ExchangeBatchPO po) {
		
		int i ;
		try {
				i = exchangeBatchWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateExchangeBatchWithTx(ExchangeBatchPO po) {
		int i;
		i = exchangeBatchWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteExchangeBatchWithTx(ExchangeBatchPO po) {
		int i;
		i = exchangeBatchWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	