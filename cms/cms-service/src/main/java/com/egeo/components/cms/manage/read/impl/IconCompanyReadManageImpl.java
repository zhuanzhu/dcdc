package com.egeo.components.cms.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.read.IconCompanyReadManage;
import com.egeo.components.cms.dao.read.IconCompanyReadDAO;
import com.egeo.components.cms.po.IconCompanyPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class IconCompanyReadManageImpl implements IconCompanyReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private IconCompanyReadDAO iconCompanyReadDAO;
	
	public IconCompanyPO findIconCompanyById(IconCompanyPO po) {
		IconCompanyPO iconCompanypo = new IconCompanyPO();
		iconCompanypo.setId(po.getId());
		return iconCompanyReadDAO.findById(iconCompanypo);
	}

	public PageResult<IconCompanyPO> findIconCompanyOfPage(IconCompanyPO po, Pagination page) {
		
		PageResult<IconCompanyPO> pageResult = new PageResult<IconCompanyPO>();
		List<IconCompanyPO> list = null;

		int cnt = iconCompanyReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = iconCompanyReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<IconCompanyPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<IconCompanyPO> findIconCompanyAll(IconCompanyPO po) {
		return iconCompanyReadDAO.findAll(po,null);
	}

	@Override
	public List<IconCompanyPO> queryIconCompanysByIconIdAndCompanyId(Long id, Long companyId) {
		return iconCompanyReadDAO.queryIconCompanysByIconIdAndCompanyId(id,companyId);
	}
	
}
	