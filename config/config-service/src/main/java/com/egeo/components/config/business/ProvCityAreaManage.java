package com.egeo.components.config.business;

import java.util.List;

import com.egeo.components.config.dto.ProvCityAreaDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface ProvCityAreaManage {

	public ProvCityAreaDTO findProvCityAreaById(ProvCityAreaDTO dto);	

	public PageResult<ProvCityAreaDTO> findProvCityAreaOfPage(ProvCityAreaDTO dto,Pagination page);

	public List<ProvCityAreaDTO> findProvCityAreaAll(ProvCityAreaDTO dto);

	Long insertProvCityAreaWithTx(ProvCityAreaDTO dto);

	int updateProvCityAreaWithTx(ProvCityAreaDTO dto);

	int deleteProvCityAreaWithTx(ProvCityAreaDTO dto);
}
	