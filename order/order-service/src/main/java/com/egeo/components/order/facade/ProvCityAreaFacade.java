package com.egeo.components.order.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.order.service.read.ProvCityAreaReadService;
import com.egeo.components.order.service.write.ProvCityAreaWriteService;
import com.egeo.components.order.dto.ProvCityAreaDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class ProvCityAreaFacade {
	
	@Resource
	private ProvCityAreaReadService provCityAreaReadService;
	
	@Resource
	private ProvCityAreaWriteService provCityAreaWriteService;
	
	
	public ProvCityAreaDTO findProvCityAreaById(Long id){
		
		return provCityAreaReadService.findProvCityAreaById(id);
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
	