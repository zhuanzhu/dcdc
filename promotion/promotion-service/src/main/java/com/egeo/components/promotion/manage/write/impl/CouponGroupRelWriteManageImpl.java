package com.egeo.components.promotion.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.dao.write.CouponGroupRelWriteDAO;
import com.egeo.components.promotion.manage.write.CouponGroupRelWriteManage;
import com.egeo.components.promotion.po.CouponGroupRelPO;
import com.egeo.exception.BusinessException;

@Service
public class CouponGroupRelWriteManageImpl implements CouponGroupRelWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CouponGroupRelWriteDAO couponGroupRelWriteDAO;

	@Override
	public Long insertCouponGroupRelWithTx(CouponGroupRelPO po) {
		
		int i ;
		try {
				i = couponGroupRelWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateCouponGroupRelWithTx(CouponGroupRelPO po) {
		int i;
		i = couponGroupRelWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteCouponGroupRelWithTx(CouponGroupRelPO po) {
		int i;
		i = couponGroupRelWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	