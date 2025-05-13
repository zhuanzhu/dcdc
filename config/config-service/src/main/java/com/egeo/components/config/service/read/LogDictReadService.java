package com.egeo.components.config.service.read;


import java.util.List;

import com.egeo.components.config.dto.LogDictDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface LogDictReadService {

	public LogDictDTO findLogDictById(LogDictDTO dto);

	public PageResult<LogDictDTO> findLogDictOfPage(LogDictDTO dto,Pagination page);

	public List<LogDictDTO> findLogDictAll(LogDictDTO dto);

	/**
	 * 通过3级字典id查询所属模块的操作
	 * @param msgId
	 * @return
	 */
	public List<LogDictDTO> findLogDictAllById(Long msgId);
}
	