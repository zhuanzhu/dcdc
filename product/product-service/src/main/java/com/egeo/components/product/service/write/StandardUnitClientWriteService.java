package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.StandardUnitClientDTO;

import java.util.List;


public interface StandardUnitClientWriteService {

	public Long insertStandardUnitClientWithTx(StandardUnitClientDTO dto);

	public int updateStandardUnitClientWithTx(StandardUnitClientDTO dto);

	public int deleteStandardUnitClientWithTx(StandardUnitClientDTO dto);
	/**
	 * 根据suid删除su客户端关系表
	 * @param merchantProdId
	 * @return
	 */
	public int deleteByStandardUnitIdWithTx(Long standardUnitId);

    void saveStandardUnitClient(List<Long> suIdList);
}
	