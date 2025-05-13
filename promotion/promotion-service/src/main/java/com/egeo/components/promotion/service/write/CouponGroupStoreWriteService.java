package com.egeo.components.promotion.service.write;

import com.egeo.components.promotion.dto.CouponGroupStoreDTO;


public interface CouponGroupStoreWriteService {

	public Long insertCouponGroupStoreWithTx(CouponGroupStoreDTO dto);

	public int updateCouponGroupStoreWithTx(CouponGroupStoreDTO dto);

	public int deleteCouponGroupStoreWithTx(CouponGroupStoreDTO dto);
}
	