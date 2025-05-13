package com.egeo.components.cms.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.read.InstReadManage;
import com.egeo.components.cms.dao.read.InstReadDAO;
import com.egeo.components.cms.po.InstPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class InstReadManageImpl implements InstReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private InstReadDAO instReadDAO;
	
	public InstPO findInstById(InstPO po) {
		InstPO instpo = new InstPO();
		instpo.setId(po.getId());
		return instReadDAO.findById(instpo);
	}

	public PageResult<InstPO> findInstOfPage(InstPO po, Pagination page) {
		
		PageResult<InstPO> pageResult = new PageResult<InstPO>();
		List<InstPO> list = null;

		int cnt = instReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = instReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<InstPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<InstPO> findInstAll(InstPO po) {
		return instReadDAO.findAll(po,null);
	}

	@Override
	public InstPO queryInstByElementId(Long elementId) {
		return instReadDAO.queryInstByElementId(elementId);
	}
	
}
	