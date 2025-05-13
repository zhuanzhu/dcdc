package com.egeo.components.promotion.manage.read;

import java.util.List;
	
import com.egeo.components.promotion.po.PointsRulePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface PointsRuleReadManage {

	public PointsRulePO findPointsRuleById(PointsRulePO po);

	public PageResult<PointsRulePO> findPointsRuleOfPage(PointsRulePO po,Pagination page);

	public List<PointsRulePO> findPointsRuleAll(PointsRulePO po);
}
	