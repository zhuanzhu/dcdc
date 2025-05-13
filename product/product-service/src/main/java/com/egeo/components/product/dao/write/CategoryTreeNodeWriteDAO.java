package com.egeo.components.product.dao.write;

import com.egeo.components.product.po.CategoryTreeNodePO;
import com.egeo.orm.BaseWriteDAO;

public interface CategoryTreeNodeWriteDAO extends BaseWriteDAO<CategoryTreeNodePO> {

	int deleteByCategoryId(CategoryTreeNodePO po);
}
	