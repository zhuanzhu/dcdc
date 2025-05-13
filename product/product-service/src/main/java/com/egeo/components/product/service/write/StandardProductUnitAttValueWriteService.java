package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.StandardProductUnitAttValueDTO;

import java.util.List;


public interface StandardProductUnitAttValueWriteService {

	public Long insertStandardProductUnitAttValueWithTx(StandardProductUnitAttValueDTO dto);

	public int updateStandardProductUnitAttValueWithTx(StandardProductUnitAttValueDTO dto);

	public int deleteStandardProductUnitAttValueWithTx(StandardProductUnitAttValueDTO dto);
	/**
	 * 根据spu属性id删除spu属性值信息
	 * @param id
	 * @return
	 */
	public int deleteByStandardProductUnitAttNameIdWithTx(Long id);

    void saveSPUValue(List<Long> spuAttNameIdList, List<Long> attValueIdList);
}
	