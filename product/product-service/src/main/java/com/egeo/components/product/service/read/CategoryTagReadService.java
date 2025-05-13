package com.egeo.components.product.service.read;


import java.util.List;
	
import com.egeo.components.product.dto.CategoryTagDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface CategoryTagReadService {

	public CategoryTagDTO findCategoryTagById(CategoryTagDTO dto);

	public PageResult<CategoryTagDTO> findCategoryTagOfPage(CategoryTagDTO dto,Pagination page);

	public List<CategoryTagDTO> findCategoryTagAll(CategoryTagDTO dto);
}
	