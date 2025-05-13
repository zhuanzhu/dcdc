package com.egeo.components.promotion.manage.write.impl;

import com.egeo.components.promotion.dao.write.BuyCardItemRefundWriteDAO;
import com.egeo.components.promotion.manage.write.BuyCardItemRefundWriteManage;
import com.egeo.components.promotion.po.BuyCardItemRefundPO;
import com.egeo.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class BuyCardItemRefundWriteManageImpl implements BuyCardItemRefundWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private BuyCardItemRefundWriteDAO buyCardItemRefundWriteDAO;

	@Override
	public Long insertBuyCardItemRefundWithTx(BuyCardItemRefundPO po) {

		int i ;
		try {
			i = buyCardItemRefundWriteDAO.insert(po);
			if (i == 0)
				throw new BusinessException("未能成功插入数据!");
		} catch (DuplicateKeyException e) {
			logger.error("", e);
			throw new BusinessException("路径必须唯一!");
		}
		return po.getId();
	}

	@Override
	public int updateBuyCardItemRefundWithTx(BuyCardItemRefundPO po) {
		int i;
		i = buyCardItemRefundWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteBuyCardItemRefundWithTx(BuyCardItemRefundPO po) {
		int i;
		i = buyCardItemRefundWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}
}
