package com.egeo.components.cms.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.read.CmsTemplateReadManage;
import com.egeo.components.cms.dao.read.CmsTemplateReadDAO;
import com.egeo.components.cms.po.CmsTemplatePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class CmsTemplateReadManageImpl implements CmsTemplateReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CmsTemplateReadDAO cmsTemplateReadDAO;
	
	public CmsTemplatePO findCmsTemplateById(CmsTemplatePO po) {
		CmsTemplatePO cmsTemplatepo = new CmsTemplatePO();
		cmsTemplatepo.setId(po.getId());
		return cmsTemplateReadDAO.findById(cmsTemplatepo);
	}

	public PageResult<CmsTemplatePO> findCmsTemplateOfPage(CmsTemplatePO po, Pagination page,Integer searchType) {
		
		PageResult<CmsTemplatePO> pageResult = new PageResult<CmsTemplatePO>();
		List<CmsTemplatePO> list = null;

		int cnt = cmsTemplateReadDAO.countOfPageByType(po,searchType);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = cmsTemplateReadDAO.findOfPageByType(po, page,searchType);
		} else {
			list = new ArrayList<CmsTemplatePO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<CmsTemplatePO> findCmsTemplateAll(CmsTemplatePO po) {

		return cmsTemplateReadDAO.findAll(po,null);
	}

	@Override
	public List<CmsTemplatePO> findCmsTemplateAll(CmsTemplatePO po, Integer searchType) {
		
		return cmsTemplateReadDAO.findAllByType(po,searchType,null);
	}
	
}
	