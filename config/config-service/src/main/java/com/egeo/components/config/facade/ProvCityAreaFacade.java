package com.egeo.components.config.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.components.config.dto.ProvCityAreaDTO;
import com.egeo.components.config.service.read.ProvCityAreaReadService;
import com.egeo.components.config.service.write.ProvCityAreaWriteService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class ProvCityAreaFacade {
	
	@Autowired
	private ProvCityAreaReadService provCityAreaReadService;
	
	@Autowired
	private ProvCityAreaWriteService provCityAreaWriteService;
	
	
	public ProvCityAreaDTO findProvCityAreaById(ProvCityAreaDTO dto){
		
		return provCityAreaReadService.findProvCityAreaById(dto);
	}

	public PageResult<ProvCityAreaDTO> findProvCityAreaOfPage(ProvCityAreaDTO dto,Pagination page){
		
		return provCityAreaReadService.findProvCityAreaOfPage(dto, page);
		
	}

	public List<ProvCityAreaDTO> findProvCityAreaAll(ProvCityAreaDTO dto){
		
		return provCityAreaReadService.findProvCityAreaAll(dto);
		
	}

	public Long insertProvCityAreaWithTx(ProvCityAreaDTO dto){
		
		return provCityAreaWriteService.insertProvCityAreaWithTx(dto);
	}

	public int updateProvCityAreaWithTx(ProvCityAreaDTO dto){
		
		return provCityAreaWriteService.updateProvCityAreaWithTx(dto);
	}

	public int deleteProvCityAreaWithTx(ProvCityAreaDTO dto){
		
		return provCityAreaWriteService.deleteProvCityAreaWithTx(dto);
		
	}

}
	