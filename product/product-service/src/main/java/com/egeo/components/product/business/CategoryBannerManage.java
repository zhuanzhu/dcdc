package com.egeo.components.product.business;

import java.util.List;
	
import com.egeo.components.product.dto.CategoryBannerDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface CategoryBannerManage {

	public CategoryBannerDTO findCategoryBannerById(CategoryBannerDTO dto);	

	public PageResult<CategoryBannerDTO> findCategoryBannerOfPage(CategoryBannerDTO dto,Pagination page);

	public List<CategoryBannerDTO> findCategoryBannerAll(CategoryBannerDTO dto);

	Long insertCategoryBannerWithTx(CategoryBannerDTO dto);

	int updateCategoryBannerWithTx(CategoryBannerDTO dto);

	int deleteCategoryBannerWithTx(CategoryBannerDTO dto);
}
	