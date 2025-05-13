package com.egeo.components.product.business.impl;
	

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.egeo.components.product.business.CategoryTreeNodeRelationManage;
import com.egeo.components.product.converter.CategoryTreeNodeRelationConverter;
import com.egeo.components.product.dto.CategoryTreeNodeRelationDTO;
import com.egeo.components.product.facade.CategoryTreeNodeRelationFacade;
import com.egeo.components.product.vo.CategoryTreeNodeRelationVO;

@Service("categoryTreeNodeRelation")
public class CategoryTreeNodeRelationManageImpl implements CategoryTreeNodeRelationManage{

	
	@Resource(name="categoryTreeNodeRelationFacade")
	private CategoryTreeNodeRelationFacade categoryTreeNodeRelationFacade;

	@Override
	public String addCategoryTreeNodeRelation(CategoryTreeNodeRelationVO categoryTreeNodeRelationVO,
			HttpServletRequest req) {
		CategoryTreeNodeRelationDTO dto = CategoryTreeNodeRelationConverter.toDTO(categoryTreeNodeRelationVO);
		
		return categoryTreeNodeRelationFacade.addCategoryTreeNodeWithTx(dto);
	}

	@Override
	public String deleteCategoryTreeNodeRelation(CategoryTreeNodeRelationVO categoryTreeNodeRelationVO) {

		CategoryTreeNodeRelationDTO dto = new CategoryTreeNodeRelationDTO();
		dto.setBackTreeNodeId(categoryTreeNodeRelationVO.getBackTreeNodeId());
		dto.setFrontTreeNodeId(categoryTreeNodeRelationVO.getFrontTreeNodeId());		
		return categoryTreeNodeRelationFacade.deleteCategoryTreeNodeWithTx(dto);
	}
	


}
	