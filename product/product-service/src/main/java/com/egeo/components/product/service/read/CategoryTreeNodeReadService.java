package com.egeo.components.product.service.read;

import java.util.List;

import com.egeo.components.product.dto.CategoryTreeNodeDTO;

public interface CategoryTreeNodeReadService {
	List<CategoryTreeNodeDTO> getCategoryTreeByCatId(Long categoryId, Long platformId);

	CategoryTreeNodeDTO CategoryTreeNodeByCategoryId(Long parentId);

	CategoryTreeNodeDTO findById(CategoryTreeNodeDTO dto);

	List<CategoryTreeNodeDTO> findAll(CategoryTreeNodeDTO dto);

	CategoryTreeNodeDTO findCategoryTreeNodeInofByNodeId(Long ctnId);

	Integer listSortMax();
	/**
	 * 根据条件查询类目节点信息
	 * @param categoryTreeNodeDTO
	 * @return
	 */
	List<CategoryTreeNodeDTO> findCategoryTreeNodeAll(CategoryTreeNodeDTO categoryTreeNodeDTO);
	/**
	 * 根据前台类目节点id查询前台类目节点信息
	 * @param categoryTreeNodeVO
	 * @param req
	 * @return
	 */
	CategoryTreeNodeDTO findByCategoryTreeNodeId(Long categoryTreeNodeId);
	/**
	 * 根据类目树id查询所有类目信息
	 * @param categoryTreeNodeDTO
	 * @return
	 */
	List<CategoryTreeNodeDTO> findWebCategoryTreeByCategoryTreeId(CategoryTreeNodeDTO categoryTreeNodeDTO);
	
	/**
	 * 查找子类目中的最大序列号
	 * @param categoryTreeId
	 * @param parentId
	 * @return
	 */
	String findMaxSubSerialNumber(Long categoryTreeId, Long parentId);

    Long findParentidById(Long id);
}
	