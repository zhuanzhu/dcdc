package com.egeo.components.cms.manage.write;

import com.egeo.components.cms.po.CategoryTreeTemplatePO;


public interface CategoryTreeTemplateWriteManage {

	Long insertCategoryTreeTemplateWithTx(CategoryTreeTemplatePO po);

	int updateCategoryTreeTemplateWithTx(CategoryTreeTemplatePO po);

	int deleteCategoryTreeTemplateWithTx(CategoryTreeTemplatePO po);
}
	