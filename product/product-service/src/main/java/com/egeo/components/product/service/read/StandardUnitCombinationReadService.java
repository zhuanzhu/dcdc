package com.egeo.components.product.service.read;


import java.util.List;

import com.egeo.components.product.dto.StandardUnitCombinationDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface StandardUnitCombinationReadService {

	public StandardUnitCombinationDTO findStandardUnitCombinationById(StandardUnitCombinationDTO dto);

	public PageResult<StandardUnitCombinationDTO> findStandardUnitCombinationOfPage(StandardUnitCombinationDTO dto,Pagination page,List<Long> standardUnitCombinationIdList);

	public List<StandardUnitCombinationDTO> findStandardUnitCombinationAll(StandardUnitCombinationDTO dto);
	/**
	 * 根据标签类型su组合id查询su商品数量
	 * @param id
	 * @return
	 */
	public Integer findStandardUnitSizeByTag(Long standardUnitCombinationId,Long platformId);

	/**
	 * 模糊查询所有商品组合信息
	 * @param dto
	 * @return
	 */
	public List<StandardUnitCombinationDTO> findStandardUnitCombinationAllByBlurry(StandardUnitCombinationDTO dto);

    List<StandardUnitCombinationDTO> findStandardUnitCombinationAllLimit(String suCombinationName);
}
	