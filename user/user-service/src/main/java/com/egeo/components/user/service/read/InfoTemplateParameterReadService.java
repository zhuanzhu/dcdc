package com.egeo.components.user.service.read;


import java.util.List;
	
import com.egeo.components.user.dto.InfoTemplateParameterDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface InfoTemplateParameterReadService {

	public InfoTemplateParameterDTO findInfoTemplateParameterById(InfoTemplateParameterDTO dto);

	public PageResult<InfoTemplateParameterDTO> findInfoTemplateParameterOfPage(InfoTemplateParameterDTO dto,Pagination page);

	public List<InfoTemplateParameterDTO> findInfoTemplateParameterAll(InfoTemplateParameterDTO dto);
}
	