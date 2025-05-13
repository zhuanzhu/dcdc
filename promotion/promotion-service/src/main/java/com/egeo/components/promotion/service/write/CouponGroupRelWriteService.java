package com.egeo.components.promotion.service.write;

import com.egeo.components.promotion.dto.CouponGroupRelDTO;


public interface CouponGroupRelWriteService {

	public Long insertCouponGroupRelWithTx(CouponGroupRelDTO dto);

	public int updateCouponGroupRelWithTx(CouponGroupRelDTO dto);

	public int deleteCouponGroupRelWithTx(CouponGroupRelDTO dto);
}
	