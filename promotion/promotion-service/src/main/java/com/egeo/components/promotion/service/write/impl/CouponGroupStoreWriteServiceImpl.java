package com.egeo.components.promotion.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 
import com.egeo.components.promotion.service.write.CouponGroupStoreWriteService;
import com.egeo.components.promotion.manage.write.CouponGroupStoreWriteManage;
import com.egeo.components.promotion.converter.CouponGroupStoreConverter;
import com.egeo.components.promotion.dto.CouponGroupStoreDTO;
import com.egeo.components.promotion.po.CouponGroupStorePO;

@Service("couponGroupStoreWriteService")
public class CouponGroupStoreWriteServiceImpl implements CouponGroupStoreWriteService {
	@Autowired
	private CouponGroupStoreWriteManage couponGroupStoreWriteManage;

	@Override
	public Long insertCouponGroupStoreWithTx(CouponGroupStoreDTO dto) {
		CouponGroupStorePO po = CouponGroupStoreConverter.toPO(dto);
		Long rt = couponGroupStoreWriteManage.insertCouponGroupStoreWithTx(po);		
		return rt;
	}

	@Override
	public int updateCouponGroupStoreWithTx(CouponGroupStoreDTO dto) {
		CouponGroupStorePO po = CouponGroupStoreConverter.toPO(dto);
		int rt = couponGroupStoreWriteManage.updateCouponGroupStoreWithTx(po);		
		return rt;
	}

	@Override
	public int deleteCouponGroupStoreWithTx(CouponGroupStoreDTO dto) {
		CouponGroupStorePO po = CouponGroupStoreConverter.toPO(dto);
		int rt = couponGroupStoreWriteManage.deleteCouponGroupStoreWithTx(po);		
		return rt;
	}
}
	