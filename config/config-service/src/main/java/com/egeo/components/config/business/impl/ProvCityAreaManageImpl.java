package com.egeo.components.config.business.impl;
	

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.components.config.business.ProvCityAreaManage;
import com.egeo.components.config.dto.ProvCityAreaDTO;
import com.egeo.components.config.facade.ProvCityAreaFacade;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("provCityArea")
public class ProvCityAreaManageImpl implements ProvCityAreaManage{

	
	@Resource(name="provCityAreaFacade")
	private ProvCityAreaFacade provCityAreaFacade;

	@Override
	public ProvCityAreaDTO findProvCityAreaById(ProvCityAreaDTO dto) {
		return provCityAreaFacade.findProvCityAreaById(dto);
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
	