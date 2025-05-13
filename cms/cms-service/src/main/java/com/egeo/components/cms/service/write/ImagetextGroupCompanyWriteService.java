package com.egeo.components.cms.service.write;

import com.egeo.components.cms.dto.ImagetextGroupCompanyDTO;


public interface ImagetextGroupCompanyWriteService {

	public Long insertImagetextGroupCompanyWithTx(ImagetextGroupCompanyDTO dto);

	public int updateImagetextGroupCompanyWithTx(ImagetextGroupCompanyDTO dto);

	public int deleteImagetextGroupCompanyWithTx(ImagetextGroupCompanyDTO dto);
}
	