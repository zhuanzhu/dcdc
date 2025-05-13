package com.egeo.components.product.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.FreightRegulationReadService;
import com.egeo.components.product.service.write.FreightRegulationWriteService;
import com.egeo.components.product.dto.FreightRegulationDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class FreightRegulationFacade {
	
	@Resource
	private FreightRegulationReadService freightRegulationReadService;
	
	@Resource
	private FreightRegulationWriteService freightRegulationWriteService;
	
	
	public FreightRegulationDTO findFreightRegulationById(FreightRegulationDTO dto){
		
		return freightRegulationReadService.findFreightRegulationById(dto);
	}

	public PageResult<FreightRegulationDTO> findFreightRegulationOfPage(FreightRegulationDTO dto,Pagination page){
		
		return freightRegulationReadService.findFreightRegulationOfPage(dto, page);
		
	}

	public List<FreightRegulationDTO> findFreightRegulationAll(FreightRegulationDTO dto){
		
		return freightRegulationReadService.findFreightRegulationAll(dto);
		
	}

	public Long insertFreightRegulationWithTx(FreightRegulationDTO dto){
		
		return freightRegulationWriteService.insertFreightRegulationWithTx(dto);
	}

	public int updateFreightRegulationWithTx(FreightRegulationDTO dto){
		
		return freightRegulationWriteService.updateFreightRegulationWithTx(dto);
	}

	public int deleteFreightRegulationWithTx(FreightRegulationDTO dto){
		
		return freightRegulationWriteService.deleteFreightRegulationWithTx(dto);
		
	}

}
	