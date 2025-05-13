package com.egeo.components.promotion.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.dao.write.CardBatchWriteDAO;
import com.egeo.components.promotion.manage.write.CardBatchWriteManage;
import com.egeo.components.promotion.po.CardBatchPO;
import com.egeo.exception.BusinessException;

@Service
public class CardBatchWriteManageImpl implements CardBatchWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CardBatchWriteDAO cardBatchWriteDAO;

	@Override
	public Long insertCardBatchWithTx(CardBatchPO po) {
		
		int i ;
		try {
				i = cardBatchWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateCardBatchWithTx(CardBatchPO po) {
		int i;
		i = cardBatchWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteCardBatchWithTx(CardBatchPO po) {
		int i;
		i = cardBatchWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	