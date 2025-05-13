package com.egeo.components.product.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.SuSerachKeywordReadService;
import com.egeo.components.product.service.write.SuSerachKeywordWriteService;
import com.egeo.components.product.dto.SuSerachKeywordDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class SuSerachKeywordFacade {
	
	@Resource
	private SuSerachKeywordReadService suSerachKeywordReadService;
	
	@Resource
	private SuSerachKeywordWriteService suSerachKeywordWriteService;
	
	
	public SuSerachKeywordDTO findSuSerachKeywordById(SuSerachKeywordDTO dto){
		
		return suSerachKeywordReadService.findSuSerachKeywordById(dto);
	}

	public PageResult<SuSerachKeywordDTO> findSuSerachKeywordOfPage(SuSerachKeywordDTO dto,Pagination page){
		
		return suSerachKeywordReadService.findSuSerachKeywordOfPage(dto, page);
		
	}

	public List<SuSerachKeywordDTO> findSuSerachKeywordAll(SuSerachKeywordDTO dto){
		
		return suSerachKeywordReadService.findSuSerachKeywordAll(dto);
		
	}

	public Long insertSuSerachKeywordWithTx(SuSerachKeywordDTO dto){
		
		return suSerachKeywordWriteService.insertSuSerachKeywordWithTx(dto);
	}

	public int updateSuSerachKeywordWithTx(SuSerachKeywordDTO dto){
		
		return suSerachKeywordWriteService.updateSuSerachKeywordWithTx(dto);
	}

	public int deleteSuSerachKeywordWithTx(SuSerachKeywordDTO dto){
		
		return suSerachKeywordWriteService.deleteSuSerachKeywordWithTx(dto);
		
	}

}
	