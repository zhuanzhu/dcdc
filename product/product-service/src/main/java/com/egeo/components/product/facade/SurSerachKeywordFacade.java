package com.egeo.components.product.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.SurSerachKeywordReadService;
import com.egeo.components.product.service.write.SurSerachKeywordWriteService;
import com.egeo.components.product.dto.SurSerachKeywordDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class SurSerachKeywordFacade {
	
	@Resource
	private SurSerachKeywordReadService surSerachKeywordReadService;
	
	@Resource
	private SurSerachKeywordWriteService surSerachKeywordWriteService;
	
	
	public SurSerachKeywordDTO findSurSerachKeywordById(SurSerachKeywordDTO dto){
		
		return surSerachKeywordReadService.findSurSerachKeywordById(dto);
	}

	public PageResult<SurSerachKeywordDTO> findSurSerachKeywordOfPage(SurSerachKeywordDTO dto,Pagination page){
		
		return surSerachKeywordReadService.findSurSerachKeywordOfPage(dto, page);
		
	}

	public List<SurSerachKeywordDTO> findSurSerachKeywordAll(SurSerachKeywordDTO dto){
		
		return surSerachKeywordReadService.findSurSerachKeywordAll(dto);
		
	}

	public Long insertSurSerachKeywordWithTx(SurSerachKeywordDTO dto){
		
		return surSerachKeywordWriteService.insertSurSerachKeywordWithTx(dto);
	}

	public int updateSurSerachKeywordWithTx(SurSerachKeywordDTO dto){
		
		return surSerachKeywordWriteService.updateSurSerachKeywordWithTx(dto);
	}

	public int deleteSurSerachKeywordWithTx(SurSerachKeywordDTO dto){
		
		return surSerachKeywordWriteService.deleteSurSerachKeywordWithTx(dto);
		
	}

}
	