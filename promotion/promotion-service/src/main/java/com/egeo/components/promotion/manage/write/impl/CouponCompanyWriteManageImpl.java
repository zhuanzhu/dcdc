package com.egeo.components.promotion.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.dao.write.CouponCompanyWriteDAO;
import com.egeo.components.promotion.manage.write.CouponCompanyWriteManage;
import com.egeo.components.promotion.po.CouponCompanyPO;
import com.egeo.exception.BusinessException;

@Service
public class CouponCompanyWriteManageImpl implements CouponCompanyWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CouponCompanyWriteDAO couponCompanyWriteDAO;

	@Override
	public Long insertCouponCompanyWithTx(CouponCompanyPO po) {
		
		int i ;
		try {
				i = couponCompanyWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateCouponCompanyWithTx(CouponCompanyPO po) {
		int i;
		i = couponCompanyWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteCouponCompanyWithTx(CouponCompanyPO po) {
		int i;
		i = couponCompanyWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	