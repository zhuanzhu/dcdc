package com.egeo.components.product.service.write;

import java.util.List;

import com.egeo.components.product.dto.StandardUnitCombinationCategoryDTO;
import com.egeo.components.product.dto.StandardUnitCombinationDTO;


public interface StandardUnitCombinationCategoryWriteService {

	public Long insertStandardUnitCombinationCategoryWithTx(StandardUnitCombinationCategoryDTO dto);

	public int updateStandardUnitCombinationCategoryWithTx(StandardUnitCombinationCategoryDTO dto);

	public int deleteStandardUnitCombinationCategoryWithTx(StandardUnitCombinationCategoryDTO dto);
	/**
	 * 批量保存su组合和前台类目节点关系
	 * @param standardUnitCombinationId
	 * @param categoryTreeId
	 * @param sortType
	 * @param categoryTreeNodeIdList
	 * @return
	 */
	public boolean saveStandardUnitCombinationCategoryAllWithTx(StandardUnitCombinationDTO standardUnitCombinationDTO, List<Long> categoryTreeNodeIdList);
}
	