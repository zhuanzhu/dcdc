package com.egeo.components.cms.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.read.LinkableParamReadManage;
import com.egeo.components.cms.dao.read.LinkableParamReadDAO;
import com.egeo.components.cms.po.LinkableParamPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class LinkableParamReadManageImpl implements LinkableParamReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private LinkableParamReadDAO linkableParamReadDAO;
	
	public LinkableParamPO findLinkableParamById(LinkableParamPO po) {
		LinkableParamPO linkableParampo = new LinkableParamPO();
		linkableParampo.setId(po.getId());
		return linkableParamReadDAO.findById(linkableParampo);
	}

	public PageResult<LinkableParamPO> findLinkableParamOfPage(LinkableParamPO po, Pagination page) {
		
		PageResult<LinkableParamPO> pageResult = new PageResult<LinkableParamPO>();
		List<LinkableParamPO> list = null;

		int cnt = linkableParamReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = linkableParamReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<LinkableParamPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<LinkableParamPO> findLinkableParamAll(LinkableParamPO po) {

		return linkableParamReadDAO.findAll(po,null);
	}
	
}
	