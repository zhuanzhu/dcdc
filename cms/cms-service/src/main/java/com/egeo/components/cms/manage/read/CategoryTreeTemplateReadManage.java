package com.egeo.components.cms.manage.read;

import java.util.List;
	
import com.egeo.components.cms.po.CategoryTreeTemplatePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface CategoryTreeTemplateReadManage {

	public CategoryTreeTemplatePO findCategoryTreeTemplateById(CategoryTreeTemplatePO po);

	public PageResult<CategoryTreeTemplatePO> findCategoryTreeTemplateOfPage(CategoryTreeTemplatePO po,Pagination page);

	public List<CategoryTreeTemplatePO> findCategoryTreeTemplateAll(CategoryTreeTemplatePO po);
}
	