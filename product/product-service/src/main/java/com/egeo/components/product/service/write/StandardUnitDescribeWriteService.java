package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.StandardUnitDescribeDTO;

import java.util.List;


public interface StandardUnitDescribeWriteService {

	public Long insertStandardUnitDescribeWithTx(StandardUnitDescribeDTO dto);

	public int updateStandardUnitDescribeWithTx(StandardUnitDescribeDTO dto);

	public int deleteStandardUnitDescribeWithTx(StandardUnitDescribeDTO dto);
	/**
	 * 根据suid同步su商品详情信息
	 * @param standardUnitDescribeDTO
	 * @return
	 */
	public int updateByStandardUnitIdWithTx(StandardUnitDescribeDTO standardUnitDescribeDTO);

    void saveStandardUnitDescribe(List<Long> suIdList);

    void updateStandardUnitDescribeSWithTx(StandardUnitDescribeDTO standardUnitDescribeDTO);
}
	