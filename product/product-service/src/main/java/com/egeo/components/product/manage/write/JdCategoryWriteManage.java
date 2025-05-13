package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.JdCategoryPO;


public interface JdCategoryWriteManage {

	Long insertJdCategoryWithTx(JdCategoryPO po);

	int updateJdCategoryWithTx(JdCategoryPO po);

	int deleteJdCategoryWithTx(JdCategoryPO po);
}
	