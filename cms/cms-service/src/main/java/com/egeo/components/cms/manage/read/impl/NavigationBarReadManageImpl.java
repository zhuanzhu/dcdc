package com.egeo.components.cms.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.read.NavigationBarReadManage;
import com.egeo.components.cms.dao.read.NavigationBarReadDAO;
import com.egeo.components.cms.po.NavigationBarPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class NavigationBarReadManageImpl implements NavigationBarReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private NavigationBarReadDAO navigationBarReadDAO;
	
	public NavigationBarPO findNavigationBarById(NavigationBarPO po) {
		NavigationBarPO navigationBarpo = new NavigationBarPO();
		navigationBarpo.setId(po.getId());
		return navigationBarReadDAO.findById(navigationBarpo);
	}

	public PageResult<NavigationBarPO> findNavigationBarOfPage(NavigationBarPO po, Pagination page) {
		
		PageResult<NavigationBarPO> pageResult = new PageResult<NavigationBarPO>();
		List<NavigationBarPO> list = null;

		int cnt = navigationBarReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = navigationBarReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<NavigationBarPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<NavigationBarPO> findNavigationBarAll(NavigationBarPO po) {

		return navigationBarReadDAO.findAll(po,null);
	}

	@Override
	public PageResult<NavigationBarPO> findNavigationBarOfPageByBlurry(NavigationBarPO po, Pagination page) {
		PageResult<NavigationBarPO> pageResult = new PageResult<NavigationBarPO>();
		List<NavigationBarPO> list = null;

		int cnt = navigationBarReadDAO.countOfPageByBlurry(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = navigationBarReadDAO.findOfPageByBlurry(po, page);
		} else {
			list = new ArrayList<NavigationBarPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;
	}
	
}
	