package com.egeo.components.promotion.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.manage.read.RuleDescriptionReadManage;
import com.egeo.components.promotion.dao.read.RuleDescriptionReadDAO;
import com.egeo.components.promotion.po.RuleDescriptionPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class RuleDescriptionReadManageImpl implements RuleDescriptionReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private RuleDescriptionReadDAO ruleDescriptionReadDAO;
	
	public RuleDescriptionPO findRuleDescriptionById(RuleDescriptionPO po) {
		RuleDescriptionPO ruleDescriptionpo = new RuleDescriptionPO();
		ruleDescriptionpo.setId(po.getId());
		return ruleDescriptionReadDAO.findById(ruleDescriptionpo);
	}

	public PageResult<RuleDescriptionPO> findRuleDescriptionOfPage(RuleDescriptionPO po, Pagination page) {
		
		PageResult<RuleDescriptionPO> pageResult = new PageResult<RuleDescriptionPO>();
		List<RuleDescriptionPO> list = null;

		int cnt = ruleDescriptionReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = ruleDescriptionReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<RuleDescriptionPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<RuleDescriptionPO> findRuleDescriptionAll(RuleDescriptionPO po) {

		return ruleDescriptionReadDAO.findAll(po,null);
	}
	
}
	