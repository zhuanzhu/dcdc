package com.egeo.components.product.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.StandardProductUnitPictureRecordManage;
import com.egeo.components.product.facade.StandardProductUnitPictureRecordFacade;
import com.egeo.components.product.dto.StandardProductUnitPictureRecordDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("standardProductUnitPictureRecord")
public class StandardProductUnitPictureRecordManageImpl implements StandardProductUnitPictureRecordManage{

	
	@Resource(name="standardProductUnitPictureRecordFacade")
	private StandardProductUnitPictureRecordFacade standardProductUnitPictureRecordFacade;

	@Override
	public StandardProductUnitPictureRecordDTO findStandardProductUnitPictureRecordById(StandardProductUnitPictureRecordDTO dto) {
		return standardProductUnitPictureRecordFacade.findStandardProductUnitPictureRecordById(dto);
	}

	@Override
	public PageResult<StandardProductUnitPictureRecordDTO> findStandardProductUnitPictureRecordOfPage(StandardProductUnitPictureRecordDTO dto, Pagination page) {
		return standardProductUnitPictureRecordFacade.findStandardProductUnitPictureRecordOfPage(dto, page);
	}

	@Override
	public List<StandardProductUnitPictureRecordDTO> findStandardProductUnitPictureRecordAll(StandardProductUnitPictureRecordDTO dto) {
		return standardProductUnitPictureRecordFacade.findStandardProductUnitPictureRecordAll(dto);
	}

	@Override
	public Long insertStandardProductUnitPictureRecordWithTx(StandardProductUnitPictureRecordDTO dto) {
		return standardProductUnitPictureRecordFacade.insertStandardProductUnitPictureRecordWithTx(dto);
	}

	@Override
	public int updateStandardProductUnitPictureRecordWithTx(StandardProductUnitPictureRecordDTO dto) {
		return standardProductUnitPictureRecordFacade.updateStandardProductUnitPictureRecordWithTx(dto);
	}

	@Override
	public int deleteStandardProductUnitPictureRecordWithTx(StandardProductUnitPictureRecordDTO dto) {
		return standardProductUnitPictureRecordFacade.deleteStandardProductUnitPictureRecordWithTx(dto);
	}


}
	