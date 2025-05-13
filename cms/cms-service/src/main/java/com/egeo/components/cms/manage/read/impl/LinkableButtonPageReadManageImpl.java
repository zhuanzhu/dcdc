package com.egeo.components.cms.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.read.LinkableButtonPageReadManage;
import com.egeo.components.cms.condition.LinkableButtonPageCondition;
import com.egeo.components.cms.dao.read.LinkableButtonPageReadDAO;
import com.egeo.components.cms.po.LinkableButtonPagePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class LinkableButtonPageReadManageImpl implements LinkableButtonPageReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private LinkableButtonPageReadDAO linkableButtonPageReadDAO;
	
	public LinkableButtonPagePO findLinkableButtonPageById(LinkableButtonPagePO po) {
		LinkableButtonPagePO linkableButtonPagepo = new LinkableButtonPagePO();
		linkableButtonPagepo.setId(po.getId());
		return linkableButtonPageReadDAO.findById(linkableButtonPagepo);
	}

	public PageResult<LinkableButtonPagePO> findLinkableButtonPageOfPage(LinkableButtonPagePO po, Pagination page) {
		
		PageResult<LinkableButtonPagePO> pageResult = new PageResult<LinkableButtonPagePO>();
		List<LinkableButtonPagePO> list = null;

		int cnt = linkableButtonPageReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = linkableButtonPageReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<LinkableButtonPagePO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<LinkableButtonPagePO> findLinkableButtonPageAll(LinkableButtonPagePO po) {

		return linkableButtonPageReadDAO.findAll(po,null);
	}

	@Override
	public List<LinkableButtonPageCondition> findCmsPageByLinkableId(Long linkableId) {
		
		return linkableButtonPageReadDAO.findCmsPageByLinkableId(linkableId);
	}
	
}
	