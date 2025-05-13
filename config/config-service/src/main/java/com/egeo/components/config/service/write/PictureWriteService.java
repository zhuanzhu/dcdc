package com.egeo.components.config.service.write;

import com.egeo.components.config.dto.PictureDTO;


public interface PictureWriteService {

	public Long insertPictureWithTx(PictureDTO dto);

	public int updatePictureWithTx(PictureDTO dto);

	public int deletePictureWithTx(PictureDTO dto);
}
	