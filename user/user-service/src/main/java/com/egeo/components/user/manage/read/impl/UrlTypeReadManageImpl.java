package com.egeo.components.user.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.manage.read.UrlTypeReadManage;
import com.egeo.components.user.dao.read.UrlTypeReadDAO;
import com.egeo.components.user.po.UrlTypePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class UrlTypeReadManageImpl implements UrlTypeReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UrlTypeReadDAO urlTypeReadDAO;
	
	public UrlTypePO findUrlTypeById(UrlTypePO po) {
		UrlTypePO urlTypepo = new UrlTypePO();
		urlTypepo.setId(po.getId());
		return urlTypeReadDAO.findById(urlTypepo);
	}

	public PageResult<UrlTypePO> findUrlTypeOfPage(UrlTypePO po, Pagination page) {
		
		PageResult<UrlTypePO> pageResult = new PageResult<UrlTypePO>();
		List<UrlTypePO> list = null;

		int cnt = urlTypeReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = urlTypeReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<UrlTypePO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<UrlTypePO> findUrlTypeAll(UrlTypePO po) {

		return urlTypeReadDAO.findAll(po,null);
	}
	
}
	