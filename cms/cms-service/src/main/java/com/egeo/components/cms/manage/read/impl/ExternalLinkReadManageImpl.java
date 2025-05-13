package com.egeo.components.cms.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.read.ExternalLinkReadManage;
import com.egeo.components.cms.dao.read.ExternalLinkReadDAO;
import com.egeo.components.cms.po.ExternalLinkPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class ExternalLinkReadManageImpl implements ExternalLinkReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ExternalLinkReadDAO externalLinkReadDAO;
	
	public ExternalLinkPO findExternalLinkById(ExternalLinkPO po) {
		ExternalLinkPO externalLinkpo = new ExternalLinkPO();
		externalLinkpo.setId(po.getId());
		return externalLinkReadDAO.findById(externalLinkpo);
	}

	public PageResult<ExternalLinkPO> findExternalLinkOfPage(ExternalLinkPO po, Pagination page) {
		
		PageResult<ExternalLinkPO> pageResult = new PageResult<ExternalLinkPO>();
		List<ExternalLinkPO> list = null;

		int cnt = externalLinkReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = externalLinkReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<ExternalLinkPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<ExternalLinkPO> findExternalLinkAll(ExternalLinkPO po) {

		return externalLinkReadDAO.findAll(po,null);
	}
	
}
	