package com.egeo.components.promotion.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 
import com.egeo.components.promotion.service.write.CouponWriteService;
import com.egeo.components.promotion.manage.write.CouponWriteManage;
import com.egeo.components.promotion.converter.CouponConverter;
import com.egeo.components.promotion.dto.CouponDTO;
import com.egeo.components.promotion.po.CouponPO;

@Service("couponWriteService")
public class CouponWriteServiceImpl implements CouponWriteService {
	@Autowired
	private CouponWriteManage couponWriteManage;

	@Override
	public Long insertCouponWithTx(CouponDTO dto) {
		CouponPO po = CouponConverter.toPO(dto);
		Long rt = couponWriteManage.insertCouponWithTx(po);		
		return rt;
	}

	@Override
	public int updateCouponWithTx(CouponDTO dto) {
		CouponPO po = CouponConverter.toPO(dto);
		int rt = couponWriteManage.updateCouponWithTx(po);		
		return rt;
	}

	@Override
	public int deleteCouponWithTx(CouponDTO dto) {
		CouponPO po = CouponConverter.toPO(dto);
		int rt = couponWriteManage.deleteCouponWithTx(po);		
		return rt;
	}
}
	