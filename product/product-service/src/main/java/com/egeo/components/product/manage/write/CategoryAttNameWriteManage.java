package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.CategoryAttNamePO;

import java.util.List;

public interface CategoryAttNameWriteManage {

	String addCategoryAttNameWithTx(CategoryAttNamePO po);

	String deleteCategoryAttNameWithTx(CategoryAttNamePO po);

	Long updaCategoryAttNameWithTx(CategoryAttNamePO po);

	int deleteByConditionWithTx(CategoryAttNamePO po);

    void addCategoryAttNameListWithTx(List<CategoryAttNamePO> categoryAttNamePOS);
}
	