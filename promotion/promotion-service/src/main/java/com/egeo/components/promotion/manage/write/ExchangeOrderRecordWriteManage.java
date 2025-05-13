package com.egeo.components.promotion.manage.write;

import com.egeo.components.promotion.po.CouponBatchPO;
import com.egeo.components.promotion.po.ExchangeOrderRecordPO;


public interface ExchangeOrderRecordWriteManage {

	Long insertExchangeOrderRecordWithTx(ExchangeOrderRecordPO po);

	int updateExchangeOrderRecordWithTx(ExchangeOrderRecordPO po);

	int deleteExchangeOrderRecordWithTx(ExchangeOrderRecordPO po);

    boolean updateExchangeAndCouponWithTx(String couponUnitCode, Integer couponUnitStatus, Long couponUnitId, CouponBatchPO couponBatchPO, Long recordId, Long userId);

	Boolean insertExchangeOrderRecordAndCouponWithTx(String mail,Long userId, Long exchangeCouponBatchId, Long exchangeCouponUnitId, Long exchangeId);

	int deleteExchangeOrderRecordByParamWithTx(ExchangeOrderRecordPO exchangeOrderRecordPO);

    int updateExchangeOrderRecordByOrderCodeWithTx(ExchangeOrderRecordPO po);
}
	