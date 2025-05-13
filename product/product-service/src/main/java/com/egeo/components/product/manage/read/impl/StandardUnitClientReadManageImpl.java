package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.StandardUnitClientReadManage;
import com.egeo.components.product.dao.read.StandardUnitClientReadDAO;
import com.egeo.components.product.po.StandardUnitClientPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class StandardUnitClientReadManageImpl implements StandardUnitClientReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StandardUnitClientReadDAO standardUnitClientReadDAO;
	
	public StandardUnitClientPO findStandardUnitClientById(StandardUnitClientPO po) {
		StandardUnitClientPO standardUnitClientpo = new StandardUnitClientPO();
		standardUnitClientpo.setId(po.getId());
		return standardUnitClientReadDAO.findById(standardUnitClientpo);
	}

	public PageResult<StandardUnitClientPO> findStandardUnitClientOfPage(StandardUnitClientPO po, Pagination page) {
		
		PageResult<StandardUnitClientPO> pageResult = new PageResult<StandardUnitClientPO>();
		List<StandardUnitClientPO> list = null;

		int cnt = standardUnitClientReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = standardUnitClientReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<StandardUnitClientPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<StandardUnitClientPO> findStandardUnitClientAll(StandardUnitClientPO po) {

		return standardUnitClientReadDAO.findAll(po,null);
	}
	
}
	