package com.egeo.components.order.service.write;

import com.egeo.components.order.dto.ProvCityAreaDTO;


public interface ProvCityAreaWriteService {

	public Long insertProvCityAreaWithTx(ProvCityAreaDTO dto);

	public int updateProvCityAreaWithTx(ProvCityAreaDTO dto);

	public int deleteProvCityAreaWithTx(ProvCityAreaDTO dto);
}
	