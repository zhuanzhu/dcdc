package com.egeo.components.product.service.write;

import java.util.List;

import com.egeo.components.product.dto.StandardUnitCombinationDTO;
import com.egeo.components.product.dto.StandardUnitCombinationTagDTO;


public interface StandardUnitCombinationTagWriteService {

	public Long insertStandardUnitCombinationTagWithTx(StandardUnitCombinationTagDTO dto);

	public int updateStandardUnitCombinationTagWithTx(StandardUnitCombinationTagDTO dto);

	public int deleteStandardUnitCombinationTagWithTx(StandardUnitCombinationTagDTO dto);
	/**
	 * 批量保存su组合id与标签关系信息
	 * @param standardUnitCombinationId
	 * @return
	 */
	public Integer saveStandardUnitCombinationTagWithTx(StandardUnitCombinationDTO standardUnitCombinationDTO,
			List<Long> tagIdList);
	/**
	 * 根据标签id清除记录
	 * @param tagId
	 * @return
	 */
	public int delByTagId(Long tagId);
}
	