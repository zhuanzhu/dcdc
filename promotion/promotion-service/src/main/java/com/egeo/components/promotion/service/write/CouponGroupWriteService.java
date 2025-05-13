package com.egeo.components.promotion.service.write;

import com.egeo.components.promotion.dto.CouponGroupDTO;


public interface CouponGroupWriteService {

	public Long insertCouponGroupWithTx(CouponGroupDTO dto);

	public int updateCouponGroupWithTx(CouponGroupDTO dto);

	public int deleteCouponGroupWithTx(CouponGroupDTO dto);
}
	