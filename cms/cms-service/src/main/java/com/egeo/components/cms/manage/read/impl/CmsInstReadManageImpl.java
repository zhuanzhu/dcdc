package com.egeo.components.cms.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.read.CmsInstReadManage;
import com.egeo.components.cms.dao.read.CmsInstReadDAO;
import com.egeo.components.cms.po.CmsInstPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class CmsInstReadManageImpl implements CmsInstReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CmsInstReadDAO cmsInstReadDAO;
	
	public CmsInstPO findCmsInstById(CmsInstPO po) {
		CmsInstPO cmsInstpo = new CmsInstPO();
		cmsInstpo.setId(po.getId());
		return cmsInstReadDAO.findById(cmsInstpo);
	}

	public PageResult<CmsInstPO> findCmsInstOfPage(CmsInstPO po, Pagination page) {
		
		PageResult<CmsInstPO> pageResult = new PageResult<CmsInstPO>();
		List<CmsInstPO> list = null;

		int cnt = cmsInstReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = cmsInstReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<CmsInstPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<CmsInstPO> findCmsInstAll(CmsInstPO po) {

		return cmsInstReadDAO.findAll(po,null);
	}

	@Override
	public PageResult<CmsInstPO> findByPageAndCompanyOfPage(CmsInstPO po, Long companyId, Long companyAllId,
			Pagination page) {
		PageResult<CmsInstPO> pageResult = new PageResult<CmsInstPO>();
		List<CmsInstPO> list = null;

		int cnt = cmsInstReadDAO.countByPageAndCompanyOfPage(po, companyId, companyAllId);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = cmsInstReadDAO.findByPageAndCompanyOfPage(po, companyId, companyAllId, page);
		} else {
			list = new ArrayList<CmsInstPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;
	}
	
}
	