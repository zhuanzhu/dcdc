package com.egeo.components.product.service.read;


import java.util.List;
	
import com.egeo.components.product.dto.CategoryBannerDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface CategoryBannerReadService {

	public CategoryBannerDTO findCategoryBannerById(CategoryBannerDTO dto);

	public PageResult<CategoryBannerDTO> findCategoryBannerOfPage(CategoryBannerDTO dto,Pagination page);

	public List<CategoryBannerDTO> findCategoryBannerAll(CategoryBannerDTO dto);
}
	