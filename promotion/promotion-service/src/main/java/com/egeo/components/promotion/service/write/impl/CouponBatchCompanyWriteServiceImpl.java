package com.egeo.components.promotion.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 
import com.egeo.components.promotion.service.write.CouponBatchCompanyWriteService;
import com.egeo.components.promotion.manage.write.CouponBatchCompanyWriteManage;
import com.egeo.components.promotion.converter.CouponBatchCompanyConverter;
import com.egeo.components.promotion.dto.CouponBatchCompanyDTO;
import com.egeo.components.promotion.po.CouponBatchCompanyPO;

@Service("couponBatchCompanyWriteService")
public class CouponBatchCompanyWriteServiceImpl implements CouponBatchCompanyWriteService {
	@Autowired
	private CouponBatchCompanyWriteManage couponBatchCompanyWriteManage;

	@Override
	public Long insertCouponBatchCompanyWithTx(CouponBatchCompanyDTO dto) {
		CouponBatchCompanyPO po = CouponBatchCompanyConverter.toPO(dto);
		Long rt = couponBatchCompanyWriteManage.insertCouponBatchCompanyWithTx(po);		
		return rt;
	}

	@Override
	public int updateCouponBatchCompanyWithTx(CouponBatchCompanyDTO dto) {
		CouponBatchCompanyPO po = CouponBatchCompanyConverter.toPO(dto);
		int rt = couponBatchCompanyWriteManage.updateCouponBatchCompanyWithTx(po);		
		return rt;
	}

	@Override
	public int deleteCouponBatchCompanyWithTx(CouponBatchCompanyDTO dto) {
		CouponBatchCompanyPO po = CouponBatchCompanyConverter.toPO(dto);
		int rt = couponBatchCompanyWriteManage.deleteCouponBatchCompanyWithTx(po);		
		return rt;
	}
}
	