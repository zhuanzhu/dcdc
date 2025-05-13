package com.egeo.components.config.manage.write;

import com.egeo.components.config.po.ProvCityAreaPO;


public interface ProvCityAreaWriteManage {

	Long insertProvCityAreaWithTx(ProvCityAreaPO po);

	int updateProvCityAreaWithTx(ProvCityAreaPO po);

	int deleteProvCityAreaWithTx(ProvCityAreaPO po);
}
	