package com.egeo.components.product.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.write.CategoryAttNameWriteService;
import com.egeo.components.product.manage.write.CategoryAttNameWriteManage;
import com.egeo.components.product.converter.CategoryAttNameConverter;
import com.egeo.components.product.dto.CategoryAttNameDTO;
import com.egeo.components.product.po.CategoryAttNamePO;

import java.util.List;

@Service("categoryAttNameWriteService")
public class CategoryAttNameWriteServiceImpl  implements CategoryAttNameWriteService {
	@Autowired
	private CategoryAttNameWriteManage categoryAttNameWriteManage;

	@Override
	public String addCategoryAttNameWithTx(CategoryAttNameDTO dto) {
	    dto.setSortValue(1);
		CategoryAttNamePO po = CategoryAttNameConverter.toPO(dto);
		return categoryAttNameWriteManage.addCategoryAttNameWithTx(po);
	}

	@Override
	public String deleteCategoryAttNameWithTx(CategoryAttNameDTO dto) {
		CategoryAttNamePO po = CategoryAttNameConverter.toPO(dto);
		return categoryAttNameWriteManage.deleteCategoryAttNameWithTx(po);
	}

	@Override
	public Long updaCategoryAttNameWithTx(CategoryAttNameDTO categoryAttNameDTO) {
		return categoryAttNameWriteManage.updaCategoryAttNameWithTx(CategoryAttNameConverter.toPO(categoryAttNameDTO));
	}

	@Override
	public int deleteByConditionWithTx(CategoryAttNameDTO categoryAttNameDTO) {
		return categoryAttNameWriteManage.deleteByConditionWithTx(CategoryAttNameConverter.toPO(categoryAttNameDTO));
	}

	@Override
	public void addCategoryAttNameListWithTx(List<CategoryAttNameDTO> list) {
		categoryAttNameWriteManage.addCategoryAttNameListWithTx(CategoryAttNameConverter.toPO(list));
	}
}
	