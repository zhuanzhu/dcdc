package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.MerchantProdAttNamePO;


public interface MerchantProdAttNameWriteManage {

	Long insertMerchantProdAttNameWithTx(MerchantProdAttNamePO po);

	int updateMerchantProdAttNameWithTx(MerchantProdAttNamePO po);

	int deleteMerchantProdAttNameWithTx(MerchantProdAttNamePO po);
}
	