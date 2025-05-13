package com.egeo.components.promotion.manage.write;

import com.egeo.components.promotion.condition.ExchangeActivityCondition;
import com.egeo.components.promotion.po.ExchangeActivityPO;


public interface ExchangeActivityWriteManage {

	Long insertExchangeActivityWithTx(ExchangeActivityPO po);

	int updateExchangeActivityWithTx(ExchangeActivityPO po);

	int deleteExchangeActivityWithTx(ExchangeActivityPO po);

    int insertOrUpdateExchangeActivityWithTx(ExchangeActivityCondition exchangeActivityCondition);
}
	