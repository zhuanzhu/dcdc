package com.egeo.components.product.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.StandardUnitPictureManage;
import com.egeo.components.product.facade.StandardUnitPictureFacade;
import com.egeo.components.product.dto.StandardUnitPictureDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("standardUnitPicture")
public class StandardUnitPictureManageImpl implements StandardUnitPictureManage{

	
	@Resource(name="standardUnitPictureFacade")
	private StandardUnitPictureFacade standardUnitPictureFacade;

	@Override
	public StandardUnitPictureDTO findStandardUnitPictureById(StandardUnitPictureDTO dto) {
		return standardUnitPictureFacade.findStandardUnitPictureById(dto);
	}

	@Override
	public PageResult<StandardUnitPictureDTO> findStandardUnitPictureOfPage(StandardUnitPictureDTO dto, Pagination page) {
		return standardUnitPictureFacade.findStandardUnitPictureOfPage(dto, page);
	}

	@Override
	public List<StandardUnitPictureDTO> findStandardUnitPictureAll(StandardUnitPictureDTO dto) {
		return standardUnitPictureFacade.findStandardUnitPictureAll(dto);
	}

	@Override
	public Long insertStandardUnitPictureWithTx(StandardUnitPictureDTO dto) {
		return standardUnitPictureFacade.insertStandardUnitPictureWithTx(dto);
	}

	@Override
	public int updateStandardUnitPictureWithTx(StandardUnitPictureDTO dto) {
		return standardUnitPictureFacade.updateStandardUnitPictureWithTx(dto);
	}

	@Override
	public int deleteStandardUnitPictureWithTx(StandardUnitPictureDTO dto) {
		return standardUnitPictureFacade.deleteStandardUnitPictureWithTx(dto);
	}


}
	