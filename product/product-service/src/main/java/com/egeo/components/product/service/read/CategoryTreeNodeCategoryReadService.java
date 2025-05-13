package com.egeo.components.product.service.read;


import java.util.List;
	
import com.egeo.components.product.dto.CategoryTreeNodeCategoryDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface CategoryTreeNodeCategoryReadService {

	public CategoryTreeNodeCategoryDTO findCategoryTreeNodeCategoryById(CategoryTreeNodeCategoryDTO dto);

	public PageResult<CategoryTreeNodeCategoryDTO> findCategoryTreeNodeCategoryOfPage(CategoryTreeNodeCategoryDTO dto,Pagination page);

	public List<CategoryTreeNodeCategoryDTO> findCategoryTreeNodeCategoryAll(CategoryTreeNodeCategoryDTO dto);

	/**
	 * 根据前台类目节点id查后台类目节点id
	 * @param categoryTreeNodeId
	 * @return
	 */
	public List<Long> findCategoryIdsByCategoryTreeNodeId(List<Long> categoryTreeNodeId);
}
	