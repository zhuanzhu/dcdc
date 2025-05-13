package com.egeo.components.config.business.impl;
	

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.components.config.business.LogDictManage;
import com.egeo.components.config.dto.LogDictDTO;
import com.egeo.components.config.facade.LogDictFacade;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("logDict")
public class LogDictManageImpl implements LogDictManage{

	
	@Resource(name="logDictFacade")
	private LogDictFacade logDictFacade;

	@Override
	public LogDictDTO findLogDictById(LogDictDTO dto) {
		return logDictFacade.findLogDictById(dto);
	}

	@Override
	public PageResult<LogDictDTO> findLogDictOfPage(LogDictDTO dto, Pagination page) {
		return logDictFacade.findLogDictOfPage(dto, page);
	}

	@Override
	public List<LogDictDTO> findLogDictAll(LogDictDTO dto) {
		return logDictFacade.findLogDictAll(dto);
	}

	@Override
	public Long insertLogDictWithTx(LogDictDTO dto) {
		return logDictFacade.insertLogDictWithTx(dto);
	}

	@Override
	public int updateLogDictWithTx(LogDictDTO dto) {
		return logDictFacade.updateLogDictWithTx(dto);
	}

	@Override
	public int deleteLogDictWithTx(LogDictDTO dto) {
		return logDictFacade.deleteLogDictWithTx(dto);
	}

	@Override
	public List<LogDictDTO> findLogDictByLogId(Long logId) {
		
		return logDictFacade.findLogDictByLogId(logId);
	}


}
	