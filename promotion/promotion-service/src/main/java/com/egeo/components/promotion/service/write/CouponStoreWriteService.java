package com.egeo.components.promotion.service.write;

import com.egeo.components.promotion.dto.CouponStoreDTO;


public interface CouponStoreWriteService {

	public Long insertCouponStoreWithTx(CouponStoreDTO dto);

	public int updateCouponStoreWithTx(CouponStoreDTO dto);

	public int deleteCouponStoreWithTx(CouponStoreDTO dto);
}
	