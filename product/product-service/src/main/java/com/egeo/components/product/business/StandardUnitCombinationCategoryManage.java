package com.egeo.components.product.business;

import java.util.List;

import com.egeo.components.product.dto.StandardUnitCombinationCategoryDTO;
import com.egeo.components.product.dto.StandardUnitCombinationDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface StandardUnitCombinationCategoryManage {

	public StandardUnitCombinationCategoryDTO findStandardUnitCombinationCategoryById(StandardUnitCombinationCategoryDTO dto);	

	public PageResult<StandardUnitCombinationCategoryDTO> findStandardUnitCombinationCategoryOfPage(StandardUnitCombinationCategoryDTO dto,Pagination page);

	public List<StandardUnitCombinationCategoryDTO> findStandardUnitCombinationCategoryAll(StandardUnitCombinationCategoryDTO dto);

	Long insertStandardUnitCombinationCategoryWithTx(StandardUnitCombinationCategoryDTO dto);

	int updateStandardUnitCombinationCategoryWithTx(StandardUnitCombinationCategoryDTO dto);

	int deleteStandardUnitCombinationCategoryWithTx(StandardUnitCombinationCategoryDTO dto);
	/**
	 * 批量保存su组合和前台类目节点关系
	 * @param standardUnitCombinationId
	 * @param categoryTreeId
	 * @param sortType
	 * @param categoryTreeNodeIdList
	 * @return
	 */
	public boolean saveStandardUnitCombinationCategoryAllWithTx(StandardUnitCombinationDTO dto, List<Long> categoryTreeNodeIdList);
}
	