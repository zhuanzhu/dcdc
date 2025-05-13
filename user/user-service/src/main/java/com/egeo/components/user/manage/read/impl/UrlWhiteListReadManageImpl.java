package com.egeo.components.user.manage.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.manage.read.UrlWhiteListReadManage;
import com.egeo.components.user.condition.UrlWhiteListCondition;
import com.egeo.components.user.dao.read.UrlWhiteListReadDAO;
import com.egeo.components.user.po.UrlWhiteListPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class UrlWhiteListReadManageImpl implements UrlWhiteListReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UrlWhiteListReadDAO urlWhiteListReadDAO;
	
	@Override
	public UrlWhiteListPO findUrlWhiteListById(UrlWhiteListPO po) {
		UrlWhiteListPO urlWhiteListpo = new UrlWhiteListPO();
		urlWhiteListpo.setId(po.getId());
		return urlWhiteListReadDAO.findById(urlWhiteListpo);
	}

	@Override
	public PageResult<UrlWhiteListCondition> findUrlWhiteListOfPage(UrlWhiteListPO po, Pagination page) {
		
		PageResult<UrlWhiteListCondition> pageResult = new PageResult<UrlWhiteListCondition>();
		List<UrlWhiteListCondition> list = null;

		int cnt = urlWhiteListReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = urlWhiteListReadDAO.findUrlWhiteListConditionOfPage(po, page);
		} else {
			list = new ArrayList<UrlWhiteListCondition>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}
	
	@Override
	public List<UrlWhiteListCondition> findUrlWhiteListAll(UrlWhiteListPO po) {

		return urlWhiteListReadDAO.findAllUrlWhiteListCondition(po,null);
	}

	@Override
	public List<String> findUrlWhiteList(Long platformId) {

		return urlWhiteListReadDAO.findUrlWhiteList(platformId);
	}
	
}
	