package com.egeo.components.promotion.manage.write;

import com.egeo.components.promotion.po.FuCoinHistoryPO;


public interface FuCoinHistoryWriteManage {

	Long insertFuCoinHistoryWithTx(FuCoinHistoryPO po);

	int updateFuCoinHistoryWithTx(FuCoinHistoryPO po);

	int deleteFuCoinHistoryWithTx(FuCoinHistoryPO po);
}
	