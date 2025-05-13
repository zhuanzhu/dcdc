package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.StandardProductUnitAttNamePO;

import java.util.List;


public interface StandardProductUnitAttNameWriteManage {

	Long insertStandardProductUnitAttNameWithTx(StandardProductUnitAttNamePO po);

	int updateStandardProductUnitAttNameWithTx(StandardProductUnitAttNamePO po);

	int deleteStandardProductUnitAttNameWithTx(StandardProductUnitAttNamePO po);

    void saveStandardProductUnitAttName(List<StandardProductUnitAttNamePO> standardProductUnitAttNamePOList);
}
	