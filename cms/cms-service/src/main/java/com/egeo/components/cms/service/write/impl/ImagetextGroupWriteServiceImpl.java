package com.egeo.components.cms.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.cms.service.write.ImagetextGroupWriteService;
import com.egeo.components.cms.manage.write.ImagetextGroupWriteManage;
import com.egeo.components.cms.converter.ImagetextGroupConverter;
import com.egeo.components.cms.dto.ImagetextGroupDTO;
import com.egeo.components.cms.po.ImagetextGroupPO;

@Service("imagetextGroupWriteService")
public class ImagetextGroupWriteServiceImpl  implements ImagetextGroupWriteService {
	@Autowired
	private ImagetextGroupWriteManage imagetextGroupWriteManage;

	@Override
	public Long insertImagetextGroupWithTx(ImagetextGroupDTO dto) {
		ImagetextGroupPO po = ImagetextGroupConverter.toPO(dto);
		Long rt = imagetextGroupWriteManage.insertImagetextGroupWithTx(po);		
		return rt;
	}

	@Override
	public int updateImagetextGroupWithTx(ImagetextGroupDTO dto) {
		ImagetextGroupPO po = ImagetextGroupConverter.toPO(dto);
		int rt = imagetextGroupWriteManage.updateImagetextGroupWithTx(po);		
		return rt;
	}

	@Override
	public int deleteImagetextGroupWithTx(ImagetextGroupDTO dto) {
		ImagetextGroupPO po = ImagetextGroupConverter.toPO(dto);
		int rt = imagetextGroupWriteManage.deleteImagetextGroupWithTx(po);		
		return rt;
	}
}
	