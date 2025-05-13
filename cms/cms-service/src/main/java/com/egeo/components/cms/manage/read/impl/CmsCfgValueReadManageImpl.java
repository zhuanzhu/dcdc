package com.egeo.components.cms.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.read.CmsCfgValueReadManage;
import com.egeo.components.cms.dao.read.CmsCfgValueReadDAO;
import com.egeo.components.cms.po.CmsCfgValuePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class CmsCfgValueReadManageImpl implements CmsCfgValueReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CmsCfgValueReadDAO cmsCfgValueReadDAO;
	
	public CmsCfgValuePO findCmsCfgValueById(CmsCfgValuePO po) {
		CmsCfgValuePO cmsCfgValuepo = new CmsCfgValuePO();
		cmsCfgValuepo.setId(po.getId());
		return cmsCfgValueReadDAO.findById(cmsCfgValuepo);
	}

	public PageResult<CmsCfgValuePO> findCmsCfgValueOfPage(CmsCfgValuePO po, Pagination page) {
		
		PageResult<CmsCfgValuePO> pageResult = new PageResult<CmsCfgValuePO>();
		List<CmsCfgValuePO> list = null;

		int cnt = cmsCfgValueReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = cmsCfgValueReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<CmsCfgValuePO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<CmsCfgValuePO> findCmsCfgValueAll(CmsCfgValuePO po) {

		return cmsCfgValueReadDAO.findAll(po,null);
	}
	
}
	