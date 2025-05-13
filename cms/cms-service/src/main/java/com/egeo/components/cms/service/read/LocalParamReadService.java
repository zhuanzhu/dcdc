package com.egeo.components.cms.service.read;


import java.util.List;
	
import com.egeo.components.cms.dto.LocalParamDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface LocalParamReadService {

	public LocalParamDTO findLocalParamById(LocalParamDTO dto);

	public PageResult<LocalParamDTO> findLocalParamOfPage(LocalParamDTO dto,Pagination page);

	public List<LocalParamDTO> findLocalParamAll(LocalParamDTO dto);
}
	