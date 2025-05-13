package com.egeo.components.product.business;

import java.util.List;
	
import com.egeo.components.product.dto.VideoDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface VideoManage {

	public VideoDTO findVideoById(VideoDTO dto);	

	public PageResult<VideoDTO> findVideoOfPage(VideoDTO dto,Pagination page);

	public List<VideoDTO> findVideoAll(VideoDTO dto);

	Long insertVideoWithTx(VideoDTO dto);

	int updateVideoWithTx(VideoDTO dto);

	int deleteVideoWithTx(VideoDTO dto);
}
	