package com.egeo.components.order.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.manage.read.SoUnitReadManage;
import com.egeo.components.order.dao.read.SoUnitReadDAO;
import com.egeo.components.order.po.SoUnitPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class SoUnitReadManageImpl implements SoUnitReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SoUnitReadDAO soUnitReadDAO;
	
	public SoUnitPO findSoUnitById(SoUnitPO po) {
		SoUnitPO soUnitpo = new SoUnitPO();
		soUnitpo.setId(po.getId());
		return soUnitReadDAO.findById(soUnitpo);
	}

	public PageResult<SoUnitPO> findSoUnitOfPage(SoUnitPO po, Pagination page) {
		
		PageResult<SoUnitPO> pageResult = new PageResult<SoUnitPO>();
		List<SoUnitPO> list = null;

		int cnt = soUnitReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = soUnitReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<SoUnitPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<SoUnitPO> findSoUnitAll(SoUnitPO po) {

		return soUnitReadDAO.findAll(po,null);
	}
	
}
	