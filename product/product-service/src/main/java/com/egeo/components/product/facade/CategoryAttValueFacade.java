package com.egeo.components.product.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.CategoryAttValueReadService;
import com.egeo.components.product.service.write.CategoryAttValueWriteService;
import com.egeo.components.product.dto.CategoryAttValueDTO;


@Component
public class CategoryAttValueFacade {
	
	@Resource
	private CategoryAttValueReadService categoryAttValueReadService;
	
	@Resource
        private CategoryAttValueWriteService categoryAttValueWriteService;

        public Long saveCategoryAttValue(CategoryAttValueDTO dto) {
            return categoryAttValueWriteService.saveCategoryAttValueWithTx(dto);
        }

        public List<CategoryAttValueDTO> findAll(CategoryAttValueDTO dto) {
            return categoryAttValueReadService.findAll(dto);
        }
	


}
	