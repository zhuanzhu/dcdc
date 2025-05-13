package com.egeo.components.product.business.impl;
	

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.components.product.business.CategoryAttValueManage;
import com.egeo.components.product.converter.CategoryAttValueConverter;
import com.egeo.components.product.dto.CategoryAttValueDTO;
import com.egeo.components.product.facade.CategoryAttValueFacade;
import com.egeo.components.product.vo.CategoryAttValueVO;

@Service("categoryAttValue")
public class CategoryAttValueManageImpl implements CategoryAttValueManage{

	
	@Resource(name="categoryAttValueFacade")
	private CategoryAttValueFacade categoryAttValueFacade;

        @Override
        public Long saveCategoryAttValue(CategoryAttValueVO categoryAttValueVO) {
            
            return categoryAttValueFacade.saveCategoryAttValue(CategoryAttValueConverter.toDTO(categoryAttValueVO));
        }

        @Override
        public List<CategoryAttValueDTO> findAll(CategoryAttValueVO categoryAttValueVO) {
            return categoryAttValueFacade.findAll(CategoryAttValueConverter.toDTO(categoryAttValueVO));
        }
	


}
	