package com.egeo.components.cms.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.read.IconGroupReadManage;
import com.egeo.components.cms.dao.read.IconGroupReadDAO;
import com.egeo.components.cms.po.IconGroupPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class IconGroupReadManageImpl implements IconGroupReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private IconGroupReadDAO iconGroupReadDAO;
	
	public IconGroupPO findIconGroupById(IconGroupPO po) {
		IconGroupPO iconGrouppo = new IconGroupPO();
		iconGrouppo.setId(po.getId());
		return iconGroupReadDAO.findById(iconGrouppo);
	}

	public PageResult<IconGroupPO> findIconGroupOfPage(IconGroupPO po, Pagination page) {
		
		PageResult<IconGroupPO> pageResult = new PageResult<IconGroupPO>();
		List<IconGroupPO> list = null;

		int cnt = iconGroupReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = iconGroupReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<IconGroupPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<IconGroupPO> findIconGroupAll(IconGroupPO po) {

		return iconGroupReadDAO.findAll(po,null);
	}

	@Override
	public IconGroupPO queryIconGroupByInstId(Long instId) {
		return iconGroupReadDAO.queryIconGroupByInstId(instId);
	}
	
}
	