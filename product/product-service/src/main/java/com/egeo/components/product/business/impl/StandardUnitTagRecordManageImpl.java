package com.egeo.components.product.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.StandardUnitTagRecordManage;
import com.egeo.components.product.facade.StandardUnitTagRecordFacade;
import com.egeo.components.product.dto.StandardUnitTagRecordDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("standardUnitTagRecord")
public class StandardUnitTagRecordManageImpl implements StandardUnitTagRecordManage{

	
	@Resource(name="standardUnitTagRecordFacade")
	private StandardUnitTagRecordFacade standardUnitTagRecordFacade;

	@Override
	public StandardUnitTagRecordDTO findStandardUnitTagRecordById(StandardUnitTagRecordDTO dto) {
		return standardUnitTagRecordFacade.findStandardUnitTagRecordById(dto);
	}

	@Override
	public PageResult<StandardUnitTagRecordDTO> findStandardUnitTagRecordOfPage(StandardUnitTagRecordDTO dto, Pagination page) {
		return standardUnitTagRecordFacade.findStandardUnitTagRecordOfPage(dto, page);
	}

	@Override
	public List<StandardUnitTagRecordDTO> findStandardUnitTagRecordAll(StandardUnitTagRecordDTO dto) {
		return standardUnitTagRecordFacade.findStandardUnitTagRecordAll(dto);
	}

	@Override
	public Long insertStandardUnitTagRecordWithTx(StandardUnitTagRecordDTO dto) {
		return standardUnitTagRecordFacade.insertStandardUnitTagRecordWithTx(dto);
	}

	@Override
	public int updateStandardUnitTagRecordWithTx(StandardUnitTagRecordDTO dto) {
		return standardUnitTagRecordFacade.updateStandardUnitTagRecordWithTx(dto);
	}

	@Override
	public int deleteStandardUnitTagRecordWithTx(StandardUnitTagRecordDTO dto) {
		return standardUnitTagRecordFacade.deleteStandardUnitTagRecordWithTx(dto);
	}


}
	