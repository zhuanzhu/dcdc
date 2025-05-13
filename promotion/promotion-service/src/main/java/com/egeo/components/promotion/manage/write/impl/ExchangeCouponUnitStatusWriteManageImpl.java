package com.egeo.components.promotion.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.dao.write.ExchangeCouponUnitStatusWriteDAO;
import com.egeo.components.promotion.manage.write.ExchangeCouponUnitStatusWriteManage;
import com.egeo.components.promotion.po.ExchangeCouponUnitStatusPO;
import com.egeo.exception.BusinessException;

@Service
public class ExchangeCouponUnitStatusWriteManageImpl implements ExchangeCouponUnitStatusWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ExchangeCouponUnitStatusWriteDAO exchangeCouponUnitStatusWriteDAO;

	@Override
	public Long insertExchangeCouponUnitStatusWithTx(ExchangeCouponUnitStatusPO po) {
		
		int i ;
		try {
				i = exchangeCouponUnitStatusWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateExchangeCouponUnitStatusWithTx(ExchangeCouponUnitStatusPO po) {
		int i;
		i = exchangeCouponUnitStatusWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteExchangeCouponUnitStatusWithTx(ExchangeCouponUnitStatusPO po) {
		int i;
		i = exchangeCouponUnitStatusWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	