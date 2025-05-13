package com.egeo.components.config.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.components.config.dto.LogDTO;
import com.egeo.components.config.dto.LogInfoDTO;
import com.egeo.components.config.service.read.LogReadService;
import com.egeo.components.config.service.write.LogWriteService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class LogFacade {
	
	@Autowired
	private LogReadService logReadService;
	
	@Autowired
	private LogWriteService logWriteService;
	
	
	public LogDTO findLogById(LogDTO dto){
		
		return logReadService.findLogById(dto);
	}

	public PageResult<LogDTO> findLogOfPage(LogDTO dto,Pagination page){
		
		return logReadService.findLogOfPage(dto, page);
		
	}

	public List<LogDTO> findLogAll(LogDTO dto){
		
		return logReadService.findLogAll(dto);
		
	}

	public Long insertLogWithTx(LogDTO dto){
		
		return logWriteService.insertLogWithTx(dto);
	}

	public int updateLogWithTx(LogDTO dto){
		
		return logWriteService.updateLogWithTx(dto);
	}

	public int deleteLogWithTx(LogDTO dto){
		
		return logWriteService.deleteLogWithTx(dto);
		
	}

	public PageResult<LogInfoDTO> findLogInfoOfPage(LogInfoDTO dto, Pagination page) {
		return logReadService.findLogInfoOfPage(dto, page);
	}

}
	