package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.StandardProductUnitDTO;

import java.util.List;


public interface StandardProductUnitWriteService {

	public Long insertStandardProductUnitWithTx(StandardProductUnitDTO dto);

	public int updateStandardProductUnitWithTx(StandardProductUnitDTO dto);

	public int deleteStandardProductUnitWithTx(StandardProductUnitDTO dto);

    void saveSPU(List<Long> spuIdList, List<String> spuSerialNumberList, List<String> productCategoryList, List<Long> catgoryTreeNodeIdList, List<String> nameList);

    void updateStandardProductUnitList(List<Long> standardProductUnitIdList, List<String> nameList);
}
	