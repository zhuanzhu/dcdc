package com.egeo.components.cms.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.read.SuListReadManage;
import com.egeo.components.cms.dao.read.SuListReadDAO;
import com.egeo.components.cms.po.SuListPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class SuListReadManageImpl implements SuListReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SuListReadDAO suListReadDAO;
	
	public SuListPO findSuListById(SuListPO po) {
		SuListPO suListpo = new SuListPO();
		suListpo.setId(po.getId());
		return suListReadDAO.findById(suListpo);
	}

	public PageResult<SuListPO> findSuListOfPage(SuListPO po, Pagination page) {
		
		PageResult<SuListPO> pageResult = new PageResult<SuListPO>();
		List<SuListPO> list = null;

		int cnt = suListReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = suListReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<SuListPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<SuListPO> findSuListAll(SuListPO po) {
		return suListReadDAO.findAll(po,null);
	}

	@Override
	public SuListPO querySuListByInstId(Long instId) {
		return suListReadDAO.querySuListByInstId(instId);
	}
	
}
	