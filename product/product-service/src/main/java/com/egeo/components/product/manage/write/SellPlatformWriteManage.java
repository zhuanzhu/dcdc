package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.SellPlatformPO;


public interface SellPlatformWriteManage {

	Long insertSellPlatformWithTx(SellPlatformPO po);

	int updateSellPlatformWithTx(SellPlatformPO po);

	int deleteSellPlatformWithTx(SellPlatformPO po);
}
	