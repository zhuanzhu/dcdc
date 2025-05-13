package com.egeo.components.config.service.write;

import com.egeo.components.config.dto.ProvCityAreaDTO;


public interface ProvCityAreaWriteService {

	public Long insertProvCityAreaWithTx(ProvCityAreaDTO dto);

	public int updateProvCityAreaWithTx(ProvCityAreaDTO dto);

	public int deleteProvCityAreaWithTx(ProvCityAreaDTO dto);
}
	