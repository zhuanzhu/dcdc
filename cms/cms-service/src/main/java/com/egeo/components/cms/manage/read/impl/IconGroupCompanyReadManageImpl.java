package com.egeo.components.cms.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.read.IconGroupCompanyReadManage;
import com.egeo.components.cms.dao.read.IconGroupCompanyReadDAO;
import com.egeo.components.cms.po.IconGroupCompanyPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class IconGroupCompanyReadManageImpl implements IconGroupCompanyReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private IconGroupCompanyReadDAO iconGroupCompanyReadDAO;
	
	public IconGroupCompanyPO findIconGroupCompanyById(IconGroupCompanyPO po) {
		IconGroupCompanyPO iconGroupCompanypo = new IconGroupCompanyPO();
		iconGroupCompanypo.setId(po.getId());
		return iconGroupCompanyReadDAO.findById(iconGroupCompanypo);
	}

	public PageResult<IconGroupCompanyPO> findIconGroupCompanyOfPage(IconGroupCompanyPO po, Pagination page) {
		
		PageResult<IconGroupCompanyPO> pageResult = new PageResult<IconGroupCompanyPO>();
		List<IconGroupCompanyPO> list = null;

		int cnt = iconGroupCompanyReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = iconGroupCompanyReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<IconGroupCompanyPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<IconGroupCompanyPO> findIconGroupCompanyAll(IconGroupCompanyPO po) {

		return iconGroupCompanyReadDAO.findAll(po,null);
	}
	
}
	