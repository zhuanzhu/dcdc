package com.egeo.components.order.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.order.business.SoCustomerServiceManage;
import com.egeo.components.order.facade.SoCustomerServiceFacade;
import com.egeo.components.order.dto.SoCustomerServiceDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("soCustomerService")
public class SoCustomerServiceManageImpl implements SoCustomerServiceManage{

	
	@Resource(name="soCustomerServiceFacade")
	private SoCustomerServiceFacade soCustomerServiceFacade;

	@Override
	public SoCustomerServiceDTO findSoCustomerServiceById(SoCustomerServiceDTO dto) {
		return soCustomerServiceFacade.findSoCustomerServiceById(dto);
	}

	@Override
	public PageResult<SoCustomerServiceDTO> findSoCustomerServiceOfPage(SoCustomerServiceDTO dto, Pagination page) {
		return soCustomerServiceFacade.findSoCustomerServiceOfPage(dto, page);
	}

	@Override
	public List<SoCustomerServiceDTO> findSoCustomerServiceAll(SoCustomerServiceDTO dto) {
		return soCustomerServiceFacade.findSoCustomerServiceAll(dto);
	}

	@Override
	public Long insertSoCustomerServiceWithTx(SoCustomerServiceDTO dto) {
		return soCustomerServiceFacade.insertSoCustomerServiceWithTx(dto);
	}

	@Override
	public int updateSoCustomerServiceWithTx(SoCustomerServiceDTO dto) {
		return soCustomerServiceFacade.updateSoCustomerServiceWithTx(dto);
	}

	@Override
	public int deleteSoCustomerServiceWithTx(SoCustomerServiceDTO dto) {
		return soCustomerServiceFacade.deleteSoCustomerServiceWithTx(dto);
	}


}
	