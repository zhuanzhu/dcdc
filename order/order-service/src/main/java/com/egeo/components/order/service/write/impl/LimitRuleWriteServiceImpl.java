package com.egeo.components.order.service.write.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.service.write.LimitRuleWriteService;
import com.egeo.components.order.manage.write.LimitRuleWriteManage;
import com.egeo.components.order.converter.LimitRuleConverter;
import com.egeo.components.order.dto.LimitRuleDTO;
import com.egeo.components.order.po.LimitRulePO;


@Service("limitRuleWriteService")
public class LimitRuleWriteServiceImpl  implements LimitRuleWriteService {
	@Autowired
	private LimitRuleWriteManage limitRuleWriteManage;

	@Override
	public Long insertLimitRuleWithTx(LimitRuleDTO dto, List<Long> companyIdList, List<Long> userCompanyIdList, List<Long> storeIdList) {
		LimitRulePO po = LimitRuleConverter.toPO(dto);
		Long rt = limitRuleWriteManage.insertLimitRuleWithTx(po, companyIdList, userCompanyIdList, storeIdList);		
		return rt;
	}

	@Override
	public int updateLimitRuleWithTx(LimitRuleDTO dto, List<Long> companyIdList, List<Long> userCompanyIdList, List<Long> storeIdList) {
		LimitRulePO po = LimitRuleConverter.toPO(dto);
		int rt = limitRuleWriteManage.updateLimitRuleWithTx(po, companyIdList, userCompanyIdList, storeIdList);		
		return rt;
	}

	@Override
	public int deleteLimitRuleWithTx(LimitRuleDTO dto) {
		LimitRulePO po = LimitRuleConverter.toPO(dto);
		int rt = limitRuleWriteManage.deleteLimitRuleWithTx(po);		
		return rt;
	}
	/**
	 * 根据限购规则id启用停用限购规则
	 * @param limitRuleId
	 * @param isStart
	 * @return
	 */
	@Override
	public int isLimitRuleStartWithTx(Long limitRuleId, Integer isStart) {
		// TODO Auto-generated method stub
		return limitRuleWriteManage.isLimitRuleStartWithTx(limitRuleId, isStart);
	}
}
	