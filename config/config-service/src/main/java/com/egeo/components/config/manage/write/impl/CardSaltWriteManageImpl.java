package com.egeo.components.config.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.config.dao.write.CardSaltWriteDAO;
import com.egeo.components.config.manage.write.CardSaltWriteManage;
import com.egeo.components.config.po.CardSaltPO;
import com.egeo.exception.BusinessException;

@Service
public class CardSaltWriteManageImpl implements CardSaltWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CardSaltWriteDAO cardSaltWriteDAO;

	@Override
	public Long insertCardSaltWithTx(CardSaltPO po) {
		
		int i ;
		try {
				i = cardSaltWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateCardSaltWithTx(CardSaltPO po) {
		int i;
		i = cardSaltWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteCardSaltWithTx(CardSaltPO po) {
		int i;
		i = cardSaltWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	