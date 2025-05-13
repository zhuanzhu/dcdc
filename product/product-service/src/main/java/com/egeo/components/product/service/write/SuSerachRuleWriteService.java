package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.StandardUnitDTO;
import com.egeo.components.product.dto.SuSerachRuleDTO;

import java.util.List;


public interface SuSerachRuleWriteService {

	public Long insertSuSerachRuleWithTx(SuSerachRuleDTO dto);

	public int updateSuSerachRuleWithTx(SuSerachRuleDTO dto);

	public int deleteSuSerachRuleWithTx(SuSerachRuleDTO dto);
	
	public void syncSaveSuSerachRule(List<StandardUnitDTO> suList, int i);

    void saveSuSerachRule(List<Long> suIdList, List<String> nameList);

    void updateSuSerachRuleList(List<Long> suIdList, List<String> nameList);
}
	