package com.egeo.components.product.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.write.CategoryBannerWriteService;
import com.egeo.components.product.manage.write.CategoryBannerWriteManage;
import com.egeo.components.product.converter.CategoryBannerConverter;
import com.egeo.components.product.dto.CategoryBannerDTO;
import com.egeo.components.product.po.CategoryBannerPO;

@Service("categoryBannerWriteService")
public class CategoryBannerWriteServiceImpl  implements CategoryBannerWriteService {
	@Autowired
	private CategoryBannerWriteManage categoryBannerWriteManage;

	@Override
	public Long insertCategoryBannerWithTx(CategoryBannerDTO dto) {
		CategoryBannerPO po = CategoryBannerConverter.toPO(dto);
		Long rt = categoryBannerWriteManage.insertCategoryBannerWithTx(po);		
		return rt;
	}

	@Override
	public int updateCategoryBannerWithTx(CategoryBannerDTO dto) {
		CategoryBannerPO po = CategoryBannerConverter.toPO(dto);
		int rt = categoryBannerWriteManage.updateCategoryBannerWithTx(po);		
		return rt;
	}

	@Override
	public int deleteCategoryBannerWithTx(CategoryBannerDTO dto) {
		CategoryBannerPO po = CategoryBannerConverter.toPO(dto);
		int rt = categoryBannerWriteManage.deleteCategoryBannerWithTx(po);		
		return rt;
	}
}
	