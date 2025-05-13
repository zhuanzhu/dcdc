package com.egeo.components.cms.service.read;


import java.util.List;
	
import com.egeo.components.cms.dto.IconGroupCompanyDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface IconGroupCompanyReadService {

	public IconGroupCompanyDTO findIconGroupCompanyById(IconGroupCompanyDTO dto);

	public PageResult<IconGroupCompanyDTO> findIconGroupCompanyOfPage(IconGroupCompanyDTO dto,Pagination page);

	public List<IconGroupCompanyDTO> findIconGroupCompanyAll(IconGroupCompanyDTO dto);
}
	