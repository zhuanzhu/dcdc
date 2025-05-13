package com.egeo.components.cms.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.read.PageTabReadManage;
import com.egeo.components.cms.dao.read.PageTabReadDAO;
import com.egeo.components.cms.po.PageTabPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class PageTabReadManageImpl implements PageTabReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private PageTabReadDAO pageTabReadDAO;
	
	public PageTabPO findPageTabById(PageTabPO po) {
		PageTabPO pageTabpo = new PageTabPO();
		pageTabpo.setId(po.getId());
		return pageTabReadDAO.findById(pageTabpo);
	}

	public PageResult<PageTabPO> findPageTabOfPage(PageTabPO po, Pagination page) {
		
		PageResult<PageTabPO> pageResult = new PageResult<PageTabPO>();
		List<PageTabPO> list = null;

		int cnt = pageTabReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = pageTabReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<PageTabPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<PageTabPO> findPageTabAll(PageTabPO po) {

		return pageTabReadDAO.findAll(po,null);
	}

	@Override
	public List<PageTabPO> findPageTabAllForCheck(PageTabPO po) {
		
		return pageTabReadDAO.findAllForCheck(po,null);
	}

	@Override
	public PageResult<PageTabPO> findPageTabOfPageByBlurry(PageTabPO po, Pagination page) {
		PageResult<PageTabPO> pageResult = new PageResult<PageTabPO>();
		List<PageTabPO> list = null;

		int cnt = pageTabReadDAO.countOfPageByBlurry(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = pageTabReadDAO.findOfPageByBlurry(po, page);
		} else {
			list = new ArrayList<PageTabPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;
	}

	@Override
	public List<PageTabPO> findPageTabAllByCompanyId(PageTabPO po, List<Long> companyIdList) {
		
		return pageTabReadDAO.findAllByCompanyId(po, companyIdList,null);
	}
	
}
	