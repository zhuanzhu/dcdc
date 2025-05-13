package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.StandardUnitCombinationPO;


public interface StandardUnitCombinationWriteManage {

	Long insertStandardUnitCombinationWithTx(StandardUnitCombinationPO po);

	int updateStandardUnitCombinationWithTx(StandardUnitCombinationPO po);

	int deleteStandardUnitCombinationWithTx(StandardUnitCombinationPO po);
}
	