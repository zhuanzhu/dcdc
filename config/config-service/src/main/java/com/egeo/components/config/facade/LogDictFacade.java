package com.egeo.components.config.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.components.config.dto.LogDTO;
import com.egeo.components.config.dto.LogDictDTO;
import com.egeo.components.config.service.read.LogDictReadService;
import com.egeo.components.config.service.read.LogReadService;
import com.egeo.components.config.service.write.LogDictWriteService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class LogDictFacade {
	
	@Autowired
	private LogDictReadService logDictReadService;
	
	@Autowired
	private LogDictWriteService logDictWriteService;
	
	@Autowired
	private LogReadService logReadService;
	
	
	public LogDictDTO findLogDictById(LogDictDTO dto){
		
		return logDictReadService.findLogDictById(dto);
	}

	public PageResult<LogDictDTO> findLogDictOfPage(LogDictDTO dto,Pagination page){
		
		return logDictReadService.findLogDictOfPage(dto, page);
		
	}

	public List<LogDictDTO> findLogDictAll(LogDictDTO dto){
		
		return logDictReadService.findLogDictAll(dto);
		
	}

	public Long insertLogDictWithTx(LogDictDTO dto){
		
		return logDictWriteService.insertLogDictWithTx(dto);
	}

	public int updateLogDictWithTx(LogDictDTO dto){
		
		return logDictWriteService.updateLogDictWithTx(dto);
	}

	public int deleteLogDictWithTx(LogDictDTO dto){
		
		return logDictWriteService.deleteLogDictWithTx(dto);
		
	}

	public List<LogDictDTO> findLogDictByLogId(Long logId) {
		LogDTO logDTO = new LogDTO();
		logDTO.setId(logId);
		logDTO = logReadService.findLogById(logDTO);
		
		return logDictReadService.findLogDictAllById(logDTO.getMsgId());
	}

}
	