package com.egeo.components.product.service.read;


import java.util.List;
	
import com.egeo.components.product.dto.VideoDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface VideoReadService {

	public VideoDTO findVideoById(VideoDTO dto);

	public PageResult<VideoDTO> findVideoOfPage(VideoDTO dto,Pagination page);

	public List<VideoDTO> findVideoAll(VideoDTO dto);
}
	