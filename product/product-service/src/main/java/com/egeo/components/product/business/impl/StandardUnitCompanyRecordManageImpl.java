package com.egeo.components.product.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.StandardUnitCompanyRecordManage;
import com.egeo.components.product.facade.StandardUnitCompanyRecordFacade;
import com.egeo.components.product.dto.StandardUnitCompanyRecordDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("standardUnitCompanyRecord")
public class StandardUnitCompanyRecordManageImpl implements StandardUnitCompanyRecordManage{

	
	@Resource(name="standardUnitCompanyRecordFacade")
	private StandardUnitCompanyRecordFacade standardUnitCompanyRecordFacade;

	@Override
	public StandardUnitCompanyRecordDTO findStandardUnitCompanyRecordById(StandardUnitCompanyRecordDTO dto) {
		return standardUnitCompanyRecordFacade.findStandardUnitCompanyRecordById(dto);
	}

	@Override
	public PageResult<StandardUnitCompanyRecordDTO> findStandardUnitCompanyRecordOfPage(StandardUnitCompanyRecordDTO dto, Pagination page) {
		return standardUnitCompanyRecordFacade.findStandardUnitCompanyRecordOfPage(dto, page);
	}

	@Override
	public List<StandardUnitCompanyRecordDTO> findStandardUnitCompanyRecordAll(StandardUnitCompanyRecordDTO dto) {
		return standardUnitCompanyRecordFacade.findStandardUnitCompanyRecordAll(dto);
	}

	@Override
	public Long insertStandardUnitCompanyRecordWithTx(StandardUnitCompanyRecordDTO dto) {
		return standardUnitCompanyRecordFacade.insertStandardUnitCompanyRecordWithTx(dto);
	}

	@Override
	public int updateStandardUnitCompanyRecordWithTx(StandardUnitCompanyRecordDTO dto) {
		return standardUnitCompanyRecordFacade.updateStandardUnitCompanyRecordWithTx(dto);
	}

	@Override
	public int deleteStandardUnitCompanyRecordWithTx(StandardUnitCompanyRecordDTO dto) {
		return standardUnitCompanyRecordFacade.deleteStandardUnitCompanyRecordWithTx(dto);
	}


}
	