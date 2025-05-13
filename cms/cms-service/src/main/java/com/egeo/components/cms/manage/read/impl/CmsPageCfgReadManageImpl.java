package com.egeo.components.cms.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.read.CmsPageCfgReadManage;
import com.egeo.components.cms.condition.CmsPageCfgCondition;
import com.egeo.components.cms.dao.read.CmsPageCfgReadDAO;
import com.egeo.components.cms.po.CmsPageCfgPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class CmsPageCfgReadManageImpl implements CmsPageCfgReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CmsPageCfgReadDAO cmsPageCfgReadDAO;
	
	public CmsPageCfgPO findCmsPageCfgById(CmsPageCfgPO po) {
		CmsPageCfgPO cmsPageCfgpo = new CmsPageCfgPO();
		cmsPageCfgpo.setId(po.getId());
		return cmsPageCfgReadDAO.findById(cmsPageCfgpo);
	}

	public PageResult<CmsPageCfgPO> findCmsPageCfgOfPage(CmsPageCfgPO po, Pagination page) {
		
		PageResult<CmsPageCfgPO> pageResult = new PageResult<CmsPageCfgPO>();
		List<CmsPageCfgPO> list = null;

		int cnt = cmsPageCfgReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = cmsPageCfgReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<CmsPageCfgPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<CmsPageCfgPO> findCmsPageCfgAll(CmsPageCfgPO po) {

		return cmsPageCfgReadDAO.findAll(po,null);
	}

	@Override
	public List<CmsPageCfgCondition> findPageCfgByPageId(Long pageId) {
		return cmsPageCfgReadDAO.findPageCfgByPageId(pageId);
	}
	
}
	