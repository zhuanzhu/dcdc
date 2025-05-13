package com.egeo.components.order.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.manage.read.LimitRuleUserReadManage;
import com.egeo.components.order.dao.read.LimitRuleUserReadDAO;
import com.egeo.components.order.po.LimitRuleUserPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class LimitRuleUserReadManageImpl implements LimitRuleUserReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private LimitRuleUserReadDAO limitRuleUserReadDAO;
	
	public LimitRuleUserPO findLimitRuleUserById(LimitRuleUserPO po) {
		LimitRuleUserPO limitRuleUserpo = new LimitRuleUserPO();
		limitRuleUserpo.setId(po.getId());
		return limitRuleUserReadDAO.findById(limitRuleUserpo);
	}

	public PageResult<LimitRuleUserPO> findLimitRuleUserOfPage(LimitRuleUserPO po, Pagination page) {
		
		PageResult<LimitRuleUserPO> pageResult = new PageResult<LimitRuleUserPO>();
		List<LimitRuleUserPO> list = null;

		int cnt = limitRuleUserReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = limitRuleUserReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<LimitRuleUserPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<LimitRuleUserPO> findLimitRuleUserAll(LimitRuleUserPO po) {

		return limitRuleUserReadDAO.findAll(po,null);
	}

	@Override
	public Integer findLimitRuleAllByParam(LimitRuleUserPO limitRuleUserPO) {
		return limitRuleUserReadDAO.findLimitRuleAllByParam(limitRuleUserPO);
	}

}
	