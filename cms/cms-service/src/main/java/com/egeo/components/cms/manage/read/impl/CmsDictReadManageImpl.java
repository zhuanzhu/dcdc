package com.egeo.components.cms.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.read.CmsDictReadManage;
import com.egeo.components.cms.dao.read.CmsDictReadDAO;
import com.egeo.components.cms.po.CmsDictPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class CmsDictReadManageImpl implements CmsDictReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CmsDictReadDAO cmsDictReadDAO;
	
	public CmsDictPO findCmsDictById(CmsDictPO po) {
		CmsDictPO cmsDictpo = new CmsDictPO();
		cmsDictpo.setId(po.getId());
		return cmsDictReadDAO.findById(cmsDictpo);
	}

	public PageResult<CmsDictPO> findCmsDictOfPage(CmsDictPO po, Pagination page) {
		
		PageResult<CmsDictPO> pageResult = new PageResult<CmsDictPO>();
		List<CmsDictPO> list = null;

		int cnt = cmsDictReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = cmsDictReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<CmsDictPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<CmsDictPO> findCmsDictAll(CmsDictPO po) {

		return cmsDictReadDAO.findAll(po,null);
	}
	
}
	