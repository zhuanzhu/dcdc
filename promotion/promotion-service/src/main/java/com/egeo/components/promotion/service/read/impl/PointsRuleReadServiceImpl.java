package com.egeo.components.promotion.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.service.read.PointsRuleReadService;
import com.egeo.components.promotion.manage.read.PointsRuleReadManage;
import com.egeo.components.promotion.converter.PointsRuleConverter;
import com.egeo.components.promotion.dto.PointsRuleDTO;
import com.egeo.components.promotion.po.PointsRulePO;
 
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("pointsRuleReadService")
public class PointsRuleReadServiceImpl implements PointsRuleReadService {
	@Autowired
	private PointsRuleReadManage pointsRuleReadManage;

	@Override
	public PointsRuleDTO findPointsRuleById(PointsRuleDTO dto) {
		PointsRulePO po = PointsRuleConverter.toPO(dto);
		PointsRulePO list = pointsRuleReadManage.findPointsRuleById(po);		
		return PointsRuleConverter.toDTO(list);
	}

	@Override
	public PageResult<PointsRuleDTO> findPointsRuleOfPage(PointsRuleDTO dto, Pagination page) {
		PointsRulePO po = PointsRuleConverter.toPO(dto);
        PageResult<PointsRulePO> pageResult = pointsRuleReadManage.findPointsRuleOfPage(po, page);
        
        List<PointsRuleDTO> list = PointsRuleConverter.toDTO(pageResult.getList());
        PageResult<PointsRuleDTO> result = new PageResult<PointsRuleDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<PointsRuleDTO> findPointsRuleAll(PointsRuleDTO dto) {
		PointsRulePO po = PointsRuleConverter.toPO(dto);
		List<PointsRulePO> list = pointsRuleReadManage.findPointsRuleAll(po);		
		return PointsRuleConverter.toDTO(list);
	}
}
	