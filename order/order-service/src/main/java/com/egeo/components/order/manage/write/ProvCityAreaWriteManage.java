package com.egeo.components.order.manage.write;

import com.egeo.components.order.po.ProvCityAreaPO;


public interface ProvCityAreaWriteManage {

	Long insertProvCityAreaWithTx(ProvCityAreaPO po);

	int updateProvCityAreaWithTx(ProvCityAreaPO po);

	int deleteProvCityAreaWithTx(ProvCityAreaPO po);
}
	