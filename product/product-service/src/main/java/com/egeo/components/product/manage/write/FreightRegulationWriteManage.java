package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.FreightRegulationPO;


public interface FreightRegulationWriteManage {

	Long insertFreightRegulationWithTx(FreightRegulationPO po);

	int updateFreightRegulationWithTx(FreightRegulationPO po);

	int deleteFreightRegulationWithTx(FreightRegulationPO po);
}
	