package com.egeo.components.config.manage.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.config.dao.read.PolymallUserReadDAO;
import com.egeo.components.config.manage.read.PolymallUserReadManage;
import com.egeo.components.config.po.PolymallUserPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class PolymallUserReadManageImpl implements PolymallUserReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private PolymallUserReadDAO polymallUserReadDAO;
	
	public PolymallUserPO findPolymallUserById(PolymallUserPO po) {
		PolymallUserPO polymallUserpo = new PolymallUserPO();
		polymallUserpo.setId(po.getId());
		return polymallUserReadDAO.findById(polymallUserpo);
	}

	public PageResult<PolymallUserPO> findPolymallUserOfPage(PolymallUserPO po, Pagination page) {
		
		PageResult<PolymallUserPO> pageResult = new PageResult<PolymallUserPO>();
		List<PolymallUserPO> list = null;

		int cnt = polymallUserReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = polymallUserReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<PolymallUserPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<PolymallUserPO> findPolymallUserAll(PolymallUserPO po) {

		return polymallUserReadDAO.findAll(po,null);
	}
	
}
	