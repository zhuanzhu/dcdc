package com.egeo.components.cms.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.cms.service.read.ExternalLinkReadService;
import com.egeo.components.cms.service.write.ExternalLinkWriteService;
import com.egeo.components.cms.dto.ExternalLinkDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class ExternalLinkFacade {
	
	@Resource
	private ExternalLinkReadService externalLinkReadService;
	
	@Resource
	private ExternalLinkWriteService externalLinkWriteService;
	
	
	public ExternalLinkDTO findExternalLinkById(Long id){
		
		return externalLinkReadService.findExternalLinkById(id);
	}

	public PageResult<ExternalLinkDTO> findExternalLinkOfPage(ExternalLinkDTO dto,Pagination page){
		
		return externalLinkReadService.findExternalLinkOfPage(dto, page);
		
	}

	public List<ExternalLinkDTO> findExternalLinkAll(ExternalLinkDTO dto){
		
		return externalLinkReadService.findExternalLinkAll(dto);
		
	}

	public Long insertExternalLinkWithTx(ExternalLinkDTO dto){
		
		return externalLinkWriteService.insertExternalLinkWithTx(dto);
	}

	public int updateExternalLinkWithTx(ExternalLinkDTO dto){
		
		return externalLinkWriteService.updateExternalLinkWithTx(dto);
	}

	public int deleteExternalLinkWithTx(ExternalLinkDTO dto){
		
		return externalLinkWriteService.deleteExternalLinkWithTx(dto);
		
	}

}
	