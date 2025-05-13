package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.CategoryBannerPO;


public interface CategoryBannerWriteManage {

	Long insertCategoryBannerWithTx(CategoryBannerPO po);

	int updateCategoryBannerWithTx(CategoryBannerPO po);

	int deleteCategoryBannerWithTx(CategoryBannerPO po);
}
	