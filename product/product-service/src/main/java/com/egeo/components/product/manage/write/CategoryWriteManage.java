package com.egeo.components.product.manage.write;

import java.util.List;

import com.egeo.components.product.po.CategoryPO;

public interface CategoryWriteManage {

	String modifyCategoryWithTX(CategoryPO po,List<Long> tagIdList);

	String deleteCategoryByIdWithTx(Long categoryId);
	
	Long insertCategoryWithTx(CategoryPO po);
}
	