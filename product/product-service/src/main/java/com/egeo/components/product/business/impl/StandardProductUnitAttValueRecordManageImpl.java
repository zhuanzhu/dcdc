package com.egeo.components.product.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.StandardProductUnitAttValueRecordManage;
import com.egeo.components.product.facade.StandardProductUnitAttValueRecordFacade;
import com.egeo.components.product.dto.StandardProductUnitAttValueRecordDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("standardProductUnitAttValueRecord")
public class StandardProductUnitAttValueRecordManageImpl implements StandardProductUnitAttValueRecordManage{

	
	@Resource(name="standardProductUnitAttValueRecordFacade")
	private StandardProductUnitAttValueRecordFacade standardProductUnitAttValueRecordFacade;

	@Override
	public StandardProductUnitAttValueRecordDTO findStandardProductUnitAttValueRecordById(StandardProductUnitAttValueRecordDTO dto) {
		return standardProductUnitAttValueRecordFacade.findStandardProductUnitAttValueRecordById(dto);
	}

	@Override
	public PageResult<StandardProductUnitAttValueRecordDTO> findStandardProductUnitAttValueRecordOfPage(StandardProductUnitAttValueRecordDTO dto, Pagination page) {
		return standardProductUnitAttValueRecordFacade.findStandardProductUnitAttValueRecordOfPage(dto, page);
	}

	@Override
	public List<StandardProductUnitAttValueRecordDTO> findStandardProductUnitAttValueRecordAll(StandardProductUnitAttValueRecordDTO dto) {
		return standardProductUnitAttValueRecordFacade.findStandardProductUnitAttValueRecordAll(dto);
	}

	@Override
	public Long insertStandardProductUnitAttValueRecordWithTx(StandardProductUnitAttValueRecordDTO dto) {
		return standardProductUnitAttValueRecordFacade.insertStandardProductUnitAttValueRecordWithTx(dto);
	}

	@Override
	public int updateStandardProductUnitAttValueRecordWithTx(StandardProductUnitAttValueRecordDTO dto) {
		return standardProductUnitAttValueRecordFacade.updateStandardProductUnitAttValueRecordWithTx(dto);
	}

	@Override
	public int deleteStandardProductUnitAttValueRecordWithTx(StandardProductUnitAttValueRecordDTO dto) {
		return standardProductUnitAttValueRecordFacade.deleteStandardProductUnitAttValueRecordWithTx(dto);
	}


}
	