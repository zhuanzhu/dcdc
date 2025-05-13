package com.egeo.components.promotion.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 
import com.egeo.components.promotion.service.write.PointsRuleWriteService;
import com.egeo.components.promotion.manage.write.PointsRuleWriteManage;
import com.egeo.components.promotion.converter.PointsRuleConverter;
import com.egeo.components.promotion.dto.PointsRuleDTO;
import com.egeo.components.promotion.po.PointsRulePO;

@Service("pointsRuleWriteService")
public class PointsRuleWriteServiceImpl implements PointsRuleWriteService {
	@Autowired
	private PointsRuleWriteManage pointsRuleWriteManage;

	@Override
	public Long insertPointsRuleWithTx(PointsRuleDTO dto) {
		PointsRulePO po = PointsRuleConverter.toPO(dto);
		Long rt = pointsRuleWriteManage.insertPointsRuleWithTx(po);		
		return rt;
	}

	@Override
	public int updatePointsRuleWithTx(PointsRuleDTO dto) {
		PointsRulePO po = PointsRuleConverter.toPO(dto);
		int rt = pointsRuleWriteManage.updatePointsRuleWithTx(po);		
		return rt;
	}

	@Override
	public int deletePointsRuleWithTx(PointsRuleDTO dto) {
		PointsRulePO po = PointsRuleConverter.toPO(dto);
		int rt = pointsRuleWriteManage.deletePointsRuleWithTx(po);		
		return rt;
	}
}
	