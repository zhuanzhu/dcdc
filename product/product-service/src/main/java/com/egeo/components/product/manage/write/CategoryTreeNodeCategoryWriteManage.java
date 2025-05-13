package com.egeo.components.product.manage.write;

import java.util.List;

import com.egeo.components.product.po.CategoryTreeNodeCategoryPO;


public interface CategoryTreeNodeCategoryWriteManage {

	Long insertCategoryTreeNodeCategoryWithTx(CategoryTreeNodeCategoryPO po);

	int updateCategoryTreeNodeCategoryWithTx(CategoryTreeNodeCategoryPO po);

	int deleteCategoryTreeNodeCategoryWithTx(CategoryTreeNodeCategoryPO po);
	/**
	 * 批量添加前台类目节点与后台类目节点关系
	 * @param frontCategoryTreeNodeId
	 * @param queenCategoryTreeNodeIdList
	 * @return
	 */
	boolean insertCategoryTreeNodeCategoryAllWithTx(Long frontCategoryTreeNodeId,
			List<Long> queenCategoryTreeNodeIdList);

	/**
	 * 前台类目节点关联后台类目节点与商品组合
	 * @param frontCategoryTreeNodeId
	 * @param queenCategoryTreeNodeIds
	 * @param standardUnitCombinationIds
	 * @return
	 */
    boolean insertCtnAndSucWithTx(Long frontCategoryTreeNodeId, List<Long> queenCategoryTreeNodeIds,
								  List<Long> standardUnitCombinationIds);
}
	