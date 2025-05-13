package com.egeo.components.promotion.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.business.RuleDescriptionManage;
import com.egeo.components.promotion.facade.RuleDescriptionFacade;
import com.egeo.components.promotion.dto.RuleDescriptionDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("ruleDescription")
public class RuleDescriptionManageImpl implements RuleDescriptionManage{

	
	@Resource(name="ruleDescriptionFacade")
	private RuleDescriptionFacade ruleDescriptionFacade;

	@Override
	public RuleDescriptionDTO findRuleDescriptionById(RuleDescriptionDTO dto) {
		return ruleDescriptionFacade.findRuleDescriptionById(dto);
	}

	@Override
	public PageResult<RuleDescriptionDTO> findRuleDescriptionOfPage(RuleDescriptionDTO dto, Pagination page) {
		return ruleDescriptionFacade.findRuleDescriptionOfPage(dto, page);
	}

	@Override
	public List<RuleDescriptionDTO> findRuleDescriptionAll(RuleDescriptionDTO dto) {
		return ruleDescriptionFacade.findRuleDescriptionAll(dto);
	}

	@Override
	public Long insertRuleDescriptionWithTx(RuleDescriptionDTO dto) {
		return ruleDescriptionFacade.insertRuleDescriptionWithTx(dto);
	}

	@Override
	public int updateRuleDescriptionWithTx(RuleDescriptionDTO dto) {
		return ruleDescriptionFacade.updateRuleDescriptionWithTx(dto);
	}

	@Override
	public int deleteRuleDescriptionWithTx(RuleDescriptionDTO dto) {
		return ruleDescriptionFacade.deleteRuleDescriptionWithTx(dto);
	}


}
	