package com.egeo.components.promotion.manage.write;

import com.egeo.components.promotion.po.ExchangeCouponUnitStatusPO;


public interface ExchangeCouponUnitStatusWriteManage {

	Long insertExchangeCouponUnitStatusWithTx(ExchangeCouponUnitStatusPO po);

	int updateExchangeCouponUnitStatusWithTx(ExchangeCouponUnitStatusPO po);

	int deleteExchangeCouponUnitStatusWithTx(ExchangeCouponUnitStatusPO po);
}
	