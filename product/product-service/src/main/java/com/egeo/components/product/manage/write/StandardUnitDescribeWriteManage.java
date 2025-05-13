package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.StandardUnitDescribePO;

import java.util.List;


public interface StandardUnitDescribeWriteManage {

	Long insertStandardUnitDescribeWithTx(StandardUnitDescribePO po);

	int updateStandardUnitDescribeWithTx(StandardUnitDescribePO po);

	int deleteStandardUnitDescribeWithTx(StandardUnitDescribePO po);
	/**
	 * 根据suid同步su商品详情信息
	 * @param standardUnitDescribeDTO
	 * @return
	 */
	int updateByStandardUnitIdWithTx(StandardUnitDescribePO po);

    void saveStandardUnitDescribe(List<StandardUnitDescribePO> standardUnitDescribePOList);

    void updateStandardUnitDescribeSWithTx(StandardUnitDescribePO standardUnitDescribePO);
}
	