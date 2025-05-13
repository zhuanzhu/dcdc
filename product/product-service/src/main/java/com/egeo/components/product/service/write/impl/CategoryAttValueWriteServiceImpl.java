package com.egeo.components.product.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.write.CategoryAttValueWriteService;
import com.egeo.components.product.manage.write.CategoryAttValueWriteManage;
import com.egeo.components.product.converter.CategoryAttValueConverter;
import com.egeo.components.product.dto.CategoryAttValueDTO;

@Service("categoryAttValueWriteService")
public class CategoryAttValueWriteServiceImpl  implements CategoryAttValueWriteService {
	@Autowired
	private CategoryAttValueWriteManage categoryAttValueWriteManage;

        @Override
        public Long saveCategoryAttValueWithTx(CategoryAttValueDTO dto) {
            if(dto.getSortValue() == null){
                dto.setSortValue(1);
            }
            dto.setSortValue(1);
            return categoryAttValueWriteManage.saveCategoryAttValue(CategoryAttValueConverter.toPO(dto));
        }
}
	