package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.CategoryTagPO;


public interface CategoryTagWriteManage {

	Long insertCategoryTagWithTx(CategoryTagPO po);

	int updateCategoryTagWithTx(CategoryTagPO po);

	int deleteCategoryTagWithTx(CategoryTagPO po);
}
	