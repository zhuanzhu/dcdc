package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.MerchantProductStorePO;

import java.util.List;


public interface MerchantProductStoreWriteManage {

	Long insertMerchantProductStoreWithTx(MerchantProductStorePO po);

	int updateMerchantProductStoreWithTx(MerchantProductStorePO po);

	int deleteMerchantProductStoreWithTx(MerchantProductStorePO po);

    void saveMerchantProductStore(List<MerchantProductStorePO> merchantProductStorePOList);
}
	