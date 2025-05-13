package com.egeo.components.promotion.manage.write.impl;

import com.egeo.components.promotion.dao.write.CardUseRecordWriteDAO;
import com.egeo.components.promotion.manage.write.CardUseRecordWriteManage;
import com.egeo.components.promotion.po.CardUseRecordPO;
import com.egeo.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CardUseRecordWriteManageImpl implements CardUseRecordWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CardUseRecordWriteDAO cardUseRecordWriteDAO;

	@Override
	public Long insertCardUseRecordWithTx(CardUseRecordPO po) {

		int i ;
		try {
			i = cardUseRecordWriteDAO.insert(po);
			if (i == 0)
				throw new BusinessException("未能成功插入数据!");
		} catch (DuplicateKeyException e) {
			logger.error("", e);
			throw new BusinessException("路径必须唯一!");
		}
		return po.getId();
	}

	@Override
	public int updateCardUseRecordWithTx(CardUseRecordPO po) {
		int i;
		i = cardUseRecordWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteCardUseRecordWithTx(CardUseRecordPO po) {
		int i;
		i = cardUseRecordWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}

	@Override
	public int cancelUserCard(){
	return cardUseRecordWriteDAO.cancelUserCard();
	}

}
