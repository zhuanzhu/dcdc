package com.egeo.components.product.service.read;


import java.util.List;
	
import com.egeo.components.product.dto.StandardUnitCombinationTagDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface StandardUnitCombinationTagReadService {

	public StandardUnitCombinationTagDTO findStandardUnitCombinationTagById(StandardUnitCombinationTagDTO dto);

	public PageResult<StandardUnitCombinationTagDTO> findStandardUnitCombinationTagOfPage(StandardUnitCombinationTagDTO dto,Pagination page);

	public List<StandardUnitCombinationTagDTO> findStandardUnitCombinationTagAll(StandardUnitCombinationTagDTO dto);
	/**
	 * 根据su组合id查询su组合标签信息
	 * @param standardUnitCombinationId
	 * @return
	 */
	public List<StandardUnitCombinationTagDTO> findTagByStandardUnitCombinationId(Long standardUnitCombinationId);
}
	