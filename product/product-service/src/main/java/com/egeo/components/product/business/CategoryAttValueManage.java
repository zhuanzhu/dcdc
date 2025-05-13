package com.egeo.components.product.business;

import java.util.List;

import com.egeo.components.product.vo.CategoryAttValueVO;
import com.egeo.components.product.dto.CategoryAttValueDTO;

public interface CategoryAttValueManage {

    Long saveCategoryAttValue(CategoryAttValueVO categoryAttValueVO);

    List<CategoryAttValueDTO> findAll(CategoryAttValueVO categoryAttValueVO);
	

}
	