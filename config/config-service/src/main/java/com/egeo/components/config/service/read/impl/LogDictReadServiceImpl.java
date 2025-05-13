package com.egeo.components.config.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.config.converter.LogDictConverter;
import com.egeo.components.config.dto.LogDictDTO;
import com.egeo.components.config.manage.read.LogDictReadManage;
import com.egeo.components.config.po.LogDictPO;
import com.egeo.components.config.service.read.LogDictReadService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("logDictReadService")
public class LogDictReadServiceImpl implements LogDictReadService {
	@Autowired
	private LogDictReadManage logDictReadManage;

	@Override
	public LogDictDTO findLogDictById(LogDictDTO dto) {
		LogDictPO po = LogDictConverter.toPO(dto);
		LogDictPO list = logDictReadManage.findLogDictById(po);		
		return LogDictConverter.toDTO(list);
	}

	@Override
	public PageResult<LogDictDTO> findLogDictOfPage(LogDictDTO dto, Pagination page) {
		LogDictPO po = LogDictConverter.toPO(dto);
        PageResult<LogDictPO> pageResult = logDictReadManage.findLogDictOfPage(po, page);
        
        List<LogDictDTO> list = LogDictConverter.toDTO(pageResult.getList());
        PageResult<LogDictDTO> result = new PageResult<LogDictDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<LogDictDTO> findLogDictAll(LogDictDTO dto) {
		LogDictPO po = LogDictConverter.toPO(dto);
		List<LogDictPO> list = logDictReadManage.findLogDictAll(po);		
		return LogDictConverter.toDTO(list);
	}

	@Override
	public List<LogDictDTO> findLogDictAllById(Long msgId) {
		List<LogDictPO> list = logDictReadManage.findLogDictAllById(msgId);		
		return LogDictConverter.toDTO(list);
	}
}
	