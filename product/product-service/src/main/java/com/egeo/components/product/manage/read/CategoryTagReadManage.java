package com.egeo.components.product.manage.read;

import java.util.List;
	
import com.egeo.components.product.po.CategoryTagPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface CategoryTagReadManage {

	public CategoryTagPO findCategoryTagById(CategoryTagPO po);

	public PageResult<CategoryTagPO> findCategoryTagOfPage(CategoryTagPO po,Pagination page);

	public List<CategoryTagPO> findCategoryTagAll(CategoryTagPO po);
}
	