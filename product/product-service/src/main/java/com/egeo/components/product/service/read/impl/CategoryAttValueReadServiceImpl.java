package com.egeo.components.product.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.CategoryAttValueReadService;
import com.egeo.components.product.manage.read.CategoryAttValueReadManage;
import com.egeo.components.product.converter.CategoryAttValueConverter;
import com.egeo.components.product.dto.CategoryAttValueDTO;
import com.egeo.components.product.po.CategoryAttValuePO;


@Service("categoryAttValueReadService")
public class CategoryAttValueReadServiceImpl  implements CategoryAttValueReadService {
	@Autowired
	private CategoryAttValueReadManage categoryAttValueReadManage;

        @Override
        public List<CategoryAttValueDTO> findAll(CategoryAttValueDTO dto) {
            List<CategoryAttValuePO> list = categoryAttValueReadManage.findAll(CategoryAttValueConverter.toPO(dto));
            return CategoryAttValueConverter.toDTO(list);
        }
}
	