package com.egeo.components.order.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.write.LimitRuleStoreWriteService;
import com.egeo.components.order.manage.write.LimitRuleStoreWriteManage;
import com.egeo.components.order.converter.LimitRuleStoreConverter;
import com.egeo.components.order.dto.LimitRuleStoreDTO;
import com.egeo.components.order.po.LimitRuleStorePO;

@Service("limitRuleStoreWriteService")
public class LimitRuleStoreWriteServiceImpl  implements LimitRuleStoreWriteService {
	@Autowired
	private LimitRuleStoreWriteManage limitRuleStoreWriteManage;

	@Override
	public Long insertLimitRuleStoreWithTx(LimitRuleStoreDTO dto) {
		LimitRuleStorePO po = LimitRuleStoreConverter.toPO(dto);
		Long rt = limitRuleStoreWriteManage.insertLimitRuleStoreWithTx(po);		
		return rt;
	}

	@Override
	public int updateLimitRuleStoreWithTx(LimitRuleStoreDTO dto) {
		LimitRuleStorePO po = LimitRuleStoreConverter.toPO(dto);
		int rt = limitRuleStoreWriteManage.updateLimitRuleStoreWithTx(po);		
		return rt;
	}

	@Override
	public int deleteLimitRuleStoreWithTx(LimitRuleStoreDTO dto) {
		LimitRuleStorePO po = LimitRuleStoreConverter.toPO(dto);
		int rt = limitRuleStoreWriteManage.deleteLimitRuleStoreWithTx(po);		
		return rt;
	}
}
	