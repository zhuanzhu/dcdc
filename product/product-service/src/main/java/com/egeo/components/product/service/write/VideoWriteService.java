package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.VideoDTO;


public interface VideoWriteService {

	public Long insertVideoWithTx(VideoDTO dto);

	public int updateVideoWithTx(VideoDTO dto);

	public int deleteVideoWithTx(VideoDTO dto);
}
	