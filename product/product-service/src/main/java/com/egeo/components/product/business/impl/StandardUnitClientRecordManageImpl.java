package com.egeo.components.product.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.StandardUnitClientRecordManage;
import com.egeo.components.product.facade.StandardUnitClientRecordFacade;
import com.egeo.components.product.dto.StandardUnitClientRecordDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("standardUnitClientRecord")
public class StandardUnitClientRecordManageImpl implements StandardUnitClientRecordManage{

	
	@Resource(name="standardUnitClientRecordFacade")
	private StandardUnitClientRecordFacade standardUnitClientRecordFacade;

	@Override
	public StandardUnitClientRecordDTO findStandardUnitClientRecordById(StandardUnitClientRecordDTO dto) {
		return standardUnitClientRecordFacade.findStandardUnitClientRecordById(dto);
	}

	@Override
	public PageResult<StandardUnitClientRecordDTO> findStandardUnitClientRecordOfPage(StandardUnitClientRecordDTO dto, Pagination page) {
		return standardUnitClientRecordFacade.findStandardUnitClientRecordOfPage(dto, page);
	}

	@Override
	public List<StandardUnitClientRecordDTO> findStandardUnitClientRecordAll(StandardUnitClientRecordDTO dto) {
		return standardUnitClientRecordFacade.findStandardUnitClientRecordAll(dto);
	}

	@Override
	public Long insertStandardUnitClientRecordWithTx(StandardUnitClientRecordDTO dto) {
		return standardUnitClientRecordFacade.insertStandardUnitClientRecordWithTx(dto);
	}

	@Override
	public int updateStandardUnitClientRecordWithTx(StandardUnitClientRecordDTO dto) {
		return standardUnitClientRecordFacade.updateStandardUnitClientRecordWithTx(dto);
	}

	@Override
	public int deleteStandardUnitClientRecordWithTx(StandardUnitClientRecordDTO dto) {
		return standardUnitClientRecordFacade.deleteStandardUnitClientRecordWithTx(dto);
	}


}
	