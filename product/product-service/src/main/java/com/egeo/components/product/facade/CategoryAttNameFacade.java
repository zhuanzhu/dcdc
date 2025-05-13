package com.egeo.components.product.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.CategoryAttNameReadService;
import com.egeo.components.product.service.write.CategoryAttNameWriteService;
import com.egeo.components.product.dto.AttributeNameDTO;
import com.egeo.components.product.dto.CategoryAttNameDTO;
import com.egeo.components.product.dto.CategoryAttNameValuse;


@Component
public class CategoryAttNameFacade {
	
	@Resource
	private CategoryAttNameReadService categoryAttNameReadService;
	
	@Resource
	private CategoryAttNameWriteService categoryAttNameWriteService;

        public List<CategoryAttNameDTO> categoryAttNameByCategoryId(CategoryAttNameDTO dto) {
            return categoryAttNameReadService.categoryAttNameByCategoryId(dto);
        }

        public CategoryAttNameDTO categoryAttNameByAttNameId(CategoryAttNameDTO dto) {
            return categoryAttNameReadService.categoryAttNameByAttNameId(dto);
        }

        public CategoryAttNameDTO findById(CategoryAttNameDTO dto) {
            return categoryAttNameReadService.findById(dto);
        }

       public List<CategoryAttNameValuse> categoryAttNameByCId(CategoryAttNameDTO dto) {
            return categoryAttNameReadService.categoryAttNameByCId(dto);
        }

        public List<CategoryAttNameDTO> findAll(CategoryAttNameDTO dto) {
            return categoryAttNameReadService.findAll(dto);
        }
	

		public String addCategoryAttNameWithTx(CategoryAttNameDTO dto) {
			return categoryAttNameWriteService.addCategoryAttNameWithTx(dto);
		}

		public String deleteCategoryAttNameWithTx(CategoryAttNameDTO categoryAttNameDTO) {
			return categoryAttNameWriteService.deleteCategoryAttNameWithTx(categoryAttNameDTO);
		}

        public List<AttributeNameDTO> merchantProductAttNameByCId(CategoryAttNameDTO dto) {
            return categoryAttNameReadService.merchantProductAttNameByCId(dto);
        }

		public Long updaCategoryAttName(CategoryAttNameDTO categoryAttNameDTO) {
			return categoryAttNameWriteService.updaCategoryAttNameWithTx(categoryAttNameDTO);
		}

		public int deleteByConditionWithTx(CategoryAttNameDTO categoryAttNameDTO) {
			return categoryAttNameWriteService.deleteByConditionWithTx(categoryAttNameDTO);
		}
	


}
	