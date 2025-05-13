package com.egeo.components.promotion.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import org.springframework.beans.factory.annotation.Autowired;
import com.egeo.components.promotion.service.read.RuleDescriptionReadService;
import com.egeo.components.promotion.service.write.RuleDescriptionWriteService;
import com.egeo.components.promotion.dto.RuleDescriptionDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class RuleDescriptionFacade {
	
	@Autowired
	private RuleDescriptionReadService ruleDescriptionReadService;
	
	@Autowired
	private RuleDescriptionWriteService ruleDescriptionWriteService;
	
	
	public RuleDescriptionDTO findRuleDescriptionById(RuleDescriptionDTO dto){
		
		return ruleDescriptionReadService.findRuleDescriptionById(dto);
	}

	public PageResult<RuleDescriptionDTO> findRuleDescriptionOfPage(RuleDescriptionDTO dto,Pagination page){
		
		return ruleDescriptionReadService.findRuleDescriptionOfPage(dto, page);
		
	}

	public List<RuleDescriptionDTO> findRuleDescriptionAll(RuleDescriptionDTO dto){
		
		return ruleDescriptionReadService.findRuleDescriptionAll(dto);
		
	}

	public Long insertRuleDescriptionWithTx(RuleDescriptionDTO dto){
		
		return ruleDescriptionWriteService.insertRuleDescriptionWithTx(dto);
	}

	public int updateRuleDescriptionWithTx(RuleDescriptionDTO dto){
		
		return ruleDescriptionWriteService.updateRuleDescriptionWithTx(dto);
	}

	public int deleteRuleDescriptionWithTx(RuleDescriptionDTO dto){
		
		return ruleDescriptionWriteService.deleteRuleDescriptionWithTx(dto);
		
	}

}
	