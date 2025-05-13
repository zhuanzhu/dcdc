package com.egeo.components.cms.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.read.CmsTemplateCfgReadManage;
import com.egeo.components.cms.dao.read.CmsTemplateCfgReadDAO;
import com.egeo.components.cms.po.CmsTemplateCfgPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class CmsTemplateCfgReadManageImpl implements CmsTemplateCfgReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CmsTemplateCfgReadDAO cmsTemplateCfgReadDAO;
	
	public CmsTemplateCfgPO findCmsTemplateCfgById(CmsTemplateCfgPO po) {
		CmsTemplateCfgPO cmsTemplateCfgpo = new CmsTemplateCfgPO();
		cmsTemplateCfgpo.setId(po.getId());
		return cmsTemplateCfgReadDAO.findById(cmsTemplateCfgpo);
	}

	public PageResult<CmsTemplateCfgPO> findCmsTemplateCfgOfPage(CmsTemplateCfgPO po, Pagination page) {
		
		PageResult<CmsTemplateCfgPO> pageResult = new PageResult<CmsTemplateCfgPO>();
		List<CmsTemplateCfgPO> list = null;

		int cnt = cmsTemplateCfgReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = cmsTemplateCfgReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<CmsTemplateCfgPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<CmsTemplateCfgPO> findCmsTemplateCfgAll(CmsTemplateCfgPO po) {

		return cmsTemplateCfgReadDAO.findAll(po,null);
	}
	
}
	