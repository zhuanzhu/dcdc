package com.egeo.components.product.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.StandardUnitCompanyManage;
import com.egeo.components.product.facade.StandardUnitCompanyFacade;
import com.egeo.components.product.dto.StandardUnitCompanyDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("standardUnitCompany")
public class StandardUnitCompanyManageImpl implements StandardUnitCompanyManage{

	
	@Resource(name="standardUnitCompanyFacade")
	private StandardUnitCompanyFacade standardUnitCompanyFacade;

	@Override
	public StandardUnitCompanyDTO findStandardUnitCompanyById(StandardUnitCompanyDTO dto) {
		return standardUnitCompanyFacade.findStandardUnitCompanyById(dto);
	}

	@Override
	public PageResult<StandardUnitCompanyDTO> findStandardUnitCompanyOfPage(StandardUnitCompanyDTO dto, Pagination page) {
		return standardUnitCompanyFacade.findStandardUnitCompanyOfPage(dto, page);
	}

	@Override
	public List<StandardUnitCompanyDTO> findStandardUnitCompanyAll(StandardUnitCompanyDTO dto) {
		return standardUnitCompanyFacade.findStandardUnitCompanyAll(dto);
	}

	@Override
	public Long insertStandardUnitCompanyWithTx(StandardUnitCompanyDTO dto) {
		return standardUnitCompanyFacade.insertStandardUnitCompanyWithTx(dto);
	}

	@Override
	public int updateStandardUnitCompanyWithTx(StandardUnitCompanyDTO dto) {
		return standardUnitCompanyFacade.updateStandardUnitCompanyWithTx(dto);
	}

	@Override
	public int deleteStandardUnitCompanyWithTx(StandardUnitCompanyDTO dto) {
		return standardUnitCompanyFacade.deleteStandardUnitCompanyWithTx(dto);
	}


}
	