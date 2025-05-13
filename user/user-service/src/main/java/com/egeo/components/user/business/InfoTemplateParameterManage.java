package com.egeo.components.user.business;

import java.util.List;
	
import com.egeo.components.user.dto.InfoTemplateParameterDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface InfoTemplateParameterManage {

	public InfoTemplateParameterDTO findInfoTemplateParameterById(InfoTemplateParameterDTO dto);	

	public PageResult<InfoTemplateParameterDTO> findInfoTemplateParameterOfPage(InfoTemplateParameterDTO dto,Pagination page);

	public List<InfoTemplateParameterDTO> findInfoTemplateParameterAll(InfoTemplateParameterDTO dto);

	Long insertInfoTemplateParameterWithTx(InfoTemplateParameterDTO dto);

	int updateInfoTemplateParameterWithTx(InfoTemplateParameterDTO dto);

	int deleteInfoTemplateParameterWithTx(InfoTemplateParameterDTO dto);
}
	