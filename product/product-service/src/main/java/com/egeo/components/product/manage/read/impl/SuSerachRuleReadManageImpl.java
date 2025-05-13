package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.SuSerachRuleReadManage;
import com.egeo.components.product.dao.read.SuSerachRuleReadDAO;
import com.egeo.components.product.po.SuSerachRulePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class SuSerachRuleReadManageImpl implements SuSerachRuleReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SuSerachRuleReadDAO suSerachRuleReadDAO;
	
	public SuSerachRulePO findSuSerachRuleById(SuSerachRulePO po) {
		SuSerachRulePO suSerachRulepo = new SuSerachRulePO();
		suSerachRulepo.setId(po.getId());
		return suSerachRuleReadDAO.findById(suSerachRulepo);
	}

	public PageResult<SuSerachRulePO> findSuSerachRuleOfPage(SuSerachRulePO po, Pagination page) {
		
		PageResult<SuSerachRulePO> pageResult = new PageResult<SuSerachRulePO>();
		List<SuSerachRulePO> list = null;

		int cnt = suSerachRuleReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = suSerachRuleReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<SuSerachRulePO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<SuSerachRulePO> findSuSerachRuleAll(SuSerachRulePO po) {

		return suSerachRuleReadDAO.findAll(po,null);
	}

	@Override
	public Integer findSuSerachRuleListSize(Long id) {
		return suSerachRuleReadDAO.findSuSerachRuleListSize(id);
	}

}
	