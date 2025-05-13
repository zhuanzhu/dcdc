package com.egeo.components.product.service.write;

import java.util.List;

import com.egeo.components.product.dto.CategoryTreeNodeCategoryDTO;


public interface CategoryTreeNodeCategoryWriteService {

	public Long insertCategoryTreeNodeCategoryWithTx(CategoryTreeNodeCategoryDTO dto);

	public int updateCategoryTreeNodeCategoryWithTx(CategoryTreeNodeCategoryDTO dto);

	public int deleteCategoryTreeNodeCategoryWithTx(CategoryTreeNodeCategoryDTO dto);
	/**
	 * 批量添加前台类目节点与后台类目节点关系
	 * @param frontCategoryTreeNodeId
	 * @param queenCategoryTreeNodeIdList
	 * @return
	 */
	public boolean insertCategoryTreeNodeCategoryAllWithTx(Long frontCategoryTreeNodeId,
			List<Long> queenCategoryTreeNodeIdList);

    boolean insertCtnAndSucWithTx(Long frontCategoryTreeNodeId, List<Long> queenCategoryTreeNodeIds,
								  List<Long> standardUnitCombinationIds);
}
	