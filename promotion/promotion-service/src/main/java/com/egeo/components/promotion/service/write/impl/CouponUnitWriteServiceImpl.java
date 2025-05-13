package com.egeo.components.promotion.service.write.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 
import com.egeo.components.promotion.service.write.CouponUnitWriteService;
import com.egeo.components.promotion.manage.write.CouponUnitWriteManage;
import com.egeo.components.promotion.converter.CouponUnitConverter;
import com.egeo.components.promotion.dto.CouponUnitDTO;
import com.egeo.components.promotion.po.CouponUnitPO;

import java.util.Date;
import java.util.List;

@Service("couponUnitWriteService")
public class CouponUnitWriteServiceImpl implements CouponUnitWriteService {
	@Autowired
	private CouponUnitWriteManage couponUnitWriteManage;

	@Override
	public Long insertCouponUnitWithTx(CouponUnitDTO dto) {
		CouponUnitPO po = CouponUnitConverter.toPO(dto);
		Long rt = couponUnitWriteManage.insertCouponUnitWithTx(po);		
		return rt;
	}

	@Override
	public int updateCouponUnitWithTx(CouponUnitDTO dto) {
		CouponUnitPO po = CouponUnitConverter.toPO(dto);
		int rt = couponUnitWriteManage.updateCouponUnitWithTx(po);		
		return rt;
	}

	@Override
	public int deleteCouponUnitWithTx(CouponUnitDTO dto) {
		CouponUnitPO po = CouponUnitConverter.toPO(dto);
		int rt = couponUnitWriteManage.deleteCouponUnitWithTx(po);		
		return rt;
	}

	@Override
	public int updateCouponByPaySuccessWithTx(Long orderId) {
	
		return couponUnitWriteManage.updateCouponByPaySuccessWithTx(orderId);
	}

	@Override
	public int updateCouponByCancelOrderWithTx(Long orderId) {
		
		return couponUnitWriteManage.updateCouponByCancelOrderWithTx(orderId);
	}

	@Override
	public int updateCouponUnitByAffirmOrderWithTx(Long orderId) {
		
		return couponUnitWriteManage.updateCouponUnitByAffirmOrderWithTx(orderId);
	}

	@Override
	public Integer insertCouponUnitListWithTx(List<CouponUnitDTO> list) {
		return couponUnitWriteManage.insertCouponUnitListWithTx(CouponUnitConverter.toPO(list));
	}

	@Override
	public void updateCouponUnitByParamWithTx(CouponUnitDTO couponUnitDTO) {
		couponUnitWriteManage.updateCouponUnitByParamWithTx(CouponUnitConverter.toPO(couponUnitDTO));
	}

	@Override
	public void deleteCouponUnitByParamWithTx(CouponUnitDTO unitDTO) {
		couponUnitWriteManage.deleteCouponUnitByParam(CouponUnitConverter.toPO(unitDTO));
	}

	@Override
	public int updateCouponUnitStatusByParamsWithTx(Long couponBatchId, Long startNum, Long endNum, Date date) {
		return couponUnitWriteManage.updateCouponUnitStatusByParamsWithTx(couponBatchId,startNum,endNum,date);
	}

	@Override
	public int updateCouponUnitLockedByCouponUnitId(Long couponUnitId) {
		return couponUnitWriteManage.updateCouponUnitLockedByCouponUnitId(couponUnitId);
	}

	@Override
	public int updateCouponUnitRemoveLock(String oldUnitCode) {
		return couponUnitWriteManage.updateCouponUnitRemoveLock(oldUnitCode);
	}

	@Override
	public void updateCouponUnitReadStatus(List<Long> ids) {
		couponUnitWriteManage.updateCouponUnitReadStatus(ids);
	}


}
	