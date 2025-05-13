package com.egeo.components.product.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.product.po.CategoryTreeNodeCategoryPO;
import com.egeo.orm.BaseReadDAO;

public interface CategoryTreeNodeCategoryReadDAO extends BaseReadDAO<CategoryTreeNodeCategoryPO>{
	/**
	 * 根据类目节点id查询类目id信息
	 * @param categoryTreeNodeId
	 * @return
	 */
	List<Long> findCategoryIdsByCategoryTreeNodeId(@Param("categoryTreeNodeId")Long categoryTreeNodeId);

	/**
	 * 根据类目节点ids查询类目id信息
	 * @param categoryTreeNodeIds
	 * @return
	 */
	List<Long> findCategoryIdsByCategoryTreeNodeIds(@Param("categoryTreeNodeIds")List<Long> categoryTreeNodeIds);
}
	