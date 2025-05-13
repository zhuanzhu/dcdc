package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.StandardProductUnitAttNameDTO;

import java.util.List;


public interface StandardProductUnitAttNameWriteService {

	public Long insertStandardProductUnitAttNameWithTx(StandardProductUnitAttNameDTO dto);

	public int updateStandardProductUnitAttNameWithTx(StandardProductUnitAttNameDTO dto);

	public int deleteStandardProductUnitAttNameWithTx(StandardProductUnitAttNameDTO dto);

    void saveStandardProductUnitAttName(List<Long> spuIdList, List<Long> spuAttNameIdList);
}
	