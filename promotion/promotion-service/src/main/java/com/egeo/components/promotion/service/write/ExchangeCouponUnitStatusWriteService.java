package com.egeo.components.promotion.service.write;

import com.egeo.components.promotion.dto.ExchangeCouponUnitStatusDTO;


public interface ExchangeCouponUnitStatusWriteService {

	public Long insertExchangeCouponUnitStatusWithTx(ExchangeCouponUnitStatusDTO dto);

	public int updateExchangeCouponUnitStatusWithTx(ExchangeCouponUnitStatusDTO dto);

	public int deleteExchangeCouponUnitStatusWithTx(ExchangeCouponUnitStatusDTO dto);
}
	