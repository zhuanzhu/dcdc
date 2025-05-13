package com.egeo.components.promotion.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.manage.read.PointsRuleReadManage;
import com.egeo.components.promotion.dao.read.PointsRuleReadDAO;
import com.egeo.components.promotion.po.PointsRulePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class PointsRuleReadManageImpl implements PointsRuleReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private PointsRuleReadDAO pointsRuleReadDAO;
	
	public PointsRulePO findPointsRuleById(PointsRulePO po) {
		PointsRulePO pointsRulepo = new PointsRulePO();
		pointsRulepo.setId(po.getId());
		return pointsRuleReadDAO.findById(pointsRulepo);
	}

	public PageResult<PointsRulePO> findPointsRuleOfPage(PointsRulePO po, Pagination page) {
		
		PageResult<PointsRulePO> pageResult = new PageResult<PointsRulePO>();
		List<PointsRulePO> list = null;

		int cnt = pointsRuleReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = pointsRuleReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<PointsRulePO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<PointsRulePO> findPointsRuleAll(PointsRulePO po) {

		return pointsRuleReadDAO.findAll(po,null);
	}
	
}
	