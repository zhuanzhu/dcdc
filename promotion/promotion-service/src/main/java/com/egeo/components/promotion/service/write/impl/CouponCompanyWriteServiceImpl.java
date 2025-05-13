package com.egeo.components.promotion.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 
import com.egeo.components.promotion.service.write.CouponCompanyWriteService;
import com.egeo.components.promotion.manage.write.CouponCompanyWriteManage;
import com.egeo.components.promotion.converter.CouponCompanyConverter;
import com.egeo.components.promotion.dto.CouponCompanyDTO;
import com.egeo.components.promotion.po.CouponCompanyPO;

@Service("couponCompanyWriteService")
public class CouponCompanyWriteServiceImpl implements CouponCompanyWriteService {
	@Autowired
	private CouponCompanyWriteManage couponCompanyWriteManage;

	@Override
	public Long insertCouponCompanyWithTx(CouponCompanyDTO dto) {
		CouponCompanyPO po = CouponCompanyConverter.toPO(dto);
		Long rt = couponCompanyWriteManage.insertCouponCompanyWithTx(po);		
		return rt;
	}

	@Override
	public int updateCouponCompanyWithTx(CouponCompanyDTO dto) {
		CouponCompanyPO po = CouponCompanyConverter.toPO(dto);
		int rt = couponCompanyWriteManage.updateCouponCompanyWithTx(po);		
		return rt;
	}

	@Override
	public int deleteCouponCompanyWithTx(CouponCompanyDTO dto) {
		CouponCompanyPO po = CouponCompanyConverter.toPO(dto);
		int rt = couponCompanyWriteManage.deleteCouponCompanyWithTx(po);		
		return rt;
	}
}
	