package com.egeo.components.product.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.StandardUnitPictureRecordManage;
import com.egeo.components.product.facade.StandardUnitPictureRecordFacade;
import com.egeo.components.product.dto.StandardUnitPictureRecordDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("standardUnitPictureRecord")
public class StandardUnitPictureRecordManageImpl implements StandardUnitPictureRecordManage{

	
	@Resource(name="standardUnitPictureRecordFacade")
	private StandardUnitPictureRecordFacade standardUnitPictureRecordFacade;

	@Override
	public StandardUnitPictureRecordDTO findStandardUnitPictureRecordById(StandardUnitPictureRecordDTO dto) {
		return standardUnitPictureRecordFacade.findStandardUnitPictureRecordById(dto);
	}

	@Override
	public PageResult<StandardUnitPictureRecordDTO> findStandardUnitPictureRecordOfPage(StandardUnitPictureRecordDTO dto, Pagination page) {
		return standardUnitPictureRecordFacade.findStandardUnitPictureRecordOfPage(dto, page);
	}

	@Override
	public List<StandardUnitPictureRecordDTO> findStandardUnitPictureRecordAll(StandardUnitPictureRecordDTO dto) {
		return standardUnitPictureRecordFacade.findStandardUnitPictureRecordAll(dto);
	}

	@Override
	public Long insertStandardUnitPictureRecordWithTx(StandardUnitPictureRecordDTO dto) {
		return standardUnitPictureRecordFacade.insertStandardUnitPictureRecordWithTx(dto);
	}

	@Override
	public int updateStandardUnitPictureRecordWithTx(StandardUnitPictureRecordDTO dto) {
		return standardUnitPictureRecordFacade.updateStandardUnitPictureRecordWithTx(dto);
	}

	@Override
	public int deleteStandardUnitPictureRecordWithTx(StandardUnitPictureRecordDTO dto) {
		return standardUnitPictureRecordFacade.deleteStandardUnitPictureRecordWithTx(dto);
	}


}
	