package com.egeo.components.promotion.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 
import com.egeo.components.promotion.service.write.CouponStoreWriteService;
import com.egeo.components.promotion.manage.write.CouponStoreWriteManage;
import com.egeo.components.promotion.converter.CouponStoreConverter;
import com.egeo.components.promotion.dto.CouponStoreDTO;
import com.egeo.components.promotion.po.CouponStorePO;

@Service("couponStoreWriteService")
public class CouponStoreWriteServiceImpl implements CouponStoreWriteService {
	@Autowired
	private CouponStoreWriteManage couponStoreWriteManage;

	@Override
	public Long insertCouponStoreWithTx(CouponStoreDTO dto) {
		CouponStorePO po = CouponStoreConverter.toPO(dto);
		Long rt = couponStoreWriteManage.insertCouponStoreWithTx(po);		
		return rt;
	}

	@Override
	public int updateCouponStoreWithTx(CouponStoreDTO dto) {
		CouponStorePO po = CouponStoreConverter.toPO(dto);
		int rt = couponStoreWriteManage.updateCouponStoreWithTx(po);		
		return rt;
	}

	@Override
	public int deleteCouponStoreWithTx(CouponStoreDTO dto) {
		CouponStorePO po = CouponStoreConverter.toPO(dto);
		int rt = couponStoreWriteManage.deleteCouponStoreWithTx(po);		
		return rt;
	}
}
	