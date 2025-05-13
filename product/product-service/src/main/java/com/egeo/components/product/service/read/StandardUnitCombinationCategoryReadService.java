package com.egeo.components.product.service.read;


import java.util.List;

import com.egeo.components.product.dto.StandardUnitCombinationCategoryDTO;
import com.egeo.components.product.dto.StandardUnitDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface StandardUnitCombinationCategoryReadService {

	public StandardUnitCombinationCategoryDTO findStandardUnitCombinationCategoryById(StandardUnitCombinationCategoryDTO dto);

	public PageResult<StandardUnitCombinationCategoryDTO> findStandardUnitCombinationCategoryOfPage(StandardUnitCombinationCategoryDTO dto,Pagination page);

	public List<StandardUnitCombinationCategoryDTO> findStandardUnitCombinationCategoryAll(StandardUnitCombinationCategoryDTO dto);
	/**
	 * 根据类目类型su组合id查询su商品数量
	 * @param id
	 * @return
	 */
	public Integer findStandardUnitSizeByCategoryType(Long standardUnitCombinationId);
	/**
	 * 根据类目类型su组合id查询su商品列表
	 * @param standardUnitCombinationId
	 * @return
	 */
	public PageResult<StandardUnitDTO> findByStandardUnitCombinationIdOfPage(
			Long standardUnitCombinationId, Pagination page);
}
	