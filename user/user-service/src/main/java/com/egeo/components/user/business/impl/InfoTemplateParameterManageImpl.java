package com.egeo.components.user.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.user.business.InfoTemplateParameterManage;
import com.egeo.components.user.facade.InfoTemplateParameterFacade;
import com.egeo.components.user.dto.InfoTemplateParameterDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("infoTemplateParameter")
public class InfoTemplateParameterManageImpl implements InfoTemplateParameterManage{

	
	@Resource(name="infoTemplateParameterFacade")
	private InfoTemplateParameterFacade infoTemplateParameterFacade;

	@Override
	public InfoTemplateParameterDTO findInfoTemplateParameterById(InfoTemplateParameterDTO dto) {
		return infoTemplateParameterFacade.findInfoTemplateParameterById(dto);
	}

	@Override
	public PageResult<InfoTemplateParameterDTO> findInfoTemplateParameterOfPage(InfoTemplateParameterDTO dto, Pagination page) {
		return infoTemplateParameterFacade.findInfoTemplateParameterOfPage(dto, page);
	}

	@Override
	public List<InfoTemplateParameterDTO> findInfoTemplateParameterAll(InfoTemplateParameterDTO dto) {
		return infoTemplateParameterFacade.findInfoTemplateParameterAll(dto);
	}

	@Override
	public Long insertInfoTemplateParameterWithTx(InfoTemplateParameterDTO dto) {
		return infoTemplateParameterFacade.insertInfoTemplateParameterWithTx(dto);
	}

	@Override
	public int updateInfoTemplateParameterWithTx(InfoTemplateParameterDTO dto) {
		return infoTemplateParameterFacade.updateInfoTemplateParameterWithTx(dto);
	}

	@Override
	public int deleteInfoTemplateParameterWithTx(InfoTemplateParameterDTO dto) {
		return infoTemplateParameterFacade.deleteInfoTemplateParameterWithTx(dto);
	}


}
	