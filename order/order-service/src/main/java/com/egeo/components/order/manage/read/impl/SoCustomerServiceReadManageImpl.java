package com.egeo.components.order.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.manage.read.SoCustomerServiceReadManage;
import com.egeo.components.order.dao.read.SoCustomerServiceReadDAO;
import com.egeo.components.order.po.SoCustomerServicePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class SoCustomerServiceReadManageImpl implements SoCustomerServiceReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SoCustomerServiceReadDAO soCustomerServiceReadDAO;
	
	public SoCustomerServicePO findSoCustomerServiceById(SoCustomerServicePO po) {
		SoCustomerServicePO soCustomerServicepo = new SoCustomerServicePO();
		soCustomerServicepo.setId(po.getId());
		return soCustomerServiceReadDAO.findById(soCustomerServicepo);
	}

	public PageResult<SoCustomerServicePO> findSoCustomerServiceOfPage(SoCustomerServicePO po, Pagination page) {
		
		PageResult<SoCustomerServicePO> pageResult = new PageResult<SoCustomerServicePO>();
		List<SoCustomerServicePO> list = null;

		int cnt = soCustomerServiceReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = soCustomerServiceReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<SoCustomerServicePO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<SoCustomerServicePO> findSoCustomerServiceAll(SoCustomerServicePO po) {

		return soCustomerServiceReadDAO.findAll(po,null);
	}

	@Override
	public SoCustomerServicePO queryCustomerServiceBySoChildId(Long scId) {
		return soCustomerServiceReadDAO.queryCustomerServiceBySoChildId(scId);
	}
	
}
	