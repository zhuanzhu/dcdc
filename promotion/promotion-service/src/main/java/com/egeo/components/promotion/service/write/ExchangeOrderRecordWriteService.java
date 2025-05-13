package com.egeo.components.promotion.service.write;

import com.egeo.components.promotion.dto.CouponBatchDTO;
import com.egeo.components.promotion.dto.ExchangeOrderRecordDTO;


public interface ExchangeOrderRecordWriteService {

	public Long insertExchangeOrderRecordWithTx(ExchangeOrderRecordDTO dto);

	public int updateExchangeOrderRecordWithTx(ExchangeOrderRecordDTO dto);

	public int deleteExchangeOrderRecordWithTx(ExchangeOrderRecordDTO dto);

    boolean updateExchangeAndCouponWithTx(String couponUnitCode, Integer couponUnitStatus, Long couponUnitId, CouponBatchDTO couponBatchDTO, Long recordId, Long userId);

	Boolean insertExchangeOrderRecordAndCouponWithTx( String mail,Long userId, Long exchangeCouponBatchId, Long exchangeCouponUnitId, Long exchangeId);

    int deleteExchangeOrderRecordByParamWithTx(ExchangeOrderRecordDTO recordDTO);

    int updateExchangeOrderRecordByOrderCodeWithTx(ExchangeOrderRecordDTO recordDTO);
}
	