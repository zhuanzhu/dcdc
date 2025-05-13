package com.egeo.components.product.service.write.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.write.CategoryWriteService;
import com.egeo.components.product.manage.write.CategoryWriteManage;
import com.egeo.components.product.converter.CategoryConverter;
import com.egeo.components.product.dto.CategoryDTO;

@Service("categoryWriteService")
public class CategoryWriteServiceImpl  implements CategoryWriteService {
	@Autowired
	private CategoryWriteManage categoryWriteManage;

	@Override
	public String modifyCategoryWithTX(CategoryDTO dto,List<Long> tagIdList) {
		
		return categoryWriteManage.modifyCategoryWithTX(CategoryConverter.toPO(dto),tagIdList);
	}

	@Override
	public String deleteCategoryByIdWithTx(Long categoryId) {
		
		return categoryWriteManage.deleteCategoryByIdWithTx(categoryId);
	}
	
	@Override
	public Long insertCategoryWithTx(CategoryDTO dto) {
		return categoryWriteManage.insertCategoryWithTx(CategoryConverter.toPO(dto));
	}
}
	