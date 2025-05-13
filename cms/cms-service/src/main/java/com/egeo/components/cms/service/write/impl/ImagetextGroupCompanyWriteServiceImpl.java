package com.egeo.components.cms.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.cms.service.write.ImagetextGroupCompanyWriteService;
import com.egeo.components.cms.manage.write.ImagetextGroupCompanyWriteManage;
import com.egeo.components.cms.converter.ImagetextGroupCompanyConverter;
import com.egeo.components.cms.dto.ImagetextGroupCompanyDTO;
import com.egeo.components.cms.po.ImagetextGroupCompanyPO;

@Service("imagetextGroupCompanyWriteService")
public class ImagetextGroupCompanyWriteServiceImpl  implements ImagetextGroupCompanyWriteService {
	@Autowired
	private ImagetextGroupCompanyWriteManage imagetextGroupCompanyWriteManage;

	@Override
	public Long insertImagetextGroupCompanyWithTx(ImagetextGroupCompanyDTO dto) {
		ImagetextGroupCompanyPO po = ImagetextGroupCompanyConverter.toPO(dto);
		Long rt = imagetextGroupCompanyWriteManage.insertImagetextGroupCompanyWithTx(po);		
		return rt;
	}

	@Override
	public int updateImagetextGroupCompanyWithTx(ImagetextGroupCompanyDTO dto) {
		ImagetextGroupCompanyPO po = ImagetextGroupCompanyConverter.toPO(dto);
		int rt = imagetextGroupCompanyWriteManage.updateImagetextGroupCompanyWithTx(po);		
		return rt;
	}

	@Override
	public int deleteImagetextGroupCompanyWithTx(ImagetextGroupCompanyDTO dto) {
		ImagetextGroupCompanyPO po = ImagetextGroupCompanyConverter.toPO(dto);
		int rt = imagetextGroupCompanyWriteManage.deleteImagetextGroupCompanyWithTx(po);		
		return rt;
	}
}
	