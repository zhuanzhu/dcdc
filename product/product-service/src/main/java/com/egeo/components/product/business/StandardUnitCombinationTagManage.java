package com.egeo.components.product.business;

import java.util.List;
import java.util.Map;

import com.egeo.components.product.dto.StandardUnitCombinationDTO;
import com.egeo.components.product.dto.StandardUnitCombinationTagDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface StandardUnitCombinationTagManage {

	public StandardUnitCombinationTagDTO findStandardUnitCombinationTagById(StandardUnitCombinationTagDTO dto);	

	public PageResult<StandardUnitCombinationTagDTO> findStandardUnitCombinationTagOfPage(StandardUnitCombinationTagDTO dto,Pagination page);

	public List<StandardUnitCombinationTagDTO> findStandardUnitCombinationTagAll(StandardUnitCombinationTagDTO dto);

	Long insertStandardUnitCombinationTagWithTx(StandardUnitCombinationTagDTO dto);

	int updateStandardUnitCombinationTagWithTx(StandardUnitCombinationTagDTO dto);

	int deleteStandardUnitCombinationTagWithTx(StandardUnitCombinationTagDTO dto);
	/**
	 * 根据su组合id查询su组合标签信息
	 * @param standardUnitCombinationId
	 * @return
	 */
	public List<Map<String, Object>> findTagByStandardUnitCombinationId(Long standardUnitCombinationId);
	/**
	 * 批量保存su组合id与标签关系信息
	 * @param standardUnitCombinationId
	 * @return
	 */
	public Integer saveStandardUnitCombinationTagWithTx(StandardUnitCombinationDTO dto, List<Long> tagIdList);
}
	