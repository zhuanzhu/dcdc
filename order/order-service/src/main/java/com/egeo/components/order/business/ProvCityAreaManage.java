package com.egeo.components.order.business;

import java.util.List;
	
import com.egeo.components.order.dto.ProvCityAreaDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface ProvCityAreaManage {

	public ProvCityAreaDTO findProvCityAreaById(Long id);	

	public PageResult<ProvCityAreaDTO> findProvCityAreaOfPage(ProvCityAreaDTO dto,Pagination page);

	public List<ProvCityAreaDTO> findProvCityAreaAll(ProvCityAreaDTO dto);

	Long insertProvCityAreaWithTx(ProvCityAreaDTO dto);

	int updateProvCityAreaWithTx(ProvCityAreaDTO dto);

	int deleteProvCityAreaWithTx(ProvCityAreaDTO dto);
}
	