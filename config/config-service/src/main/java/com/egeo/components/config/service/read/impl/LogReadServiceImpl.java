package com.egeo.components.config.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.config.condition.LogCondition;
import com.egeo.components.config.converter.LogConverter;
import com.egeo.components.config.converter.LogInfoConverter;
import com.egeo.components.config.dto.LogDTO;
import com.egeo.components.config.dto.LogInfoDTO;
import com.egeo.components.config.manage.read.LogReadManage;
import com.egeo.components.config.po.LogPO;
import com.egeo.components.config.service.read.LogReadService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("logReadService")
public class LogReadServiceImpl implements LogReadService {
	@Autowired
	private LogReadManage logReadManage;

	@Override
	public LogDTO findLogById(LogDTO dto) {
		LogPO po = LogConverter.toPO(dto);
		LogPO list = logReadManage.findLogById(po);		
		return LogConverter.toDTO(list);
	}

	@Override
	public PageResult<LogDTO> findLogOfPage(LogDTO dto, Pagination page) {
		LogPO po = LogConverter.toPO(dto);
        PageResult<LogPO> pageResult = logReadManage.findLogOfPage(po, page);
        
        List<LogDTO> list = LogConverter.toDTO(pageResult.getList());
        PageResult<LogDTO> result = new PageResult<LogDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<LogDTO> findLogAll(LogDTO dto) {
		LogPO po = LogConverter.toPO(dto);
		List<LogPO> list = logReadManage.findLogAll(po);		
		return LogConverter.toDTO(list);
	}

	@Override
	public PageResult<LogInfoDTO> findLogInfoOfPage(LogInfoDTO dto, Pagination page) {
		 PageResult<LogCondition> pageResult = logReadManage.findLogInfoOfPage(LogInfoConverter.toPO(dto), page);
	        
	     List<LogInfoDTO> list = LogInfoConverter.toDTO(pageResult.getList());
	     PageResult<LogInfoDTO> result = new PageResult<LogInfoDTO>();
	     result.setList(list);
	     result.setPageNo(pageResult.getPageNo());
	     result.setPageSize(pageResult.getPageSize());
	     result.setTotalSize(pageResult.getTotalSize());
	     return result;
	}
}
	