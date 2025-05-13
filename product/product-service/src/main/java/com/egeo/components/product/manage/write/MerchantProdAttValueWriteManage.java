package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.MerchantProdAttValuePO;

import java.util.List;


public interface MerchantProdAttValueWriteManage {

	Long insertMerchantProdAttValueWithTx(MerchantProdAttValuePO po);

	int updateMerchantProdAttValueWithTx(MerchantProdAttValuePO po);

	int deleteMerchantProdAttValueWithTx(MerchantProdAttValuePO po);

    void insertList(List<String> my);
}
	