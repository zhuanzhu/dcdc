package com.egeo.components.product.facade;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.CategoryTreeNodeRelationReadService;
import com.egeo.components.product.service.write.CategoryTreeNodeRelationWriteService;
import com.egeo.components.product.dto.CategoryTreeNodeRelationDTO;


@Component
public class CategoryTreeNodeRelationFacade {
	
	@Resource
	private CategoryTreeNodeRelationReadService categoryTreeNodeRelationReadService;
	
	@Resource
	private CategoryTreeNodeRelationWriteService categoryTreeNodeRelationWriteService;

	public String addCategoryTreeNodeWithTx(CategoryTreeNodeRelationDTO dto) {
		return categoryTreeNodeRelationWriteService.addCategoryTreeNodeWithTx(dto);
	}

	public String deleteCategoryTreeNodeWithTx(CategoryTreeNodeRelationDTO dto) {
		return categoryTreeNodeRelationWriteService.deleteCategoryTreeNodeWithTx(dto);
	}
	


}
	