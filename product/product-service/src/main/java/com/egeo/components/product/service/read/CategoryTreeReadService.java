package com.egeo.components.product.service.read;

import java.util.List;

import com.egeo.components.product.dto.CategoryTreeDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;



public interface CategoryTreeReadService {

	List<CategoryTreeDTO> queryAllCategoryTreeByParam(CategoryTreeDTO dto);
	/**
	 * 根据条件查询类目树
	 * @param categoryTreeDTO
	 * @return
	 */
	List<CategoryTreeDTO> findCategoryAll(CategoryTreeDTO categoryTreeDTO);
	/**
	 * 分页查询前台类目树
	 * @param req
	 * @return
	 */
	PageResult<CategoryTreeDTO> findCategoryTreeOfPage(CategoryTreeDTO dto, Pagination page);
	/**
	 * 根据类目树id查询类目树信息
	 */
	CategoryTreeDTO findByCategoryTreeId(Long categoryTreeId);
	/**
	 * 查询所有类目树信息
	 */
	List<CategoryTreeDTO> findCategoryTreeAll(Long platformId);

	
}
	