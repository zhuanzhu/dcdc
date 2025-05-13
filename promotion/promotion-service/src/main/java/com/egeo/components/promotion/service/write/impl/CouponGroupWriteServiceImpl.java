package com.egeo.components.promotion.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 
import com.egeo.components.promotion.service.write.CouponGroupWriteService;
import com.egeo.components.promotion.manage.write.CouponGroupWriteManage;
import com.egeo.components.promotion.converter.CouponGroupConverter;
import com.egeo.components.promotion.dto.CouponGroupDTO;
import com.egeo.components.promotion.po.CouponGroupPO;

@Service("couponGroupWriteService")
public class CouponGroupWriteServiceImpl implements CouponGroupWriteService {
	@Autowired
	private CouponGroupWriteManage couponGroupWriteManage;

	@Override
	public Long insertCouponGroupWithTx(CouponGroupDTO dto) {
		CouponGroupPO po = CouponGroupConverter.toPO(dto);
		Long rt = couponGroupWriteManage.insertCouponGroupWithTx(dto.getStoreList(), po);
		return rt;
	}

	@Override
	public int updateCouponGroupWithTx(CouponGroupDTO dto) {
		CouponGroupPO po = CouponGroupConverter.toPO(dto);
		int rt = couponGroupWriteManage.updateCouponGroupWithTx(dto.getStoreList(), po);
		return rt;
	}

	@Override
	public int deleteCouponGroupWithTx(CouponGroupDTO dto) {
		CouponGroupPO po = CouponGroupConverter.toPO(dto);
		int rt = couponGroupWriteManage.deleteCouponGroupWithTx(po);		
		return rt;
	}
}
	