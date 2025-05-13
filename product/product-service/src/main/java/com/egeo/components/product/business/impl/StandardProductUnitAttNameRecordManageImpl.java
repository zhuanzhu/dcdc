package com.egeo.components.product.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.StandardProductUnitAttNameRecordManage;
import com.egeo.components.product.facade.StandardProductUnitAttNameRecordFacade;
import com.egeo.components.product.dto.StandardProductUnitAttNameRecordDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("standardProductUnitAttNameRecord")
public class StandardProductUnitAttNameRecordManageImpl implements StandardProductUnitAttNameRecordManage{

	
	@Resource(name="standardProductUnitAttNameRecordFacade")
	private StandardProductUnitAttNameRecordFacade standardProductUnitAttNameRecordFacade;

	@Override
	public StandardProductUnitAttNameRecordDTO findStandardProductUnitAttNameRecordById(StandardProductUnitAttNameRecordDTO dto) {
		return standardProductUnitAttNameRecordFacade.findStandardProductUnitAttNameRecordById(dto);
	}

	@Override
	public PageResult<StandardProductUnitAttNameRecordDTO> findStandardProductUnitAttNameRecordOfPage(StandardProductUnitAttNameRecordDTO dto, Pagination page) {
		return standardProductUnitAttNameRecordFacade.findStandardProductUnitAttNameRecordOfPage(dto, page);
	}

	@Override
	public List<StandardProductUnitAttNameRecordDTO> findStandardProductUnitAttNameRecordAll(StandardProductUnitAttNameRecordDTO dto) {
		return standardProductUnitAttNameRecordFacade.findStandardProductUnitAttNameRecordAll(dto);
	}

	@Override
	public Long insertStandardProductUnitAttNameRecordWithTx(StandardProductUnitAttNameRecordDTO dto) {
		return standardProductUnitAttNameRecordFacade.insertStandardProductUnitAttNameRecordWithTx(dto);
	}

	@Override
	public int updateStandardProductUnitAttNameRecordWithTx(StandardProductUnitAttNameRecordDTO dto) {
		return standardProductUnitAttNameRecordFacade.updateStandardProductUnitAttNameRecordWithTx(dto);
	}

	@Override
	public int deleteStandardProductUnitAttNameRecordWithTx(StandardProductUnitAttNameRecordDTO dto) {
		return standardProductUnitAttNameRecordFacade.deleteStandardProductUnitAttNameRecordWithTx(dto);
	}


}
	