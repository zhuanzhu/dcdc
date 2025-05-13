package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.JdProductInnerIdPO;

import java.util.List;


public interface JdProductInnerIdWriteManage {

	Long insertJdProductInnerIdWithTx(JdProductInnerIdPO po);

	int updateJdProductInnerIdWithTx(JdProductInnerIdPO po);

	int deleteJdProductInnerIdWithTx(JdProductInnerIdPO po);

    void saveJdProductInnerIdList(List<JdProductInnerIdPO> jdProductInnerIdPOList);
}
	