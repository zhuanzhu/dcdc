package com.egeo.components.user.facade;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.egeo.components.user.dto.InfoTemplateParameterDTO;
import com.egeo.components.user.service.read.InfoTemplateParameterReadService;
import com.egeo.components.user.service.write.InfoTemplateParameterWriteService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class InfoTemplateParameterFacade {
	
	@Resource
	private InfoTemplateParameterReadService infoTemplateParameterReadService;
	
	@Resource
	private InfoTemplateParameterWriteService infoTemplateParameterWriteService;
	
	
	public InfoTemplateParameterDTO findInfoTemplateParameterById(InfoTemplateParameterDTO dto){
		
		return infoTemplateParameterReadService.findInfoTemplateParameterById(dto);
	}

	public PageResult<InfoTemplateParameterDTO> findInfoTemplateParameterOfPage(InfoTemplateParameterDTO dto,Pagination page){
		
		return infoTemplateParameterReadService.findInfoTemplateParameterOfPage(dto, page);
		
	}

	public List<InfoTemplateParameterDTO> findInfoTemplateParameterAll(InfoTemplateParameterDTO dto){
		
		return infoTemplateParameterReadService.findInfoTemplateParameterAll(dto);
		
	}

	public Long insertInfoTemplateParameterWithTx(InfoTemplateParameterDTO dto){
		
		return infoTemplateParameterWriteService.insertInfoTemplateParameterWithTx(dto);
	}

	public int updateInfoTemplateParameterWithTx(InfoTemplateParameterDTO dto){
		
		return infoTemplateParameterWriteService.updateInfoTemplateParameterWithTx(dto);
	}

	public int deleteInfoTemplateParameterWithTx(InfoTemplateParameterDTO dto){
		
		return infoTemplateParameterWriteService.deleteInfoTemplateParameterWithTx(dto);
		
	}

}
	