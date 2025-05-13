package com.egeo.components.user.business;

import java.util.List;
import java.util.Map;

import com.egeo.components.user.dto.DownloadDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.JsonResult;	

public interface DownloadManage {

	public DownloadDTO findDownloadById(DownloadDTO dto);	

	public PageResult<DownloadDTO> findDownloadOfPage(DownloadDTO dto,Pagination page);

	public List<DownloadDTO> findDownloadAll(DownloadDTO dto);

	Long insertDownloadWithTx(DownloadDTO dto);

	int updateDownloadWithTx(DownloadDTO dto);

	int deleteDownloadWithTx(DownloadDTO dto);

	public JsonResult<Map<String, Object>> askDownloadUrlWithTx(String session, Integer type,Integer user);
}
	