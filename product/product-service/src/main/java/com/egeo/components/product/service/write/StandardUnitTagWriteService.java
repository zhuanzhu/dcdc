package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.StandardUnitTagDTO;


public interface StandardUnitTagWriteService {

	public Long insertStandardUnitTagWithTx(StandardUnitTagDTO dto);

	public int updateStandardUnitTagWithTx(StandardUnitTagDTO dto);

	public int deleteStandardUnitTagWithTx(StandardUnitTagDTO dto);
	/**
	 * 根据标签id清除记录
	 * @param tagId
	 * @return
	 */
	public int delByTagId(Long tagId);

	/**
	 * 根据suId 删除su与标签关联关系
	 * @param standardUnitId
	 * @return
	 */
	int delSuRelationBysuIdWithTx(Long standardUnitId);
}
	