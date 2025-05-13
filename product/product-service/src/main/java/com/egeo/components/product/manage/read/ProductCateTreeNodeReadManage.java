package com.egeo.components.product.manage.read;

import java.util.List;
import com.egeo.components.product.po.ProductCateTreeNodePO;

public interface ProductCateTreeNodeReadManage {

	public List<ProductCateTreeNodePO> findAll(ProductCateTreeNodePO productCateTreeNodePO);
	
}
	