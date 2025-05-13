package com.egeo.components.promotion.service.write;

import com.egeo.components.promotion.dto.CouponBatchCompanyDTO;


public interface CouponBatchCompanyWriteService {

	public Long insertCouponBatchCompanyWithTx(CouponBatchCompanyDTO dto);

	public int updateCouponBatchCompanyWithTx(CouponBatchCompanyDTO dto);

	public int deleteCouponBatchCompanyWithTx(CouponBatchCompanyDTO dto);
}
	