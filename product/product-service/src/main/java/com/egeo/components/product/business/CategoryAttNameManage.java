package com.egeo.components.product.business;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.egeo.components.product.vo.ApecificationAndAtt;
import com.egeo.components.product.vo.AttributeNameVO;
import com.egeo.components.product.vo.CategoryAttName;
import com.egeo.components.product.vo.CategoryAttNameVO;
import com.egeo.components.product.dto.CategoryAttNameDTO;

public interface CategoryAttNameManage {

    CategoryAttNameDTO categoryAttNameByAttNameId(CategoryAttNameVO categoryAttNameVO);

    CategoryAttNameDTO findById(CategoryAttNameVO categoryAttNameVO);

	String addCategoryAttName(CategoryAttNameDTO categoryAttNameDTO,HttpServletRequest req);

    List<CategoryAttNameDTO> findAll(CategoryAttNameVO categoryAttNameVO);

    ApecificationAndAtt categoryAttNameByCId(CategoryAttNameVO categoryAttNameVO);

	String deleteCategoryAttName(CategoryAttNameDTO categoryAttNameDTO);

    List<AttributeNameVO> merchantProductAttNameByCId(CategoryAttNameVO categoryAttNameVO);

	String saveCategoryAttNameByCategoryId(Long categoryId,Integer type, List<CategoryAttNameDTO> lists,Long platformId,HttpServletRequest req);

	String updateCategoryAttNameByCategoryId(List<CategoryAttName> lists);

	List<CategoryAttName> showCategoryAttName(Long categoryId, Integer type);
	

}
	