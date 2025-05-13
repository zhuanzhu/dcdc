package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.SupplierPO;


public interface SupplierWriteManage {

	Long insertSupplierWithTx(SupplierPO po);

	int updateSupplierWithTx(SupplierPO po);

	int deleteSupplierWithTx(SupplierPO po);
}
	