package com.egeo.components.product.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.VideoManage;
import com.egeo.components.product.facade.VideoFacade;
import com.egeo.components.product.dto.VideoDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("video")
public class VideoManageImpl implements VideoManage{

	
	@Resource(name="videoFacade")
	private VideoFacade videoFacade;

	@Override
	public VideoDTO findVideoById(VideoDTO dto) {
		return videoFacade.findVideoById(dto);
	}

	@Override
	public PageResult<VideoDTO> findVideoOfPage(VideoDTO dto, Pagination page) {
		return videoFacade.findVideoOfPage(dto, page);
	}

	@Override
	public List<VideoDTO> findVideoAll(VideoDTO dto) {
		return videoFacade.findVideoAll(dto);
	}

	@Override
	public Long insertVideoWithTx(VideoDTO dto) {
		return videoFacade.insertVideoWithTx(dto);
	}

	@Override
	public int updateVideoWithTx(VideoDTO dto) {
		return videoFacade.updateVideoWithTx(dto);
	}

	@Override
	public int deleteVideoWithTx(VideoDTO dto) {
		return videoFacade.deleteVideoWithTx(dto);
	}


}
	