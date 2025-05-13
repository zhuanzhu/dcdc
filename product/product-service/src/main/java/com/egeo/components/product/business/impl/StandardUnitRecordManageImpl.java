package com.egeo.components.product.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.StandardUnitRecordManage;
import com.egeo.components.product.facade.StandardUnitRecordFacade;
import com.egeo.components.product.dto.StandardUnitRecordDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("standardUnitRecord")
public class StandardUnitRecordManageImpl implements StandardUnitRecordManage{

	
	@Resource(name="standardUnitRecordFacade")
	private StandardUnitRecordFacade standardUnitRecordFacade;

	@Override
	public StandardUnitRecordDTO findStandardUnitRecordById(StandardUnitRecordDTO dto) {
		return standardUnitRecordFacade.findStandardUnitRecordById(dto);
	}

	@Override
	public PageResult<StandardUnitRecordDTO> findStandardUnitRecordOfPage(StandardUnitRecordDTO dto, Pagination page) {
		return standardUnitRecordFacade.findStandardUnitRecordOfPage(dto, page);
	}

	@Override
	public List<StandardUnitRecordDTO> findStandardUnitRecordAll(StandardUnitRecordDTO dto) {
		return standardUnitRecordFacade.findStandardUnitRecordAll(dto);
	}

	@Override
	public Long insertStandardUnitRecordWithTx(StandardUnitRecordDTO dto) {
		return standardUnitRecordFacade.insertStandardUnitRecordWithTx(dto);
	}

	@Override
	public int updateStandardUnitRecordWithTx(StandardUnitRecordDTO dto) {
		return standardUnitRecordFacade.updateStandardUnitRecordWithTx(dto);
	}

	@Override
	public int deleteStandardUnitRecordWithTx(StandardUnitRecordDTO dto) {
		return standardUnitRecordFacade.deleteStandardUnitRecordWithTx(dto);
	}


}
	