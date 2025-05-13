package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.MerchantProdDescribePO;

import java.util.List;


public interface MerchantProdDescribeWriteManage {

	Long insertMerchantProdDescribeWithTx(MerchantProdDescribePO po);

	int updateMerchantProdDescribeWithTx(MerchantProdDescribePO po);

	int deleteMerchantProdDescribeWithTx(MerchantProdDescribePO po);

    void saveMerchantProdDescribe(List<MerchantProdDescribePO> merchantProdDescribePOList);

    void updateMerchantProdDescribeSWithTx(MerchantProdDescribePO merchantProdDescribePO);
}
	