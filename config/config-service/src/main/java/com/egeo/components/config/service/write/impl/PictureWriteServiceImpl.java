package com.egeo.components.config.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.config.converter.PictureConverter;
import com.egeo.components.config.dto.PictureDTO;
import com.egeo.components.config.manage.write.PictureWriteManage;
import com.egeo.components.config.po.PicturePO;
import com.egeo.components.config.service.write.PictureWriteService;

@Service("pictureWriteService")
public class PictureWriteServiceImpl implements PictureWriteService {
	@Autowired
	private PictureWriteManage pictureWriteManage;

	@Override
	public Long insertPictureWithTx(PictureDTO dto) {
		PicturePO po = PictureConverter.toPO(dto);
		Long rt = pictureWriteManage.insertPictureWithTx(po);		
		return rt;
	}

	@Override
	public int updatePictureWithTx(PictureDTO dto) {
		PicturePO po = PictureConverter.toPO(dto);
		int rt = pictureWriteManage.updatePictureWithTx(po);		
		return rt;
	}

	@Override
	public int deletePictureWithTx(PictureDTO dto) {
		PicturePO po = PictureConverter.toPO(dto);
		int rt = pictureWriteManage.deletePictureWithTx(po);		
		return rt;
	}
}
	