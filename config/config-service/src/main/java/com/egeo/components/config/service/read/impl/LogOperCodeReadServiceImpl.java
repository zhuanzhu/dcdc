package com.egeo.components.config.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.config.converter.LogOperCodeConverter;
import com.egeo.components.config.dto.LogOperCodeDTO;
import com.egeo.components.config.manage.read.LogOperCodeReadManage;
import com.egeo.components.config.po.LogOperCodePO;
import com.egeo.components.config.service.read.LogOperCodeReadService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("logOperCodeReadService")
public class LogOperCodeReadServiceImpl implements LogOperCodeReadService {
	@Autowired
	private LogOperCodeReadManage logOperCodeReadManage;

	@Override
	public LogOperCodeDTO findLogOperCodeById(LogOperCodeDTO dto) {
		LogOperCodePO po = LogOperCodeConverter.toPO(dto);
		LogOperCodePO list = logOperCodeReadManage.findLogOperCodeById(po);		
		return LogOperCodeConverter.toDTO(list);
	}

	@Override
	public PageResult<LogOperCodeDTO> findLogOperCodeOfPage(LogOperCodeDTO dto, Pagination page) {
		LogOperCodePO po = LogOperCodeConverter.toPO(dto);
        PageResult<LogOperCodePO> pageResult = logOperCodeReadManage.findLogOperCodeOfPage(po, page);
        
        List<LogOperCodeDTO> list = LogOperCodeConverter.toDTO(pageResult.getList());
        PageResult<LogOperCodeDTO> result = new PageResult<LogOperCodeDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<LogOperCodeDTO> findLogOperCodeAll(LogOperCodeDTO dto) {
		LogOperCodePO po = LogOperCodeConverter.toPO(dto);
		List<LogOperCodePO> list = logOperCodeReadManage.findLogOperCodeAll(po);		
		return LogOperCodeConverter.toDTO(list);
	}
}
	