package com.egeo.components.promotion.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.dao.write.CouponBatchCompanyWriteDAO;
import com.egeo.components.promotion.manage.write.CouponBatchCompanyWriteManage;
import com.egeo.components.promotion.po.CouponBatchCompanyPO;
import com.egeo.exception.BusinessException;

@Service
public class CouponBatchCompanyWriteManageImpl implements CouponBatchCompanyWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CouponBatchCompanyWriteDAO couponBatchCompanyWriteDAO;

	@Override
	public Long insertCouponBatchCompanyWithTx(CouponBatchCompanyPO po) {
		
		int i ;
		try {
				i = couponBatchCompanyWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateCouponBatchCompanyWithTx(CouponBatchCompanyPO po) {
		int i;
		i = couponBatchCompanyWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteCouponBatchCompanyWithTx(CouponBatchCompanyPO po) {
		int i;
		i = couponBatchCompanyWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	