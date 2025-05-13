package com.egeo.components.product.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.VideoReadService;
import com.egeo.components.product.service.write.VideoWriteService;
import com.egeo.components.product.dto.VideoDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class VideoFacade {
	
	@Resource
	private VideoReadService videoReadService;
	
	@Resource
	private VideoWriteService videoWriteService;
	
	
	public VideoDTO findVideoById(VideoDTO dto){
		
		return videoReadService.findVideoById(dto);
	}

	public PageResult<VideoDTO> findVideoOfPage(VideoDTO dto,Pagination page){
		
		return videoReadService.findVideoOfPage(dto, page);
		
	}

	public List<VideoDTO> findVideoAll(VideoDTO dto){
		
		return videoReadService.findVideoAll(dto);
		
	}

	public Long insertVideoWithTx(VideoDTO dto){
		
		return videoWriteService.insertVideoWithTx(dto);
	}

	public int updateVideoWithTx(VideoDTO dto){
		
		return videoWriteService.updateVideoWithTx(dto);
	}

	public int deleteVideoWithTx(VideoDTO dto){
		
		return videoWriteService.deleteVideoWithTx(dto);
		
	}

}
	