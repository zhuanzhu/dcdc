package com.egeo.components.product.business;

import java.util.List;

import com.egeo.components.product.dto.CategoryTreeNodeCategoryDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface CategoryTreeNodeCategoryManage {

	public CategoryTreeNodeCategoryDTO findCategoryTreeNodeCategoryById(CategoryTreeNodeCategoryDTO dto);	

	public PageResult<CategoryTreeNodeCategoryDTO> findCategoryTreeNodeCategoryOfPage(CategoryTreeNodeCategoryDTO dto,Pagination page);

	public List<CategoryTreeNodeCategoryDTO> findCategoryTreeNodeCategoryAll(CategoryTreeNodeCategoryDTO dto);

	Long insertCategoryTreeNodeCategoryWithTx(CategoryTreeNodeCategoryDTO dto);

	int updateCategoryTreeNodeCategoryWithTx(CategoryTreeNodeCategoryDTO dto);

	int deleteCategoryTreeNodeCategoryWithTx(CategoryTreeNodeCategoryDTO dto);
	/**
	 * 批量添加前台类目节点与后台类目节点关系
	 * @param frontCategoryTreeNodeId
	 * @param queenCategoryTreeNodeIdList
	 * @return
	 */
	public boolean insertCategoryTreeNodeCategoryAllWithTx(Long frontCategoryTreeNodeId,
			List<Long> queenCategoryTreeNodeIdList);
	/**
	 * 根据前台类目节点查询后台类目节点
	 * @param categoryTreeNodeId
	 * @return
	 */
	public List<Long> findCategoryTreeNodeId(Long categoryTreeNodeId);

    boolean insertCtnAndSucWithTx(Long frontCategoryTreeNodeId, List<Long> queenCategoryTreeNodeIds,
								  List<Long> standardUnitCombinationIds);
}
	