package com.egeo.components.promotion.service.write;

import com.egeo.components.promotion.dto.CouponCompanyDTO;


public interface CouponCompanyWriteService {

	public Long insertCouponCompanyWithTx(CouponCompanyDTO dto);

	public int updateCouponCompanyWithTx(CouponCompanyDTO dto);

	public int deleteCouponCompanyWithTx(CouponCompanyDTO dto);
}
	