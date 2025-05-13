package com.egeo.components.promotion.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.dao.write.CouponGroupStoreWriteDAO;
import com.egeo.components.promotion.manage.write.CouponGroupStoreWriteManage;
import com.egeo.components.promotion.po.CouponGroupStorePO;
import com.egeo.exception.BusinessException;

@Service
public class CouponGroupStoreWriteManageImpl implements CouponGroupStoreWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CouponGroupStoreWriteDAO couponGroupStoreWriteDAO;

	@Override
	public Long insertCouponGroupStoreWithTx(CouponGroupStorePO po) {
		
		int i ;
		try {
				i = couponGroupStoreWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateCouponGroupStoreWithTx(CouponGroupStorePO po) {
		int i;
		i = couponGroupStoreWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteCouponGroupStoreWithTx(CouponGroupStorePO po) {
		int i;
		i = couponGroupStoreWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	