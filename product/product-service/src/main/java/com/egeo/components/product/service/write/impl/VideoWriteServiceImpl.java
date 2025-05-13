package com.egeo.components.product.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.write.VideoWriteService;
import com.egeo.components.product.manage.write.VideoWriteManage;
import com.egeo.components.product.converter.VideoConverter;
import com.egeo.components.product.dto.VideoDTO;
import com.egeo.components.product.po.VideoPO;

@Service("videoWriteService")
public class VideoWriteServiceImpl  implements VideoWriteService {
	@Autowired
	private VideoWriteManage videoWriteManage;

	@Override
	public Long insertVideoWithTx(VideoDTO dto) {
		VideoPO po = VideoConverter.toPO(dto);
		Long rt = videoWriteManage.insertVideoWithTx(po);		
		return rt;
	}

	@Override
	public int updateVideoWithTx(VideoDTO dto) {
		VideoPO po = VideoConverter.toPO(dto);
		int rt = videoWriteManage.updateVideoWithTx(po);		
		return rt;
	}

	@Override
	public int deleteVideoWithTx(VideoDTO dto) {
		VideoPO po = VideoConverter.toPO(dto);
		int rt = videoWriteManage.deleteVideoWithTx(po);		
		return rt;
	}
}
	