package com.egeo.components.product.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.StandardProductUnitDescriptionRecordManage;
import com.egeo.components.product.facade.StandardProductUnitDescriptionRecordFacade;
import com.egeo.components.product.dto.StandardProductUnitDescriptionRecordDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("standardProductUnitDescriptionRecord")
public class StandardProductUnitDescriptionRecordManageImpl implements StandardProductUnitDescriptionRecordManage{

	
	@Resource(name="standardProductUnitDescriptionRecordFacade")
	private StandardProductUnitDescriptionRecordFacade standardProductUnitDescriptionRecordFacade;

	@Override
	public StandardProductUnitDescriptionRecordDTO findStandardProductUnitDescriptionRecordById(StandardProductUnitDescriptionRecordDTO dto) {
		return standardProductUnitDescriptionRecordFacade.findStandardProductUnitDescriptionRecordById(dto);
	}

	@Override
	public PageResult<StandardProductUnitDescriptionRecordDTO> findStandardProductUnitDescriptionRecordOfPage(StandardProductUnitDescriptionRecordDTO dto, Pagination page) {
		return standardProductUnitDescriptionRecordFacade.findStandardProductUnitDescriptionRecordOfPage(dto, page);
	}

	@Override
	public List<StandardProductUnitDescriptionRecordDTO> findStandardProductUnitDescriptionRecordAll(StandardProductUnitDescriptionRecordDTO dto) {
		return standardProductUnitDescriptionRecordFacade.findStandardProductUnitDescriptionRecordAll(dto);
	}

	@Override
	public Long insertStandardProductUnitDescriptionRecordWithTx(StandardProductUnitDescriptionRecordDTO dto) {
		return standardProductUnitDescriptionRecordFacade.insertStandardProductUnitDescriptionRecordWithTx(dto);
	}

	@Override
	public int updateStandardProductUnitDescriptionRecordWithTx(StandardProductUnitDescriptionRecordDTO dto) {
		return standardProductUnitDescriptionRecordFacade.updateStandardProductUnitDescriptionRecordWithTx(dto);
	}

	@Override
	public int deleteStandardProductUnitDescriptionRecordWithTx(StandardProductUnitDescriptionRecordDTO dto) {
		return standardProductUnitDescriptionRecordFacade.deleteStandardProductUnitDescriptionRecordWithTx(dto);
	}


}
	