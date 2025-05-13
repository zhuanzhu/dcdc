package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.MerchantPO;


public interface MerchantWriteManage {

	Long insertMerchantWithTx(MerchantPO po);

	int updateMerchantWithTx(MerchantPO po);

	int deleteMerchantWithTx(MerchantPO po);
}
	