package com.egeo.components.config.business;

import java.util.List;

import com.egeo.components.config.dto.LogDTO;
import com.egeo.components.config.dto.LogInfoDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface LogManage {

	public LogDTO findLogById(LogDTO dto);	

	public PageResult<LogDTO> findLogOfPage(LogDTO dto,Pagination page);

	public List<LogDTO> findLogAll(LogDTO dto);

	Long insertLogWithTx(LogDTO dto);

	int updateLogWithTx(LogDTO dto);

	int deleteLogWithTx(LogDTO dto);

	public PageResult<LogInfoDTO> findLogInfoOfPage(LogInfoDTO dto, Pagination page);
}
	