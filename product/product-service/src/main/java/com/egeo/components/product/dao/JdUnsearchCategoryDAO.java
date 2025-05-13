package com.egeo.components.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.egeo.components.product.dto.JdUnsearchCategoryDTO;

@Mapper
public interface JdUnsearchCategoryDAO{
	/**
	 * 根据属性id更新属性范围信息
	 * @param attributeNameDecimalDTO
	 * @return
	 */
	@Select("SELECT * FROM jd_unsearch_category")
	List<JdUnsearchCategoryDTO> findAll();
}
	