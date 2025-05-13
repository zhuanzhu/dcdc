package com.egeo.components.promotion.manage.write.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.dao.read.CouponUnitReadDAO;
import com.egeo.components.promotion.dao.write.CouponUnitWriteDAO;
import com.egeo.components.promotion.manage.write.CouponUnitWriteManage;
import com.egeo.components.promotion.po.CouponUnitPO;
import com.egeo.exception.BusinessException;
import com.egeo.utils.EmptyUtil;

@Service
public class CouponUnitWriteManageImpl implements CouponUnitWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CouponUnitWriteDAO couponUnitWriteDAO;
	
	@Autowired
	private CouponUnitReadDAO couponUnitReadDAO;

	@Override
	public Long insertCouponUnitWithTx(CouponUnitPO po) {
		
		int i ;
		try {
				i = couponUnitWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateCouponUnitWithTx(CouponUnitPO po) {
		int i;
		i = couponUnitWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteCouponUnitWithTx(CouponUnitPO po) {
		int i;
		i = couponUnitWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}

	@Override
	public int updateCouponByPaySuccessWithTx(Long orderId) {
		int i = 0;
		CouponUnitPO couponUnitPO = new CouponUnitPO();
		couponUnitPO.setOrderId(orderId);
		couponUnitPO.setCouponUnitStatus(2);
		List<CouponUnitPO> couponUnitPOList = couponUnitReadDAO.findAll(couponUnitPO,null);
		
		if (EmptyUtil.isNotEmpty(couponUnitPOList)) {
			CouponUnitPO couponUnitPO_ = couponUnitPOList.get(0);
			couponUnitPO_.setCouponUnitStatus(1);
			i = couponUnitWriteDAO.update(couponUnitPO_);
			if (i == 0)
				throw new BusinessException("未能成功更新数据!");
		}
		
		return i;
	}

	@Override
	public int updateCouponByCancelOrderWithTx(Long orderId) {
		int i = 0;
		CouponUnitPO couponUnitPO = new CouponUnitPO();
		couponUnitPO.setOrderId(orderId);
		List<CouponUnitPO> couponUnitPOList = couponUnitReadDAO.findAll(couponUnitPO,null);
		
		if (EmptyUtil.isNotEmpty(couponUnitPOList)) {
			CouponUnitPO couponUnitPO_ = couponUnitPOList.get(0);
			couponUnitPO_.setCouponUnitStatus(0);
			couponUnitPO_.setUsedTime(null);
			couponUnitPO_.setUsedCount(0);
			couponUnitPO_.setOrderId(null);
			i = couponUnitWriteDAO.updateForNull(couponUnitPO_);
			if (i == 0)
				throw new BusinessException("未能成功更新数据!");
		}
		
		return i;
	}

	@Override
	public int updateCouponUnitByAffirmOrderWithTx(Long orderId) {
		return 0;
	}

	@Override
	public Integer insertCouponUnitListWithTx(List<CouponUnitPO> poList) {
		int i;
		try {
			i = couponUnitWriteDAO.insertCouponUnitList(poList);
		} catch (DuplicateKeyException e) {
			logger.error("", e);
			throw new BusinessException("路径必须唯一!");
		}
		return i;
	}

	@Override
	public void updateCouponUnitByParamWithTx(CouponUnitPO po) {
		int i;
		i = couponUnitWriteDAO.updateByParam(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
	}

	@Override
	public void deleteCouponUnitByParam(CouponUnitPO po) {
		couponUnitWriteDAO.deleteByPara(po);
	}

	@Override
	public int updateCouponUnitStatusByParamsWithTx(Long couponBatchId, Long startNum, Long endNum, Date date) {
		return couponUnitWriteDAO.updateCouponUnitStatusByParamsWithTx(couponBatchId,startNum,endNum,date);
	}

	@Override
	public int updateCouponUnitLockedByCouponUnitId(Long couponUnitId) {
		return couponUnitWriteDAO.updateCouponUnitLockedByCouponUnitId(couponUnitId);
	}

	@Override
	public int updateCouponUnitRemoveLock(String oldUnitCode) {
		return couponUnitWriteDAO.updateCouponUnitRemoveLock(oldUnitCode);
	}

	@Override
	public void updateCouponUnitReadStatus(List<Long> ids) {
		couponUnitWriteDAO.updateCouponUnitReadStatus(ids);
	}
}
	