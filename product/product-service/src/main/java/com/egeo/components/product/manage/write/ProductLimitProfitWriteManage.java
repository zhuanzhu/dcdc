package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.ProductLimitProfitPO;


public interface ProductLimitProfitWriteManage {

	Long insertProductLimitProfitWithTx(ProductLimitProfitPO po);

	int updateProductLimitProfitWithTx(ProductLimitProfitPO po);

	int deleteProductLimitProfitWithTx(ProductLimitProfitPO po);
}
	