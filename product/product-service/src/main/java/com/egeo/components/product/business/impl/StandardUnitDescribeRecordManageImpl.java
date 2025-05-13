package com.egeo.components.product.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.StandardUnitDescribeRecordManage;
import com.egeo.components.product.facade.StandardUnitDescribeRecordFacade;
import com.egeo.components.product.dto.StandardUnitDescribeRecordDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("standardUnitDescribeRecord")
public class StandardUnitDescribeRecordManageImpl implements StandardUnitDescribeRecordManage{

	
	@Resource(name="standardUnitDescribeRecordFacade")
	private StandardUnitDescribeRecordFacade standardUnitDescribeRecordFacade;

	@Override
	public StandardUnitDescribeRecordDTO findStandardUnitDescribeRecordById(StandardUnitDescribeRecordDTO dto) {
		return standardUnitDescribeRecordFacade.findStandardUnitDescribeRecordById(dto);
	}

	@Override
	public PageResult<StandardUnitDescribeRecordDTO> findStandardUnitDescribeRecordOfPage(StandardUnitDescribeRecordDTO dto, Pagination page) {
		return standardUnitDescribeRecordFacade.findStandardUnitDescribeRecordOfPage(dto, page);
	}

	@Override
	public List<StandardUnitDescribeRecordDTO> findStandardUnitDescribeRecordAll(StandardUnitDescribeRecordDTO dto) {
		return standardUnitDescribeRecordFacade.findStandardUnitDescribeRecordAll(dto);
	}

	@Override
	public Long insertStandardUnitDescribeRecordWithTx(StandardUnitDescribeRecordDTO dto) {
		return standardUnitDescribeRecordFacade.insertStandardUnitDescribeRecordWithTx(dto);
	}

	@Override
	public int updateStandardUnitDescribeRecordWithTx(StandardUnitDescribeRecordDTO dto) {
		return standardUnitDescribeRecordFacade.updateStandardUnitDescribeRecordWithTx(dto);
	}

	@Override
	public int deleteStandardUnitDescribeRecordWithTx(StandardUnitDescribeRecordDTO dto) {
		return standardUnitDescribeRecordFacade.deleteStandardUnitDescribeRecordWithTx(dto);
	}


}
	