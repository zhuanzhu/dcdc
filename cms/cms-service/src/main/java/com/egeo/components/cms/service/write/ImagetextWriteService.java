package com.egeo.components.cms.service.write;

import com.egeo.components.cms.dto.ImagetextDTO;


public interface ImagetextWriteService {

	public Long insertImagetextWithTx(ImagetextDTO dto);

	public int updateImagetextWithTx(ImagetextDTO dto);

	public int deleteImagetextWithTx(ImagetextDTO dto);
}
	