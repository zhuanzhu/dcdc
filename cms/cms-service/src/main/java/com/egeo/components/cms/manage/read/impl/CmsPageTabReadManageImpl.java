package com.egeo.components.cms.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.read.CmsPageTabReadManage;
import com.egeo.components.cms.condition.CmsPageTabCondition;
import com.egeo.components.cms.dao.read.CmsPageTabReadDAO;
import com.egeo.components.cms.po.CmsPageTabPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class CmsPageTabReadManageImpl implements CmsPageTabReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CmsPageTabReadDAO cmsPageTabReadDAO;
	
	public CmsPageTabPO findCmsPageTabById(CmsPageTabPO po) {
		CmsPageTabPO cmsPageTabpo = new CmsPageTabPO();
		cmsPageTabpo.setId(po.getId());
		return cmsPageTabReadDAO.findById(cmsPageTabpo);
	}

	public PageResult<CmsPageTabPO> findCmsPageTabOfPage(CmsPageTabPO po, Pagination page) {
		
		PageResult<CmsPageTabPO> pageResult = new PageResult<CmsPageTabPO>();
		List<CmsPageTabPO> list = null;

		int cnt = cmsPageTabReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = cmsPageTabReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<CmsPageTabPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<CmsPageTabPO> findCmsPageTabAll(CmsPageTabPO po) {

		return cmsPageTabReadDAO.findAll(po,null);
	}

	@Override
	public List<CmsPageTabCondition> findCmsPageTabCondition(CmsPageTabCondition condition) {
		// TODO Auto-generated method stub
		return cmsPageTabReadDAO.findCmsPageTabCondition(condition);
	}

	@Override
	public List<CmsPageTabCondition> findDefaultCmsPageTabFront(Long platformId) {
		return cmsPageTabReadDAO.findDefaultCmsPageTabFront(platformId);
	}

}
	