package com.egeo.components.config.service.read;


import java.util.List;

import com.egeo.components.config.dto.LogDTO;
import com.egeo.components.config.dto.LogInfoDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface LogReadService {

	public LogDTO findLogById(LogDTO dto);

	public PageResult<LogDTO> findLogOfPage(LogDTO dto,Pagination page);

	public List<LogDTO> findLogAll(LogDTO dto);

	/**
	 * 日志列表
	 * @param dto
	 * @param page
	 * @return
	 */
	public PageResult<LogInfoDTO> findLogInfoOfPage(LogInfoDTO dto, Pagination page);
}
	