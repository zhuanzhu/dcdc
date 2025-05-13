package com.egeo.components.promotion.business;

import java.util.List;
	
import com.egeo.components.promotion.dto.PointsRuleDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface PointsRuleManage {

	public PointsRuleDTO findPointsRuleById(PointsRuleDTO dto);	

	public PageResult<PointsRuleDTO> findPointsRuleOfPage(PointsRuleDTO dto,Pagination page);

	public List<PointsRuleDTO> findPointsRuleAll(PointsRuleDTO dto);

	Long insertPointsRuleWithTx(PointsRuleDTO dto);

	int updatePointsRuleWithTx(PointsRuleDTO dto);

	int deletePointsRuleWithTx(PointsRuleDTO dto);
}
	