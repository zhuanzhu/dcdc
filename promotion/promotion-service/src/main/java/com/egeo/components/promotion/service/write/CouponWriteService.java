package com.egeo.components.promotion.service.write;

import com.egeo.components.promotion.dto.CouponDTO;


public interface CouponWriteService {

	public Long insertCouponWithTx(CouponDTO dto);

	public int updateCouponWithTx(CouponDTO dto);

	public int deleteCouponWithTx(CouponDTO dto);
}
	