package com.egeo.components.order.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.manage.read.LimitRuleStoreReadManage;
import com.egeo.components.order.dao.read.LimitRuleStoreReadDAO;
import com.egeo.components.order.po.LimitRuleStorePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class LimitRuleStoreReadManageImpl implements LimitRuleStoreReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private LimitRuleStoreReadDAO limitRuleStoreReadDAO;
	
	public LimitRuleStorePO findLimitRuleStoreById(LimitRuleStorePO po) {
		LimitRuleStorePO limitRuleStorepo = new LimitRuleStorePO();
		limitRuleStorepo.setId(po.getId());
		return limitRuleStoreReadDAO.findById(limitRuleStorepo);
	}

	public PageResult<LimitRuleStorePO> findLimitRuleStoreOfPage(LimitRuleStorePO po, Pagination page) {
		
		PageResult<LimitRuleStorePO> pageResult = new PageResult<LimitRuleStorePO>();
		List<LimitRuleStorePO> list = null;

		int cnt = limitRuleStoreReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = limitRuleStoreReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<LimitRuleStorePO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<LimitRuleStorePO> findLimitRuleStoreAll(LimitRuleStorePO po) {

		return limitRuleStoreReadDAO.findAll(po,null);
	}

	@Override
	public Integer findLimitRuleStoreCount(LimitRuleStorePO limitRuleStorePO) {
		return limitRuleStoreReadDAO.findLimitRuleStoreCount(limitRuleStorePO);
	}

}
	