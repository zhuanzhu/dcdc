package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.MerchantProdCausePO;


public interface MerchantProdCauseWriteManage {

	Long insertMerchantProdCauseWithTx(MerchantProdCausePO po);

	int updateMerchantProdCauseWithTx(MerchantProdCausePO po);

	int deleteMerchantProdCauseWithTx(MerchantProdCausePO po);
}
	