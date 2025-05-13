package com.egeo.components.config.business.impl;
	

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.components.config.business.LogManage;
import com.egeo.components.config.dto.LogDTO;
import com.egeo.components.config.dto.LogInfoDTO;
import com.egeo.components.config.facade.LogFacade;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("log")
public class LogManageImpl implements LogManage{

	
	@Resource(name="logFacade")
	private LogFacade logFacade;

	@Override
	public LogDTO findLogById(LogDTO dto) {
		return logFacade.findLogById(dto);
	}

	@Override
	public PageResult<LogDTO> findLogOfPage(LogDTO dto, Pagination page) {
		return logFacade.findLogOfPage(dto, page);
	}

	@Override
	public List<LogDTO> findLogAll(LogDTO dto) {
		return logFacade.findLogAll(dto);
	}

	@Override
	public Long insertLogWithTx(LogDTO dto) {
		return logFacade.insertLogWithTx(dto);
	}

	@Override
	public int updateLogWithTx(LogDTO dto) {
		return logFacade.updateLogWithTx(dto);
	}

	@Override
	public int deleteLogWithTx(LogDTO dto) {
		return logFacade.deleteLogWithTx(dto);
	}

	@Override
	public PageResult<LogInfoDTO> findLogInfoOfPage(LogInfoDTO dto, Pagination page) {
		return logFacade.findLogInfoOfPage(dto, page);
	}


}
	