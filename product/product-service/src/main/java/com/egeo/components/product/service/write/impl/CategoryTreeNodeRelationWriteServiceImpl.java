package com.egeo.components.product.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.write.CategoryTreeNodeRelationWriteService;
import com.egeo.components.product.manage.write.CategoryTreeNodeRelationWriteManage;
import com.egeo.components.product.converter.CategoryTreeNodeRelationConverter;
import com.egeo.components.product.dto.CategoryTreeNodeRelationDTO;
import com.egeo.components.product.po.CategoryTreeNodeRelationPO;

@Service("categoryTreeNodeRelationWriteService")
public class CategoryTreeNodeRelationWriteServiceImpl  implements CategoryTreeNodeRelationWriteService {
	@Autowired
	private CategoryTreeNodeRelationWriteManage categoryTreeNodeRelationWriteManage;

	@Override
	public String addCategoryTreeNodeWithTx(CategoryTreeNodeRelationDTO dto) {
		
		CategoryTreeNodeRelationPO po = CategoryTreeNodeRelationConverter.toPO(dto);
		
		return categoryTreeNodeRelationWriteManage.addCategoryTreeNodeWithTx(po);
	}

	@Override
	public String deleteCategoryTreeNodeWithTx(CategoryTreeNodeRelationDTO dto) {
		CategoryTreeNodeRelationPO po = CategoryTreeNodeRelationConverter.toPO(dto);
		
		return categoryTreeNodeRelationWriteManage.deleteCategoryTreeNodeWithTx(po);
	}
}
	