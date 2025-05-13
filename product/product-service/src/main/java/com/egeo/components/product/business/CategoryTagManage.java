package com.egeo.components.product.business;

import java.util.List;
	
import com.egeo.components.product.dto.CategoryTagDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface CategoryTagManage {

	public CategoryTagDTO findCategoryTagById(CategoryTagDTO dto);	

	public PageResult<CategoryTagDTO> findCategoryTagOfPage(CategoryTagDTO dto,Pagination page);

	public List<CategoryTagDTO> findCategoryTagAll(CategoryTagDTO dto);

	Long insertCategoryTagWithTx(CategoryTagDTO dto);

	int updateCategoryTagWithTx(CategoryTagDTO dto);

	int deleteCategoryTagWithTx(CategoryTagDTO dto);
}
	