package com.egeo.components.promotion.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 
import com.egeo.components.promotion.service.write.CouponGroupRelWriteService;
import com.egeo.components.promotion.manage.write.CouponGroupRelWriteManage;
import com.egeo.components.promotion.converter.CouponGroupRelConverter;
import com.egeo.components.promotion.dto.CouponGroupRelDTO;
import com.egeo.components.promotion.po.CouponGroupRelPO;

@Service("couponGroupRelWriteService")
public class CouponGroupRelWriteServiceImpl implements CouponGroupRelWriteService {
	@Autowired
	private CouponGroupRelWriteManage couponGroupRelWriteManage;

	@Override
	public Long insertCouponGroupRelWithTx(CouponGroupRelDTO dto) {
		CouponGroupRelPO po = CouponGroupRelConverter.toPO(dto);
		Long rt = couponGroupRelWriteManage.insertCouponGroupRelWithTx(po);		
		return rt;
	}

	@Override
	public int updateCouponGroupRelWithTx(CouponGroupRelDTO dto) {
		CouponGroupRelPO po = CouponGroupRelConverter.toPO(dto);
		int rt = couponGroupRelWriteManage.updateCouponGroupRelWithTx(po);		
		return rt;
	}

	@Override
	public int deleteCouponGroupRelWithTx(CouponGroupRelDTO dto) {
		CouponGroupRelPO po = CouponGroupRelConverter.toPO(dto);
		int rt = couponGroupRelWriteManage.deleteCouponGroupRelWithTx(po);		
		return rt;
	}
}
	