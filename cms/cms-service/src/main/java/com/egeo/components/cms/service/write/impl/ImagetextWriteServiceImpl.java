package com.egeo.components.cms.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.cms.service.write.ImagetextWriteService;
import com.egeo.components.cms.manage.write.ImagetextWriteManage;
import com.egeo.components.cms.converter.ImagetextConverter;
import com.egeo.components.cms.dto.ImagetextDTO;
import com.egeo.components.cms.po.ImagetextPO;

@Service("imagetextWriteService")
public class ImagetextWriteServiceImpl  implements ImagetextWriteService {
	@Autowired
	private ImagetextWriteManage imagetextWriteManage;

	@Override
	public Long insertImagetextWithTx(ImagetextDTO dto) {
		ImagetextPO po = ImagetextConverter.toPO(dto);
		Long rt = imagetextWriteManage.insertImagetextWithTx(po);		
		return rt;
	}

	@Override
	public int updateImagetextWithTx(ImagetextDTO dto) {
		ImagetextPO po = ImagetextConverter.toPO(dto);
		int rt = imagetextWriteManage.updateImagetextWithTx(po);		
		return rt;
	}

	@Override
	public int deleteImagetextWithTx(ImagetextDTO dto) {
		ImagetextPO po = ImagetextConverter.toPO(dto);
		int rt = imagetextWriteManage.deleteImagetextWithTx(po);		
		return rt;
	}
}
	