package com.egeo.components.config.business.impl;
	

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.components.config.business.PictureManage;
import com.egeo.components.config.dto.PictureDTO;
import com.egeo.components.config.facade.PictureFacade;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("picture")
public class PictureManageImpl implements PictureManage{

	
	@Resource(name="pictureFacade")
	private PictureFacade pictureFacade;

	@Override
	public PictureDTO findPictureById(PictureDTO dto) {
		return pictureFacade.findPictureById(dto);
	}

	@Override
	public PageResult<PictureDTO> findPictureOfPage(PictureDTO dto, Pagination page) {
		return pictureFacade.findPictureOfPage(dto, page);
	}

	@Override
	public List<PictureDTO> findPictureAll(PictureDTO dto) {
		return pictureFacade.findPictureAll(dto);
	}

	@Override
	public Long insertPictureWithTx(PictureDTO dto) {
		return pictureFacade.insertPictureWithTx(dto);
	}

	@Override
	public int updatePictureWithTx(PictureDTO dto) {
		return pictureFacade.updatePictureWithTx(dto);
	}

	@Override
	public int deletePictureWithTx(PictureDTO dto) {
		return pictureFacade.deletePictureWithTx(dto);
	}


}
	