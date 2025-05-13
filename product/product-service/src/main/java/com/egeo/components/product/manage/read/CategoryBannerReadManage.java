package com.egeo.components.product.manage.read;

import java.util.List;
	
import com.egeo.components.product.po.CategoryBannerPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface CategoryBannerReadManage {

	public CategoryBannerPO findCategoryBannerById(CategoryBannerPO po);

	public PageResult<CategoryBannerPO> findCategoryBannerOfPage(CategoryBannerPO po,Pagination page);

	public List<CategoryBannerPO> findCategoryBannerAll(CategoryBannerPO po);
}
	