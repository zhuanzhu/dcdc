package com.egeo.components.user.service.read;


import java.util.List;
	
import com.egeo.components.user.dto.DownloadDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface DownloadReadService {

	public DownloadDTO findDownloadById(DownloadDTO dto);

	public PageResult<DownloadDTO> findDownloadOfPage(DownloadDTO dto,Pagination page);

	public List<DownloadDTO> findDownloadAll(DownloadDTO dto);

	public PageResult<DownloadDTO> findDownloadOfPageByBlurry(DownloadDTO dto, Pagination page);
}
	