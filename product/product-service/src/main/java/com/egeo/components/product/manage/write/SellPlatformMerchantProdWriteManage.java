package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.SellPlatformMerchantProdPO;


public interface SellPlatformMerchantProdWriteManage {

	Long insertSellPlatformMerchantProdWithTx(SellPlatformMerchantProdPO po);

	int updateSellPlatformMerchantProdWithTx(SellPlatformMerchantProdPO po);

	int deleteSellPlatformMerchantProdWithTx(SellPlatformMerchantProdPO po);
}
	