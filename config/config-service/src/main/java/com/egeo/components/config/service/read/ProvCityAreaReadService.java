package com.egeo.components.config.service.read;


import java.util.List;

import com.egeo.components.config.dto.ProvCityAreaDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface ProvCityAreaReadService {

	public ProvCityAreaDTO findProvCityAreaById(ProvCityAreaDTO dto);

	public PageResult<ProvCityAreaDTO> findProvCityAreaOfPage(ProvCityAreaDTO dto,Pagination page);

	public List<ProvCityAreaDTO> findProvCityAreaAll(ProvCityAreaDTO dto);
}
	