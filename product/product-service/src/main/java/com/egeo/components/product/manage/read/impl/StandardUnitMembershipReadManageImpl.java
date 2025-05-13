package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.StandardUnitMembershipReadManage;
import com.egeo.components.product.dao.read.StandardUnitMembershipReadDAO;
import com.egeo.components.product.po.StandardUnitMembershipPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class StandardUnitMembershipReadManageImpl implements StandardUnitMembershipReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StandardUnitMembershipReadDAO standardUnitMembershipReadDAO;
	
	public StandardUnitMembershipPO findStandardUnitMembershipById(StandardUnitMembershipPO po) {
		StandardUnitMembershipPO standardUnitMembershippo = new StandardUnitMembershipPO();
		standardUnitMembershippo.setId(po.getId());
		return standardUnitMembershipReadDAO.findById(standardUnitMembershippo);
	}

	public PageResult<StandardUnitMembershipPO> findStandardUnitMembershipOfPage(StandardUnitMembershipPO po, Pagination page) {
		
		PageResult<StandardUnitMembershipPO> pageResult = new PageResult<StandardUnitMembershipPO>();
		List<StandardUnitMembershipPO> list = null;

		int cnt = standardUnitMembershipReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = standardUnitMembershipReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<StandardUnitMembershipPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<StandardUnitMembershipPO> findStandardUnitMembershipAll(StandardUnitMembershipPO po) {

		return standardUnitMembershipReadDAO.findAll(po,null);
	}
	
}
	