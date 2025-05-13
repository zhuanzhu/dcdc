package com.egeo.components.cms.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.read.NavigationBarLabelReadManage;
import com.egeo.components.cms.dao.read.NavigationBarLabelReadDAO;
import com.egeo.components.cms.po.NavigationBarLabelPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class NavigationBarLabelReadManageImpl implements NavigationBarLabelReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private NavigationBarLabelReadDAO navigationBarLabelReadDAO;
	
	public NavigationBarLabelPO findNavigationBarLabelById(NavigationBarLabelPO po) {
		NavigationBarLabelPO navigationBarLabelpo = new NavigationBarLabelPO();
		navigationBarLabelpo.setId(po.getId());
		return navigationBarLabelReadDAO.findById(navigationBarLabelpo);
	}

	public PageResult<NavigationBarLabelPO> findNavigationBarLabelOfPage(NavigationBarLabelPO po, Pagination page) {
		
		PageResult<NavigationBarLabelPO> pageResult = new PageResult<NavigationBarLabelPO>();
		List<NavigationBarLabelPO> list = null;

		int cnt = navigationBarLabelReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = navigationBarLabelReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<NavigationBarLabelPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<NavigationBarLabelPO> findNavigationBarLabelAll(NavigationBarLabelPO po) {

		return navigationBarLabelReadDAO.findAll(po,null);
	}
	
}
	