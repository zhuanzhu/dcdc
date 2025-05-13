package com.egeo.components.order.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.write.LimitRuleRecordWriteService;
import com.egeo.components.order.manage.write.LimitRuleRecordWriteManage;
import com.egeo.components.order.converter.LimitRuleRecordConverter;
import com.egeo.components.order.dto.LimitRuleRecordDTO;
import com.egeo.components.order.po.LimitRuleRecordPO;

@Service("limitRuleRecordWriteService")
public class LimitRuleRecordWriteServiceImpl  implements LimitRuleRecordWriteService {
	@Autowired
	private LimitRuleRecordWriteManage limitRuleRecordWriteManage;

	@Override
	public Long insertLimitRuleRecordWithTx(LimitRuleRecordDTO dto) {
		LimitRuleRecordPO po = LimitRuleRecordConverter.toPO(dto);
		Long rt = limitRuleRecordWriteManage.insertLimitRuleRecordWithTx(po);		
		return rt;
	}

	@Override
	public int updateLimitRuleRecordWithTx(LimitRuleRecordDTO dto) {
		LimitRuleRecordPO po = LimitRuleRecordConverter.toPO(dto);
		int rt = limitRuleRecordWriteManage.updateLimitRuleRecordWithTx(po);		
		return rt;
	}

	@Override
	public int deleteLimitRuleRecordWithTx(LimitRuleRecordDTO dto) {
		LimitRuleRecordPO po = LimitRuleRecordConverter.toPO(dto);
		int rt = limitRuleRecordWriteManage.deleteLimitRuleRecordWithTx(po);		
		return rt;
	}

	@Override
	public void updateOrderStatus(String orderCode, Integer orderStatus) {
		limitRuleRecordWriteManage.updateOrderStatus(orderCode, orderStatus);
	}
}
	