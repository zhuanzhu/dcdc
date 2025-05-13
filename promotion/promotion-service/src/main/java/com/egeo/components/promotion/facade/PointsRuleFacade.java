package com.egeo.components.promotion.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import org.springframework.beans.factory.annotation.Autowired;
import com.egeo.components.promotion.service.read.PointsRuleReadService;
import com.egeo.components.promotion.service.write.PointsRuleWriteService;
import com.egeo.components.promotion.dto.PointsRuleDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class PointsRuleFacade {
	
	@Autowired
	private PointsRuleReadService pointsRuleReadService;
	
	@Autowired
	private PointsRuleWriteService pointsRuleWriteService;
	
	
	public PointsRuleDTO findPointsRuleById(PointsRuleDTO dto){
		
		return pointsRuleReadService.findPointsRuleById(dto);
	}

	public PageResult<PointsRuleDTO> findPointsRuleOfPage(PointsRuleDTO dto,Pagination page){
		
		return pointsRuleReadService.findPointsRuleOfPage(dto, page);
		
	}

	public List<PointsRuleDTO> findPointsRuleAll(PointsRuleDTO dto){
		
		return pointsRuleReadService.findPointsRuleAll(dto);
		
	}

	public Long insertPointsRuleWithTx(PointsRuleDTO dto){
		
		return pointsRuleWriteService.insertPointsRuleWithTx(dto);
	}

	public int updatePointsRuleWithTx(PointsRuleDTO dto){
		
		return pointsRuleWriteService.updatePointsRuleWithTx(dto);
	}

	public int deletePointsRuleWithTx(PointsRuleDTO dto){
		
		return pointsRuleWriteService.deletePointsRuleWithTx(dto);
		
	}

}
	