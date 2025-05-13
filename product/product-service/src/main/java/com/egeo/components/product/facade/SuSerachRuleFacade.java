package com.egeo.components.product.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.SuSerachRuleReadService;
import com.egeo.components.product.service.write.SuSerachRuleWriteService;
import com.egeo.components.product.dto.SuSerachRuleDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class SuSerachRuleFacade {
	
	@Resource
	private SuSerachRuleReadService suSerachRuleReadService;
	
	@Resource
	private SuSerachRuleWriteService suSerachRuleWriteService;
	
	public SuSerachRuleDTO findSuSerachRuleById(SuSerachRuleDTO dto){
		
		return suSerachRuleReadService.findSuSerachRuleById(dto);
	}

	public PageResult<SuSerachRuleDTO> findSuSerachRuleOfPage(SuSerachRuleDTO dto,Pagination page){
		
		return suSerachRuleReadService.findSuSerachRuleOfPage(dto, page);
		
	}

	public List<SuSerachRuleDTO> findSuSerachRuleAll(SuSerachRuleDTO dto){
		
		return suSerachRuleReadService.findSuSerachRuleAll(dto);
		
	}

	public Long insertSuSerachRuleWithTx(SuSerachRuleDTO dto){
		
		return suSerachRuleWriteService.insertSuSerachRuleWithTx(dto);
	}

	public int updateSuSerachRuleWithTx(SuSerachRuleDTO dto){
		
		return suSerachRuleWriteService.updateSuSerachRuleWithTx(dto);
	}

	public int deleteSuSerachRuleWithTx(SuSerachRuleDTO dto){
		
		return suSerachRuleWriteService.deleteSuSerachRuleWithTx(dto);
		
	}
	
}
	