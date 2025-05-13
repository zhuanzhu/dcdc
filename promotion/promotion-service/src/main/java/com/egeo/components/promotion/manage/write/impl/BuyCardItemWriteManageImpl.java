package com.egeo.components.promotion.manage.write.impl;

import com.egeo.components.promotion.dao.write.BuyCardItemWriteDAO;
import com.egeo.components.promotion.manage.write.BuyCardItemWriteManage;
import com.egeo.components.promotion.po.BuyCardItemPO;
import com.egeo.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class BuyCardItemWriteManageImpl implements BuyCardItemWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private BuyCardItemWriteDAO buyCardItemWriteDAO;

	@Override
	public Long insertBuyCardItemWithTx(BuyCardItemPO po) {

		int i ;
		try {
			i = buyCardItemWriteDAO.insert(po);
			if (i == 0)
				throw new BusinessException("未能成功插入数据!");
		} catch (DuplicateKeyException e) {
			logger.error("", e);
			throw new BusinessException("路径必须唯一!");
		}
		return po.getId();
	}

	@Override
	public int updateBuyCardItemWithTx(BuyCardItemPO po) {
		int i;
		i = buyCardItemWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteBuyCardItemWithTx(BuyCardItemPO po) {
		int i;
		i = buyCardItemWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}
}
