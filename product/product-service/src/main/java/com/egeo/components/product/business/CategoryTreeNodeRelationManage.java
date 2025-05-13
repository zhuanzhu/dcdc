package com.egeo.components.product.business;

import javax.servlet.http.HttpServletRequest;

import com.egeo.components.product.vo.CategoryTreeNodeRelationVO;

public interface CategoryTreeNodeRelationManage {

	String addCategoryTreeNodeRelation(CategoryTreeNodeRelationVO categoryTreeNodeRelationVO, HttpServletRequest req);

	String deleteCategoryTreeNodeRelation(CategoryTreeNodeRelationVO categoryTreeNodeRelationVO);
	

}
	