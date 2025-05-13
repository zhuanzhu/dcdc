package com.egeo.components.product.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.StandardUnitClientManage;
import com.egeo.components.product.facade.StandardUnitClientFacade;
import com.egeo.components.product.dto.StandardUnitClientDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("standardUnitClient")
public class StandardUnitClientManageImpl implements StandardUnitClientManage{

	
	@Resource(name="standardUnitClientFacade")
	private StandardUnitClientFacade standardUnitClientFacade;

	@Override
	public StandardUnitClientDTO findStandardUnitClientById(StandardUnitClientDTO dto) {
		return standardUnitClientFacade.findStandardUnitClientById(dto);
	}

	@Override
	public PageResult<StandardUnitClientDTO> findStandardUnitClientOfPage(StandardUnitClientDTO dto, Pagination page) {
		return standardUnitClientFacade.findStandardUnitClientOfPage(dto, page);
	}

	@Override
	public List<StandardUnitClientDTO> findStandardUnitClientAll(StandardUnitClientDTO dto) {
		return standardUnitClientFacade.findStandardUnitClientAll(dto);
	}

	@Override
	public Long insertStandardUnitClientWithTx(StandardUnitClientDTO dto) {
		return standardUnitClientFacade.insertStandardUnitClientWithTx(dto);
	}

	@Override
	public int updateStandardUnitClientWithTx(StandardUnitClientDTO dto) {
		return standardUnitClientFacade.updateStandardUnitClientWithTx(dto);
	}

	@Override
	public int deleteStandardUnitClientWithTx(StandardUnitClientDTO dto) {
		return standardUnitClientFacade.deleteStandardUnitClientWithTx(dto);
	}


}
	