package com.egeo.components.product.service.read;


import java.util.List;
	
import com.egeo.components.product.dto.SuSerachRuleDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface SuSerachRuleReadService {

	public SuSerachRuleDTO findSuSerachRuleById(SuSerachRuleDTO dto);

	public PageResult<SuSerachRuleDTO> findSuSerachRuleOfPage(SuSerachRuleDTO dto,Pagination page);

	public List<SuSerachRuleDTO> findSuSerachRuleAll(SuSerachRuleDTO dto);
}
	