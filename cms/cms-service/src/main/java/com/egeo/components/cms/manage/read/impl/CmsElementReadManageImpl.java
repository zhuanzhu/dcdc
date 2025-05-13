package com.egeo.components.cms.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.read.CmsElementReadManage;
import com.egeo.components.cms.dao.read.CmsElementReadDAO;
import com.egeo.components.cms.po.CmsElementPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class CmsElementReadManageImpl implements CmsElementReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CmsElementReadDAO cmsElementReadDAO;
	
	public CmsElementPO findCmsElementById(CmsElementPO po) {
		return cmsElementReadDAO.findById(po);
	}

	public PageResult<CmsElementPO> findCmsElementOfPage(CmsElementPO po, Pagination page) {
		
		PageResult<CmsElementPO> pageResult = new PageResult<CmsElementPO>();
		List<CmsElementPO> list = null;

		int cnt = cmsElementReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = cmsElementReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<CmsElementPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<CmsElementPO> findCmsElementAll(CmsElementPO po) {

		return cmsElementReadDAO.findAll(po,null);
	}

	@Override
	public List<CmsElementPO> findCmsElementByTemplateId(Long templateId) {
		
		return cmsElementReadDAO.findCmsElementByTemplateId(templateId);
	}

	@Override
	public List<CmsElementPO> findMaxVersionByElementIdList(List<Long> elementIdList) {
		
		return cmsElementReadDAO.findCmsElementByIdList(elementIdList);
	}
	
}
	