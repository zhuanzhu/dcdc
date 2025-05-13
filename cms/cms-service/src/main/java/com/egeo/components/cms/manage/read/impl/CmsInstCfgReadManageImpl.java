package com.egeo.components.cms.manage.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.read.CmsInstCfgReadManage;
import com.egeo.components.cms.condition.CmsInstCfgCondition;
import com.egeo.components.cms.dao.read.CmsInstCfgReadDAO;
import com.egeo.components.cms.po.CmsInstCfgPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class CmsInstCfgReadManageImpl implements CmsInstCfgReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CmsInstCfgReadDAO cmsInstCfgReadDAO;
	
	public CmsInstCfgPO findCmsInstCfgById(CmsInstCfgPO po) {
		CmsInstCfgPO cmsInstCfgpo = new CmsInstCfgPO();
		cmsInstCfgpo.setId(po.getId());
		return cmsInstCfgReadDAO.findById(cmsInstCfgpo);
	}

	public PageResult<CmsInstCfgPO> findCmsInstCfgOfPage(CmsInstCfgPO po, Pagination page) {
		
		PageResult<CmsInstCfgPO> pageResult = new PageResult<CmsInstCfgPO>();
		List<CmsInstCfgPO> list = null;

		int cnt = cmsInstCfgReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = cmsInstCfgReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<CmsInstCfgPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<CmsInstCfgPO> findCmsInstCfgAll(CmsInstCfgPO po) {

		return cmsInstCfgReadDAO.findAll(po,null);
	}

	@Override
	public List<CmsInstCfgCondition> findPageInstCfgByPageId(Long pageId, List<Long> instIdList) {
		return cmsInstCfgReadDAO.findPageInstCfgByPageId(pageId, instIdList);
	}

}
	