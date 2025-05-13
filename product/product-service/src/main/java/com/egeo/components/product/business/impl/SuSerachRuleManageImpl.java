package com.egeo.components.product.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.SuSerachRuleManage;
import com.egeo.components.product.facade.SuSerachRuleFacade;
import com.egeo.components.product.dto.SuSerachRuleDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("suSerachRule")
public class SuSerachRuleManageImpl implements SuSerachRuleManage{

	
	@Resource(name="suSerachRuleFacade")
	private SuSerachRuleFacade suSerachRuleFacade;

	@Override
	public SuSerachRuleDTO findSuSerachRuleById(SuSerachRuleDTO dto) {
		return suSerachRuleFacade.findSuSerachRuleById(dto);
	}

	@Override
	public PageResult<SuSerachRuleDTO> findSuSerachRuleOfPage(SuSerachRuleDTO dto, Pagination page) {
		return suSerachRuleFacade.findSuSerachRuleOfPage(dto, page);
	}

	@Override
	public List<SuSerachRuleDTO> findSuSerachRuleAll(SuSerachRuleDTO dto) {
		return suSerachRuleFacade.findSuSerachRuleAll(dto);
	}

	@Override
	public Long insertSuSerachRuleWithTx(SuSerachRuleDTO dto) {
		return suSerachRuleFacade.insertSuSerachRuleWithTx(dto);
	}

	@Override
	public int updateSuSerachRuleWithTx(SuSerachRuleDTO dto) {
		return suSerachRuleFacade.updateSuSerachRuleWithTx(dto);
	}

	@Override
	public int deleteSuSerachRuleWithTx(SuSerachRuleDTO dto) {
		return suSerachRuleFacade.deleteSuSerachRuleWithTx(dto);
	}


}
	