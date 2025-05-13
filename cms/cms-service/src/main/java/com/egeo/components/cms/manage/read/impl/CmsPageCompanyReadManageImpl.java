package com.egeo.components.cms.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.read.CmsPageCompanyReadManage;
import com.egeo.components.cms.dao.read.CmsPageCompanyReadDAO;
import com.egeo.components.cms.po.CmsPageCompanyPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class CmsPageCompanyReadManageImpl implements CmsPageCompanyReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CmsPageCompanyReadDAO cmsPageCompanyReadDAO;
	
	public CmsPageCompanyPO findCmsPageCompanyById(CmsPageCompanyPO po) {
		CmsPageCompanyPO cmsPageCompanypo = new CmsPageCompanyPO();
		cmsPageCompanypo.setId(po.getId());
		return cmsPageCompanyReadDAO.findById(cmsPageCompanypo);
	}

	public PageResult<CmsPageCompanyPO> findCmsPageCompanyOfPage(CmsPageCompanyPO po, Pagination page) {
		
		PageResult<CmsPageCompanyPO> pageResult = new PageResult<CmsPageCompanyPO>();
		List<CmsPageCompanyPO> list = null;

		int cnt = cmsPageCompanyReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = cmsPageCompanyReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<CmsPageCompanyPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<CmsPageCompanyPO> findCmsPageCompanyAll(CmsPageCompanyPO po) {

		return cmsPageCompanyReadDAO.findAll(po,null);
	}
	
}
	