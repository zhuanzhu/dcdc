package com.egeo.components.finance.manage.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.finance.dao.read.ReasonCompanyReadDAO;
import com.egeo.components.finance.manage.read.ReasonCompanyReadManage;
import com.egeo.components.finance.po.ReasonCompanyPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class ReasonCompanyReadManageImpl implements ReasonCompanyReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ReasonCompanyReadDAO reasonCompanyReadDAO;
	
	public ReasonCompanyPO findReasonCompanyById(ReasonCompanyPO po) {
		ReasonCompanyPO reasonCompanypo = new ReasonCompanyPO();
		reasonCompanypo.setId(po.getId());
		return reasonCompanyReadDAO.findById(reasonCompanypo);
	}

	public PageResult<ReasonCompanyPO> findReasonCompanyOfPage(ReasonCompanyPO po, Pagination page) {
		
		PageResult<ReasonCompanyPO> pageResult = new PageResult<ReasonCompanyPO>();
		List<ReasonCompanyPO> list = null;

		int cnt = reasonCompanyReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = reasonCompanyReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<ReasonCompanyPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<ReasonCompanyPO> findReasonCompanyAll(ReasonCompanyPO po) {

		return reasonCompanyReadDAO.findAll(po,null);
	}
	
}
	