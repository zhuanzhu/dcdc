package com.egeo.components.order.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.write.LimitRuleCompanyWriteService;
import com.egeo.components.order.manage.write.LimitRuleCompanyWriteManage;
import com.egeo.components.order.converter.LimitRuleCompanyConverter;
import com.egeo.components.order.dto.LimitRuleCompanyDTO;
import com.egeo.components.order.po.LimitRuleCompanyPO;

@Service("limitRuleCompanyWriteService")
public class LimitRuleCompanyWriteServiceImpl  implements LimitRuleCompanyWriteService {
	@Autowired
	private LimitRuleCompanyWriteManage limitRuleCompanyWriteManage;

	@Override
	public Long insertLimitRuleCompanyWithTx(LimitRuleCompanyDTO dto) {
		LimitRuleCompanyPO po = LimitRuleCompanyConverter.toPO(dto);
		Long rt = limitRuleCompanyWriteManage.insertLimitRuleCompanyWithTx(po);		
		return rt;
	}

	@Override
	public int updateLimitRuleCompanyWithTx(LimitRuleCompanyDTO dto) {
		LimitRuleCompanyPO po = LimitRuleCompanyConverter.toPO(dto);
		int rt = limitRuleCompanyWriteManage.updateLimitRuleCompanyWithTx(po);		
		return rt;
	}

	@Override
	public int deleteLimitRuleCompanyWithTx(LimitRuleCompanyDTO dto) {
		LimitRuleCompanyPO po = LimitRuleCompanyConverter.toPO(dto);
		int rt = limitRuleCompanyWriteManage.deleteLimitRuleCompanyWithTx(po);		
		return rt;
	}
}
	