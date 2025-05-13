package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.CategoryBannerDTO;


public interface CategoryBannerWriteService {

	public Long insertCategoryBannerWithTx(CategoryBannerDTO dto);

	public int updateCategoryBannerWithTx(CategoryBannerDTO dto);

	public int deleteCategoryBannerWithTx(CategoryBannerDTO dto);
}
	