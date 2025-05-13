package com.egeo.components.cms.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.read.CmsTemplateElementReadManage;
import com.egeo.components.cms.dao.read.CmsTemplateElementReadDAO;
import com.egeo.components.cms.po.CmsTemplateElementPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class CmsTemplateElementReadManageImpl implements CmsTemplateElementReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CmsTemplateElementReadDAO cmsTemplateElementReadDAO;
	
	public CmsTemplateElementPO findCmsTemplateElementById(CmsTemplateElementPO po) {
		CmsTemplateElementPO cmsTemplateElementpo = new CmsTemplateElementPO();
		cmsTemplateElementpo.setId(po.getId());
		return cmsTemplateElementReadDAO.findById(cmsTemplateElementpo);
	}

	public PageResult<CmsTemplateElementPO> findCmsTemplateElementOfPage(CmsTemplateElementPO po, Pagination page) {
		
		PageResult<CmsTemplateElementPO> pageResult = new PageResult<CmsTemplateElementPO>();
		List<CmsTemplateElementPO> list = null;

		int cnt = cmsTemplateElementReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = cmsTemplateElementReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<CmsTemplateElementPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<CmsTemplateElementPO> findCmsTemplateElementAll(CmsTemplateElementPO po) {

		return cmsTemplateElementReadDAO.findAll(po,null);
	}
	
}
	