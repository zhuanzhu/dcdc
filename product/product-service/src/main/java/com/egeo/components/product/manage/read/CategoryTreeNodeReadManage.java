package com.egeo.components.product.manage.read;

import java.util.List;

import com.egeo.components.product.condition.CategoryTreeNodeCondition;
import com.egeo.components.product.po.CategoryTreeNodePO;


public interface CategoryTreeNodeReadManage {

	List<CategoryTreeNodeCondition> getCategoryTreeByCatId(Long categoryId, Long platformId);

	CategoryTreeNodePO CategoryTreeNodeByCategoryId(Long categoryId);

	CategoryTreeNodePO findById(CategoryTreeNodePO po);

	List<CategoryTreeNodePO> findAll(CategoryTreeNodePO po);

	CategoryTreeNodeCondition findCategoryTreeNodeInofByNodeId(Long ctnId);

	Integer listSortMax();
	/**
	 * 根据条件查询类目节点信息
	 * @param categoryTreeNodeDTO
	 * @return
	 */
	List<CategoryTreeNodeCondition> findCategoryTreeNodeAll(CategoryTreeNodePO po);
	/**
	 * 根据前台类目节点id查询前台类目节点信息
	 * @param categoryTreeNodeVO
	 * @param req
	 * @return
	 */
	CategoryTreeNodeCondition findByCategoryTreeNodeId(Long categoryTreeNodeId);
	/**
	 * 根据类目树id查询所有类目信息
	 * @param po
	 * @return
	 */
	List<CategoryTreeNodeCondition> findWebCategoryTreeByCategoryTreeId(CategoryTreeNodePO po);
	
	/**
	 * 查找子类目中的最大序列号
	 * @param categoryTreeId
	 * @param parentId
	 * @return
	 */
	String findMaxSubSerialNumber(Long categoryTreeId, Long parentId);

    Long findParentidById(Long id);
}
	