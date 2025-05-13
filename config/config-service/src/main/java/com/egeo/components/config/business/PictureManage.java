package com.egeo.components.config.business;

import java.util.List;

import com.egeo.components.config.dto.PictureDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface PictureManage {

	public PictureDTO findPictureById(PictureDTO dto);	

	public PageResult<PictureDTO> findPictureOfPage(PictureDTO dto,Pagination page);

	public List<PictureDTO> findPictureAll(PictureDTO dto);

	Long insertPictureWithTx(PictureDTO dto);

	int updatePictureWithTx(PictureDTO dto);

	int deletePictureWithTx(PictureDTO dto);
}
	