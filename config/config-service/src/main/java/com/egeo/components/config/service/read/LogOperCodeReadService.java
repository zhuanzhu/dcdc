package com.egeo.components.config.service.read;


import java.util.List;

import com.egeo.components.config.dto.LogOperCodeDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface LogOperCodeReadService {

	public LogOperCodeDTO findLogOperCodeById(LogOperCodeDTO dto);

	public PageResult<LogOperCodeDTO> findLogOperCodeOfPage(LogOperCodeDTO dto,Pagination page);

	public List<LogOperCodeDTO> findLogOperCodeAll(LogOperCodeDTO dto);
}
	