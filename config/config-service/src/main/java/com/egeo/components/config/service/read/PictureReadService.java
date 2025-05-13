package com.egeo.components.config.service.read;


import java.util.List;

import com.egeo.components.config.dto.PictureDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface PictureReadService {

	public PictureDTO findPictureById(PictureDTO dto);

	public PageResult<PictureDTO> findPictureOfPage(PictureDTO dto,Pagination page);

	public List<PictureDTO> findPictureAll(PictureDTO dto);
}
	