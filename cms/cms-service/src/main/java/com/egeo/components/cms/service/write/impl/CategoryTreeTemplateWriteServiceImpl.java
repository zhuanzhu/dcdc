package com.egeo.components.cms.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.cms.service.write.CategoryTreeTemplateWriteService;
import com.egeo.components.cms.manage.write.CategoryTreeTemplateWriteManage;
import com.egeo.components.cms.converter.CategoryTreeTemplateConverter;
import com.egeo.components.cms.dto.CategoryTreeTemplateDTO;
import com.egeo.components.cms.po.CategoryTreeTemplatePO;

@Service("categoryTreeTemplateWriteService")
public class CategoryTreeTemplateWriteServiceImpl  implements CategoryTreeTemplateWriteService {
	@Autowired
	private CategoryTreeTemplateWriteManage categoryTreeTemplateWriteManage;

	@Override
	public Long insertCategoryTreeTemplateWithTx(CategoryTreeTemplateDTO dto) {
		CategoryTreeTemplatePO po = CategoryTreeTemplateConverter.toPO(dto);
		Long rt = categoryTreeTemplateWriteManage.insertCategoryTreeTemplateWithTx(po);		
		return rt;
	}

	@Override
	public int updateCategoryTreeTemplateWithTx(CategoryTreeTemplateDTO dto) {
		CategoryTreeTemplatePO po = CategoryTreeTemplateConverter.toPO(dto);
		int rt = categoryTreeTemplateWriteManage.updateCategoryTreeTemplateWithTx(po);		
		return rt;
	}

	@Override
	public int deleteCategoryTreeTemplateWithTx(CategoryTreeTemplateDTO dto) {
		CategoryTreeTemplatePO po = CategoryTreeTemplateConverter.toPO(dto);
		int rt = categoryTreeTemplateWriteManage.deleteCategoryTreeTemplateWithTx(po);		
		return rt;
	}
}
	