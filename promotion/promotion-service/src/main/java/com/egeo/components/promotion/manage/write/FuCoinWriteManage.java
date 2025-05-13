package com.egeo.components.promotion.manage.write;

import com.egeo.components.promotion.po.FuCoinPO;


public interface FuCoinWriteManage {

	Long insertFuCoinWithTx(FuCoinPO po);

	int updateFuCoinWithTx(FuCoinPO po);

	int deleteFuCoinWithTx(FuCoinPO po);
}
	