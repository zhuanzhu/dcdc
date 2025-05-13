package com.egeo.components.promotion.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.dao.write.CouponStoreWriteDAO;
import com.egeo.components.promotion.manage.write.CouponStoreWriteManage;
import com.egeo.components.promotion.po.CouponStorePO;
import com.egeo.exception.BusinessException;

@Service
public class CouponStoreWriteManageImpl implements CouponStoreWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CouponStoreWriteDAO couponStoreWriteDAO;

	@Override
	public Long insertCouponStoreWithTx(CouponStorePO po) {
		
		int i ;
		try {
				i = couponStoreWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateCouponStoreWithTx(CouponStorePO po) {
		int i;
		i = couponStoreWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteCouponStoreWithTx(CouponStorePO po) {
		int i;
		i = couponStoreWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	