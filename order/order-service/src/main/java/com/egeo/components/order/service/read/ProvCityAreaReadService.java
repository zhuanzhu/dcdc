package com.egeo.components.order.service.read;


import java.util.List;
	
import com.egeo.components.order.dto.ProvCityAreaDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface ProvCityAreaReadService {

	public ProvCityAreaDTO findProvCityAreaById(Long id);

	public PageResult<ProvCityAreaDTO> findProvCityAreaOfPage(ProvCityAreaDTO dto,Pagination page);

	public List<ProvCityAreaDTO> findProvCityAreaAll(ProvCityAreaDTO dto);
}
	