package com.egeo.components.cms.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.read.IconReadManage;
import com.egeo.components.cms.dao.read.IconReadDAO;
import com.egeo.components.cms.po.IconPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class IconReadManageImpl implements IconReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private IconReadDAO iconReadDAO;
	
	public IconPO findIconById(IconPO po) {
		IconPO iconpo = new IconPO();
		iconpo.setId(po.getId());
		return iconReadDAO.findById(iconpo);
	}

	public PageResult<IconPO> findIconOfPage(IconPO po, Pagination page) {
		
		PageResult<IconPO> pageResult = new PageResult<IconPO>();
		List<IconPO> list = null;

		int cnt = iconReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = iconReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<IconPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<IconPO> findIconAll(IconPO po) {

		return iconReadDAO.findAll(po,null);
	}

	@Override
	public List<IconPO> queryIconsByGroupId(Long groupId) {
		return iconReadDAO.queryIconsByGroupId(groupId);
	}
	
}
	