package com.egeo.components.promotion.manage.write;

import com.egeo.components.promotion.po.CardBatchPO;


public interface CardBatchWriteManage {

	Long insertCardBatchWithTx(CardBatchPO po);

	int updateCardBatchWithTx(CardBatchPO po);

	int deleteCardBatchWithTx(CardBatchPO po);
}
	