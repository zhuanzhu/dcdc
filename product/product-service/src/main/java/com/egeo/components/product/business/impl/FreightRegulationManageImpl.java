package com.egeo.components.product.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.FreightRegulationManage;
import com.egeo.components.product.facade.FreightRegulationFacade;
import com.egeo.components.product.dto.FreightRegulationDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("freightRegulation")
public class FreightRegulationManageImpl implements FreightRegulationManage{

	
	@Resource(name="freightRegulationFacade")
	private FreightRegulationFacade freightRegulationFacade;

	@Override
	public FreightRegulationDTO findFreightRegulationById(FreightRegulationDTO dto) {
		return freightRegulationFacade.findFreightRegulationById(dto);
	}

	@Override
	public PageResult<FreightRegulationDTO> findFreightRegulationOfPage(FreightRegulationDTO dto, Pagination page) {
		return freightRegulationFacade.findFreightRegulationOfPage(dto, page);
	}

	@Override
	public List<FreightRegulationDTO> findFreightRegulationAll(FreightRegulationDTO dto) {
		return freightRegulationFacade.findFreightRegulationAll(dto);
	}

	@Override
	public Long insertFreightRegulationWithTx(FreightRegulationDTO dto) {
		return freightRegulationFacade.insertFreightRegulationWithTx(dto);
	}

	@Override
	public int updateFreightRegulationWithTx(FreightRegulationDTO dto) {
		return freightRegulationFacade.updateFreightRegulationWithTx(dto);
	}

	@Override
	public int deleteFreightRegulationWithTx(FreightRegulationDTO dto) {
		return freightRegulationFacade.deleteFreightRegulationWithTx(dto);
	}


}
	