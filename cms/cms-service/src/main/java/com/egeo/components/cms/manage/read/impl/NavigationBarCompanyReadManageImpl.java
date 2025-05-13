package com.egeo.components.cms.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.read.NavigationBarCompanyReadManage;
import com.egeo.components.cms.dao.read.NavigationBarCompanyReadDAO;
import com.egeo.components.cms.po.NavigationBarCompanyPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class NavigationBarCompanyReadManageImpl implements NavigationBarCompanyReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private NavigationBarCompanyReadDAO navigationBarCompanyReadDAO;
	
	public NavigationBarCompanyPO findNavigationBarCompanyById(NavigationBarCompanyPO po) {
		NavigationBarCompanyPO navigationBarCompanypo = new NavigationBarCompanyPO();
		navigationBarCompanypo.setId(po.getId());
		return navigationBarCompanyReadDAO.findById(navigationBarCompanypo);
	}

	public PageResult<NavigationBarCompanyPO> findNavigationBarCompanyOfPage(NavigationBarCompanyPO po, Pagination page) {
		
		PageResult<NavigationBarCompanyPO> pageResult = new PageResult<NavigationBarCompanyPO>();
		List<NavigationBarCompanyPO> list = null;

		int cnt = navigationBarCompanyReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = navigationBarCompanyReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<NavigationBarCompanyPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<NavigationBarCompanyPO> findNavigationBarCompanyAll(NavigationBarCompanyPO po) {

		return navigationBarCompanyReadDAO.findAll(po,null);
	}

	@Override
	public List<NavigationBarCompanyPO> findPageTabAllByCompanyId(List<Long> companyIdList) {
		
		return navigationBarCompanyReadDAO.findPageTabAllByCompanyId(companyIdList);
	}
	
}
	