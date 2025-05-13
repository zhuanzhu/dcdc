package com.egeo.components.cms.service.write;

import com.egeo.components.cms.dto.ImagetextGroupDTO;


public interface ImagetextGroupWriteService {

	public Long insertImagetextGroupWithTx(ImagetextGroupDTO dto);

	public int updateImagetextGroupWithTx(ImagetextGroupDTO dto);

	public int deleteImagetextGroupWithTx(ImagetextGroupDTO dto);
}
	