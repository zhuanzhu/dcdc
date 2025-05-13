package com.egeo.components.product.business;

import java.util.List;
	
import com.egeo.components.product.dto.SuSerachRuleDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface SuSerachRuleManage {

	public SuSerachRuleDTO findSuSerachRuleById(SuSerachRuleDTO dto);	

	public PageResult<SuSerachRuleDTO> findSuSerachRuleOfPage(SuSerachRuleDTO dto,Pagination page);

	public List<SuSerachRuleDTO> findSuSerachRuleAll(SuSerachRuleDTO dto);

	Long insertSuSerachRuleWithTx(SuSerachRuleDTO dto);

	int updateSuSerachRuleWithTx(SuSerachRuleDTO dto);

	int deleteSuSerachRuleWithTx(SuSerachRuleDTO dto);
}
	