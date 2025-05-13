package com.egeo.components.promotion.manage.write;

import com.egeo.components.promotion.po.ExchangeBatchPO;


public interface ExchangeBatchWriteManage {

	Long insertExchangeBatchWithTx(ExchangeBatchPO po);

	int updateExchangeBatchWithTx(ExchangeBatchPO po);

	int deleteExchangeBatchWithTx(ExchangeBatchPO po);
}
	