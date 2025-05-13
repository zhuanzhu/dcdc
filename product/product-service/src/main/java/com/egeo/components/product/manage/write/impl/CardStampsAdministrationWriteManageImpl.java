package com.egeo.components.product.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.CardStampsAdministrationWriteManage;
import com.egeo.components.product.dao.write.CardStampsAdministrationWriteDAO;
import com.egeo.components.product.po.CardStampsAdministrationPO;
import com.egeo.exception.BusinessException;

@Service
public class CardStampsAdministrationWriteManageImpl implements CardStampsAdministrationWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CardStampsAdministrationWriteDAO cardStampsAdministrationWriteDAO;

	@Override
	public Long insertCardStampsAdministrationWithTx(CardStampsAdministrationPO po) {
		
		int i ;
		try {
				i = cardStampsAdministrationWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateCardStampsAdministrationWithTx(CardStampsAdministrationPO po) {
		int i;
		i = cardStampsAdministrationWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteCardStampsAdministrationWithTx(CardStampsAdministrationPO po) {
		int i;
		i = cardStampsAdministrationWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	