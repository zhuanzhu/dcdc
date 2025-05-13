package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.StandardUnitRecordMembershipReadManage;
import com.egeo.components.product.dao.read.StandardUnitRecordMembershipReadDAO;
import com.egeo.components.product.po.StandardUnitRecordMembershipPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class StandardUnitRecordMembershipReadManageImpl implements StandardUnitRecordMembershipReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StandardUnitRecordMembershipReadDAO standardUnitRecordMembershipReadDAO;
	
	public StandardUnitRecordMembershipPO findStandardUnitRecordMembershipById(StandardUnitRecordMembershipPO po) {
		StandardUnitRecordMembershipPO standardUnitRecordMembershippo = new StandardUnitRecordMembershipPO();
		standardUnitRecordMembershippo.setId(po.getId());
		return standardUnitRecordMembershipReadDAO.findById(standardUnitRecordMembershippo);
	}

	public PageResult<StandardUnitRecordMembershipPO> findStandardUnitRecordMembershipOfPage(StandardUnitRecordMembershipPO po, Pagination page) {
		
		PageResult<StandardUnitRecordMembershipPO> pageResult = new PageResult<StandardUnitRecordMembershipPO>();
		List<StandardUnitRecordMembershipPO> list = null;

		int cnt = standardUnitRecordMembershipReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = standardUnitRecordMembershipReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<StandardUnitRecordMembershipPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<StandardUnitRecordMembershipPO> findStandardUnitRecordMembershipAll(StandardUnitRecordMembershipPO po) {

		return standardUnitRecordMembershipReadDAO.findAll(po,null);
	}
	
}
	