package com.egeo.components.config.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.components.config.dto.KeywordDTO;
import com.egeo.components.config.service.read.KeywordReadService;
import com.egeo.components.config.service.write.KeywordWriteService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class KeywordFacade {
	
	@Autowired
	private KeywordReadService keywordReadService;
	
	@Autowired
	private KeywordWriteService keywordWriteService;
	
	
	public KeywordDTO findKeywordById(KeywordDTO dto){
		
		return keywordReadService.findKeywordById(dto);
	}

	public PageResult<KeywordDTO> findKeywordOfPage(KeywordDTO dto,Pagination page){
		
		return keywordReadService.findKeywordOfPage(dto, page);
		
	}

	public List<KeywordDTO> findKeywordAll(KeywordDTO dto){
		
		return keywordReadService.findKeywordAll(dto);
		
	}

	public Long insertKeywordWithTx(KeywordDTO dto){
		
		return keywordWriteService.insertKeywordWithTx(dto);
	}

	public int updateKeywordWithTx(KeywordDTO dto){
		
		return keywordWriteService.updateKeywordWithTx(dto);
	}

	public int deleteKeywordWithTx(KeywordDTO dto){
		
		return keywordWriteService.deleteKeywordWithTx(dto);
		
	}

}
	