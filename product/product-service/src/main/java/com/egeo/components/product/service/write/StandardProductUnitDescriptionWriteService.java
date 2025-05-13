package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.StandardProductUnitDescriptionDTO;

import java.util.List;


public interface StandardProductUnitDescriptionWriteService {

	public Long insertStandardProductUnitDescriptionWithTx(StandardProductUnitDescriptionDTO dto);

	public int updateStandardProductUnitDescriptionWithTx(StandardProductUnitDescriptionDTO dto);

	public int deleteStandardProductUnitDescriptionWithTx(StandardProductUnitDescriptionDTO dto);

    void saveStandardProductUnitDescription(List<Long> spuIdList);

    void updateStandardProductUnitDescriptionSWithTx(StandardProductUnitDescriptionDTO standardProductUnitDescriptionDTO);
}
	