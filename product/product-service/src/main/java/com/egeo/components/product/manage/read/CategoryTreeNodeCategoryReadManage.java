package com.egeo.components.product.manage.read;

import java.util.List;
	
import com.egeo.components.product.po.CategoryTreeNodeCategoryPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface CategoryTreeNodeCategoryReadManage {

	public CategoryTreeNodeCategoryPO findCategoryTreeNodeCategoryById(CategoryTreeNodeCategoryPO po);

	public PageResult<CategoryTreeNodeCategoryPO> findCategoryTreeNodeCategoryOfPage(CategoryTreeNodeCategoryPO po,Pagination page);

	public List<CategoryTreeNodeCategoryPO> findCategoryTreeNodeCategoryAll(CategoryTreeNodeCategoryPO po);

	public List<Long> findCategoryIdsByCategoryTreeNodeId(List<Long> categoryTreeNodeId);
}
	