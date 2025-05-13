package com.egeo.components.cms.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.read.CmsPageTabCompanyReadManage;
import com.egeo.components.cms.dao.read.CmsPageTabCompanyReadDAO;
import com.egeo.components.cms.po.CmsPageTabCompanyPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class CmsPageTabCompanyReadManageImpl implements CmsPageTabCompanyReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CmsPageTabCompanyReadDAO cmsPageTabCompanyReadDAO;
	
	public CmsPageTabCompanyPO findCmsPageTabCompanyById(CmsPageTabCompanyPO po) {
		CmsPageTabCompanyPO cmsPageTabCompanypo = new CmsPageTabCompanyPO();
		cmsPageTabCompanypo.setId(po.getId());
		return cmsPageTabCompanyReadDAO.findById(cmsPageTabCompanypo);
	}

	public PageResult<CmsPageTabCompanyPO> findCmsPageTabCompanyOfPage(CmsPageTabCompanyPO po, Pagination page) {
		
		PageResult<CmsPageTabCompanyPO> pageResult = new PageResult<CmsPageTabCompanyPO>();
		List<CmsPageTabCompanyPO> list = null;

		int cnt = cmsPageTabCompanyReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = cmsPageTabCompanyReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<CmsPageTabCompanyPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<CmsPageTabCompanyPO> findCmsPageTabCompanyAll(CmsPageTabCompanyPO po) {

		return cmsPageTabCompanyReadDAO.findAll(po,null);
	}
	
}
	