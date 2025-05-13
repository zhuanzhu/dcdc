package com.egeo.components.user.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.manage.read.UrlTypeDictReadManage;
import com.egeo.components.user.dao.read.UrlTypeDictReadDAO;
import com.egeo.components.user.po.UrlTypeDictPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class UrlTypeDictReadManageImpl implements UrlTypeDictReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UrlTypeDictReadDAO urlTypeDictReadDAO;
	
	public UrlTypeDictPO findUrlTypeDictById(UrlTypeDictPO po) {
		UrlTypeDictPO urlTypeDictpo = new UrlTypeDictPO();
		urlTypeDictpo.setId(po.getId());
		return urlTypeDictReadDAO.findById(urlTypeDictpo);
	}

	public PageResult<UrlTypeDictPO> findUrlTypeDictOfPage(UrlTypeDictPO po, Pagination page) {
		
		PageResult<UrlTypeDictPO> pageResult = new PageResult<UrlTypeDictPO>();
		List<UrlTypeDictPO> list = null;

		int cnt = urlTypeDictReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = urlTypeDictReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<UrlTypeDictPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<UrlTypeDictPO> findUrlTypeDictAll(UrlTypeDictPO po) {

		return urlTypeDictReadDAO.findAll(po,null);
	}
	
}
	