package com.egeo.components.cms.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.read.LinkableButtonReadManage;
import com.egeo.components.cms.dao.read.LinkableButtonReadDAO;
import com.egeo.components.cms.po.LinkableButtonPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class LinkableButtonReadManageImpl implements LinkableButtonReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private LinkableButtonReadDAO linkableButtonReadDAO;
	
	public LinkableButtonPO findLinkableButtonById(LinkableButtonPO po) {
		LinkableButtonPO linkableButtonpo = new LinkableButtonPO();
		linkableButtonpo.setId(po.getId());
		return linkableButtonReadDAO.findById(linkableButtonpo);
	}

	public PageResult<LinkableButtonPO> findLinkableButtonOfPage(LinkableButtonPO po, Pagination page) {
		
		PageResult<LinkableButtonPO> pageResult = new PageResult<LinkableButtonPO>();
		List<LinkableButtonPO> list = null;

		int cnt = linkableButtonReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = linkableButtonReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<LinkableButtonPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<LinkableButtonPO> findLinkableButtonAll(LinkableButtonPO po) {

		return linkableButtonReadDAO.findAll(po,null);
	}
	
}
	