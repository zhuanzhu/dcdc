package com.egeo.components.product.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.VideoReadService;
import com.egeo.components.product.manage.read.VideoReadManage;
import com.egeo.components.product.converter.VideoConverter;
import com.egeo.components.product.dto.VideoDTO;
import com.egeo.components.product.po.VideoPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("videoReadService")
public class VideoReadServiceImpl  implements VideoReadService {
	@Autowired
	private VideoReadManage videoReadManage;

	@Override
	public VideoDTO findVideoById(VideoDTO dto) {
		VideoPO po = VideoConverter.toPO(dto);
		VideoPO list = videoReadManage.findVideoById(po);		
		return VideoConverter.toDTO(list);
	}

	@Override
	public PageResult<VideoDTO> findVideoOfPage(VideoDTO dto, Pagination page) {
		VideoPO po = VideoConverter.toPO(dto);
        PageResult<VideoPO> pageResult = videoReadManage.findVideoOfPage(po, page);
        
        List<VideoDTO> list = VideoConverter.toDTO(pageResult.getList());
        PageResult<VideoDTO> result = new PageResult<VideoDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<VideoDTO> findVideoAll(VideoDTO dto) {
		VideoPO po = VideoConverter.toPO(dto);
		List<VideoPO> list = videoReadManage.findVideoAll(po);		
		return VideoConverter.toDTO(list);
	}
}
	