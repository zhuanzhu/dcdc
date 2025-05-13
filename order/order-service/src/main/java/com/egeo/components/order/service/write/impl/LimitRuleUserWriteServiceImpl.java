package com.egeo.components.order.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.write.LimitRuleUserWriteService;
import com.egeo.components.order.manage.write.LimitRuleUserWriteManage;
import com.egeo.components.order.converter.LimitRuleUserConverter;
import com.egeo.components.order.dto.LimitRuleUserDTO;
import com.egeo.components.order.po.LimitRuleUserPO;

@Service("limitRuleUserWriteService")
public class LimitRuleUserWriteServiceImpl  implements LimitRuleUserWriteService {
	@Autowired
	private LimitRuleUserWriteManage limitRuleUserWriteManage;

	@Override
	public Long insertLimitRuleUserWithTx(LimitRuleUserDTO dto) {
		LimitRuleUserPO po = LimitRuleUserConverter.toPO(dto);
		Long rt = limitRuleUserWriteManage.insertLimitRuleUserWithTx(po);		
		return rt;
	}

	@Override
	public int updateLimitRuleUserWithTx(LimitRuleUserDTO dto) {
		LimitRuleUserPO po = LimitRuleUserConverter.toPO(dto);
		int rt = limitRuleUserWriteManage.updateLimitRuleUserWithTx(po);		
		return rt;
	}

	@Override
	public int deleteLimitRuleUserWithTx(LimitRuleUserDTO dto) {
		LimitRuleUserPO po = LimitRuleUserConverter.toPO(dto);
		int rt = limitRuleUserWriteManage.deleteLimitRuleUserWithTx(po);		
		return rt;
	}
}
	