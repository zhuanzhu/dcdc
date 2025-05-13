package com.egeo.components.order.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.manage.read.LimitRuleCompanyReadManage;
import com.egeo.components.order.dao.read.LimitRuleCompanyReadDAO;
import com.egeo.components.order.po.LimitRuleCompanyPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class LimitRuleCompanyReadManageImpl implements LimitRuleCompanyReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private LimitRuleCompanyReadDAO limitRuleCompanyReadDAO;
	
	public LimitRuleCompanyPO findLimitRuleCompanyById(LimitRuleCompanyPO po) {
		LimitRuleCompanyPO limitRuleCompanypo = new LimitRuleCompanyPO();
		limitRuleCompanypo.setId(po.getId());
		return limitRuleCompanyReadDAO.findById(limitRuleCompanypo);
	}

	public PageResult<LimitRuleCompanyPO> findLimitRuleCompanyOfPage(LimitRuleCompanyPO po, Pagination page) {
		
		PageResult<LimitRuleCompanyPO> pageResult = new PageResult<LimitRuleCompanyPO>();
		List<LimitRuleCompanyPO> list = null;

		int cnt = limitRuleCompanyReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = limitRuleCompanyReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<LimitRuleCompanyPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<LimitRuleCompanyPO> findLimitRuleCompanyAll(LimitRuleCompanyPO po) {

		return limitRuleCompanyReadDAO.findAll(po,null);
	}
	/**
	 * 根据限购规则id查询限购规则与公司关系
	 * @param limitRuleId
	 * @return
	 */
	@Override
	public List<Long> findCompanyByLimitRuleId(Long limitRuleId) {
		// TODO Auto-generated method stub
		return limitRuleCompanyReadDAO.findCompanyByLimitRuleId(limitRuleId);
	}

	@Override
	public Integer findLimitRuleCompanyCount(LimitRuleCompanyPO limitRuleCompanyPO) {
		return limitRuleCompanyReadDAO.findLimitRuleCompanyCount(limitRuleCompanyPO);
	}

}
	