package com.egeo.components.product.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.StandardProductUnitPictureManage;
import com.egeo.components.product.facade.StandardProductUnitPictureFacade;
import com.egeo.components.product.dto.StandardProductUnitPictureDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("standardProductUnitPicture")
public class StandardProductUnitPictureManageImpl implements StandardProductUnitPictureManage{

	
	@Resource(name="standardProductUnitPictureFacade")
	private StandardProductUnitPictureFacade standardProductUnitPictureFacade;

	@Override
	public StandardProductUnitPictureDTO findStandardProductUnitPictureById(StandardProductUnitPictureDTO dto) {
		return standardProductUnitPictureFacade.findStandardProductUnitPictureById(dto);
	}

	@Override
	public PageResult<StandardProductUnitPictureDTO> findStandardProductUnitPictureOfPage(StandardProductUnitPictureDTO dto, Pagination page) {
		return standardProductUnitPictureFacade.findStandardProductUnitPictureOfPage(dto, page);
	}

	@Override
	public List<StandardProductUnitPictureDTO> findStandardProductUnitPictureAll(StandardProductUnitPictureDTO dto) {
		return standardProductUnitPictureFacade.findStandardProductUnitPictureAll(dto);
	}

	@Override
	public Long insertStandardProductUnitPictureWithTx(StandardProductUnitPictureDTO dto) {
		return standardProductUnitPictureFacade.insertStandardProductUnitPictureWithTx(dto);
	}

	@Override
	public int updateStandardProductUnitPictureWithTx(StandardProductUnitPictureDTO dto) {
		return standardProductUnitPictureFacade.updateStandardProductUnitPictureWithTx(dto);
	}

	@Override
	public int deleteStandardProductUnitPictureWithTx(StandardProductUnitPictureDTO dto) {
		return standardProductUnitPictureFacade.deleteStandardProductUnitPictureWithTx(dto);
	}


}
	