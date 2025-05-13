package com.egeo.components.cms.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.read.NavigationLabelReadManage;
import com.egeo.components.cms.dao.read.NavigationLabelReadDAO;
import com.egeo.components.cms.po.NavigationLabelPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class NavigationLabelReadManageImpl implements NavigationLabelReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private NavigationLabelReadDAO navigationLabelReadDAO;
	
	public NavigationLabelPO findNavigationLabelById(NavigationLabelPO po) {
		NavigationLabelPO navigationLabelpo = new NavigationLabelPO();
		navigationLabelpo.setId(po.getId());
		return navigationLabelReadDAO.findById(navigationLabelpo);
	}

	public PageResult<NavigationLabelPO> findNavigationLabelOfPage(NavigationLabelPO po, Pagination page) {
		
		PageResult<NavigationLabelPO> pageResult = new PageResult<NavigationLabelPO>();
		List<NavigationLabelPO> list = null;

		int cnt = navigationLabelReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = navigationLabelReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<NavigationLabelPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<NavigationLabelPO> findNavigationLabelAll(NavigationLabelPO po) {

		return navigationLabelReadDAO.findAll(po,null);
	}
	
}
	