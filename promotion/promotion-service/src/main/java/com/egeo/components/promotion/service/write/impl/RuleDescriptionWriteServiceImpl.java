package com.egeo.components.promotion.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 
import com.egeo.components.promotion.service.write.RuleDescriptionWriteService;
import com.egeo.components.promotion.manage.write.RuleDescriptionWriteManage;
import com.egeo.components.promotion.converter.RuleDescriptionConverter;
import com.egeo.components.promotion.dto.RuleDescriptionDTO;
import com.egeo.components.promotion.po.RuleDescriptionPO;

@Service("ruleDescriptionWriteService")
public class RuleDescriptionWriteServiceImpl implements RuleDescriptionWriteService {
	@Autowired
	private RuleDescriptionWriteManage ruleDescriptionWriteManage;

	@Override
	public Long insertRuleDescriptionWithTx(RuleDescriptionDTO dto) {
		RuleDescriptionPO po = RuleDescriptionConverter.toPO(dto);
		Long rt = ruleDescriptionWriteManage.insertRuleDescriptionWithTx(po);		
		return rt;
	}

	@Override
	public int updateRuleDescriptionWithTx(RuleDescriptionDTO dto) {
		RuleDescriptionPO po = RuleDescriptionConverter.toPO(dto);
		int rt = ruleDescriptionWriteManage.updateRuleDescriptionWithTx(po);		
		return rt;
	}

	@Override
	public int deleteRuleDescriptionWithTx(RuleDescriptionDTO dto) {
		RuleDescriptionPO po = RuleDescriptionConverter.toPO(dto);
		int rt = ruleDescriptionWriteManage.deleteRuleDescriptionWithTx(po);		
		return rt;
	}
}
	