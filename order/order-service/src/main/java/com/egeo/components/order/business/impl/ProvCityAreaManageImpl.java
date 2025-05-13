package com.egeo.components.order.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.order.business.ProvCityAreaManage;
import com.egeo.components.order.facade.ProvCityAreaFacade;
import com.egeo.components.order.dto.ProvCityAreaDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("provCityArea")
public class ProvCityAreaManageImpl implements ProvCityAreaManage{

	
	@Resource(name="provCityAreaFacade")
	private ProvCityAreaFacade provCityAreaFacade;

	@Override
	public ProvCityAreaDTO findProvCityAreaById(Long id) {
		return provCityAreaFacade.findProvCityAreaById(id);
	}

	@Override
	public PageResult<ProvCityAreaDTO> findProvCityAreaOfPage(ProvCityAreaDTO dto, Pagination page) {
		return provCityAreaFacade.findProvCityAreaOfPage(dto, page);
	}

	@Override
	public List<ProvCityAreaDTO> findProvCityAreaAll(ProvCityAreaDTO dto) {
		return provCityAreaFacade.findProvCityAreaAll(dto);
	}

	@Override
	public Long insertProvCityAreaWithTx(ProvCityAreaDTO dto) {
		return provCityAreaFacade.insertProvCityAreaWithTx(dto);
	}

	@Override
	public int updateProvCityAreaWithTx(ProvCityAreaDTO dto) {
		return provCityAreaFacade.updateProvCityAreaWithTx(dto);
	}

	@Override
	public int deleteProvCityAreaWithTx(ProvCityAreaDTO dto) {
		return provCityAreaFacade.deleteProvCityAreaWithTx(dto);
	}


}
	