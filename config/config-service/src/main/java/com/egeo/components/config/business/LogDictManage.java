package com.egeo.components.config.business;

import java.util.List;

import com.egeo.components.config.dto.LogDictDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface LogDictManage {

	public LogDictDTO findLogDictById(LogDictDTO dto);	

	public PageResult<LogDictDTO> findLogDictOfPage(LogDictDTO dto,Pagination page);

	public List<LogDictDTO> findLogDictAll(LogDictDTO dto);

	Long insertLogDictWithTx(LogDictDTO dto);

	int updateLogDictWithTx(LogDictDTO dto);

	int deleteLogDictWithTx(LogDictDTO dto);

	/**
	 * 	通过日志id查询字典信息
	 */
	public List<LogDictDTO> findLogDictByLogId(Long logId);
}
	