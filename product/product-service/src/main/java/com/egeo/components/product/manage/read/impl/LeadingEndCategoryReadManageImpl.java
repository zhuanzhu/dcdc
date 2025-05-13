package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.LeadingEndCategoryReadManage;
import com.egeo.components.product.dao.read.LeadingEndCategoryReadDAO;
import com.egeo.components.product.po.LeadingEndCategoryPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class LeadingEndCategoryReadManageImpl implements LeadingEndCategoryReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private LeadingEndCategoryReadDAO leadingEndCategoryReadDAO;
	
	public LeadingEndCategoryPO findLeadingEndCategoryById(LeadingEndCategoryPO po) {
		LeadingEndCategoryPO leadingEndCategorypo = new LeadingEndCategoryPO();
		leadingEndCategorypo.setId(po.getId());
		return leadingEndCategoryReadDAO.findById(leadingEndCategorypo);
	}

	public PageResult<LeadingEndCategoryPO> findLeadingEndCategoryOfPage(LeadingEndCategoryPO po, Pagination page) {
		
		PageResult<LeadingEndCategoryPO> pageResult = new PageResult<LeadingEndCategoryPO>();
		List<LeadingEndCategoryPO> list = null;

		int cnt = leadingEndCategoryReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = leadingEndCategoryReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<LeadingEndCategoryPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<LeadingEndCategoryPO> findLeadingEndCategoryAll(LeadingEndCategoryPO po) {

		return leadingEndCategoryReadDAO.findAll(po,null);
	}
	
}
	