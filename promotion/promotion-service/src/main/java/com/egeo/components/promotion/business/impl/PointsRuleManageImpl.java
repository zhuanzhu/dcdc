package com.egeo.components.promotion.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.business.PointsRuleManage;
import com.egeo.components.promotion.facade.PointsRuleFacade;
import com.egeo.components.promotion.dto.PointsRuleDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("pointsRule")
public class PointsRuleManageImpl implements PointsRuleManage{

	
	@Resource(name="pointsRuleFacade")
	private PointsRuleFacade pointsRuleFacade;

	@Override
	public PointsRuleDTO findPointsRuleById(PointsRuleDTO dto) {
		return pointsRuleFacade.findPointsRuleById(dto);
	}

	@Override
	public PageResult<PointsRuleDTO> findPointsRuleOfPage(PointsRuleDTO dto, Pagination page) {
		return pointsRuleFacade.findPointsRuleOfPage(dto, page);
	}

	@Override
	public List<PointsRuleDTO> findPointsRuleAll(PointsRuleDTO dto) {
		return pointsRuleFacade.findPointsRuleAll(dto);
	}

	@Override
	public Long insertPointsRuleWithTx(PointsRuleDTO dto) {
		return pointsRuleFacade.insertPointsRuleWithTx(dto);
	}

	@Override
	public int updatePointsRuleWithTx(PointsRuleDTO dto) {
		return pointsRuleFacade.updatePointsRuleWithTx(dto);
	}

	@Override
	public int deletePointsRuleWithTx(PointsRuleDTO dto) {
		return pointsRuleFacade.deletePointsRuleWithTx(dto);
	}


}
	