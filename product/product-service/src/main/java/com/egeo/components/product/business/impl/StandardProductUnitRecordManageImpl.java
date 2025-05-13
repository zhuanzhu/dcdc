package com.egeo.components.product.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.StandardProductUnitRecordManage;
import com.egeo.components.product.facade.StandardProductUnitRecordFacade;
import com.egeo.components.product.dto.StandardProductUnitRecordDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("standardProductUnitRecord")
public class StandardProductUnitRecordManageImpl implements StandardProductUnitRecordManage{

	
	@Resource(name="standardProductUnitRecordFacade")
	private StandardProductUnitRecordFacade standardProductUnitRecordFacade;

	@Override
	public StandardProductUnitRecordDTO findStandardProductUnitRecordById(StandardProductUnitRecordDTO dto) {
		return standardProductUnitRecordFacade.findStandardProductUnitRecordById(dto);
	}

	@Override
	public PageResult<StandardProductUnitRecordDTO> findStandardProductUnitRecordOfPage(StandardProductUnitRecordDTO dto, Pagination page) {
		return standardProductUnitRecordFacade.findStandardProductUnitRecordOfPage(dto, page);
	}

	@Override
	public List<StandardProductUnitRecordDTO> findStandardProductUnitRecordAll(StandardProductUnitRecordDTO dto) {
		return standardProductUnitRecordFacade.findStandardProductUnitRecordAll(dto);
	}

	@Override
	public Long insertStandardProductUnitRecordWithTx(StandardProductUnitRecordDTO dto) {
		return standardProductUnitRecordFacade.insertStandardProductUnitRecordWithTx(dto);
	}

	@Override
	public int updateStandardProductUnitRecordWithTx(StandardProductUnitRecordDTO dto) {
		return standardProductUnitRecordFacade.updateStandardProductUnitRecordWithTx(dto);
	}

	@Override
	public int deleteStandardProductUnitRecordWithTx(StandardProductUnitRecordDTO dto) {
		return standardProductUnitRecordFacade.deleteStandardProductUnitRecordWithTx(dto);
	}


}
	