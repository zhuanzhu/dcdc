package com.egeo.components.cms.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.read.CmsInstCompanyReadManage;
import com.egeo.components.cms.dao.read.CmsInstCompanyReadDAO;
import com.egeo.components.cms.po.CmsInstCompanyPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class CmsInstCompanyReadManageImpl implements CmsInstCompanyReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CmsInstCompanyReadDAO cmsInstCompanyReadDAO;
	
	public CmsInstCompanyPO findCmsInstCompanyById(CmsInstCompanyPO po) {
		CmsInstCompanyPO cmsInstCompanypo = new CmsInstCompanyPO();
		cmsInstCompanypo.setId(po.getId());
		return cmsInstCompanyReadDAO.findById(cmsInstCompanypo);
	}

	public PageResult<CmsInstCompanyPO> findCmsInstCompanyOfPage(CmsInstCompanyPO po, Pagination page) {
		
		PageResult<CmsInstCompanyPO> pageResult = new PageResult<CmsInstCompanyPO>();
		List<CmsInstCompanyPO> list = null;

		int cnt = cmsInstCompanyReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = cmsInstCompanyReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<CmsInstCompanyPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<CmsInstCompanyPO> findCmsInstCompanyAll(CmsInstCompanyPO po) {

		return cmsInstCompanyReadDAO.findAll(po,null);
	}
	
}
	