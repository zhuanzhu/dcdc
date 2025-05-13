package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.SupplierProductPO;


public interface SupplierProductWriteManage {

	Long insertSupplierProductWithTx(SupplierProductPO po);

	int updateSupplierProductWithTx(SupplierProductPO po);

	int deleteSupplierProductWithTx(SupplierProductPO po);
}
	