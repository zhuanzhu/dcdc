package com.egeo.components.product.manage.read;

import java.util.List;

import com.egeo.components.product.po.CategoryTreePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface CategoryTreeReadManage {

	List<CategoryTreePO> queryAllCategoryTreeByParam(CategoryTreePO po);
	/**
	 * 根据条件查询类目树
	 * @param categoryTreeDTO
	 * @return
	 */
	List<CategoryTreePO> findCategoryAll(CategoryTreePO po);
	/**
	 * 分页查询前台类目树
	 * @param req
	 * @return
	 */
	PageResult<CategoryTreePO> findCategoryTreeOfPage(CategoryTreePO po, Pagination page);
	/**
	 * 根据类目树id查询类目树信息
	 */
	CategoryTreePO findByCategoryTreeId(Long categoryTreeId);
	/**
	 * 查询所有类目树信息
	 */
	List<CategoryTreePO> findCategoryTreeAll(Long platformId);

	
}
	