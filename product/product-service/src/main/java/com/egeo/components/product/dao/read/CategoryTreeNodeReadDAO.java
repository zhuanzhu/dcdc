package com.egeo.components.product.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.product.condition.CategoryTreeNodeCondition;
import com.egeo.components.product.po.CategoryTreeNodePO;
import com.egeo.orm.BaseReadDAO;
import com.egeo.orm.Pagination;

public interface CategoryTreeNodeReadDAO extends BaseReadDAO<CategoryTreeNodePO>{

	List<CategoryTreeNodeCondition> getCategoryTreeByCatId(@Param("po") CategoryTreeNodePO po);

	CategoryTreeNodePO categoryTreeNodeByCategoryId(@Param("parentId") Long parentId);

	List<CategoryTreeNodeCondition> findChildNodebyCategoryName(@Param("po") CategoryTreeNodeCondition po);

	CategoryTreeNodeCondition findCategoryTreeNodeInofByNodeId(@Param("ctnId") Long ctnId);

	Integer listSortMax();
	/**
	 * 同一个父节点下，不允许有2个相同的类目序列号
	 * @param categoryTreeId
	 * @param parentId
	 * @param serialNumber
	 * @return
	 */
	List<CategoryTreeNodeCondition> findChildNodebyCategorySerialNumber(@Param("po")CategoryTreeNodeCondition po);
	/**
	 * 根据条件查询类目节点信息
	 * @param categoryTreeNodeDTO
	 * @return
	 */
	List<CategoryTreeNodeCondition> findCategoryTreeNodeAll(@Param("po")CategoryTreeNodePO po , @Param("page") Pagination page);
	/**
	 * 根据前台类目节点id查询前台类目节点信息
	 * @param categoryTreeNodeVO
	 * @param req
	 * @return
	 */
	CategoryTreeNodeCondition findByCategoryTreeNodeId(@Param("categoryTreeNodeId")Long categoryTreeNodeId);
	/**
	 * 根据类目树id查询所有类目信息
	 * @param po
	 * @return
	 */
	List<CategoryTreeNodeCondition> findWebCategoryTreeByCategoryTreeId(@Param("po")CategoryTreeNodePO po);
	
	/**
	 * 查找子类目中的最大序列号
	 * @param categoryTreeId
	 * @param parentId
	 * @return
	 */
	String findMaxSubSerialNumber(@Param("categoryTreeId")Long categoryTreeId, @Param("parentId")Long parentId);

    Long findParentidById(@Param("id")Long id);
}
	
