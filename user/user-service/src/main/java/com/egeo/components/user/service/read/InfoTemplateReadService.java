package com.egeo.components.user.service.read;


import java.util.List;
	
import com.egeo.components.user.dto.InfoTemplateDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface InfoTemplateReadService {

	public InfoTemplateDTO findInfoTemplateById(InfoTemplateDTO dto);

	public PageResult<InfoTemplateDTO> findInfoTemplateOfPage(InfoTemplateDTO dto,Pagination page);

	public List<InfoTemplateDTO> findInfoTemplateAll(InfoTemplateDTO dto);
}
	