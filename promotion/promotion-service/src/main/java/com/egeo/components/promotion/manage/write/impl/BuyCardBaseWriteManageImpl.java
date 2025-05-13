package com.egeo.components.promotion.manage.write.impl;

import com.egeo.components.promotion.dao.write.BuyCardBaseWriteDAO;
import com.egeo.components.promotion.manage.write.BuyCardBaseWriteManage;
import com.egeo.components.promotion.po.BuyCardBasePO;
import com.egeo.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class BuyCardBaseWriteManageImpl implements BuyCardBaseWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private BuyCardBaseWriteDAO buyCardBaseWriteDAO;

	@Override
	public Long insertBuyCardBaseWithTx(BuyCardBasePO po) {

		int i ;
		try {
			i = buyCardBaseWriteDAO.insert(po);
			if (i == 0)
				throw new BusinessException("未能成功插入数据!");
		} catch (DuplicateKeyException e) {
			logger.error("", e);
			throw new BusinessException("路径必须唯一!");
		}
		return po.getId();
	}

	@Override
	public int updateBuyCardBaseWithTx(BuyCardBasePO po) {
		int i;
		i = buyCardBaseWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteBuyCardBaseWithTx(BuyCardBasePO po) {
		int i;
		i = buyCardBaseWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}
}
