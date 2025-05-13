package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.CategoryTreeNodeRelationPO;

public interface CategoryTreeNodeRelationWriteManage {

	String addCategoryTreeNodeWithTx(CategoryTreeNodeRelationPO po);

	String deleteCategoryTreeNodeWithTx(CategoryTreeNodeRelationPO po);
}
	